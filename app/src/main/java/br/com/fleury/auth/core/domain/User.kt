package br.com.fleury.auth.core.domain

import com.google.gson.annotations.SerializedName


data class User(
  @SerializedName("id") var id: String,
  @SerializedName("nomeCompleto") var fullName: String = "",
  @SerializedName("cpf") var cpf: String,
  @SerializedName("usuario") var userName: String = "",
  @SerializedName("sexo") var gender: String = "",
  @SerializedName("dataNascimento") var birthDate: String = "",
  @SerializedName("idCliente") var clientId: String = ""
) {

  @SerializedName("accessToken")
  var accessToken: String = ""
  @SerializedName("refreshToken")
  var refreshToken: String = ""
}