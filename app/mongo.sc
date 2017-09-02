import com.mongodb.casbah.Imports._
val mongoClient = MongoClient("localhost", 27017)
val db = mongoClient("test")
db.collectionNames

