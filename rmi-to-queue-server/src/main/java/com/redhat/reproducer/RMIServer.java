package com.redhat.reproducer;

import javax.naming.CommunicationException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Logger;

public class RMIServer implements HelloMessage {
    private final static Logger LOGGER = Logger.getLogger(RMIServer.class.getName());
    private int counter = 1;

    public RMIServer() {}

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
        try {
            new MessageToQueue().sendMessage();
            counter++;
            return "Message sent for the " + time + " time!";
        } catch (CommunicationException e) {
            return "Maybe JBoss EAP 7 is down. Check it first and try later !!!";
        }
    }

    public static void main(String args[]) {
        try {
            RMIServer obj = new RMIServer();
            HelloMessage stub = (HelloMessage) UnicastRemoteObject.exportObject(obj, 0);
            Registry registry = LocateRegistry.createRegistry(5000);
            registry.bind("HelloMessage", stub);
            System.out.println("Server ready");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            LOGGER.severe(e.getMessage());
        }
    }
}
