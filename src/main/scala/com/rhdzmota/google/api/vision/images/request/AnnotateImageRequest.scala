package com.rhdzmota.google.api.vision.images.request

import com.rhdzmota.google.api.vision.images.request.Image._
import io.circe.generic.semiauto.deriveEncoder
import io.circe.Encoder
import io.circe.syntax._

case class AnnotateImageRequest(image: Image, features: List[Feature]) {
  def toJson: String = this.asJson.toString
}

object AnnotateImageRequest {
  implicit val encodeAnnotateImageRequest: Encoder[AnnotateImageRequest] = deriveEncoder[AnnotateImageRequest]
}
