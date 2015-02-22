/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.postitppd.forms;

import com.postitppd.rmi.Client;
import com.postitppd.rmi.RecordServer;
import com.postitppd.rmi.Server;
import com.postitppd.user.User;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.rmi.RemoteException;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

/**
 *
 * @author Keylla
 */
public class guiUserLogin extends javax.swing.JFrame {
   private Server rmiServer = null;
   private RecordServer regitryServer = null;
   private Client cliente = null;
   private User user;
   
    /**
     * A return status code - returned if Cancel button has been pressed
     */
    public static final int RET_CANCEL = 0;
    /**
     * A return status code - returned if OK button has been pressed
     */
    public static final int RET_OK = 1;

    /**
     * Creates new form guiUserLogin
     * @param parent
     * @param modal
     */
    public guiUserLogin() {
        
        this.setLocationRelativeTo(null);
        initComponents();
        this.okButton.setEnabled(false);
        this.jTextUser.setEnabled(false);
        this.jPassUser.setEnabled(false);
        this.jLinkCadastro.setEnabled(false);
       

        // Close the dialog when Esc is pressed
        String cancelName = "cancel";
        InputMap inputMap = getRootPane().getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), cancelName);
        ActionMap actionMap = getRootPane().getActionMap();
        actionMap.put(cancelName, new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                doClose(RET_CANCEL);
            }
        });
    }

  
    /**
     * @return the return status of this dialog - one of RET_OK or RET_CANCEL
     */
    public int getReturnStatus() {
        return returnStatus;
    }
    
    private void setClient(){
        String ip = jtxtIpServer.getText();
        cliente = new Client();
       try {
           cliente.connectClient(ip);
       } catch (RemoteException ex) {
           cliente = null;
           JOptionPane.showMessageDialog(null, "Não foi possível estabeler conexão com o servidor informado. "
                                       +"\n" + "Por favor verificar IP do servidor!");
           this.jbStartServer.setEnabled(true);
       }
    }
    
    public Client getClient(){
        return this.cliente;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        okButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        jPassUser = new javax.swing.JPasswordField();
        jTextUser = new javax.swing.JTextField();
        jLinkCadastro = new javax.swing.JLabel();
        jlbLogin = new javax.swing.JLabel();
        jlbSenha = new javax.swing.JLabel();
        jtxtIpServer = new javax.swing.JTextField();
        jlbIpServer = new javax.swing.JLabel();
        jbStartServer = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        okButton.setText("Conectar");
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        jLinkCadastro.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLinkCadastro.setForeground(new java.awt.Color(102, 102, 255));
        jLinkCadastro.setText("Cadastrar-se");
        jLinkCadastro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLinkCadastroMouseClicked(evt);
            }
        });

        jlbLogin.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jlbLogin.setText("Login:");

        jlbSenha.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jlbSenha.setText("Senha:");

        jlbIpServer.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jlbIpServer.setText("IP Servidor:");

        jbStartServer.setText("Start");
        jbStartServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbStartServerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlbLogin)
                            .addComponent(jlbSenha)
                            .addComponent(jlbIpServer))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jTextUser, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
                            .addComponent(jPassUser, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtxtIpServer))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(okButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cancelButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jbStartServer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(131, 131, 131)
                        .addComponent(jLinkCadastro)))
                .addGap(0, 19, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtxtIpServer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlbIpServer)
                    .addComponent(jbStartServer))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlbLogin)
                    .addComponent(okButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jPassUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlbSenha)
                    .addComponent(cancelButton))
                .addGap(18, 18, 18)
                .addComponent(jLinkCadastro)
                .addGap(19, 19, 19))
        );

        getRootPane().setDefaultButton(okButton);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed

        setClient();
        boolean valida = false;
       if (getClient()!=null) { 
        try {
            valida = this.getClient().validaLogin(this.jTextUser.getText(), this.jPassUser.getText());
        } catch (RemoteException ex) {
            System.out.println("Não foi possível validar entrada!");
        }
        if(valida) {
            ListaPostit lpost = new ListaPostit();
            lpost.clientListPost = getClient();
           try {
               lpost.userListPost =  getClient().getUser(this.jTextUser.getText());
               lpost.carregaPostits(lpost.userListPost.getLogin());
           } catch (RemoteException ex) {
               System.out.println("Não foi possível iniciar usuário na guiUserLogin.");
           }
            doClose(RET_CANCEL);  
            lpost.getJlbBemVindo().setText("Bem Vindo "+lpost.userListPost.getName()); 
            lpost.setLocationRelativeTo(null);
            lpost.setVisible(true);
       }
       else
        JOptionPane.showMessageDialog(null, "Login ou Senha inválidos!");  
       }     
    }//GEN-LAST:event_okButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        doClose(RET_CANCEL);
    }//GEN-LAST:event_cancelButtonActionPerformed

    /**
     * Closes the dialog
     */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        doClose(RET_CANCEL);
    }//GEN-LAST:event_closeDialog

    private void jLinkCadastroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLinkCadastroMouseClicked
        // TODO add your handling code here:
        setClient();
        guiUserCadastro guiUser = new guiUserCadastro (); 
        guiUser.clientUser = getClient();
        doClose(RET_CANCEL);
        guiUser.setLocationRelativeTo(null);
        guiUser.setVisible(true); 
        
       
    }//GEN-LAST:event_jLinkCadastroMouseClicked

    private void jbStartServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbStartServerActionPerformed
       if (this.jtxtIpServer.getText().isEmpty())
             JOptionPane.showMessageDialog(null, "Deve ser informado IP do servidor!"); 
        else{
            try {
               
               rmiServer = new Server();
               regitryServer = new RecordServer();
               regitryServer.connectServer(rmiServer);
           } catch (RemoteException ex) {
               JOptionPane.showMessageDialog(null, "Não foi possível estabeler conexão com o servidor informado!"); 
           }
            this.jLinkCadastro.setEnabled(true);
            this.okButton.setEnabled(true);
            this.jTextUser.setEnabled(true);
            this.jPassUser.setEnabled(true);
            this.jbStartServer.setEnabled(false);
        }
    }//GEN-LAST:event_jbStartServerActionPerformed
    
    private void doClose(int retStatus) {
        returnStatus = retStatus;
        setVisible(false);
        this.dispose();
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
            java.util.logging.Logger.getLogger(guiUserLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(guiUserLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(guiUserLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(guiUserLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                guiUserLogin dialog = new guiUserLogin();
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JLabel jLinkCadastro;
    private javax.swing.JPasswordField jPassUser;
    private javax.swing.JTextField jTextUser;
    private javax.swing.JButton jbStartServer;
    private javax.swing.JLabel jlbIpServer;
    private javax.swing.JLabel jlbLogin;
    private javax.swing.JLabel jlbSenha;
    private javax.swing.JTextField jtxtIpServer;
    private javax.swing.JButton okButton;
    // End of variables declaration//GEN-END:variables

    private int returnStatus = RET_CANCEL;
}
