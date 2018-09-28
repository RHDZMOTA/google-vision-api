package com.rhdzmota.google.api.vision.images.response

import com.rhdzmota.google.api.vision.images.response.AnnotateImageResponse
import com.rhdzmota.google.api.vision.images.response.AnnotateImageResponse.WebDetection
import io.circe.generic.semiauto.deriveDecoder
import io.circe.parser.decode
import io.circe.Decoder
import io.circe.Error

case class Response(
                   labelAnnotations: Option[List[AnnotateImageResponse.EntityAnnotation]],
                   safeSearchAnnotation: Option[AnnotateImageResponse.SafeSearchAnnotation],
                   webDetection: Option[AnnotateImageResponse.WebDetection]
                   )

object Response {
  implicit val decodeResponse: Decoder[Response] = deriveDecoder[Response]
  def fromString(string: String): Either[Error, Response] =
    decode[Response](string)
}