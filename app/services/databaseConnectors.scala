package services

import com.mongodb.casbah
import com.mongodb.casbah.MongoConnection
import com.mongodb.casbah.commons.{MongoDBObject, TypeImports}
import models.Stock
import com.mongodb.casbah.Imports._

object MongoFactory {
  private val SERVER = "localhost"
  private val PORT   = 27017
  private val DATABASE = "portfolio"
  private val COLLECTION = "stocks"
  val connection = MongoConnection(SERVER)
  val collection = connection(DATABASE)(COLLECTION)

  def saveStock(stock: Stock): Unit = {
    val mongoObj = buildMongoDbObject(stock)
    MongoFactory.collection.save(mongoObj)
  }

  def getMoves: List[Stock] ={
    val stocks: MongoCursor =collection.find
    stocks.map{convertDbObjectToStock(_)}.toList

  }

  def updateMovesQuery: casbah.TypeImports.WriteResult ={
    val google = Stock("GOOG", 400)
    val query = MongoDBObject("symbol" -> "GOOG")
    collection.update(query, buildMongoDbObject(google),false,true)
  }

  def getSelectedMove(query:String): List[Stock] ={
    val q = MongoDBObject("symbol" -> query)
    val stocks: MongoCursor =collection.find(q)
    stocks.map{convertDbObjectToStock(_)}.toList
  }

  private def buildMongoDbObject(stock: Stock): MongoDBObject = {
    val builder = MongoDBObject.newBuilder
    builder += "symbol" -> stock.symbol
    builder += "price" -> stock.price
    builder.result
  }

  def convertDbObjectToStock(obj: MongoDBObject): Stock = {
    val symbol = obj.getAs[String]("symbol").get
    val price = obj.getAs[Int]("price").get
    Stock(symbol, price)
  }
}

