package controllers

import play.api.mvc.{Action, Controller}
import javax.inject.Inject

import scala.concurrent.Future
import scala.concurrent.duration._
import play.api.mvc._
import play.api.libs.ws._
import play.api.http.HttpEntity
import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.scaladsl._
import akka.util.ByteString
import play.api.libs.json.Json

import scala.concurrent.ExecutionContext._

class GetMoveController @Inject() (ws: WSClient) extends Controller {

  def getMove()=Action.async {request =>
    implicit val context = play.api.libs.concurrent.Execution.Implicits.defaultContext
    val url="http://localhost:9001/move"
    val request: WSRequest = ws.url(url)
    val futureResponse: Future[WSResponse] = request.get()
    futureResponse.map{
      x=>
      //(x.body \ "name").asOpt[String].map { name =>
        Ok("Hello " + x.body.toString)
//      }.getOrElse {
//        BadRequest("Missing parameter [name]")
      }
    }
}
