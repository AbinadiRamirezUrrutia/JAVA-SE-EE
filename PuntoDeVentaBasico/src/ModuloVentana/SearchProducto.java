/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuloVentana;

import DataBase.Dao.DataBase;
import DataBase.Pojo.Item;
import DataBase.Pojo.Lote;
import DataBase.Pojo.Producto;
import EstructuraRow.RowConsulta;
import EstructuraRow.RowSearchProductoVenta;
import ModeloTabla.ModelT;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class SearchProducto extends javax.swing.JDialog {
    private DataBase db;
    private Producto producto=null;
    /**
     * Creates new form SearchProducto
     */
    public SearchProducto(java.awt.Frame parent) {
        super(parent, true);
        initComponents();
        this.setLocationRelativeTo(parent);
        db=DataBase.createDateBase();
        crearTabla();
        jTextPaneInformacion.setText("Informacion");
        this.setVisible(true);
        
    }
    DefaultTableModel dtm;
    private void crearTabla(){        
        
       
        dtm=new ModelT(new String []{"ID","Nombre","Precio U","Existencia"},
                new Class [] { java.lang.String.class, java.lang.String.class, java.lang.Float.class, java.lang.Float.class
            });
        int j=0;
        Producto aux=null;
       
       
        tabla.setModel(dtm);
        
        tabla.setFont(new Font("Arial",Font.PLAIN,20));
        tabla.setRowHeight(30);
        tabla.getColumnModel().getColumn(0).setMaxWidth(100);
        tabla.getColumnModel().getColumn(1).setMinWidth(260);
        tabla.getColumnModel().getColumn(2).setMaxWidth(100);
        List<RowSearchProductoVenta> productos=null;
        if(!search.getText().isEmpty()){
            productos=db.getProductoAndExistenciaByNombre(search.getText());
            if(productos==null){
                productos=new ArrayList<RowSearchProductoVenta>();
            }
            
        }else{
            productos=new ArrayList<RowSearchProductoVenta>();
        }
        
        int i=0;
        for(RowSearchProductoVenta producto:productos){
            dtm.addRow(new Vector());
            dtm.setValueAt(producto.getId(),i , 0);
            dtm.setValueAt(producto.getNombre(),i , 1);
            dtm.setValueAt(producto.getPrecioVenta(),i, 2);
            dtm.setValueAt(producto.getExistencia(),i, 3);
            i++;
        }
        
        
        tabla.setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component component=super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); 
                if(row%2==0){
                    component.setBackground(Color.decode("#eaeaea"));
                }else{
                    component.setBackground(Color.WHITE);
                }
                if(isSelected){
                    component.setBackground(Color.decode("#0048ff"));
                }
                
                return component;
            }
            
            
        });
        tabla.setDefaultRenderer(Float.class, new DefaultTableCellRenderer(){
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component component=super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); 
                if(row%2==0){
                    component.setBackground(Color.decode("#eaeaea"));
                }else{
                    component.setBackground(Color.WHITE);
                }
                if(column==2){
                    component.setBackground(Color.decode("#ccffce"));
                }
                if(isSelected){
                    component.setBackground(Color.decode("#0048ff"));
                }
                
                return component;
            }
            
        });
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }
    private void descripcionProducto(){
        
        int id=0;
        try {
            id=Integer.parseInt(dtm.getValueAt(tabla.getSelectedRow(), 0).toString());
        } catch (Exception e) {
            return;
        }
        
        String text="";
        RowConsulta r=db.getDescripcionProducto(id);
        Producto p=r.getProducto();
        Lote l=r.getLote();
        if(r==null)return;
        text+="Nombre: "+p.getNombre();
        text+="\nReceta: "+((p.isReceta())?"Si":"No");
        text+="\nActivo: "+((!p.getActivo().isEmpty())?p.getActivo():"Ninguno");
        text+="\nUbicacion: "+l.getUbicacion();
        text+="\nDescripcion:\n "+p.getDescripcion();
        jTextPaneInformacion.setText(text);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        search = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextPaneInformacion = new javax.swing.JTextPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jLabel1.setText("Nombre");

        search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/boton/Search1.png"))); // NOI18N
        jButton1.setText("Buscar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(search)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Nombre", "Precio"
            }
        ));
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaMouseClicked(evt);
            }
        });
        tabla.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tablaKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tabla);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/boton/ok.png"))); // NOI18N
        jButton2.setText("Selecionar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jTextPaneInformacion.setEditable(false);
        jTextPaneInformacion.setOpaque(false);
        jScrollPane2.setViewportView(jTextPaneInformacion);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 468, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton2))
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        crearTabla();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchActionPerformed
        crearTabla();
    }//GEN-LAST:event_searchActionPerformed
    
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int row=tabla.getSelectedRow();
        try{
            int id=Integer.parseInt(tabla.getValueAt(row,0).toString());
            System.out.println(row);
            producto=db.getProductoById(id);
            this.dispose();
        }catch(Exception e){
            
        }
        
        
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked
        descripcionProducto();
    }//GEN-LAST:event_tablaMouseClicked

    private void tablaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaKeyReleased
        descripcionProducto();
    }//GEN-LAST:event_tablaKeyReleased

    public Producto getProducto() {
        return producto;
    }



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextPane jTextPaneInformacion;
    private javax.swing.JTextField search;
    private javax.swing.JTable tabla;
    // End of variables declaration//GEN-END:variables
}
