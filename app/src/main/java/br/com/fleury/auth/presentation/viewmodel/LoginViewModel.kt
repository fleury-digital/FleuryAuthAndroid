package br.com.fleury.auth.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import br.com.fleury.auth.core.common.BaseCommand
import br.com.fleury.auth.core.domain.User
import br.com.fleury.auth.service.AuthFleury
import kotlinx.coroutines.launch

class LoginViewModel(app: Application, val authFleury: AuthFleury) : AndroidViewModel(app) {

  val authorized = MutableLiveData<User>()
  val unauthorized = MutableLiveData<Boolean>()

}