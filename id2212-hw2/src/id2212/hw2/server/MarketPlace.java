/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id2212.hw2.server;

import id2212.hw2.bank.RejectedException;
import id2212.hw2.client.Client;
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
    
    public void registerClient(String name) throws RemoteException,RejectedException;
    public void unregisterClient(String name) throws RemoteException;
    public void sellItem(Item it, String name, Client c) throws RemoteException;
    public void buyItem(Integer iitemId, String name, Client c) throws RemoteException,RejectedException;
    public ArrayList<Item> inspectItem() throws RemoteException;
    public void wishItem(Item it, Client c) throws RemoteException;

    public Item newItem(String text, float parseFloat,Client c)throws RemoteException;
    public void addMoneyToAccount(String name, float money) throws RemoteException;
    public float getBalance(String name) throws RemoteException;
}
