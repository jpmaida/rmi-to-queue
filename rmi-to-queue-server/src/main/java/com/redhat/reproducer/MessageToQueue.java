package com.redhat.reproducer;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.print.attribute.standard.Destination;
import java.util.Properties;

public class MessageToQueue {

    private String USERNAME = "user-remote-mq";
    private String PASSWORD = "user123";

    public void sendMessage() {
        InitialContext context = null;

//        try {
//            context = new InitialContext(environmentProperties());
//            ConnectionFactory connectionFactory = null;
//            connectionFactory = (ConnectionFactory) context.lookup("java:jms/RemoteConnectionFactory");
//
//            JMSContext jmsContext = connectionFactory.createContext(username, password);
//
//            Destination fooQueue = (Destination) context.lookup("java:jms/queue/foo");
//
//            jmsContext.createProducer().send(fooQueue, "Hi!!!");
//        } catch (NamingException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (context != null)
//                    context.close();
//            } catch (NamingException e) {
//                e.printStackTrace();
//            }
//        }
    }

    private Properties environmentProperties(){
        Properties environment = new Properties();
        environment.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
        environment.put(InitialContext.PROVIDER_URL, "http-remoting://127.0.0.1:8080");
        environment.put(Context.SECURITY_PRINCIPAL, USERNAME);
        environment.put(Context.SECURITY_CREDENTIALS, PASSWORD);
        return environment;
    }
}
