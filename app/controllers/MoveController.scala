package controllers

import models.Stock
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.MongoFactory.saveStock


class MoveController extends Controller {
  def move() = Action {
  val apple = Stock("AAPL", 600)
  val google = Stock("GOOG", 650)
  val netflix = Stock("NFLX", 60)

  // save them to the mongodb database
   saveStock(apple)
    saveStock(google)
    saveStock(netflix)

    Ok(Json.toJson("PAPER"))
  }

  def lastOpponentMove() = Action { request =>
    Ok
  }

  def sayHello = Action(parse.json) { request =>
    (request.body \ "name").asOpt[String].map { name =>
      Ok("Hello " + name)
    }.getOrElse {
      BadRequest("Missing parameter [name]")
    }

  }
}
