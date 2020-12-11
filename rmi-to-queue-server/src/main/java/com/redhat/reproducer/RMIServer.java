package com.redhat.reproducer;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class RMIServer implements Hello{
    public RMIServer() {}

    private int counter = 1;

    public String sayHello() {
        String time = "";
        if(counter == 1)
            time = "first";
        else if(counter == 2)
            time = "second";
        else if(counter == 3)
            time = "third";
        else
            time = counter + "th";
        counter++;
        new MessageToQueue().sendMessage();
        return "Message sent for the " + time + " time!";
    }

    public static void main(String args[]) {

        try {
            RMIServer obj = new RMIServer();
            Hello stub = (Hello) UnicastRemoteObject.exportObject(obj, 0);

            // Bind the remote object's stub in the registry
            //Registry registry = LocateRegistry.getRegistry();
            Registry registry = LocateRegistry.createRegistry(5000);
            registry.bind("Hello", stub);

            System.out.println("Server ready");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
