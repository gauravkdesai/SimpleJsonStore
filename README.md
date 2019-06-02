# SimpleJsonStore

This project is deploys a Servlet to asynchronously store and retrieve any given JSON object with at least one attribute "id".
At front end it is vanilla Servlet and at the backend it is MongoDB.
I have not used any REST APIs.

## Asynchronous request/response
To achieve asynchronous end to end pipeline I used
1. A Blocking Queue executor to store parallel request tasks. I stored this executor in context so that it is available to all the request threads.
2. Asynchronous context to pass on request and response object when blocking queue picks up our request tasks and wants to write response back.
3. MongoDB async drivers to give us callback when DB tasks is complete

## Jetty Server
This application can be run directly on Jetty server. Necessary pom entries have been made.
Please rever to "How to execute" section for details

## How to execute
Execute maven goal jetty:run to build and deploy the SimpleJsonStore webapp to Jetty

## How to Use
The app supports two types of calls. One is to search records and second is to save records.

### Search records
To retrieve one or more JSON object already stored in MongoDB with id=123 use below GET web url
(Assuming jetty is running on port 8080 on localhost)

http://localhost:8080/database/123

The above web url will return
1. If there are records in DB with given id then it will list down all such records
2. If no records found for given id then it will send message back saying "No records found for id=<id>"

### Save Records
To save a JSON object use POST url
(Assuming jetty is running on port 8080 on localhost)

localhost:8080/database

POST BODY

{
  "id":"123",
   "foo":"far"
}

The above web url will return
1. If the input JSON is valid json format and it contains attribute "id" then it will insert that record into DB and display success message.
2. If the input record is not in JSON format or there is no id attrbute or because of any other reason it can not store the record in DB then error message will be shown.
3. You can post multiple messaged with same id, they will get added to DB as separate message and a list of messages will appear while searching on that id