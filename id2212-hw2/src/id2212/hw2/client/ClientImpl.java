/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id2212.hw2.client;

import id2212.hw2.client.view.ClientUI;
import id2212.hw2.item.Item;
import id2212.hw2.server.MarketPlace;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 *
 * @author Marcel
 */
public class ClientImpl extends UnicastRemoteObject implements Client {

    public String clientName;
    public MarketPlace servObj;
    private String servName;
    
    
    public ClientUI gui;
      
    
    
    public ClientImpl(String name, String sname) throws RemoteException {
        this.clientName = name;
        this.servName = sname;
        try {
            
            servObj = (MarketPlace)Naming.lookup("rmi://localhost/EBAY");
        } catch (Exception e) {
            System.out.println("The runtime failed: " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Connected to bank: " + this.servName);
    }
    
 
    
    
    
    @Override
     public void notifyItemSold(Item it) throws RemoteException {
        this.gui.yourItemWasSold(it);
     }

    @Override
    public void notifyWishedAvailable(Item it)throws RemoteException {
        this.gui.whishAvailable(it);
        
    }

   
    
     

    @Override
    public String getName() throws RemoteException {
        return this.clientName;
    }

    @Override
    public void notifyWishDisappeared(Item it) {
        this.gui.whishDisappeared(it);
    }

      
    
}

