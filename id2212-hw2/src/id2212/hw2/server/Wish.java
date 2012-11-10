/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id2212.hw2.server;

import id2212.hw2.client.Client;
import id2212.hw2.item.Item;

/**
 *
 * @author alfredo
 */
public class Wish {
    public Item item;
    public Client wisher;

    public Wish(Item item, Client wisher) {
        this.item = item;
        this.wisher = wisher;
    }
    
    
    
    
}
