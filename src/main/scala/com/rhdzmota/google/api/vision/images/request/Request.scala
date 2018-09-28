package com.rhdzmota.google.api.vision.images.request

import java.net.URL

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{ContentTypes, HttpMethods, HttpRequest, HttpResponse}
import io.circe.Encoder
import io.circe.generic.semiauto.deriveEncoder
import io.circe.syntax._

import scala.concurrent.Future

case class Request(requests: List[AnnotateImageRequest]) {
  def toJson: String = this.asJson.toString

  def send(apiKey: String)(implicit actorSystem: ActorSystem): Future[HttpResponse] = Http(actorSystem).singleRequest(
    HttpRequest(HttpMethods.POST, Request.getUrl(apiKey).toString)
      .withEntity(ContentTypes.`application/json`, this.toJson)
  )
}

object Request {
  implicit val encodeRequest: Encoder[Request] = deriveEncoder[Request]
  def getUrl(apiKey: String): URL = new URL(s"https://vision.googleapis.com/v1/images:annotate?key=$apiKey")
}
