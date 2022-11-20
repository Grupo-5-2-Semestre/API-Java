package janela;

import conexao.ConexaoUsuario;
import conexao.Database;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import logar.LogarUsuario;
import org.springframework.jdbc.core.JdbcTemplate;
import slack.SlackBd;

/**
 *
 * @author Diogo
 */
public class Login extends javax.swing.JFrame {

    public Login() {
        initComponents();
    }

    @Override
    public void paintComponents(Graphics g) {
        super.paintComponents(g); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        kGradientPanel1 = new keeptoo.KGradientPanel();
        lbUsuario = new javax.swing.JLabel();
        inputUsuario = new javax.swing.JTextField();
        lbSenha = new javax.swing.JLabel();
        btnEntrar = new javax.swing.JButton();
        btnRegister = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        logo = new javax.swing.JLabel();
        facebookImage = new javax.swing.JLabel();
        instagramImage = new javax.swing.JLabel();
        mailImage = new javax.swing.JLabel();
        inputSenha = new javax.swing.JPasswordField();
        checkPass = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        facebook = new javax.swing.JLabel();
        instagram = new javax.swing.JLabel();
        mail = new javax.swing.JLabel();

        jToolBar1.setRollover(true);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(204, 0, 153));

        kGradientPanel1.setToolTipText("");
        kGradientPanel1.setAlignmentX(50.0F);
        kGradientPanel1.setAlignmentY(50.0F);
        kGradientPanel1.setkEndColor(new java.awt.Color(0, 51, 102));
        kGradientPanel1.setkGradientFocus(900);
        kGradientPanel1.setkStartColor(new java.awt.Color(102, 0, 51));

        lbUsuario.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbUsuario.setForeground(new java.awt.Color(255, 255, 255));
        lbUsuario.setText("Username");

