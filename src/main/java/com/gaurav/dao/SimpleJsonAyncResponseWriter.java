package com.gaurav.dao;

import org.bson.Document;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by gauravdesai on 01/06/19.
 */
public class SimpleJsonAyncResponseWriter {
    public static void respondSuccessInsertOne(HttpServletResponse response, Throwable throwable)  {
        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);

        try {
            PrintWriter writer = response.getWriter();
        if(throwable!=null){
            System.err.println(throwable);
            writer.println("<h1>Record could not be written to database. Please check logs</h1>");
        }
        else {
            writer.println("<h1>Record successfully Inserted</h1>");
        }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void respondFindResult(HttpServletResponse response, Document doc, Throwable throwable) {
        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);

        try {
            PrintWriter writer = response.getWriter();
            if(throwable==null){
                System.err.println(throwable);
                writer.println("<h1>Record could not be retrieved from database. Please check logs</h1>");
            }
            else {
                writer.println("<h1>Record retrieved successfully\n"+doc.toJson()+"</h1>");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
