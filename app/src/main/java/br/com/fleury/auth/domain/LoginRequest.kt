package br.com.fleury.auth.domain

import android.util.Base64
import com.google.gson.annotations.SerializedName
import java.nio.charset.StandardCharsets

data class LoginRequest(
  @SerializedName("login") var userName: String,
  @SerializedName("password") var password: String,
  @SerializedName("marca") var brand: String,
  @SerializedName("tipo") var type: String = TYPE_LOGIN_PASSWORD
) {

  fun encode() {
    userName = userName.encodeToBase64String()
    password = password.encodeToBase64String()
  }

  fun String.encodeToBase64String(): String =
    Base64.encodeToString(this.toByteArray(StandardCharsets.UTF_8), Base64.NO_WRAP) ?: ""

  companion object {
    const val TYPE_LOGIN_PASSWORD = "LOGIN_SENHA"
  }
}