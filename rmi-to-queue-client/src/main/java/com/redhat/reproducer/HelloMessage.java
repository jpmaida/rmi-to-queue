package com.redhat.reproducer;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface HelloMessage extends Remote {
    String sayHello() throws RemoteException;
}
