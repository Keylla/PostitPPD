/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.postitppd.user;

import java.util.HashMap;
import java.util.Map;
import com.google.gson.Gson;
import com.postitppd.forms.guiUserCadastro;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.util.ArrayList;
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
public class ManipulaJson {
    //public String saidaPostit = "C:\\Users\\"+System.getProperty("user.name")+"\\Documents\\saidaPostit.json";
    //public String saidaUser = "C:\\Users\\"+System.getProperty("user.name")+"\\Documents\\saidaUser.json";
    public String saidaUser = "saidaUser.json";
    public String saidaPostit = "saidaPostit.json";
   
    Gson gson = new Gson();
    Map<String, Object> map = new HashMap<String, Object>();
    JSONArray jsonArray;
    JSONParser parser;
    JSONObject jsonObject;
    
    public void escreveJsonUser(int id, String nome, String login, String senha){
         jsonArray = new JSONArray();
         parser = new JSONParser();
         jsonObject = new JSONObject();       
        File arquivo = new File(saidaUser);
        try {
            if (!arquivo.exists()){
                arquivo.createNewFile();
                FileWriter writeFile = new FileWriter(saidaUser); 
                writeFile.write("[]");
                writeFile.close();
            }
            jsonObject.put("id", String.valueOf(id));
            jsonObject.put("nome", nome);
            jsonObject.put("login", login);
            jsonObject.put("senha", senha);
            jsonArray = (JSONArray) parser.parse(new FileReader(saidaUser));
            jsonArray.add(jsonObject);
            FileWriter writeFile = new FileWriter(saidaUser); 
            writeFile.write(jsonArray.toString());
            writeFile.close();
        } catch (IOException ex) {
            Logger.getLogger(ManipulaJson.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(ManipulaJson.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void escreveJsonPostit(String loginUser, int idPost, String postText){
        jsonArray = new JSONArray();
        parser = new JSONParser();
        jsonObject = new JSONObject();          
        File arquivo = new File(saidaPostit);
        try {
            if (!arquivo.exists()){
                arquivo.createNewFile();
                FileWriter writeFile = new FileWriter(saidaPostit); 
                writeFile.write("[]");
                writeFile.close();
            }
            jsonObject.put("loginUser", loginUser);
            jsonObject.put("idPost", String.valueOf(idPost));
            jsonObject.put("postText", postText);
            jsonArray = (JSONArray) parser.parse(new FileReader(saidaPostit));
            jsonArray.add(jsonObject);
            FileWriter writeFile = new FileWriter(saidaPostit); 
            writeFile.write(jsonArray.toString());
            writeFile.close();
        } catch (IOException ex) {
            Logger.getLogger(ManipulaJson.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(ManipulaJson.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public User carregaUser(String jsonUser){
        map = (Map<String, Object>)gson.fromJson(jsonUser, map.getClass());
        String id = (String) map.get("id");
        String nome = (String) map.get("nome");
        String login = (String) map.get("login");
        String senha = (String) map.get("senha");
        User user = new User(parseInt(id),nome,login,senha);
        return user;
    }
    
    public Postit carregaPostit(String jsonPostit){
         
        map = (Map<String, Object>)gson.fromJson(jsonPostit, map.getClass());
        String loginUser = (String) map.get("loginUser");
        String idPost = (String) map.get("idPost");
        String postText = (String) map.get("postText");
        Postit postit = new Postit(loginUser,parseInt(idPost),postText);
        return postit;
    }
    
      
    public ArrayList<Postit> carregaPostitUser(String loginUser){
        int i = 0;
        String idUserPost = "";
        ArrayList<Postit> aPost = new ArrayList<Postit>();
        String userConnect = loginUser;
        jsonObject = null;
        jsonArray = new JSONArray();
        parser = new JSONParser();
        File arquivo = new File(saidaPostit);
        
        if(arquivo.exists()){
        try {
            jsonArray = (JSONArray) parser.parse(new FileReader(saidaPostit));
            while (i<= jsonArray.size()-1){     
              jsonObject = (JSONObject)jsonArray.get(i);
              map = (Map<String, Object>)gson.fromJson(jsonObject.toJSONString(), map.getClass());
              idUserPost = (String) map.get("loginUser");
              if(userConnect.equals(idUserPost)){
               aPost.add(carregaPostit(jsonObject.toJSONString()));
              }
              i++;
            }
        } catch (IOException ex) {
            Logger.getLogger(guiUserCadastro.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(guiUserCadastro.class.getName()).log(Level.SEVERE, null, ex);
        }
      }  
      return aPost;
    }
    
      public void removePostit(int idPostit, String userConnect){
        int a =  0;
        String idUserPost;
        String idPostRem = String.valueOf(idPostit);
        jsonArray = new JSONArray();
        parser = new JSONParser();
        jsonObject = new JSONObject();       
        File arquivo = new File(saidaPostit);

        try {
            jsonArray = (JSONArray) parser.parse(new FileReader(saidaPostit));
            while (a<= jsonArray.size()-1){
             jsonObject = (JSONObject)jsonArray.get(a);  
              map = (Map<String, Object>)gson.fromJson(jsonObject.toJSONString(), map.getClass());
              idUserPost = (String) map.get("loginUser");
              String idPost = (String) map.get("idPost");
              if(userConnect.equals(idUserPost) && idPostRem.endsWith(idPost)){
                 jsonArray.remove(jsonObject);           
                }
              a++;
            }     
            FileWriter writeFile = new FileWriter(saidaPostit); 
            writeFile.write(jsonArray.toString());
            writeFile.close();
        } catch (IOException ex) {
            Logger.getLogger(ManipulaJson.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(ManipulaJson.class.getName()).log(Level.SEVERE, null, ex);
        }
     } 
   
    public User carregaUsuarioLogado (String loginUser){
        int i = 0;
        String login = "";
        User user = new User();
        String userConnect = loginUser;
        jsonObject = null;
        jsonArray = new JSONArray();
        parser = new JSONParser();
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
        user =  carregaUser(jsonObject.toJSONString());
        return user;
    }
    
    public int carregaProxIdPost(String login){
        int maxId = 0;
        ArrayList<Postit> apot = this.carregaPostitUser(login);
        if(apot.size()>0){
            for(int i = 0; i <= apot.size()-1; i++){
                if(apot.get(i).getIdPost()>maxId)
                    maxId = apot.get(i).getIdPost();
            }
        }
        maxId = maxId+1;
        return maxId;
    }
    
     public int carregaProxIdUser(){
        int maxId = 0;
        int i = 0;
        String id;
        int idUser;
        jsonObject = null;
        jsonArray = new JSONArray();
        parser = new JSONParser();
        File arquivo = new File(saidaUser);
        if(arquivo.exists()){
            try {
                jsonArray = (JSONArray) parser.parse(new FileReader(saidaUser));
                while ( i<= jsonArray.size()-1){     
                  jsonObject = (JSONObject)jsonArray.get(i);
                  map = (Map<String, Object>)gson.fromJson(jsonObject.toJSONString(), map.getClass());
                  id = (String) map.get("id");
                  idUser = Integer.valueOf(id);
                  if(idUser>maxId){
                      maxId=idUser;
                  }
                  i++;
                }
            } catch (IOException ex) {
                Logger.getLogger(guiUserCadastro.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(guiUserCadastro.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }
        maxId = maxId+1;
        return maxId;
    }
     
     public void editPostit(int idPostit, String userConnect, String novoText){
          int a =  0;
        String idUserPost = "";
        String idPost = "";
        String idPostRem = String.valueOf(idPostit);
        jsonArray = new JSONArray();
        parser = new JSONParser();
        jsonObject = new JSONObject();
        JSONObject jsonObjectEdit = new JSONObject();
        File arquivo = new File(saidaPostit);

        try {
            jsonArray = (JSONArray) parser.parse(new FileReader(saidaPostit));
            while (a<= jsonArray.size()-1){
             jsonObject = (JSONObject)jsonArray.get(a);  
              map = (Map<String, Object>)gson.fromJson(jsonObject.toJSONString(), map.getClass());
              idUserPost = (String) map.get("loginUser");
              idPost = (String) map.get("idPost");
              if(userConnect.equals(idUserPost) && idPostRem.endsWith(idPost)){
                jsonObjectEdit.put("loginUser", idUserPost);
                jsonObjectEdit.put("idPost", idPost);
                jsonObjectEdit.put("postText", novoText);
                jsonArray.remove(a);
                jsonArray.add(a, jsonObjectEdit);           
                }
              a++;
            }     
            FileWriter writeFile = new FileWriter(saidaPostit); 
            writeFile.write(jsonArray.toString());
            writeFile.close();
        } catch (IOException ex) {
            Logger.getLogger(ManipulaJson.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(ManipulaJson.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
     
     public boolean verificaLoginExists (String loginUser){
        boolean existsLogin = false; 
        int i = 0;
        String login = "";
        String userConnect = loginUser;
        jsonObject = null;
        jsonArray = new JSONArray();
        parser = new JSONParser();
        try {
            jsonArray = (JSONArray) parser.parse(new FileReader(saidaUser));
            while (!userConnect.equals(login) && i<= jsonArray.size()-1){     
              jsonObject = (JSONObject)jsonArray.get(i);
              map = (Map<String, Object>)gson.fromJson(jsonObject.toJSONString(), map.getClass());
              login = (String) map.get("login");
              if(userConnect.equals(login)){
                  existsLogin = true;
              }
              i++;
            }
        } catch (IOException ex) {
            Logger.getLogger(guiUserCadastro.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(guiUserCadastro.class.getName()).log(Level.SEVERE, null, ex);
        }  
      return existsLogin;  
    }
 
}
