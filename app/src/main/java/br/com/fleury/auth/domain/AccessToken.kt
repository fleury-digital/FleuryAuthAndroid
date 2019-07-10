package br.com.fleury.auth.domain

import com.google.gson.annotations.SerializedName

class AccessToken(
  @SerializedName("access_token") var accessToken: String,
  @SerializedName("refresh_token") var refreshToken: String
)