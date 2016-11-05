package com.vedipen;

public class App {
        public static void main (String[] args) throws Exception {
                System.out.println("Hello World!");
                CustomerServer server = new CustomerServer();
                server.listen(4444);
        }
}
