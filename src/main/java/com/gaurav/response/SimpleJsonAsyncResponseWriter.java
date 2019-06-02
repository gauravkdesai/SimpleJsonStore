package com.gaurav.response;

import org.bson.Document;

import javax.servlet.AsyncContext;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Created by gauravdesai on 01/06/19.
 * <p>
 * This class writes asynchronous response back to user
 * This class has two methods, one for response on finding objects and one for inserting.
 */
public class SimpleJsonAsyncResponseWriter {

    /**
     * Sends response back to user on request of adding new JSON object to DB
     *
     * @param asyncContext
     * @param throwable
     */
    public static void respondSuccessInsertOne(AsyncContext asyncContext, Throwable throwable) {
        System.out.println("Inside respondSuccessInsertOne");
        ServletResponse response = getServletResponseAndSetContentType(asyncContext);

        try {
            PrintWriter writer = response.getWriter();

            if (throwable != null) {
                System.out.println("Error occurred while adding new record in DB");
                System.out.println(throwable.getLocalizedMessage());
                writer.println("<h1>Record could not be written to database. Please check logs</h1>");

            } else {
                writer.println("<h1>Record successfully Inserted</h1>");
            }
        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            asyncContext.complete();
        }
    }


    /**
     * Sends response back to user on request of searching JSON object by id
     *
     * @param id
     * @param asyncContext
     * @param documents
     * @param throwable
     */
    public static void respondFindResult(String id, AsyncContext asyncContext, List<Document> documents, Throwable throwable) {
        System.out.println("Inside respondFindResult");
        ServletResponse response = getServletResponseAndSetContentType(asyncContext);

        try {
            PrintWriter writer = response.getWriter();

            if (throwable != null) {
                System.out.println("Error occurred while retrieving records from DB");
                System.out.println(throwable.getLocalizedMessage());
                writer.println("<h1>Record could not be retrieved from database. Please check logs</h1>");

            } else {

                if (documents == null || documents.size() == 0) {
                    System.out.println("no records found");
                    writer.println("<h1>No records found for id=" + id + "</h1>");

                } else {
                    System.out.println(documents.size() + " record/s found");
                    writer.println("<h1>" + documents.size() + " record/s found</h1>");
                    IntStream.range(0, documents.size()).forEach(i -> {
                                Document doc = documents.get(i);
                                doc.remove("_id"); // remove DB generated id
                                writer.println((i + 1) + ".");
                                writer.println(doc.toJson());
                                writer.println("<br>");

                            }
                    );
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            asyncContext.complete();
        }
    }

    /**
     * Util method to get response object from async context and set reaponse type as text/html
     *
     * @param asyncContext
     * @return
     */
    private static ServletResponse getServletResponseAndSetContentType(AsyncContext asyncContext) {
        ServletResponse response = asyncContext.getResponse();
        response.setContentType("text/html");
        System.out.println("Async Supported="
                + asyncContext.getRequest().isAsyncSupported());
        return response;
    }
}
