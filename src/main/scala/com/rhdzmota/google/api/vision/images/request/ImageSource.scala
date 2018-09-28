package com.rhdzmota.google.api.vision.images.request

import io.circe.generic.semiauto.deriveEncoder
import io.circe.Encoder
import io.circe.syntax._
import java.net.URL

import scala.util.Try

object ImageSource {
  implicit val encodeImageSource: Encoder[ImageSource] = Encoder.instance {
    case gcsImageUri: GcsImageUri => gcsImageUri.asJson
    case imageUri: ImageUri => imageUri.asJson
  }

  sealed trait ImageSource {
    def toJson: String
  }

  final case class GcsImageUri(gcsImageUri: String) extends ImageSource {
    override def toJson: String = this.asJson.toString
  }
  object GcsImageUri {
    implicit val encodeGscImageUri: Encoder[GcsImageUri] = deriveEncoder[GcsImageUri]
    def fromString(gcsImageUri: String): Option[GcsImageUri] =
      if (gcsImageUri startsWith "gs://") Some(GcsImageUri(gcsImageUri))
      else None
  }

  final case class ImageUri(imageUri: String) extends ImageSource {
    override def toJson: String = this.asJson.toString()
  }
  object ImageUri {
    implicit val encodeImageUri: Encoder[ImageUri] = deriveEncoder[ImageUri]
    def fromString(imageUri: String): Option[ImageUri] =
      Try(ImageUri(new URL(imageUri) .toString)).toOption
  }
}