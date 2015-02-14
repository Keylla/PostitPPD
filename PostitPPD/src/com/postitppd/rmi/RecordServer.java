/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.postitppd.rmi;


import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Keylla
 */
public class RecordServer {
    private Registry registryServer = null;
    
    public void connectServer(Server server) {
      if (registryServer == null) {
   
        try { 
            try {
                registryServer = LocateRegistry.createRegistry(5000);
                registryServer.bind("ServidorRMI", server);
            } catch (AlreadyBoundException ex) {
                Logger.getLogger(RecordServer.class.getName()).log(Level.SEVERE, null, ex);
            } catch (AccessException ex) {
                Logger.getLogger(RecordServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (RemoteException ex) {
            Logger.getLogger(RecordServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        }  
    }
}    
