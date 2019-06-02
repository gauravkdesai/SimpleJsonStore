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
 */
public class SimpleJsonDAO {

    public static void insertNewJsonToDB(String json, AsyncContext asyncContext){
        Document doc = Document.parse(json);
        Throwable t = null;

        MongoCollection<Document> collection = DBAccessUtil.getCollection();
        collection.insertOne(doc,(result,throwable)-> SimpleJsonAsyncResponseWriter.respondSuccessInsertOne(asyncContext,throwable));

    }

    public static void findDocumentById(String id , AsyncContext asyncContext){
        MongoCollection<Document> collection = DBAccessUtil.getCollection();
        List<Document> documents = new ArrayList<>();
        collection.find(Filters.eq("id", id)).into(documents, (document, throwable)-> SimpleJsonAsyncResponseWriter.respondFindResult(id, asyncContext, documents, throwable));

    }


}
