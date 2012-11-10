/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id2212.hw2.server;

import id2212.hw2.bank.Account;
import id2212.hw2.bank.Bank;
import id2212.hw2.bank.RejectedException;
import id2212.hw2.client.Client;
import id2212.hw2.client.ClientImpl;
import id2212.hw2.item.Item;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marcel
 */
public class MarketPlaceImpl extends UnicastRemoteObject implements MarketPlace {

    private String servName;
    private String bankName;
    private Bank objBank;
    private Map<String, Account> listAccounts = new ConcurrentHashMap<String, Account>();
    private Map<Item, String> listItems = new ConcurrentHashMap<Item, String>();
    private ArrayList<Wish> wishes = new ArrayList<>();

    public MarketPlaceImpl(String sname, String bname) throws RemoteException {
        super();
        this.servName = sname;
        this.bankName = bname;
        try {
            objBank = (Bank) Naming.lookup("rmi://localhost/" + this.bankName);
        } catch (Exception e) {
            System.out.println("The runtime failed: " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Connected to bank: " + this.bankName);
    }

    @Override
    public void registerClient(String name) throws RemoteException, RejectedException {

        listAccounts.put(name, this.objBank.newAccount(name));



    }

    @Override
    public void unregisterClient(String name) throws RemoteException {
        ArrayList<Item> tmp = new ArrayList<Item>();
        for (Map.Entry<Item, String> entry : listItems.entrySet()) {
            if (entry.getValue().compareTo(name) == 0) {
                tmp.add(entry.getKey());
            }
        }
        for (Item i : tmp) {
            listItems.remove(i);
        }

        this.objBank.deleteAccount(name);
        this.listAccounts.remove(name);

    }

    @Override
    public void sellItem(Item it, String name, Client c) throws RemoteException {
        it.setSeller(c);
        listItems.put(it, name);

        this.checkForWishedItems(it);


    }

    private Item findItemById(int id) {
        for (Map.Entry<Item, String> entry : listItems.entrySet()) {
            if (entry.getKey().getId() == id) {
                return entry.getKey();
            }
        }

        return null;
    }

    @Override
    public void buyItem(Integer itemId, String name, Client c) throws RemoteException, RejectedException {


        Account buyAcc = listAccounts.get(name);
        Item it = findItemById(itemId);
        if (it == null) {
            throw new RemoteException("no item found");
        }
        Account sellAcc = listAccounts.get(it.getSeller().getName());


        buyAcc.withdraw(it.getPrice());
        sellAcc.deposit(it.getPrice());
        it.getSeller().notifyItemSold(it);

        for(Wish w : wishes){
            if(w.item.getId()==it.getId()){
                w.wisher.notifyWishDisappeared(it);
            }
        }
        //
        
        for (Map.Entry<Item, String> entry : listItems.entrySet()) {
            if (entry.getKey().getId() == it.getId()) {
                listItems.remove(entry.getKey());
            }
        }



    }

    @Override
    public ArrayList<Item> inspectItem() throws RemoteException {
        ArrayList<Item> tmp = new ArrayList<>();
        for (Map.Entry<Item, String> entry : listItems.entrySet()) {
            tmp.add(entry.getKey());
        }

        return tmp;
    }

    @Override
    public void wishItem(Item it, Client c) throws RemoteException {
        this.wishes.add(new Wish(it, c));
    }

    private void checkForWishedItems(Item it) throws RemoteException {

        for (Wish w : wishes) {
            Client c = w.wisher;
            Item i = w.item;

            if ((i.getName().contains(it.getName()) || it.getName().contains(i.getName()))
                    && i.getPrice() <= it.getPrice()) {
                c.notifyWishedAvailable(i);
                w.item=it;

            }
        }

    }

    @Override
    public Item newItem(String text, float parseFloat, Client client) throws RemoteException {
        return new Item(text, parseFloat, client);

    }
}
