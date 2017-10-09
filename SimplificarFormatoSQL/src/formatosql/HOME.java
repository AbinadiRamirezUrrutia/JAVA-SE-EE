//Este programa permite simplificar el código que nos proporciona Workbeanch
package formatosql;

import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;
import java.awt.Color;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

/**
 *
 * @author Ramirez Urrutia Angel Abinadi <abinanye@hotmail.com>
 */
public class HOME extends javax.swing.JFrame {

    public HOME() {
        initComponents();
        JFileChooser jfc=new JFileChooser();
        this.setLocationRelativeTo(null);
    }
    
    
    private  DefaultStyledDocument colorTexto(String str) {
        try {
            
            StyleContext styleContext = new StyleContext();

            DefaultStyledDocument styledDocument = new DefaultStyledDocument(styleContext);
            styledDocument.insertString(0, str, null);

            // Permite dar estilo al texto a mostrar.
            Style negro = styleContext.addStyle("ConstantWidth", null);
            StyleConstants.setForeground(negro, Color.BLACK);
            
            Style violeta = styleContext.addStyle("ConstantWidth", null);
            StyleConstants.setForeground(violeta, Color.decode("#380054"));
            
            Style rojo = styleContext.addStyle("ConstantWidth", null);
            StyleConstants.setForeground(rojo, Color.red);

            Style verde = styleContext.addStyle("ConstantWidth", null);
            StyleConstants.setForeground(verde, Color.decode("#004008"));

            Style azul = styleContext.addStyle("ConstantWidth", null);
            StyleConstants.setForeground(azul, Color.blue);
            int inicio,fin,i=0;
            while(i<str.length() && true){

                for(;i<str.length() && (str.charAt(i)==' ' || str.charAt(i)=='\t' || str.charAt(i)=='\n' || str.charAt(i)=='\r'|| str.charAt(i)=='(' || str.charAt(i)==',');i++);

                inicio=i;
                for(;i<str.length() && !(str.charAt(i)==' ' || str.charAt(i)=='\t' || str.charAt(i)=='\n' || str.charAt(i)=='\r' || str.charAt(i)=='(' || str.charAt(i)==',');i++);
                fin=i;

                
                String aux=str.substring(inicio,fin);
                if(aux.equalsIgnoreCase("CREATE") ||
                        aux.equals("SCHEMA")||
                        aux.equalsIgnoreCase("IF")||
                        aux.equalsIgnoreCase("NOT")||
                        aux.equalsIgnoreCase("EXISTS")||
                        aux.equalsIgnoreCase("DEFAULT")||
                        aux.equalsIgnoreCase("CHARACTER")||
                        aux.equalsIgnoreCase("SET")||
                        aux.equalsIgnoreCase("USE")||
                        aux.equals("TABLE")||
                        aux.equalsIgnoreCase("NULL")
                        ){
                    styledDocument.setCharacterAttributes(inicio,fin, rojo, true);
                }else if(aux.equalsIgnoreCase("INT") ||
                        aux.equals("FLOAT")||
                        aux.equalsIgnoreCase("BOOLEAN")||
                        aux.equalsIgnoreCase("TinyInt")||
                        aux.equalsIgnoreCase("Bit")||
                        aux.equalsIgnoreCase("Bool")||
                        aux.equalsIgnoreCase("BigInt")||
                        aux.equalsIgnoreCase("Double")||
                        aux.equalsIgnoreCase("Decimal")||
                        aux.equalsIgnoreCase("Dec")||
                        aux.equalsIgnoreCase("Numeric")||
                        aux.equalsIgnoreCase("varchar")||
                        aux.equals("Date")||
                        aux.equalsIgnoreCase("DateTime")||
                        aux.equalsIgnoreCase("char")||
                        aux.equalsIgnoreCase("Text")||
                        aux.equalsIgnoreCase("Enum")||
                        aux.equalsIgnoreCase("time")
                        ){
                    styledDocument.setCharacterAttributes(inicio,fin, azul, true);
                }else if(aux.equalsIgnoreCase("AUTO_INCREMENT") ||
                        aux.equals("PRIMARY")||
                        aux.equalsIgnoreCase("KEY")||
                        aux.equalsIgnoreCase("ENGINE")||
                        aux.equalsIgnoreCase("=")||
                        aux.equalsIgnoreCase("InnoDB;")||
                        aux.equalsIgnoreCase("UNIQUE")||
                        aux.equalsIgnoreCase("INDEX")
                        ){
                    styledDocument.setCharacterAttributes(inicio,fin, verde, true);
                }
                else{
                    if(aux.length()>1 && ((aux.charAt(0)=='`' &&  aux.charAt(aux.length()-1)=='`')||(aux.charAt(0)=='\'' && aux.charAt(aux.length()-1)=='\'') )){
                        styledDocument.setCharacterAttributes(inicio,fin, violeta, true);
                    }else{
                        styledDocument.setCharacterAttributes(inicio,fin, negro, true);
                    }
                    
                }
            }
            
            return styledDocument;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        tamnioFuente = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        textoPanel = new javax.swing.JTextPane();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Formato SQL");
        setBackground(new java.awt.Color(0, 0, 0));
        setMinimumSize(new java.awt.Dimension(800, 600));

        jButton1.setText("Buscar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Guardar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel1.setText("Tamaño");

        tamnioFuente.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tamnioFuente.setText("12");
        tamnioFuente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tamnioFuenteKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tamnioFuenteKeyTyped(evt);
            }
        });

