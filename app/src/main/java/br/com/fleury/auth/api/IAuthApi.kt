package br.com.fleury.auth.api

import br.com.fleury.auth.domain.*
import br.com.fleury.auth.domain.LoginRequest
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface IAuthApi {

  @POST("identidade/corporativo/v1/pessoas/fisica/login")
  fun login(
    @Body request: LoginRequest,
    @Header("client_id") clientId: String,
    @Header("access_token") accessToken: String
  ): Deferred<Response<User>>

  @POST("oauth/grant-code")
  fun authorizationCode(
    @Body authCode: AuthCodeRequest
  ): Deferred<Response<AuthorizationCode>>

  @FormUrlEncoded
  @POST("oauth/access-token")
  fun accessToken(
    @Header("Authorization") authorization: String,
    @Field("grant_type") grantType: String,
    @Field("code") code: String
  ): Deferred<Response<AccessToken>>

  @FormUrlEncoded
  @POST("oauth/access-token")
  fun refreshToken(
    @Header("Authorization") authorization: String,
    @Field("grant_type") grantType: String,
    @Field("refresh_token") refreshToken: String
  ): Deferred<Response<AccessToken>>

  @POST("tokens")
  fun token(
    @Body token: UserToken,
    @Header("client_id") clientId: String,
    @Header("access_token") accessToken: String
  ): Call<Void>

  @HTTP(method = "DELETE", path = "tokens", hasBody = true)
  fun deleteToken(
    @Body user: UserDeleteToken,
    @Header("client_id") clientId: String,
    @Header("access_token") accessToken: String
  ): Call<Void>

}