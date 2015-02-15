package com.postitppd.user;

import java.io.Serializable;

/**
 * Created by Keylla on 07/02/2015.
 */
public class Postit implements Serializable {

    private String loginUser;
    private int idPost;
    private String postText;

    public Postit (String loginUser, int idPost, String postText){
        this.loginUser = loginUser;
        this.idPost = idPost;
        this.postText = postText;
    }

    public String getLoginUser() {
        return loginUser;
    }

    public void setLoginUser(String loginUser) {
        this.loginUser = loginUser;
    }



    public int getIdPost() {
        return idPost;
    }

    public void setIdPost(int idPost) {
        this.idPost = idPost;
    }

    public String getPostText() {
        return postText;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }

    @Override
    public String toString() {
        return "Postit{" + "idUser=" + loginUser + ", idPost=" + idPost + ", postText=" + postText + '}';
    }
   
    
    
}