        inputUsuario.setForeground(new java.awt.Color(153, 153, 153));
        inputUsuario.setText("meuEmail@gmail.com");
        inputUsuario.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                inputUsuarioFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                inputUsuarioFocusLost(evt);
            }
        });
        inputUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                inputUsuarioMouseReleased(evt);
            }
        });
        inputUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputUsuarioActionPerformed(evt);
            }
        });

        lbSenha.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbSenha.setForeground(new java.awt.Color(255, 255, 255));
        lbSenha.setText("Password");

        btnEntrar.setBackground(new java.awt.Color(153, 0, 204));
        btnEntrar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnEntrar.setForeground(new java.awt.Color(255, 255, 255));
        btnEntrar.setText("Login");
        btnEntrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEntrarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnEntrarMouseEntered(evt);
            }
        });
        btnEntrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEntrarActionPerformed(evt);
            }
        });

        btnRegister.setBackground(new java.awt.Color(153, 102, 255));
        btnRegister.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnRegister.setForeground(new java.awt.Color(255, 255, 255));
        btnRegister.setText("Register");
        btnRegister.setActionCommand("Registrar");
        btnRegister.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnRegisterMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnRegisterMouseEntered(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Não tem uma conta?");

        facebookImage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                facebookImageMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                facebookImageMouseEntered(evt);
            }
        });

        instagramImage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                instagramImageMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                instagramImageMouseEntered(evt);
            }
        });

        mailImage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mailImageMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                mailImageMouseEntered(evt);
            }
        });

        inputSenha.setForeground(new java.awt.Color(153, 153, 153));
        inputSenha.setText("********");
        inputSenha.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                inputSenhaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                inputSenhaFocusLost(evt);
            }
        });

        checkPass.setBackground(new java.awt.Color(102, 0, 102));
        checkPass.setForeground(new java.awt.Color(255, 255, 255));
        checkPass.setText("Mostrar senha");
        checkPass.setContentAreaFilled(false);
        checkPass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkPassActionPerformed(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/user.png"))); // NOI18N

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/senha.png"))); // NOI18N

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/logoTrackio.png"))); // NOI18N

        facebook.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/facebook.png"))); // NOI18N
        facebook.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                facebookMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                facebookMouseEntered(evt);
            }
        });

        instagram.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/iconInstagram.png"))); // NOI18N
        instagram.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                instagramMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                instagramMouseEntered(evt);
            }
        });

        mail.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/iconEmail.png"))); // NOI18N
        mail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mailMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                mailMouseEntered(evt);
            }
        });

        javax.swing.GroupLayout kGradientPanel1Layout = new javax.swing.GroupLayout(kGradientPanel1);
        kGradientPanel1.setLayout(kGradientPanel1Layout);
        kGradientPanel1Layout.setHorizontalGroup(
            kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kGradientPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(facebookImage)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(kGradientPanel1Layout.createSequentialGroup()
                        .addComponent(instagramImage)
                        .addGap(10, 10, 10)
                        .addComponent(mailImage)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(kGradientPanel1Layout.createSequentialGroup()
                        .addComponent(facebook)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(mail)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(instagram)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2)))
                .addGap(18, 18, 18)
                .addComponent(btnRegister, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
            .addGroup(kGradientPanel1Layout.createSequentialGroup()
                .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(kGradientPanel1Layout.createSequentialGroup()
                        .addGap(227, 227, 227)
                        .addComponent(jLabel1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kGradientPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel5)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(kGradientPanel1Layout.createSequentialGroup()
                        .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(kGradientPanel1Layout.createSequentialGroup()
                                .addGap(248, 248, 248)
                                .addComponent(btnEntrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(inputUsuario)
                            .addComponent(lbUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(70, 70, 70)
                        .addComponent(jLabel4)
                        .addGap(78, 78, 78))
                    .addGroup(kGradientPanel1Layout.createSequentialGroup()
                        .addComponent(checkPass, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
                        .addGap(413, 413, 413))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kGradientPanel1Layout.createSequentialGroup()
                        .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(kGradientPanel1Layout.createSequentialGroup()
                                .addGap(71, 71, 71)
                                .addComponent(logo)
                                .addGap(278, 278, 278))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, kGradientPanel1Layout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(47, 47, 47)))
                        .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kGradientPanel1Layout.createSequentialGroup()
                                .addGap(88, 88, 88)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(39, 39, 39))
                            .addComponent(jLabel6)))
                    .addGroup(kGradientPanel1Layout.createSequentialGroup()
                        .addComponent(inputSenha)
                        .addGap(148, 148, 148)))
                .addGap(45, 45, 45))
        );
        kGradientPanel1Layout.setVerticalGroup(
            kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kGradientPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(logo)
                .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(kGradientPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21))
                    .addGroup(kGradientPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(lbUsuario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(inputUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1))
                .addGap(16, 16, 16)
                .addComponent(lbSenha)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(inputSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(checkPass)
                .addGap(2, 2, 2)
                .addComponent(btnEntrar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(98, 98, 98)
                .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(facebookImage)
                    .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnRegister, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2))
                    .addGroup(kGradientPanel1Layout.createSequentialGroup()
                        .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(instagramImage, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(mailImage, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(facebook))
                    .addComponent(mail)
                    .addComponent(instagram))
                .addGap(24, 24, 24))
        );

        jLabel1.getAccessibleContext().setAccessibleName("usuario");
        jLabel5.getAccessibleContext().setAccessibleName("passwd");
        jLabel7.getAccessibleContext().setAccessibleName("trackioLogo");
        facebook.getAccessibleContext().setAccessibleName("facebookImage");
        instagram.getAccessibleContext().setAccessibleName("instagramImage");
        mail.getAccessibleContext().setAccessibleName("mailImage");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(kGradientPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(kGradientPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEntrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEntrarActionPerformed
        logar();
    }//GEN-LAST:event_btnEntrarActionPerformed

    private void inputUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputUsuarioActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_inputUsuarioActionPerformed

    private void btnEntrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEntrarMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEntrarMouseClicked

    private void inputUsuarioMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inputUsuarioMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_inputUsuarioMouseReleased

    private void inputUsuarioFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputUsuarioFocusGained
        if (inputUsuario.getText().equals("meuEmail@gmail.com")) {
            inputUsuario.setText("");
            inputUsuario.setForeground(new Color(0, 0, 0));
        }
    }//GEN-LAST:event_inputUsuarioFocusGained

    private void inputUsuarioFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputUsuarioFocusLost
        if (inputUsuario.getText().equals("")) {
            inputUsuario.setText("meuEmail@gmail.com");
            inputUsuario.setForeground(new Color(153, 153, 153));
        }
    }//GEN-LAST:event_inputUsuarioFocusLost

    private void inputSenhaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputSenhaFocusGained
        if (inputSenha.getText().equals("********")) {
            inputSenha.setText("");
            inputSenha.setForeground(new Color(0, 0, 0));
        }
    }//GEN-LAST:event_inputSenhaFocusGained

    private void inputSenhaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputSenhaFocusLost
        if (inputSenha.getText().equals("")) {
            inputSenha.setText("********");
            inputSenha.setForeground(new Color(153, 153, 153));
        }
    }//GEN-LAST:event_inputSenhaFocusLost

    private void checkPassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkPassActionPerformed
        if (checkPass.isSelected()) {
            inputSenha.setEchoChar((char) 0);
        } else {
            inputSenha.setEchoChar('*');
        }
    }//GEN-LAST:event_checkPassActionPerformed

    private void btnEntrarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEntrarMouseEntered
        btnEntrar.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btnEntrarMouseEntered

    private void btnRegisterMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegisterMouseEntered
        btnRegister.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btnRegisterMouseEntered

    private void facebookImageMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_facebookImageMouseEntered
        facebookImage.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_facebookImageMouseEntered

    private void instagramImageMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_instagramImageMouseEntered
        instagramImage.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_instagramImageMouseEntered

    private void mailImageMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mailImageMouseEntered
        mailImage.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_mailImageMouseEntered

    private void facebookImageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_facebookImageMouseClicked
        try {
            Desktop.getDesktop().browse(new URI("http://www.google.com"));
        } catch (IOException | URISyntaxException e1) {
            e1.printStackTrace();
        }
    }//GEN-LAST:event_facebookImageMouseClicked

    private void instagramImageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_instagramImageMouseClicked
        try {
            Desktop.getDesktop().browse(new URI("http://www.google.com"));
        } catch (IOException | URISyntaxException e1) {
            e1.printStackTrace();
        }
    }//GEN-LAST:event_instagramImageMouseClicked

    private void mailImageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mailImageMouseClicked
        try {
            Desktop.getDesktop().browse(new URI("http://trackio.consultoria@gmail.com"));
        } catch (IOException | URISyntaxException e1) {
            e1.printStackTrace();
        }
    }//GEN-LAST:event_mailImageMouseClicked

    private void btnRegisterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegisterMouseClicked
        try {
            Desktop.getDesktop().browse(new URI("http://www.google.com"));
        } catch (IOException | URISyntaxException e1) {
            e1.printStackTrace();
        }
    }//GEN-LAST:event_btnRegisterMouseClicked

    private void facebookMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_facebookMouseClicked
        try {
            Desktop.getDesktop().browse(new URI("https://www.facebook.com"));
        } catch (IOException | URISyntaxException e1) {
            e1.printStackTrace();
        }
    }//GEN-LAST:event_facebookMouseClicked

    private void mailMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mailMouseClicked
        try {
            Desktop.getDesktop().browse(new URI("mailto:trackio.consultoria@gmail.com?subject=Opa%20again"));
        } catch (IOException | URISyntaxException e1) {
            e1.printStackTrace();
        }
    }//GEN-LAST:event_mailMouseClicked

    private void instagramMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_instagramMouseClicked
        try {
            Desktop.getDesktop().browse(new URI("https://www.instagram.com"));
        } catch (IOException | URISyntaxException e1) {
            e1.printStackTrace();
        }
    }//GEN-LAST:event_instagramMouseClicked

    private void facebookMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_facebookMouseEntered
        facebook.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_facebookMouseEntered

    private void mailMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mailMouseEntered
        mail.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_mailMouseEntered

    private void instagramMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_instagramMouseEntered
        instagram.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_instagramMouseEntered

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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEntrar;
    private javax.swing.JButton btnRegister;
    private javax.swing.JCheckBox checkPass;
    private javax.swing.JLabel facebook;
    private javax.swing.JLabel facebookImage;
    private javax.swing.JPasswordField inputSenha;
    private javax.swing.JTextField inputUsuario;
    private javax.swing.JLabel instagram;
    private javax.swing.JLabel instagramImage;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JToolBar jToolBar1;
    private keeptoo.KGradientPanel kGradientPanel1;
    private javax.swing.JLabel lbSenha;
    private javax.swing.JLabel lbUsuario;
    private javax.swing.JLabel logo;
    private javax.swing.JLabel mail;
    private javax.swing.JLabel mailImage;
    // End of variables declaration//GEN-END:variables
      private void logar() {

        //data/hora atual
        LocalDateTime agora = LocalDateTime.now();

        // formatar a data
        DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("uuuu/MM/dd");
        String dataFormatada = formatterData.format(agora);

        // formatar a hora
        DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("HH:mm:ss");
        String horaFormatada = formatterHora.format(agora);

        String nomeUsuario = inputUsuario.getText();
        String senhaUsuario = inputSenha.getText();

        LogarUsuario logarusuario = new LogarUsuario(nomeUsuario, senhaUsuario);

        JdbcTemplate conexao = new Database().getConnection();

        String selectSql = "select * from [dbo].[Funcionario] where nomeFuncionario = '"
                + logarusuario.getNomeUsuario()
                + "' and senhaFuncionario = '"
                + logarusuario.getSenhaUsuario()
                + "'";

        List retorno = conexao.queryForList(selectSql);
        if (retorno.size() >= 1) {
            ConexaoUsuario conexaousuario = new ConexaoUsuario();
            conexaousuario.getSlackBd();
            conexaousuario.guardarDados();

            Principal principal = new Principal();
            principal.setVisible(true);
            dispose();

            logGenerator.LogInfo.generateLogInfo("Info: Tentativa de acesso autorizada -  API Trackio | " + " Username: " + logarusuario.getNomeUsuario()
                    + " | Data:" + dataFormatada + " Hora:" + horaFormatada + "\n");

        } else {

            JOptionPane.showMessageDialog(null, "Usuário e/ou Senha errados");

            logGenerator.LogError.generateLogError("Error: Tentativa de acesso negada - API Trackio | " + " Username: " + logarusuario.getNomeUsuario()
                    + " | Data:" + dataFormatada + " Hora:" + horaFormatada + "\n");

        }
    }
}
