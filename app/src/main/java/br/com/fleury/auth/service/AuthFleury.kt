package br.com.fleury.auth.service

import br.com.fleury.auth.api.IAuthApi
import br.com.fleury.auth.di.Injection
import br.com.fleury.auth.repository.AuthRepository
import br.com.fleury.auth.repository.contracts.IAuthRepository

class AuthFleury(url: String, clientId: String, brand: String, credentials: String) {

  private val api: IAuthApi =
    Injection.providesAuthRetrofit(url, Injection.providesGson(), Injection.providesOkHttp()).create(IAuthApi::class.java)
  private val repository: IAuthRepository = AuthRepository(api, clientId, credentials, brand)

  suspend fun signIn(user: String, pass: String) = repository.byUsernameAndPass(user, pass)

  suspend fun byEmail(email: String) = repository.byEmail()
}