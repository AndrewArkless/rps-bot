package controllers

import play.api.mvc.{Action, Controller}
import services.MongoFactory._
/**
  * Created by andrew on 29/07/17.
  */
class ReturnMovesController extends Controller {
  def returnMoves=Action{

    Ok("Return Moves " +getMoves )
  }

  def updateMoves=Action{

    val x=updateMovesQuery
    Ok("Update Moves" + x+ "EEE" + getSelectedMove("GOOG"))
  }

  def findMove=Action{
    Ok("Return selected moves "  + getSelectedMove("GOOG") )
  }
}
