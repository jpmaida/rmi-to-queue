package com.redhat.reproducer;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

public class MessageToQueue {

    private String USERNAME = "user-remote-mq";
    private String PASSWORD = "user123";

    public static void main(String[] args) {
        MessageToQueue m = new MessageToQueue();
        m.sendMessage();
    }

    public void sendMessage() {
        InitialContext context = null;

        try {
            context = new InitialContext(environmentProperties());
            ConnectionFactory connectionFactory = (ConnectionFactory) context.lookup("java:/jms/RemoteConnectionFactory");

            JMSContext jmsContext = connectionFactory.createContext(USERNAME, PASSWORD);

            Destination fooQueue = (Destination) context.lookup("java:/jms/queue/foo");

            jmsContext.createProducer().send(fooQueue, "Hi!!!");
        } catch (NamingException e) {
            e.printStackTrace();
        } finally {
            try {
                if (context != null)
                    context.close();
            } catch (NamingException e) {
                e.printStackTrace();
            }
        }
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
