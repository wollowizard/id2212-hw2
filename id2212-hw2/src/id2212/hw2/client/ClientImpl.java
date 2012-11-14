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
import javax.swing.SwingUtilities;

/**
 *
 * @author Marcel
 */
public class ClientImpl extends UnicastRemoteObject implements Client {

    public String clientName;
    public MarketPlace servObj;
    private String servName;
    private ArrayList<Item> wishes = new ArrayList<>();
    public ClientUI gui;

    public ClientImpl(String name, String sname) throws RemoteException {
        this.clientName = name;
        this.servName = sname;
        try {

            servObj = (MarketPlace) Naming.lookup("rmi://localhost/EBAY");
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
    public void notifyWishedAvailable(Item it) throws RemoteException {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                gui.updateWishes(wishes);
            }
        });


    }

    @Override
    public String getName() throws RemoteException {
        return this.clientName;
    }

    @Override
    public void notifyWishDisappeared(Integer itemId) throws RemoteException {
        Item it = null;
        for (Item i : wishes) {
            if (i.getId() == itemId) {
                it = i;
            }
        }
        if (it != null) {
            wishes.remove(it);
        }

        System.out.println("Wishes of " + this.getName());
        for (Item i : wishes) {
            System.out.println(i.getId() + i.getPrice() + i.getName());
        }


        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                gui.updateWishes(wishes);
            }
        });

    }

    @Override
    public void addWish(Item it) throws RemoteException {
        this.wishes.add(it);

        System.out.println("Wishes of " + this.getName());
        for (Item i : wishes) {
            System.out.println(i.getId() + i.getPrice() + i.getName());
        }
    }

    @Override
    public boolean hasNoMoreWishes() throws RemoteException {

        return this.wishes.isEmpty();
    }
}
