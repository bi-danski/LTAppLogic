package com.lifetrack.services

import com.mongodb.client.MongoCollection
import com.mongodb.client.MongoDatabase
import com.mongodb.client.model.Filters
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.bson.Document
import org.bson.types.ObjectId
import com.lifetrack.model.data.users.User

class UserService(private val database: MongoDatabase) {
    //        database.createCollection("users")
    var collection: MongoCollection<Document> = database.getCollection("users")

    suspend fun create(user: User): String = withContext(Dispatchers.IO) {
        val doc = user.toDocument()
        collection.insertOne(doc)
//        doc["_id"].toString()
        doc.toString()
    }

    suspend fun read(id: String): User? = withContext(Dispatchers.IO) {
        collection.find(Filters.eq("_id", ObjectId(id))).first()?.let(User::fromDocument)
    }

    suspend fun update(id: String, user: User): Document? = withContext(Dispatchers.IO) {
        collection.findOneAndReplace(Filters.eq("_id", ObjectId(id)), user.toDocument())
    }

    suspend fun delete(id: String): Document? = withContext(Dispatchers.IO) {
        collection.findOneAndDelete(Filters.eq("_id", ObjectId(id)))
    }
}

