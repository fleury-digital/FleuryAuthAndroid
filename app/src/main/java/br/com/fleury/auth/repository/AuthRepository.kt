package br.com.fleury.auth.repository

import br.com.fleury.auth.api.IAuthApi
import br.com.fleury.auth.common.BaseCommand
import br.com.fleury.auth.common.SingleLiveEvent
import br.com.fleury.auth.domain.*
import br.com.fleury.auth.repository.contracts.IAuthRepository
import java.net.HttpURLConnection

class AuthRepository(
  private val api: IAuthApi,
  private val clientId: String,
  private val credentials: String,
  private val brand: String
) : IAuthRepository {

  @Throws(NotImplementedError::class)
  override fun byCpfAndPass() {
  }

  @Throws(NotImplementedError::class)
  override fun byEmail() {
  }

  @Throws(NotImplementedError::class)
  override fun byPhone() {
  }

  @Throws(NotImplementedError::class)
  override fun recoverMyPass() {
  }

  @Throws(NotImplementedError::class)
  override fun byCpfAndBirthday() {
  }

  override suspend fun byUsernameAndPass(userName: String, password: String): SingleLiveEvent<BaseCommand> {
    val data = SingleLiveEvent<BaseCommand>()
    try {
      authCodeHandler(data)?.let { authCode ->
        accessTokenHandler(authCode.code, data)?.let { token ->
          loginHandler(userName, password, token.accessToken, data)?.let { user ->
            val userCompleted = completeUserData(user, token.accessToken, token.refreshToken)
            data.postValue(BaseCommand.Success(userCompleted))
          }
        }
      }
    } catch (t: Throwable) {
      t.message?.let { data.postValue(BaseCommand.Error(it)) } ?: data.postValue(BaseCommand.TimeOut())
    }
    return data
  }

  private suspend fun authCodeHandler(data: SingleLiveEvent<BaseCommand>): AuthorizationCode? {
    val response = api.authorizationCode(AuthCodeRequest(clientId, REDIRECT_URI)).await()
    when (response.code()) {
      HttpURLConnection.HTTP_CREATED -> return response.body()
      else -> {
        errorResponseHandler(response.code(), data)
        return null
      }
    }
  }

  private suspend fun accessTokenHandler(code: String, data: SingleLiveEvent<BaseCommand>): AccessToken? {
    val response = api.accessToken(credentials, GRANT_TYPE_AUTHORIZATION_CODE, code).await()
    when (response.code()) {
      HttpURLConnection.HTTP_CREATED -> return response.body()
      else -> {
        errorResponseHandler(response.code(), data)
        return null
      }
    }
  }

  private suspend fun loginHandler(
    userName: String,
    password: String,
    accessToken: String,
    data: SingleLiveEvent<BaseCommand>
  ): User? {
    val request = LoginRequest(userName, password.toUpperCase(), brand)
    request.encode()
    val response = api.login(request, accessToken = accessToken, clientId = clientId).await()
    when (response.code()) {
      HttpURLConnection.HTTP_OK -> return response.body()
      else -> {
        errorResponseHandler(response.code(), data)
        return null
      }
    }
  }

  private fun errorResponseHandler(code: Int, data: SingleLiveEvent<BaseCommand>) {
    when (code) {
      HttpURLConnection.HTTP_UNAUTHORIZED -> data.postValue(BaseCommand.Unauthorized())
      HttpURLConnection.HTTP_FORBIDDEN -> data.postValue(BaseCommand.Forbidden())
    }
  }

  private fun completeUserData(user: User, accessToken: String, refreshToken: String): User {
    user.accessToken = accessToken
    user.refreshToken = refreshToken
    return user
  }

  companion object {
    const val REDIRECT_URI = "http://localhost"
    const val GRANT_TYPE_AUTHORIZATION_CODE = "authorization_code"
  }
}