/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.postitppd.user;

import java.util.HashMap;
import java.util.Map;
import com.google.gson.Gson;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import static java.lang.Integer.parseInt;
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
    
    public void escreveJsonUser(int id, String nome, String login, String senha){
        JSONArray jsonArray = new JSONArray();
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = new JSONObject();       
        String saidaUser = "C:\\Users\\"+System.getProperty("user.name")+"\\Documents\\saidaUser.json";
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
    
    public void escreveJsonPostit(int idUser, int idPost, String postText){
        JSONArray jsonArray = new JSONArray();
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = new JSONObject();       
        String saidaPostit = "C:\\Users\\"+System.getProperty("user.name")+"\\Documents\\saidaPostit.json";
        File arquivo = new File(saidaPostit);
  
        try {
            if (!arquivo.exists()){
                arquivo.createNewFile();
                FileWriter writeFile = new FileWriter(saidaPostit); 
                writeFile.write("[]");
                writeFile.close();
            }
            jsonObject.put("idUser", String.valueOf(idUser));
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
    Gson gson = new Gson();
    Map<String, Object> map = new HashMap<String, Object>();
    map = (Map<String, Object>)gson.fromJson(jsonUser, map.getClass());
    
    String id = (String) map.get("id");
    String nome = (String) map.get("nome");
    String login = (String) map.get("login");
    String senha = (String) map.get("senha");
    User user = new User(parseInt(id),nome,login,senha);
    return user;
    }
    
     public Postit carregaPostit(String jsonPostit){
    Gson gson = new Gson();
    Map<String, Object> map = new HashMap<String, Object>();
    map = (Map<String, Object>)gson.fromJson(jsonPostit, map.getClass());
    
    String idUser = (String) map.get("idUser");
    String idPost = (String) map.get("idPost");
    String postText = (String) map.get("postText");
    Postit postit = new Postit(parseInt(idUser),parseInt(idPost),postText);
    return postit;
    }
    
}
