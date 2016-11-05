package com.vedipen;

import java.net.*;
import java.io.*;
import java.util.*;

import org.json.*;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class CustomServer {
  public void listen(int port) throws IOException {
    HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
    server.createContext("/get_keys", new MyHandler());
    server.setExecutor(null);
    server.start();
  }

  static class MyHandler implements HttpHandler {
    private void replyString(HttpExchange hex, String response) throws IOException {
      hex.sendResponseHeaders(200, response.length());
      OutputStream os = hex.getResponseBody();
      os.write(response.getBytes());
      os.close();
    }

    private void replyJSONError(HttpExchange hex) throws IOException {
      String response = "Invalid json in body\n";
      hex.sendResponseHeaders(403, response.length());
      OutputStream os = hex.getResponseBody();
      os.write(response.getBytes());
      os.close();
    }

    @Override
    public void handle(HttpExchange hex) throws IOException {
      JSONObject jsonObject;
      String inputString = "";
      StringBuilder jsonString = new StringBuilder();
      StringBuilder responseString = new StringBuilder();
      short appendCommaFlag = 0;
      responseString.append("The JSON Object has the following keys -\n");

      BufferedReader request = new BufferedReader(new InputStreamReader(hex.getRequestBody()));

      while(( inputString = request.readLine() ) != null) {
        jsonString.append(inputString);
      }
      try {
        System.out.println("Got POST body - " + jsonString.toString());
        jsonObject = new JSONObject(jsonString.toString());
      } catch(JSONException e) {
        replyJSONError(hex);
        return;
      }

      Iterator<String> keys = jsonObject.keys();
      String prefix = "";
      
      while(keys.hasNext()) {
        responseString.append(prefix + keys.next() );
        prefix = ",";
      }
      responseString.append("\n");
      replyString(hex, responseString.toString());
    }
  }
}
