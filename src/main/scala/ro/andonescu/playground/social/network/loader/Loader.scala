package ro.andonescu.playground.social.network.loader

import io.circe.parser._
import io.circe.{Json, ParsingFailure}

import scala.io.Source

object Loader {

  def inputData(pathToFile: String): scala.Either[ParsingFailure, Json] =
    parse(inputDataAsString(pathToFile))

  def inputDataAsString(pathToFile: String): String =
    Source.fromFile(pathToFile).getLines().mkString
}