        textoPanel.setForeground(new java.awt.Color(255, 255, 255));
        jScrollPane2.setViewportView(textoPanel);

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("www.abinadi.com.mx");

        jLabel4.setForeground(new java.awt.Color(51, 51, 51));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Ramirez Urrutia Angel Abinadi");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jScrollPane2)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addGap(31, 31, 31)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tamnioFuente, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 444, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton2)
                            .addComponent(jButton1)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(tamnioFuente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jLabel1)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    File file =new File("url.conf");
    
    private File seleccionarUltimaRuta(){
        File ultimaRuta=null;
        if(file.exists()){
            try {
                FileReader fr=new FileReader(file);
                BufferedReader br=new BufferedReader(fr);
                String url=br.readLine();
                if(url!=null && url.length()>2){
                    ultimaRuta=new File(url);
                }
                
                br.close();
                fr.close();
            } catch (Exception e) {
            }
        }
        return ultimaRuta;
    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        
        JFileChooser jfc=new JFileChooser(seleccionarUltimaRuta());
        if(jfc.showOpenDialog(jfc)==JFileChooser.APPROVE_OPTION){
            FormatoSQL fsql=new FormatoSQL();
            try {
                FileWriter fw=new FileWriter(file);
                fw.write(jfc.getSelectedFile().getParent());
                fw.close();
            } catch (Exception e) {
            }
            String text=fsql.formato(jfc.getSelectedFile());
            textoPanel.setDocument(colorTexto(text));
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       JFileChooser jfc=new JFileChooser(seleccionarUltimaRuta());
        if(jfc.showSaveDialog(jfc)==JFileChooser.APPROVE_OPTION){
            try {
                FileWriter fw=new FileWriter(jfc.getSelectedFile());
                fw.write(textoPanel.getText());
                fw.close();
            } catch (Exception e) {
            }
            JOptionPane.showMessageDialog(null, "Guardado");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void tamnioFuenteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tamnioFuenteKeyTyped
        char caracter = evt.getKeyChar();
        if(caracter<'0' || caracter > '9' || tamnioFuente.getText().length()>1) evt.consume();
    }//GEN-LAST:event_tamnioFuenteKeyTyped

    private void tamnioFuenteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tamnioFuenteKeyReleased
        int tamanio=Integer.parseInt(tamnioFuente.getText());
        if(tamanio<30){
            textoPanel.setFont(new Font(textoPanel.getFont().getName(), textoPanel.getFont().getStyle(), tamanio));
        }
    }//GEN-LAST:event_tamnioFuenteKeyReleased

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
                    javax.swing.UIManager.setLookAndFeel(new WindowsLookAndFeel());
                    break;
                }
            }
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HOME.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HOME().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField tamnioFuente;
    private javax.swing.JTextPane textoPanel;
    // End of variables declaration//GEN-END:variables
}
