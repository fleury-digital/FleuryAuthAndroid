package br.com.fleury.auth.domain

import com.google.gson.annotations.SerializedName

class AuthorizationCode(uriCode: String) {

  @SerializedName("redirect_uri")
  var code: String = uriCode
    get() {
      return field.replace(CODE_URI, "", true)
    }

  companion object {
    /**
     * Constante criada para remover a parte referente a Url que chega com a resposta da requisição para
     * o Authorization Code, pois para o próximo serviço chamado precisamos apenas do Code (número após a String)
     */
    private const val CODE_URI = "http://localhost/?code="
  }
}