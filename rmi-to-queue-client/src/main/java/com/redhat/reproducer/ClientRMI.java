package com.redhat.reproducer;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Logger;

public class ClientRMI {
    private static final Logger LOGGER = Logger.getLogger(ClientRMI.class.getName());

    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("127.0.0.1", 5000);
            HelloMessage stub = (HelloMessage)registry.lookup("HelloMessage");
            String response = stub.sayHello();
            System.out.println("response: " + response);
        } catch (Exception e) {
            System.err.println("ClientRMI exception: " + e.toString());
            LOGGER.severe(e.getMessage());
        }
    }
}
