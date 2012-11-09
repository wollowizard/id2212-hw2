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

    
    
    public MarketPlaceServer(String servName, String bankName) {
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
		if (args.length > 2 || (args.length > 0 && args[0].equalsIgnoreCase("-h"))) {
			System.out.println(":)");
			System.exit(1);
		}

		String servName = null;
                String bankName = null;
		if (args.length == 2) {
			servName = args[0];
                        bankName = args[1];
		} else {
			servName = "EBAY";
                        bankName = "bankia";
		}

		new MarketPlaceServer(servName,bankName);
	}
}
