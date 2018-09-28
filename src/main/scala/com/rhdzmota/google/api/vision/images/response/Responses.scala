package com.rhdzmota.google.api.vision.images.response

import io.circe.generic.semiauto.deriveDecoder
import io.circe.parser.decode
import io.circe.Decoder
import io.circe.Error

case class Responses(responses: List[Response])

object Responses {
  implicit val decodeResponses: Decoder[Responses] = deriveDecoder[Responses]
  def fromString(string: String): Either[Error, Responses] =
    decode[Responses](string)
}
