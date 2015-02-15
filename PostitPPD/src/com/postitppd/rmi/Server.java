/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.postitppd.rmi;

import com.google.gson.Gson;
import com.postitppd.forms.guiUserCadastro;
import com.postitppd.user.ManipulaJson;
import com.postitppd.user.Postit;
import com.postitppd.user.User;
import java.io.FileReader;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

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
      System.out.println("Servidor criado!");
   }

    @Override
    public ArrayList<Postit> getUserPostit(String loginUser) throws RemoteException {
        int i = 0;
        String idUserPost = "";
        ArrayList<Postit> aPost = new ArrayList<Postit>();
        String userConnect = loginUser;
        Gson gson = new Gson();
        Map<String, Object> map = new HashMap<String, Object>();
        String saidaUserPostit = "C:\\Users\\"+System.getProperty("user.name")+"\\Documents\\saidaPostit.json";;
        JSONObject jsonObject = null;
        JSONArray jsonArray = new JSONArray();
        JSONParser parser = new JSONParser();
        try {
            jsonArray = (JSONArray) parser.parse(new FileReader(saidaUserPostit));
            while (i<= jsonArray.size()-1){     
              jsonObject = (JSONObject)jsonArray.get(i);
              map = (Map<String, Object>)gson.fromJson(jsonObject.toJSONString(), map.getClass());
              idUserPost = (String) map.get("loginUser");
              if(userConnect.equals(idUserPost)){
               aPost.add(mj.carregaPostit(jsonObject.toJSONString()));
              }
              i++;
            }
        } catch (IOException ex) {
            Logger.getLogger(guiUserCadastro.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(guiUserCadastro.class.getName()).log(Level.SEVERE, null, ex);
        }      
        return aPost;  
    }

    @Override
    public User getUser(String iduser) throws RemoteException {
        int i = 0;
        String login = "";
        User user = new User();
        String userConnect = iduser;
        Gson gson = new Gson();
        Map<String, Object> map = new HashMap<String, Object>();
        String saidaUser = "C:\\Users\\"+System.getProperty("user.name")+"\\Documents\\saidaUser.json";
        JSONObject jsonObject = null;
        JSONArray jsonArray = new JSONArray();
        JSONParser parser = new JSONParser();
        try {
            jsonArray = (JSONArray) parser.parse(new FileReader(saidaUser));
            while (!userConnect.equals(login) && i<= jsonArray.size()-1){     
              jsonObject = (JSONObject)jsonArray.get(i);
              map = (Map<String, Object>)gson.fromJson(jsonObject.toJSONString(), map.getClass());
              login = (String) map.get("login");
              i++;
            }
        } catch (IOException ex) {
            Logger.getLogger(guiUserCadastro.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(guiUserCadastro.class.getName()).log(Level.SEVERE, null, ex);
        }  
        user =  mj.carregaUser(jsonObject.toJSONString());
        return user;
    }

    @Override
    public void setUser(String nome, String login, String senha) throws RemoteException {
        id = id+1;
        mj.escreveJsonUser(id, nome, login, senha);
    }

    @Override
    public void setPostit(String loginUser, String postText) throws RemoteException {
        idPost = idPost+1;
        mj.escreveJsonPostit(loginUser, idPost, postText);

    }
}
