package br.com.fleury.auth.core.domain

import br.com.fleury.auth.core.common.encodeToBase64String
import com.google.gson.annotations.SerializedName

data class LoginCpfRequest(
        @SerializedName("cpf") var cpf: String,
        @SerializedName("password") var password: String,
        @SerializedName("marca") var brand: String,
        @SerializedName("tipo") var type: String = TYPE_LOGIN_CPF
) {

  fun encode() {
    password = password.encodeToBase64String()
  }

  companion object {
    const val TYPE_LOGIN_CPF = "LOGIN_CPF"
  }
}