package br.com.fleury.auth.domain

import com.google.gson.annotations.SerializedName

class UserDeleteToken(@SerializedName("user_id") val userId: String)