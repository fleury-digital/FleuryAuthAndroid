package br.com.fleury.auth.presentation.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import br.com.fleury.auth.R
import br.com.fleury.auth.presentation.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.login_activity.*
import kotlinx.coroutines.launch
import org.jetbrains.anko.toast

class LoginActivity : AppCompatActivity() {

  private lateinit var viewModel: LoginViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.login_activity)
//    viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
//    listeners()
//    observers()
  }

  private fun listeners() {
    login.setOnClickListener {
//      viewModel.logIn(this, user.text.toString(), pass.text.toString())
    }
  }

  private fun observers() {
    viewModel.authorized.observe(this, Observer {
      it?.let { user ->
        toast(user.fullName)
      }
    })
    viewModel.unauthorized.observe(this, Observer {
      it?.let { unauth ->
        toast("Não autorizado")
      }
    })
  }
}