/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.postitppd.rmi;

import com.postitppd.user.Postit;
import com.postitppd.user.User;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
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
        try {
            stub.setPostit(User, postit);
        } catch (RemoteException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
                
    }  
    public User getUser(String user) throws RemoteException{
        return stub.getUser(user);
    }
    
    public ArrayList<Postit> getUserPostit(String loginUser) throws RemoteException{
        return stub.getUserPostit(loginUser);
    }
    
    public boolean validaLogin(String login, String senha) throws RemoteException{
       return stub.validaLogin(login, senha);     
    }
    
    public void removePostit(int idPost){
        try {
            stub.removePostit(idPost);
        } catch (RemoteException ex) {
            System.out.println("Não foi possível remover o Postit selecionado!");
        }
    }
}
