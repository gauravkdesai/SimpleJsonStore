# SimpleJsonStore

This project is deploys a Servlet to asynchronously store and retrieve any given JSON object with at least one attribute "id".
At front end it is vanilla Servlet and at the backend it is MongoDB.
I have not used any REST APIs.

## Asynchronous request/response
To achieve asynchronous end to end pipeline I used
1. A Blocking Queue executor to store parallel request tasks. I stored this executor in context so that it is available to all the request threads.
2. Asynchronous context to pass on request and response object when blocking queue picks up our request tasks and wants to write response back.
3. MongoDB async drivers to give us callback when DB tasks is complete
