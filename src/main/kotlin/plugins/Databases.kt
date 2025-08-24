package com.lifetrack.plugins

import com.mongodb.client.*
import io.ktor.server.application.*
import io.ktor.server.config.*


fun Application.connectToMongoDB(): MongoDatabase {
    val user = environment.config.tryGetString("db.mongo.user") ?: "root"
    val password = environment.config.tryGetString("db.mongo.password") ?: "#LifeTrackAlltheWay616"
    val host = environment.config.tryGetString("db.mongo.host") ?: "cluster0.qs1ih0q.mongodb.net"
//    val maxPoolSize = environment.config.tryGetString("db.mongo.maxPoolSize")?.toInt() ?: 20
    val databaseName = environment.config.tryGetString("db.mongo.database.name") ?: "CitizenRecords"
    val uri = "mongodb+srv://$user:<$password>@$host/?retryWrites=true&w=majority&appName=Cluster0"
    val mongoClient = MongoClients.create(uri)
    val database = mongoClient.getDatabase(databaseName)
    monitor.subscribe(ApplicationStopped) {
        mongoClient.close()
    }
    return database
}

