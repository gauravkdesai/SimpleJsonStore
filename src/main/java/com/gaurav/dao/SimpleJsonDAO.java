package com.gaurav.dao;

import com.gaurav.response.SimpleJsonAsyncResponseWriter;
import com.gaurav.util.DBAccessUtil;
import com.mongodb.async.client.MongoCollection;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import javax.servlet.AsyncContext;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by gauravdesai on 01/06/19.
 * <p>
 * DAO class that acts as a bridge between front end servlet and Mongo DB access methods
 */
public class SimpleJsonDAO {

    /**
     * Expects a valid json object and an async context to respond back asynchronously to user
     * Adds the json object into MongoDB and then calls the async response
     *
     * @param json
     * @param asyncContext
     */
    public static void insertNewJsonToDB(String json, AsyncContext asyncContext) {
        Document doc = Document.parse(json);
        Throwable t = null;

        MongoCollection<Document> collection = DBAccessUtil.getCollection();
        collection.insertOne(doc, (result, throwable) -> SimpleJsonAsyncResponseWriter.respondSuccessInsertOne(asyncContext, throwable));

    }

    /**
     * Expects an id to search on and async context to write async response back to user
     * searches the id and finds all matching json objects and returns the collection to response writer
     *
     * @param id
     * @param asyncContext
     */
    public static void findDocumentById(String id, AsyncContext asyncContext) {
        MongoCollection<Document> collection = DBAccessUtil.getCollection();
        List<Document> documents = new ArrayList<>();
        collection.find(Filters.eq("id", id)).into(documents, (document, throwable) -> SimpleJsonAsyncResponseWriter.respondFindResult(id, asyncContext, documents, throwable));

    }


}
