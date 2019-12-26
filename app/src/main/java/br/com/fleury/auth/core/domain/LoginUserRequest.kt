package br.com.fleury.auth.core.domain

import br.com.fleury.auth.core.common.encodeToBase64String
import com.google.gson.annotations.SerializedName

data class LoginUserRequest(
    @SerializedName("login") var userName: String,
    @SerializedName("password") var password: String,
    @SerializedName("tipo") var type: String = TYPE_LOGIN_PASSWORD,
    @SerializedName("marca") var brand: String
) {

    fun encode() {
        userName = userName.encodeToBase64String()
        password = password.encodeToBase64String()
    }

    companion object {
        const val TYPE_LOGIN_PASSWORD = "LOGIN_SENHA"
    }
}