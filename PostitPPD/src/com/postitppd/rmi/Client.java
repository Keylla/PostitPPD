/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.postitppd.rmi;

import com.postitppd.user.User;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Keylla
 */
public class Client {
    private Registry registry = null;
    private PostitPPDInt stub = null;
    
    public void connectClient(String ip) throws RemoteException {
        registry = LocateRegistry.getRegistry(ip, 5000);
        try {
            stub = (PostitPPDInt)registry.lookup("ServidorRMI");
        } catch (NotBoundException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (AccessException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void cadastraUser(String nome, String login, String senha){
        try {
            stub.setUser(nome, login, senha);
        } catch (RemoteException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void cadastraPostit(String User, String postit){
        int idUser = 0;
        try {
            User user = stub.getUser(User);
            idUser = user.getId();
            stub.setPostit(idUser, postit);
        } catch (RemoteException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
                
    }
    
    public User getUser(String user) throws RemoteException{
        return stub.getUser(user);
    }
    
}
