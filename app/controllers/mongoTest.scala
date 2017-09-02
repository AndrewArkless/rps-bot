package controllers
import com.mongodb.casbah.Imports._
object mongoTest extends App{
  println("START")
  val mongoClient = MongoClient("localhost", 27017)
  val db = mongoClient("test")
  db.collectionNames
  val a = MongoDBObject("hello" -> "world")
  val b = MongoDBObject("language" -> "scala")
  val coll = db("test")
  coll.insert( a )
  coll.insert( b )
  val query = MongoDBObject("platform" -> "JVM")
  val update = $set("language" -> "Scala")
  val result = coll.update( query, update )

  println( "Number updated: " + result.getN )
  for ( c <- coll.find ) println( c )
}
