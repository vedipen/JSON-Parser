package com.vedipen;

public class App {
        public static void main (String[] args) throws Exception {
                System.out.println("Hello World!");
                CustomServer server = new CustomServer();
                server.listen(4444);
        }
}
