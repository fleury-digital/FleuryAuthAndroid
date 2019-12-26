package br.com.fleury.auth.core.domain

import com.google.gson.annotations.SerializedName

data class AuthCodeRequest(
  @SerializedName("client_id") var clientId: String,
  @SerializedName("redirect_uri") var redirectUri: String
)