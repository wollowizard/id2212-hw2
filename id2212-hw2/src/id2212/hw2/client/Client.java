/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id2212.hw2.client;

import id2212.hw2.item.Item;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 * @author Marcel
 */
public interface Client extends Remote {
    public void notifyWishedAvailable(Item it) throws RemoteException;

    public void notifyItemSold(Item it)throws RemoteException;

    public void addItemToWished(Item it)throws RemoteException;
    public void removeItemFromWished(Item it) throws RemoteException;

    public ArrayList<Item> getWishedItems() throws RemoteException;
}
