package com.rhdzmota.google.api.vision.images.request
import io.circe.Encoder
import io.circe.syntax._
import io.circe.generic.semiauto.deriveEncoder
import com.rhdzmota.google.api.vision.images.request.Feature._
import com.rhdzmota.google.api.vision.images.request.Feature.Type._

case class Feature(`type`: Type, maxResults: Int, model: String = "builtin/stable") {
  def toHelperFeature: HelperFeature = HelperFeature(`type`.toString, maxResults, model)
  def toJson: String = this.asJson.toString()
}

object Feature {
  implicit val encodeFeature: Encoder[Feature] = Encoder.instance {
    _.toHelperFeature.asJson
  }

  case class HelperFeature(`type`: String, maxResults: Int, model: String) {
    def toJson: String = {
      import io.circe.syntax._
      this.asJson.toString()
    }
  }

  object HelperFeature {
    implicit val encodeHelperFeature: Encoder[HelperFeature] = deriveEncoder[HelperFeature]
  }

  object Type {

    sealed trait Type {
      override def toString: String = this match {
        case TYPE_UNSPECIFIED         => "TYPE_UNSPECIFIED"
        case FACE_DETECTION           => "FACE_DETECTION"
        case LANDMARK_DETECTION       => "LANDMARK_DETECTION"
        case LOGO_DETECTION           => "LOGO_DETECTION"
        case LABEL_DETECTION          => "LABEL_DETECTION"
        case TEXT_DETECTION           => "TEXT_DETECTION"
        case DOCUMENT_TEXT_DETECTION  => "DOCUMENT_TEXT_DETECTION"
        case SAFE_SEARCH_DETECTION    => "SAFE_SEARCH_DETECTION"
        case IMAGE_PROPERTIES         => "IMAGE_PROPERTIES"
        case CROP_HINTS               => "CROP_HINTS"
        case WEB_DETECTION            => "WEB_DETECTION"
      }
    }
    case object TYPE_UNSPECIFIED extends Type
    case object FACE_DETECTION extends Type
    case object LANDMARK_DETECTION extends Type
    case object LOGO_DETECTION extends Type
    case object LABEL_DETECTION extends Type
    case object TEXT_DETECTION extends Type
    case object DOCUMENT_TEXT_DETECTION extends Type
    case object SAFE_SEARCH_DETECTION extends Type
    case object IMAGE_PROPERTIES extends Type
    case object CROP_HINTS extends Type
    case object WEB_DETECTION extends Type
  }
}