package com.gaurav.dao;

import com.gaurav.DBAccessUtil;
import com.mongodb.async.client.MongoCollection;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import javax.servlet.http.HttpServletResponse;


/**
 * Created by gauravdesai on 01/06/19.
 */
public class SimpleJsonDAO {

    public static void insertNewJsonToDB(String json, HttpServletResponse response){
        Document doc = Document.parse(json);
        Throwable t = null;

        MongoCollection<Document> collection = DBAccessUtil.getCollection();
        collection.insertOne(doc,(result,throwable)->SimpleJsonAyncResponseWriter.respondSuccessInsertOne(response,throwable));

    }

    public static void findDocumentById(String id , HttpServletResponse response){
        MongoCollection<Document> collection = DBAccessUtil.getCollection();
        collection.find(Filters.eq("id", id)).first((document, throwable)->SimpleJsonAyncResponseWriter.respondFindResult(response, document, throwable));

    }


}
