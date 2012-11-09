/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id2212.hw2.client;

import id2212.hw2.client.view.ClientUI;
import id2212.hw2.item.Item;
import java.rmi.RemoteException;

/**
 *
 * @author Marcel
 */
public class mainClient {
    
    private static String clientName = "Marcel";
    
    public static void main(String[] args) throws RemoteException {
        ClientImpl c = new ClientImpl(clientName, "EBAY");
        ClientUI cf = new ClientUI(c);
        cf.setVisible(true);
        /*c.servObj.registerClient(clientName);
        Item itm = new Item("camera",3000);
        c.servObj.sellItem(itm, clientName, c);
        System.out.println(c.servObj.inspectItem().get(0).name);*/
    }
}
