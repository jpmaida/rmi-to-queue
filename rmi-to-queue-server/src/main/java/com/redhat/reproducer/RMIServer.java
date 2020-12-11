package com.redhat.reproducer;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class RMIServer implements Hello{
    public RMIServer() {}

    private int counter = 1;

    public String sayHello() {
        return "Hello, for the " + counter + "th time!";
    }

    public static void main(String args[]) {

        try {
            RMIServer obj = new RMIServer();
            Hello stub = (Hello) UnicastRemoteObject.exportObject(obj, 0);

            // Bind the remote object's stub in the registry
            //Registry registry = LocateRegistry.getRegistry();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.bind("Hello", stub);

            System.out.println("RMIServer ready");
        } catch (Exception e) {
            System.err.println("RMIServer exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
