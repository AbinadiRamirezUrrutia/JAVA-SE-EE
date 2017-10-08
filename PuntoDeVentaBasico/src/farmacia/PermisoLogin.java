/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmacia;

import DataBase.Dao.DataBase;
import DataBase.Pojo.Trabajador;
import javax.swing.JOptionPane;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class PermisoLogin extends javax.swing.JDialog {
    private DataBase db;
    public static final int NORMAL=1;
    public static final int SUPERUSUARIO=2;
    private Trabajador trabajador=null;
    
    private boolean acceso;
    
    private int tipo=0;
    /**
     * Creates new form Permiso
     */
    public PermisoLogin(javax.swing.JFrame  parent, boolean modal,String tema,String msj,int tipo) {
        super(parent, modal);
        initComponents();
        inicialisacion(tema, msj, tipo);
    }
    public PermisoLogin(javax.swing.JDialog parent, boolean modal,String tema,String msj,int tipo) {
        super(parent, modal);
        initComponents();
        inicialisacion(tema, msj, tipo);
    }
    
    
    private void inicialisacion(String tema,String msj,int tipo){
        this.setLocationRelativeTo(null);
        this.tipo=tipo;
        acceso=false;
        
        
        db=DataBase.createDateBase();
        if(db.testInternt("www.google.com")){
            if(!db.estaConectado() ){
                JOptionPane.showMessageDialog(this, "El sistema se encuentra desactivado.");
                this.dispose();
            }
        }else{
            JOptionPane.showMessageDialog(this, "No hay internet.");
            this.dispose();
        }
        this.msj.setText(msj);
        this.tema.setText(tema);
        enableCampos(true);
    }
    private void enableCampos(boolean boolen){
        usr.setEnabled(boolen);
        psw.setEnabled(boolen);
        btm.setEnabled(boolen);
    }
    
    public boolean permiso(){
        return acceso;
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        usr = new javax.swing.JTextField();
        tema = new javax.swing.JLabel();
        msj = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        psw = new javax.swing.JPasswordField();
        btm = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Usuario");

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Contraseña");

        usr.setToolTipText("Ingresa el nombre de usuario.");
        usr.setEnabled(false);

        tema.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        tema.setForeground(new java.awt.Color(0, 51, 102));
        tema.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        msj.setForeground(new java.awt.Color(204, 0, 0));
        msj.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/logo/usr1.png"))); // NOI18N

        psw.setEnabled(false);

        btm.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/boton/ok.png"))); // NOI18N
        btm.setText("Aceptar");
        btm.setEnabled(false);
        btm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(msj, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(psw))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(usr, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 34, Short.MAX_VALUE))
                    .addComponent(tema, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btm)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tema)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(3, 3, 3)
                .addComponent(msj, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(usr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(psw, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addComponent(btm, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmActionPerformed
        String pw="";
        for(int i=0;i<this.psw.getPassword().length;i++){
            pw+=psw.getPassword()[i];
        }

        enableCampos(false);
        switch(tipo){
            case SUPERUSUARIO:
            trabajador=db.getJefe(usr.getText().trim(), pw);
            if(trabajador!=null){
                acceso=true;
                this.dispose();
            }

            break;
        }
    }//GEN-LAST:event_btmActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btm;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel msj;
    private javax.swing.JPasswordField psw;
    private javax.swing.JLabel tema;
    private javax.swing.JTextField usr;
    // End of variables declaration//GEN-END:variables
}
