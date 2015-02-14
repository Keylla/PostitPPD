package com.postitppd.user;

import java.io.Serializable;

/**
 * Created by Keylla on 07/02/2015.
 */
public class Postit implements Serializable {

    private int idUser;
    private int idPost;
    private String postText;

    public Postit (int idUser, int idPost, String postText){
        this.idUser = idUser;
        this.idPost = idPost;
        this.postText = postText;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
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
        return "Postit{" + "idUser=" + idUser + ", idPost=" + idPost + ", postText=" + postText + '}';
    }
   
    
    
}
