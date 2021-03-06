/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.postitppd.forms;

import com.postitppd.rmi.Client;
import com.postitppd.user.Postit;
import com.postitppd.user.User;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

/**
 *
 * @author Keylla
 */
public class ListaPostit extends javax.swing.JFrame {
    public Client clientListPost;
    public User userListPost ;
    protected ArrayList<JFrame> listJframe;
    protected Object[] ids;
    protected CadastraPostit cadp;
    protected int idEdit;
    protected Object[] es;
    

    /**
     * Creates new form CadastraPostit
     */
    public ListaPostit() {
        initComponents();
        edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtEditarActionPerformed(evt);
            }
        });
        listJframe = new ArrayList<>();
    }

    public JLabel getJlbBemVindo() {
        return jlbBemVindo;
    }

    public JList getjListPostits() {
        return jListPostits;
    }
     public void carregaEditPostit(String loginuser) throws RemoteException{
        ArrayList<Postit> postits = new ArrayList<>();
        postits = clientListPost.getUserPostit(userListPost.getLogin());  
        es = new Object[postits.size()];
        ids = new Object[postits.size()];
        
        for(int a = 0; a<postits.size();a++){
            if(postits.get(a).getPostText().length() >20){
                ids[a] = postits.get(a).getIdPost();
                es[a] = postits.get(a).getPostText().substring(1, 16)+" ...";
            }
            else { 
            ids[a] = postits.get(a).getIdPost();    
            es[a] = postits.get(a).getPostText();
            }
        }   
        int idRemov = jListPostits.getSelectedIndex(); 
        String novoText = postits.get(idRemov).getPostText();
        FormPostit jRem = (FormPostit) listJframe.get(idRemov);
        jRem.getJtxtpostit().setText(novoText);
        this.jListPostits.setListData(es);    
    }
     
    public void carregaRemovePostit(String loginuser) throws RemoteException{
        ArrayList<Postit> postits = new ArrayList<>();
        postits = clientListPost.getUserPostit(userListPost.getLogin());  
        es = new Object[postits.size()];
        ids = new Object[postits.size()];
        ArrayList<JFrame> listJframeNovo = new ArrayList<>();
        
         for( int i = 0; i<jListPostits.getSelectedIndices().length; i++){
            int idNovo = 0; 
            int idRemov = jListPostits.getSelectedIndices()[i]; 
            JFrame jRem = listJframe.get(idRemov);
            jRem.dispose();
           for( JFrame frame : listJframe){     
               if(idNovo!= idRemov){
                listJframeNovo.add(listJframe.get(idNovo));
               }
               idNovo++;
           }
         } 
         listJframe.removeAll(listJframe);
         listJframe= listJframeNovo;
        
        for(int a = 0; a<postits.size();a++){
            if(postits.get(a).getPostText().length() >20){
                ids[a] = postits.get(a).getIdPost(); 
                es[a] = postits.get(a).getPostText().substring(1, 16)+" ...";
            }
            else { 
            ids[a] = postits.get(a).getIdPost();    
            es[a] = postits.get(a).getPostText();
            }
        } 
        this.jListPostits.setListData(es);    
    }
    public void carregaNovoPostit(String loginuser) throws RemoteException{
        Toolkit toolkit = Toolkit.getDefaultToolkit(); 
        Dimension screenSize = toolkit.getScreenSize(); 
        int x = (screenSize.width/2)+320 ; 
        int y = (screenSize.height/2)-350; 
        ArrayList<Postit> postits = new ArrayList<>();
        postits = clientListPost.getUserPostit(userListPost.getLogin());
        es = new Object[postits.size()];
        ids = new Object[postits.size()];
        
          for (int i = 0; i <= postits.size()-1; i++){
            if(postits.get(i).getPostText().length() >20){
                ids[i] = postits.get(i).getIdPost();    
                es[i] = postits.get(i).getPostText().substring(1, 16)+" ...";
            }
            else { 
            ids[i] = postits.get(i).getIdPost();    
            es[i] = postits.get(i).getPostText();
            }        
        }
        FormPostit formp;
        int i = postits.size()-1;
        formp = new FormPostit();
        formp.getJtxtpostit().setText(postits.get(i).getPostText());
        listJframe.add(formp);   
        formp.setLocation(x, y);
        formp.setVisible(true);
        this.jListPostits.setListData(es);    
    }
    
    public void carregaPostits(String loginuser) throws RemoteException{
        Toolkit toolkit = Toolkit.getDefaultToolkit(); 
        Dimension screenSize = toolkit.getScreenSize(); 
        int x = (screenSize.width/2)+350 ; 
        int y = (screenSize.height/2)-350; 
        ArrayList<Postit> postits = new ArrayList<>();
        postits = clientListPost.getUserPostit(userListPost.getLogin());
        es = new Object[postits.size()];
        ids = new Object[postits.size()]; 
        FormPostit formp;
         for( JFrame frame : listJframe){  
              frame.setFocusable(true);
              frame.dispose();
            };
        listJframe.removeAll(listJframe);
        for (int i = 0; i <= postits.size()-1; i++){
            if(postits.get(i).getPostText().length() >20){
                ids[i] = postits.get(i).getIdPost();    
                es[i] = postits.get(i).getPostText().substring(1, 16)+" ...";
            }
            else { 
            ids[i] = postits.get(i).getIdPost();    
            es[i] = postits.get(i).getPostText();
            }
            formp = new FormPostit();
            formp.getJtxtpostit().setText(postits.get(i).getPostText());
            listJframe.add(formp);  
        }
          for( JFrame frame : listJframe){ 
              frame.setLocation(x, y);
              frame.setVisible(true);
              x=x-100;
            }
        this.jListPostits.setListData(es);  
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupEditar = new javax.swing.JPopupMenu();
        jlbBemVindo = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jListPostits = new javax.swing.JList();
        jbtNovo = new javax.swing.JButton();
        jbtExcluir = new javax.swing.JButton();
        jbtLogout = new javax.swing.JButton();

        jPopupEditar.setBorder(null);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jlbBemVindo.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jlbBemVindo.setText("Bem Vindo!");

        jListPostits.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jListPostitsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jListPostits);

        jbtNovo.setText("Novo");
        jbtNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtNovoActionPerformed(evt);
            }
        });

        jbtExcluir.setText("Excluir");
        jbtExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtExcluirActionPerformed(evt);
            }
        });

        jbtLogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/postitppd/imagens/logout-icon.png"))); // NOI18N
        jbtLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtLogoutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 368, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jbtNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jbtExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(57, 57, 57))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jlbBemVindo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jbtLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jlbBemVindo)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jbtLogout, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtNovo)
                    .addComponent(jbtExcluir))
                .addGap(33, 33, 33))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtNovoActionPerformed
        // TODO add your handling code here:
        cadp = new CadastraPostit();
        cadp.clientCadPost = this.clientListPost;
        cadp.userCadPost = this.userListPost;
        cadp.listCadPost = this;
        cadp.tipoEvento = 0;
        cadp.setLocationRelativeTo(null);
        cadp.setVisible(true);
    }//GEN-LAST:event_jbtNovoActionPerformed

    private void jbtExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtExcluirActionPerformed
      for( int i = 0; i<jListPostits.getSelectedIndices().length; i++){
          int idRemov = (int) ids[(jListPostits.getSelectedIndices()[i])];
          this.clientListPost.removePostit(idRemov, this.userListPost.getLogin());
        }  
        try {
            this.carregaRemovePostit(this.userListPost.getLogin());
        } catch (RemoteException ex) {
            JOptionPane.showMessageDialog(null, "Não foi possível estabeler conexão com o servidor!"); 
        }
    }//GEN-LAST:event_jbtExcluirActionPerformed

    private void jListPostitsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jListPostitsMouseClicked
        // TODO add your handling code here:
        if (evt.getButton() == MouseEvent.BUTTON3)
        {
            final int index = jListPostits.locationToIndex(evt.getPoint()); 
            this.jListPostits.setSelectedIndex(index);
            jPopupEditar.show(jListPostits, evt.getX(), evt.getY());
            edit.setText("Editar");
            jPopupEditar.add(edit);
                      
        }
    }//GEN-LAST:event_jListPostitsMouseClicked

    private void jbtLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtLogoutActionPerformed
        // TODO add your handling code here:
        guiUserLogin guiUser = new guiUserLogin();
        clientListPost = null;
        userListPost = null ;
         for( JFrame frame : listJframe){  
              frame.setFocusable(true);
              frame.dispose();
            };
        listJframe.removeAll(listJframe);
        ids = null;
        cadp = null;
        this.dispose();
        guiUser.setVisible(true);
       
    }//GEN-LAST:event_jbtLogoutActionPerformed
        private void jbtEditarActionPerformed(java.awt.event.ActionEvent evt) {                                           
          int i = jListPostits.getSelectedIndex();  
          idEdit = (int) ids[i];
          FormPostit fpost = (FormPostit) listJframe.get(i);
          cadp = new CadastraPostit();
          cadp.clientCadPost = this.clientListPost;
          cadp.userCadPost = this.userListPost;
          cadp.listCadPost = this;
          cadp.tipoEvento = 1;
          cadp.getJtxtPostText().setText(fpost.getJtxtpostit().getText());
          cadp.setLocationRelativeTo(null);
          cadp.setVisible(true);
          
       /* try {
            this.carregaEditPostit(this.userListPost.getLogin());
        } catch (RemoteException ex) {
            Logger.getLogger(ListaPostit.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    } 
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ListaPostit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ListaPostit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ListaPostit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ListaPostit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ListaPostit().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList jListPostits;
    private javax.swing.JPopupMenu jPopupEditar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbtExcluir;
    private javax.swing.JButton jbtLogout;
    private javax.swing.JButton jbtNovo;
    private javax.swing.JLabel jlbBemVindo;
    // End of variables declaration//GEN-END:variables
    private JButton edit = new JButton();
}
