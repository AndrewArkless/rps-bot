package controllers

import javax.inject.Inject

import play.api.libs.json.JsValue
import play.api.mvc.{Action, Controller}
import services.MongoFactory


class StartController @Inject()(dc:MongoFactory) extends Controller {
  def start() = Action { request=>
    val start: JsValue =request.body.asJson.get
    dc.updateTable("TEST UPDATe",start)
    Ok
  }
}
