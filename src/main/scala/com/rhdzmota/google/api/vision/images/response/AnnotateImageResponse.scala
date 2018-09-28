package com.rhdzmota.google.api.vision.images.response

import com.rhdzmota.google.api.vision.images.response.AnnotateImageResponse.WebDetection.{WebEntity, WebImage, WebLabel, WebPage}
import com.rhdzmota.google.api.vision.images.response.Likelihood.Likelihood
import io.circe.generic.semiauto.deriveDecoder
import io.circe.parser.decode
import io.circe.Decoder
import io.circe.Error

object AnnotateImageResponse {
  sealed trait AnnotateImageResponse

  // FaceAnnotation
  final case class EntityAnnotation(
                                   mid: String,
                                   locale: Option[String],
                                   description: Option[String],
                                   score: Option[Double],
                                   confidence: Option[Double],
                                   topicality: Option[Double]
                                   // TODO: add more https://cloud.google.com/vision/docs/reference/rest/v1/images/annotate#EntityAnnotation
                                   ) extends AnnotateImageResponse
  object EntityAnnotation {
    implicit val decodeEntityAnnotation: Decoder[EntityAnnotation] = deriveDecoder[EntityAnnotation]
    def fromString(string: String): Either[Error, EntityAnnotation] =
      decode[EntityAnnotation](string)
  }
  // TextAnnotation
  final case class SafeSearchAnnotation(
                                       adult: Likelihood,
                                       spoof: Likelihood,
                                       medical: Likelihood,
                                       violence: Likelihood,
                                       racy: Likelihood
                                       ) extends AnnotateImageResponse {
    def toHelper: SafeSearchAnnotation.SafeSearchAnnotationHelper =
      SafeSearchAnnotation.SafeSearchAnnotationHelper(
        adult.toString, spoof.toString, medical.toString, violence.toString, racy.toString)
  }
  object SafeSearchAnnotation {
    implicit val decodeSafeSearchAnnotation: Decoder[SafeSearchAnnotation] =
      Decoder[SafeSearchAnnotationHelper].map(_.toSafeSearchAnnotation)
    case class SafeSearchAnnotationHelper(adult: String, spoof: String, medical: String, violence: String, racy: String) {
      def toSafeSearchAnnotation: SafeSearchAnnotation =
        SafeSearchAnnotation(
          Likelihood.fromString(adult),
          Likelihood.fromString(spoof),
          Likelihood.fromString(medical),
          Likelihood.fromString(violence),
          Likelihood.fromString(racy))
    }
    object SafeSearchAnnotationHelper {
      implicit val decodeSafeSearchAnnotationHelper: Decoder[SafeSearchAnnotationHelper] = deriveDecoder[SafeSearchAnnotationHelper]
      def fromString(string: String): Either[Error, SafeSearchAnnotationHelper] = decode[SafeSearchAnnotationHelper](string)
    }

  }
  // ImageProperties
  // CropHintsAnnotation
  final case class WebDetection(
                                 webEntities: Option[List[WebEntity]],
                                 fullMatchingImages: Option[List[WebImage]],
                                 partialMatchingImages: Option[List[WebImage]],
                                 pagesWithMatchingImages: Option[List[WebPage]],
                                 visuallySimilarImages: Option[List[WebImage]],
                                 bestGuessLabels: Option[List[WebLabel]]
                               ) extends AnnotateImageResponse
  object WebDetection {
    implicit val decodeWebDetection: Decoder[WebDetection] = deriveDecoder[WebDetection]
    final case class WebEntity(entityId: String, score: Option[Double], description: Option[String])
    object WebEntity {
      implicit val decodeWebEntity: Decoder[WebEntity] = deriveDecoder[WebEntity]
      def fromString(string: String): Either[Error, WebEntity] =
        decode[WebEntity](string)
    }
    final case class WebImage(url: String, score: Option[Double])
    object WebImage {
      implicit val decodeWebImage: Decoder[WebImage] = deriveDecoder[WebImage]
      def fromString(string: String): Either[Error, WebImage] =
        decode[WebImage](string)
    }
    final case class WebPage(url: String, score: Option[Double], pageTitle: Option[String], fullMatchingImages: Option[List[WebImage]], partialMatchingImages: Option[List[WebImage]])
    object WebPage {
      implicit val decodeWebPage: Decoder[WebPage] = deriveDecoder[WebPage]
      def fromString(string: String): Either[Error, WebPage] =
        decode[WebPage](string)
    }
    final case class WebLabel(label: String, languageCode: Option[String])
    object WebLabel {
      implicit val decodeWebLabel: Decoder[WebLabel] = deriveDecoder[WebLabel]
      def fromString(string: String): Either[Error, WebLabel] =
        decode[WebLabel](string)
    }
  }
  // Status
  // ImageAnnotationContext
}