package com.postitppd.user;

/**
 * Created by Keylla on 07/02/2015.
 */
public class User {
    private int id;
    private String name;
    private String login;
    private String senha;

    public User (int id, String name,String login, String senha){
        this.id = id;
        this.name = name;
        this.login = login;
        this.senha = senha;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    public int getId(){
        return id;
    }
    public String getName() {
        return name;
    }

    public String getSenha() {
        return senha;
    } 

    public String getLogin() {
        return login;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", name=" + name + ", login=" + login + ", senha=" + senha + '}';
    }
   
}
