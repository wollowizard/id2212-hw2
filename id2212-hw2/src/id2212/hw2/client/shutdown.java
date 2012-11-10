/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id2212.hw2.client;

import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alfredo
 */
public class shutdown implements Runnable{
    ClientImpl c;
    public shutdown(ClientImpl cl){
        c=cl;
    }
    @Override
    public void run() {
        try {
            System.out.println("Client Exiting!!!");
            c.servObj.unregisterClient(c.getName());
        } catch (RemoteException ex) {
            Logger.getLogger(shutdown.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    
    
}
