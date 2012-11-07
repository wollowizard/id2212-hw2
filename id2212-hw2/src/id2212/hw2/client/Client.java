/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id2212.hw2.client;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Marcel
 */
public interface Client extends Remote {
    public void notifyClient() throws RemoteException;
}
