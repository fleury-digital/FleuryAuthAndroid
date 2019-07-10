package br.com.fleury.auth.domain

import com.google.gson.annotations.SerializedName

data class UserToken(
  @SerializedName("user_id") var user_id: String,
  @SerializedName("token") var token: String
)