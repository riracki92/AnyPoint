To run the server application, simply build the maven project and run the Application.java
I have hardcoded some example calls in the Client class, you can simply run the main method to send them to the server.
For the simplicity I have used the blocking stubs, but this can be of course changed in a real application.
I used in-memory H2 database, so by default all data will be deleted after application shutdown, this can be changed
with the commented-out property in the application.properties file.

Due to time constrains, I was not able to cover all the code with test cases.
I was also trying to find some better way to validate the input,
but all the solutions I was able to find online didn't work.
I'm sure there must be a better way to validate the request, but I'm not very familiar with the best practices in gRPC.