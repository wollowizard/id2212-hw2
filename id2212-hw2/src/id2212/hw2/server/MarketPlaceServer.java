/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id2212.hw2.server;

import java.rmi.registry.LocateRegistry;

/**
 *
 * @author Marcel
 */
public class MarketPlaceServer {

    private static final String bankName = "bankia";
    
    public MarketPlaceServer(String servName) {
        try {
            MarketPlace mpObj = new MarketPlaceImpl(servName, bankName);
            // Register the newly created object at rmiregistry.
            //LocateRegistry.createRegistry(1234);
            java.rmi.Naming.rebind("rmi://localhost/"+servName, mpObj);
            System.out.println(mpObj + " is ready.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
		if (args.length > 1 || (args.length > 0 && args[0].equalsIgnoreCase("-h"))) {
			System.out.println(":)");
			System.exit(1);
		}

		String servName = null;
		if (args.length > 0) {
			servName = args[0];
		} else {
			servName = "EBAY";
		}

		new MarketPlaceServer(servName);
	}
}
