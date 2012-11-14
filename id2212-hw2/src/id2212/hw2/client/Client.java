/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id2212.hw2.client;

import id2212.hw2.item.Item;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Marcel
 */
public interface Client extends Remote {
    public void notifyWishedAvailable(Item it) throws RemoteException;

    public void notifyItemSold(Item it)throws RemoteException;

    
   public String getName() throws RemoteException;

    public void notifyWishDisappeared(Integer itemId)throws RemoteException;

    public void addWish(Item it)throws RemoteException;

    public boolean hasNoMoreWishes()throws RemoteException;

    
    
}
