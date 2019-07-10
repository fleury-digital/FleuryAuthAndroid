package br.com.fleury.auth.common

sealed class BaseCommand {

  class NoContent() : BaseCommand()
  class Unauthorized() : BaseCommand()
  class Created() : BaseCommand()
  class Success(val any: Any) : BaseCommand()
  class Error(val value: Any) : BaseCommand()
  class TimeOut() : BaseCommand()
  class NotFound() : BaseCommand()
  class Forbidden() : BaseCommand()
  class ServerError : BaseCommand()

}