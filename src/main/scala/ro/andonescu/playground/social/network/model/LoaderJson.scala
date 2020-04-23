package ro.andonescu.playground.social.network.model

import io.circe.Decoder
import io.circe.generic.semiauto._

object LoaderJson {
  implicit val connectionStringDecoder: Decoder[Connection[String]] = deriveDecoder[Connection[String]]
}

