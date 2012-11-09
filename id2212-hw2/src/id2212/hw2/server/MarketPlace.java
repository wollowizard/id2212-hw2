/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id2212.hw2.server;

import id2212.hw2.client.ClientImpl;
import id2212.hw2.item.Item;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 * @author Marcel
 */
public interface MarketPlace extends Remote {
    
    public void registerClient(String name) throws RemoteException;
    public void unregisterClient(String name) throws RemoteException;
    public void sellItem(Item it, String name, ClientImpl c) throws RemoteException;
    public void buyItem(Item it, String name) throws RemoteException;
    public ArrayList<Item> inspectItem() throws RemoteException;
    public void wishItem(Item it, ClientImpl c) throws RemoteException;
}
