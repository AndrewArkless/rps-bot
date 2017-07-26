package controllers

import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}

class MoveController extends Controller {
  def move() = Action {
    println("HEREbv I AM!fdsdsds!!!!")
    Ok(Json.toJson("PAPER"))
  }

  def lastOpponentMove() = Action { request =>
    val body=request.body.asText.getOrElse("Empty") + "ANDREW"
    Ok(body)
  }
}
