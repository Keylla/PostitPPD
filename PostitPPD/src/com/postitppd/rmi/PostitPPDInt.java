package com.postitppd.rmi;

import com.postitppd.user.Postit;
import com.postitppd.user.User;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by Keylla on 07/02/2015.
 */
public interface PostitPPDInt extends Remote{
    
    public User getUser(String user)throws RemoteException;
    public ArrayList<Postit> getUserPostit(String loginUser) throws RemoteException;
    public void setUser(String nome, String login, String senha) throws RemoteException;
    public void setPostit(String loginUser, String postText) throws RemoteException;
    public boolean validaLogin(String login, String senha) throws RemoteException;
    public void removePostit (int idPostit, String userLogado)throws RemoteException;
    public void editPostit(int idPostit, String userConnect, String novoText) throws RemoteException;
    public boolean verificaLoginExists (String loginUser) throws RemoteException;

}
