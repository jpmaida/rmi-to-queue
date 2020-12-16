# RMI to Queue repo

Repository dedicated to RMI to external Active MQ Queue Proof of Concept. This POC tries to prove that is possible and feasible to listen to RMI calls which are originated by a client and send messages to queue hosted in a external JBoss EAP.

## Project structure
```
rmi-to-queue
├── README.md 											~> Read me file
├── lib 												~> Classpath libs
├── rmi-to-queue-client 								~> Client project
│   ├── pom.xml
│   ├── src
│   │   ├── main
│   │   │   └── java
│   │   │       └── com
│   │   │           └── redhat
│   │   │               └── reproducer
│   │   │                   ├── ClientRMI.java
│   │   │                   └── HelloMessage.java
├── rmi-to-queue-server 								~> Server project
│   ├── pom.xml
│   ├── src
│   │   ├── main
│   │   │   └── java
│   │   │       └── com
│   │   │           └── redhat
│   │   │               └── reproducer
│   │   │                   ├── HelloMessage.java
│   │   │                   ├── MessageToQueue.java
│   │   │                   └── RMIServer.java
└── standalone
    └── configuration 									~> Configuration files for the JBoss EAP envinroment
```

## Envinroment configuration
```
mkdir /opt/rmi-to-queue
cp -r lib/ /opt/rmi-to-queue/
```

## Build and run the RMI Server
```
git clone https://github.com/jpmaida/rmi-to-queue.git
cd rmi-to-queue/rmi-to-queue-server
mvn clean package -DskipTests
java -jar target/rmi-to-queue-server.jar
```

## Build and run the RMI Client
```
git clone https://github.com/jpmaida/rmi-to-queue.git
cd rmi-to-queue/rmi-to-queue-client
mvn clean package -DskipTests
java -jar target/rmi-to-queue-client.jar
```