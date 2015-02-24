/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.postitppd.rmi;


import java.rmi.AccessException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;



/**
 *
 * @author Keylla
 */
public class RecordServer {
    private Registry registryServer = null;
    
    public void connectServer(Server server) {
      if (registryServer == null) {   
            try {  
                registryServer = LocateRegistry.createRegistry(5000);
                registryServer.rebind("ServidorRMI", server);              
            } catch (AccessException ex) {
                System.out.println("Servidor já registrado!");
            } catch (RemoteException ex) {
               System.out.println("Servidor já registrado!");
          }
       
        }  
    }
}    
