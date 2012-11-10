/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id2212.hw2.item;

import id2212.hw2.client.Client;
import java.io.Serializable;

/**
 *
 * @author Marcel
 */
public class Item implements Serializable {
    
    private int id;
    private String name;
    private float price;
    private Client seller;
    private static int counter = 0;
    
    public Item(String n, float p, Client s) {
        
        this.name = n;
        this.price= p;
        this.seller=s;
        this.id = counter;
        System.out.println("Item created: " + id);
        counter++;
    }

    public void setSeller(Client c) {
        this.seller=c;
    }

    public int getId() {
        return this.id;
    }

    public Client getSeller() {
        return this.seller;
    }

    public float getPrice() {
        return this.price;
    }

    public String getName() {
        return this.name;
    }
}
