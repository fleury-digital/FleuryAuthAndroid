package br.com.fleury.auth.repository.contracts

import br.com.fleury.auth.common.BaseCommand
import br.com.fleury.auth.common.SingleLiveEvent

interface IAuthRepository {

  suspend fun byUsernameAndPass(userName: String, password: String): SingleLiveEvent<BaseCommand>
  fun byCpfAndPass()
  fun byEmail()
  fun byPhone()
  fun byCpfAndBirthday()
  fun recoverMyPass()

}