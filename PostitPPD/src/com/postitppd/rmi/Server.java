/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.postitppd.rmi;


import com.postitppd.user.ManipulaJson;
import com.postitppd.user.Postit;
import com.postitppd.user.User;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;


/**
 *
 * @author Keylla
 */
public class Server extends UnicastRemoteObject implements PostitPPDInt {
    ManipulaJson mj = new ManipulaJson();
    int id =0;
    int idPost = 0;
    
    public Server() throws RemoteException{
      super();
   }

    @Override
    public ArrayList<Postit> getUserPostit(String loginUser) throws RemoteException {      
        return mj.carregaPostitUser(loginUser);  
    }

    @Override
    public User getUser(String iduser) throws RemoteException {
        return mj.carregaUsuarioLogado(iduser);
    }

    @Override
    public void setUser(String nome, String login, String senha) throws RemoteException {
        id = mj.carregaProxIdUser();
        mj.escreveJsonUser(id, nome, login, senha);
    }

    @Override
    public void setPostit(String loginUser, String postText) throws RemoteException {
        idPost = mj.carregaProxIdPost(loginUser);
        mj.escreveJsonPostit(loginUser, idPost, postText);

    }
    
    @Override
    public boolean validaLogin(String login, String senha) throws RemoteException{
        boolean loginOK = false;
        User userLogin = null;
        userLogin = this.getUser(login);
        if(userLogin != null && userLogin.getSenha().equals(senha)&& userLogin.getLogin().equals(login)){
            loginOK = true;  
        }
        return loginOK;
    }

    @Override
    public void removePostit(int idPostit, String userLogado) throws RemoteException {
        mj.removePostit(idPostit, userLogado);
    }

    @Override
    public void editPostit(int idPostit, String userConnect, String novoText) throws RemoteException {
        mj.editPostit(idPostit, userConnect, novoText);
    }
    
    @Override
    public boolean verificaLoginExists (String loginUser){
        return mj.verificaLoginExists(loginUser);
    }
    
}
