package com.gaurav.util;

import com.mongodb.async.client.MongoClient;
import com.mongodb.async.client.MongoClients;
import com.mongodb.async.client.MongoCollection;
import com.mongodb.async.client.MongoDatabase;
import org.bson.Document;

/**
 * Created by gauravdesai on 01/06/19.
 * <p>
 * Utility class to access MongoDB via MongoDB Client
 * All the methods in this class are static and singleton.
 * So any number of calls to this class will generate at max only one set of DB resources
 */
final public class DBAccessUtil {

    static private MongoClient dbClient;
    static private MongoDatabase database;
    static private MongoCollection<Document> collection;

    public static final MongoClient getDBClient() {
        if (dbClient == null) {
            synchronized (DBAccessUtil.class) {
                if (dbClient == null) {
                    dbClient = MongoClients.create(Parameters.getDatabaseConnectionString());
                    System.out.println("Connected to DB at "+Parameters.getDatabaseConnectionString());
                }
            }
        }

        return dbClient;
    }

    public static final MongoDatabase getDatabase() {

        if (database == null) {
            synchronized (DBAccessUtil.class) {
                if (database == null) {
                    database = getDBClient().getDatabase(Parameters.getDatabaseName());
                    System.out.println("Using DB "+Parameters.getDatabaseName());
                }
            }
        }

        return database;

    }

    public static final MongoCollection<Document> getCollection() {
        if (collection == null) {
            synchronized (DBAccessUtil.class) {
                if (collection == null) {
                    collection = getDatabase().getCollection(Parameters.getDbCollectionName());
                    System.out.println("Using mongo db collection "+ Parameters.getDbCollectionName() +" to store json objects");
                }
            }
        }

        return collection;

    }

    public static final void close() {
        if (dbClient != null) {
            System.out.println("Closing down DB client");
            dbClient.close();
        }

    }


}
