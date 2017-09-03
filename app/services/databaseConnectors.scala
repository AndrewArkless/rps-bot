package services

import com.google.inject.ImplementedBy
import com.mongodb.casbah
import com.mongodb.casbah.MongoConnection
import com.mongodb.casbah.commons.{MongoDBObject, TypeImports}
import models.Stock
import com.mongodb.casbah.Imports._
import com.mongodb.util.JSON
import play.api.libs.json.JsValue


class concreteMongoFactory extends MongoFactory {
  private val SERVER = "localhost"
  private val PORT   = 27017
  private val DATABASE = "games"
  private val COLLECTION = "game"
  val connection = MongoConnection(SERVER)
  val collection = connection(DATABASE)(COLLECTION)

  def saveStock(stock: Stock): Unit = {
    val mongoObj = buildMongoDbObject(stock)
    collection.save(mongoObj)
  }

  def getMoves: List[Stock] ={
    val stocks: MongoCursor =collection.find
    stocks.map{convertDbObjectToStock(_)}.toList

  }

  def updateMovesQuery: casbah.TypeImports.WriteResult ={

    val query = MongoDBObject("symbol" -> "NFLX")
    val update = $set("price"->400)
    val x= collection.update(query, update,false,multi= true)
    x
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

  def updateTable(table:String,data:JsValue): Unit ={
   // val obj: JsValue = Json.obj("age" -> JsNumber(100))
    val doc: DBObject = JSON.parse(data.toString).asInstanceOf[DBObject]
  //  val mongoObj = buildMongoDbObject(data)
   val collection = connection(DATABASE)(table)
    collection.save(doc)
  }
}
@ImplementedBy(classOf[concreteMongoFactory])
trait MongoFactory{
  def updateTable(table:String,data:JsValue):Unit
  def convertDbObjectToStock(obj: MongoDBObject): Stock
  def getSelectedMove(query:String): List[Stock]
  def updateMovesQuery: casbah.TypeImports.WriteResult
  def getMoves: List[Stock]
  def saveStock(stock: Stock): Unit
}
