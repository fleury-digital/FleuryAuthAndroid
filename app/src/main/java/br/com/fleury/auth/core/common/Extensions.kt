package br.com.fleury.auth.core.common

import android.util.Base64
import java.nio.charset.StandardCharsets

fun String.encodeToBase64String(): String =
    Base64.encodeToString(this.toByteArray(StandardCharsets.UTF_8), Base64.NO_WRAP) ?: ""
