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
    public ArrayList<Postit> getUserPostit(int idUser) throws RemoteException;
    public void setUser(String nome, String login, String senha) throws RemoteException;
    public void setPostit(int idUser, String postText) throws RemoteException;

}
