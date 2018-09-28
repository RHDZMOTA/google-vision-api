package com.rhdzmota.google.api.vision.images.request

import com.rhdzmota.google.api.vision.images.request.ImageSource._
import io.circe.generic.semiauto.deriveEncoder
import io.circe.Encoder
import io.circe.syntax._

object Image {
  implicit val encodeImage: Encoder[Image] = Encoder.instance {
    case imageWithContent: ImageWithContent => imageWithContent.asJson
    case imageWithSource: ImageWithSource => imageWithSource.asJson
  }

  sealed trait Image {
    def toJson: String
  }

  final case class ImageWithContent(content: Array[Byte]) extends Image {
    override def toJson: String = this.asJson.toString
  }
  object ImageWithContent {
    implicit val encodeImageWithContent: Encoder[ImageWithContent] = deriveEncoder[ImageWithContent]
  }

  final case class ImageWithSource(source: ImageSource) extends Image {
    override def toJson: String = this.asJson.toString()
  }
  object ImageWithSource {
    implicit val encodeImageWithSource: Encoder[ImageWithSource] = deriveEncoder[ImageWithSource]
  }
}
