package com.rhdzmota.google.api.vision.images.response

object Likelihood {
  sealed trait Likelihood {
    override def toString: String = this match {
      case UNKNOWN        => "UNKNOWN"
      case VERY_UNLIKELY  => "VERY_UNLIKELY"
      case UNLIKELY       => "UNLIKELY"
      case POSSIBLE       => "POSSIBLE"
      case LIKELY         => "LIKELY"
      case VERY_LIKELY    => "VERY_LIKELY"
    }
    def meaning: String = this match {
      case UNKNOWN        => "Unknown likelihood."
      case VERY_UNLIKELY  => "It is very unlikely that the image belongs to the specified vertical."
      case UNLIKELY       => "It is unlikely that the image belongs to the specified vertical."
      case POSSIBLE       => "It is possible that the image belongs to the specified vertical."
      case LIKELY         => "It is likely that the image belongs to the specified vertical."
      case VERY_LIKELY    => "It is very likely that the image belongs to the specified vertical."
    }
  }

  case object UNKNOWN extends Likelihood
  case object VERY_UNLIKELY extends Likelihood
  case object UNLIKELY extends Likelihood
  case object POSSIBLE extends Likelihood
  case object LIKELY extends Likelihood
  case object VERY_LIKELY extends Likelihood

  def fromString(string: String): Likelihood = string match {
    case "VERY_LIKELY"    => VERY_LIKELY
    case "LIKELY"         => LIKELY
    case "POSSIBLE"       => POSSIBLE
    case "UNLIKELY"       => UNLIKELY
    case "VERY_UNLIKELY"  => VERY_UNLIKELY
    case _ => UNKNOWN
  }
}
