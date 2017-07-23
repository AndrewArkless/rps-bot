package controllers

import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}

class MoveController extends Controller {
  def move() = Action {
    println("HERE I AM!!!!!")
    Ok(Json.toJson("ROCKED"))
  }

  def lastOpponentMove() = Action {
    Ok
  }
}
