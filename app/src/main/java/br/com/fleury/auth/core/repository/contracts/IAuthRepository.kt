package br.com.fleury.auth.core.repository.contracts

import br.com.fleury.auth.core.common.BaseCommand
import br.com.fleury.auth.core.common.SingleLiveEvent

interface IAuthRepository {

  suspend fun byUsernameAndPass(userName: String, password: String): SingleLiveEvent<BaseCommand>
  suspend fun byCpfAndPass(cpf: String, password: String): SingleLiveEvent<BaseCommand>
  fun byEmail()
  fun byPhone()
  fun byCpfAndBirthday()
  fun recoverMyPass()

}