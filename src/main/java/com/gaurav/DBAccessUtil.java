package com.gaurav;

import com.mongodb.async.client.MongoClient;
import com.mongodb.async.client.MongoClients;
import com.mongodb.async.client.MongoCollection;
import com.mongodb.async.client.MongoDatabase;
import org.bson.Document;

/**
 * Created by gauravdesai on 01/06/19.
 */
public class DBAccessUtil {

    static private MongoClient dbClient;
    static private MongoDatabase database;
    static private MongoCollection<Document> collection;

    static public final MongoClient getDBClient(){
        if(dbClient == null){
            synchronized (DBAccessUtil.class){
                if(dbClient == null){
                    dbClient = MongoClients.create(); //TODO read DB details from property file
                }
            }
        }

        return dbClient;
    }

    static public final MongoDatabase getDatabase(){

        if(database == null){
            synchronized (DBAccessUtil.class){
                if(database == null){
                    database = getDBClient().getDatabase("GauravJSonStore"); //TODO read DB name from property file
                }
            }
        }

        return database;

    }

    static public final MongoCollection<Document> getCollection(){
        if(collection == null){
            synchronized (DBAccessUtil.class){
                if(collection == null){
                    collection = getDatabase().getCollection("documents"); //TODO read collection name from property file
                }
            }
        }

        return collection;

    }


}
