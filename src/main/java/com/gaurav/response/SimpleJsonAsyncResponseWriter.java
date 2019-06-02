package com.gaurav.response;

import org.bson.Document;

import javax.servlet.AsyncContext;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by gauravdesai on 01/06/19.
 */
public class SimpleJsonAsyncResponseWriter {
    public static void respondSuccessInsertOne(AsyncContext asyncContext, Throwable throwable)  {
        ServletResponse response = asyncContext.getResponse();
        response.setContentType("text/html");
        System.out.println("Async Supported? "
                + asyncContext.getRequest().isAsyncSupported());

        System.out.println("Inside respondSuccessInsertOne");
        try {
            PrintWriter writer = response.getWriter();
        if(throwable!=null){
            System.out.println(throwable.getLocalizedMessage());
            writer.println("<h1>Record could not be written to database. Please check logs</h1>");
        }
        else {
            writer.println("<h1>Record successfully Inserted</h1>");
        }
        } catch (IOException e) {
            e.printStackTrace();
        }
        asyncContext.complete();

    }

    public static void respondFindResult(String id, AsyncContext asyncContext, List<Document> documents, Throwable throwable) {
        ServletResponse response = asyncContext.getResponse();
        System.out.println("Async Supported? "
                + asyncContext.getRequest().isAsyncSupported());
        response.setContentType("text/html");

        System.out.println("Inside respondFindResult");

        try {
            PrintWriter writer = response.getWriter();
            if(throwable!=null){
                System.out.println(throwable.getLocalizedMessage());
                writer.println("<h1>Record could not be retrieved from database. Please check logs</h1>");
            }
            else {
                System.out.println("Document="+(documents==null?"null": documents.size()+ " records found"));
                if(documents == null || documents.size() == 0){
                    writer.println("<h1>No records found for id="+id+"</h1>");
                }
                else {
                    writer.println("<h1>"+documents.size()+" records found</h1>");
                    documents.forEach(doc -> {
                                doc.remove("_id"); // remove system generated id
                                writer.println(doc.toJson());
                            }
                    );
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        asyncContext.complete();
    }
}
