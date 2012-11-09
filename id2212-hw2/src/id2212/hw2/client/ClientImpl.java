/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id2212.hw2.client;

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
public class ClientImpl extends UnicastRemoteObject implements Client  {

    private String clientName;
    public MarketPlace servObj;
    private String servName;
    
    public ArrayList<Item> wishedItems=new ArrayList<>();
      
    
    
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
    public void notifyClient() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    
    
    
}

