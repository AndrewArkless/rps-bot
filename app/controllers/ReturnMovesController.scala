package controllers

import javax.inject.Inject

import play.api.mvc.{Action, Controller}
import services.MongoFactory

/**
  * Created by andrew on 29/07/17.
  */
class ReturnMovesController @Inject()(dc:MongoFactory) extends Controller {
  def returnMoves=Action{

    Ok("Return Moves " +dc.getMoves )
  }

  def updateMoves=Action{

    val x=dc.updateMovesQuery
    Ok("Update Moves" + x+ "EEE" + dc.getSelectedMove("GOOG"))
  }

  def findMove=Action{
    Ok("Return selected moves "  + dc.getSelectedMove("GOOG") )
  }
}
