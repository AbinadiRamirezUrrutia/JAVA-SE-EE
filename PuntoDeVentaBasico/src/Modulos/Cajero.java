/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modulos;

import Configuracion.Conf;
import DataBase.Dao.DataBase;
import DataBase.Pojo.Item;
import DataBase.Pojo.Medico;
import DataBase.Pojo.Producto;
import DataBase.Pojo.Trabajador;
import ModeloTabla.ModelT;
import ModuloVentana.Pago.Pago;
import ModuloVentana.SearchProducto;
import Modulos.InputV.InputDoctor;
import Modulos.InputV.inputCantidad;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javafx.scene.control.SelectionMode;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SingleSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class Cajero extends javax.swing.JPanel {
    private JFrame THIS;
    private DataBase db;
    private List<Producto> productos;
    private List<Item> items;
    private float total;
    private Medico medico=null;
    private Trabajador trabajador;
    private Conf conf;
    Color color;
    /**
     * Creates new form Cajero
     */
    public Cajero(JFrame padre, Trabajador trabajador) {
        initComponents();
        conf=Conf.getConf();
        db=DataBase.createDateBase();
        productos=new ArrayList<Producto>();
        items=new ArrayList<Item>();
        total=0;
        crearTabla();
        THIS=padre;
        this.trabajador=trabajador;
        color=msjAlert.getBackground();
        jTable1.getTableHeader().setReorderingAllowed(false) ;
    }
    DefaultTableModel dtm;
    private void crearTabla(){        
        
        total=0;
        dtm=new ModelT(new String []{"ID","Nombre","Precio U","Cantidad","Precio G"},
                new Class [] { java.lang.String.class, java.lang.String.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class
            });
        int j=0;
        Producto aux=null;
        total=0;
        for(Item i:items){
            dtm.addRow(new Vector());
            for(Producto p:productos){
                if(p.getId()==i.getProductoId()){
                    aux=p;
                    break;
                }
            }
            dtm.setValueAt(aux.getId(), j, 0);
            dtm.setValueAt(aux.getNombre(), j, 1);
            dtm.setValueAt(aux.getPrecioVenta(), j, 2);
            dtm.setValueAt((int)i.getCantidad(), j, 3);
            dtm.setValueAt(i.getCantidad()*aux.getPrecioVenta(), j++, 4);
            total+=i.getCantidad()*aux.getPrecioVenta();
            
        }
        DecimalFormat d=new DecimalFormat("###,###.##");
        totalText.setText(conf.moneda(total));
        jTable1.setModel(dtm);
        
        jTable1.setFont(new Font("Arial",Font.PLAIN,25));
        
        
        
        
        
        jTable1.setRowHeight(35);
        jTable1.getColumnModel().getColumn(0).setMaxWidth(100);
        jTable1.getColumnModel().getColumn(1).setMinWidth(300);
        
        for(int i=2;i<5;i++){
            jTable1.getColumnModel().getColumn(i).setMaxWidth(100);
            jTable1.getColumnModel().getColumn(i).setMinWidth(100);
        }
        
        DefaultTableCellRenderer m2 = new DefaultTableCellRenderer();
        DefaultTableCellRenderer m3 = new DefaultTableCellRenderer();
        
        
        jTable1.setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component component=super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); 
                if(row%2==0){
                    component.setBackground(Color.decode("#f9f9f9"));
                    
                }else{
                    component.setBackground(Color.WHITE);
                }
                if(isSelected){
                    component.setBackground(Color.decode("#0048ff"));
                }
                
                return component;
            }
        });
        jTable1.setDefaultRenderer(Float.class, new DefaultTableCellRenderer(){
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component component=super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); 
                this.setHorizontalAlignment(RIGHT);
                if(row%2==0){
                    component.setBackground(Color.decode("#f9f9f9"));
                }else{
                    component.setBackground(Color.WHITE);
                }
                if(column==2){
                    component.setBackground(Color.decode("#e1ffd8"));
                }
                if(isSelected){
                    component.setBackground(Color.decode("#0048ff"));
                }
                return component;
            }
            
        });
       
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        cb = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        totalText = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        msjAlert = new javax.swing.JPanel();
        cedulaText = new javax.swing.JLabel();
        medicoTex = new javax.swing.JLabel();

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel1.setText("Codigo de barra");

        cb.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        cb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTable1KeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        totalText.setBackground(new java.awt.Color(255, 255, 255));
        totalText.setFont(new java.awt.Font("Dialog", 0, 48)); // NOI18N
        totalText.setForeground(new java.awt.Color(0, 51, 0));
        totalText.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        totalText.setText("$9999.99 MXN ");
        totalText.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        jButton1.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/boton/US-dollar32.png"))); // NOI18N
        jButton1.setMnemonic('p');
        jButton1.setText("Pagar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/boton/Search.png"))); // NOI18N
        jButton2.setText("Buscar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/boton/Trash-can32.png"))); // NOI18N
        jButton3.setText("Borrar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        cedulaText.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        cedulaText.setText(" ");

        medicoTex.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        medicoTex.setText(" ");

        javax.swing.GroupLayout msjAlertLayout = new javax.swing.GroupLayout(msjAlert);
        msjAlert.setLayout(msjAlertLayout);
        msjAlertLayout.setHorizontalGroup(
            msjAlertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(msjAlertLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(msjAlertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cedulaText, javax.swing.GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE)
                    .addComponent(medicoTex, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        msjAlertLayout.setVerticalGroup(
            msjAlertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(msjAlertLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cedulaText)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(medicoTex)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cb))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(totalText, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE)
                                .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(msjAlert, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 413, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(totalText, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(msjAlert, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void agregarACarrito(Producto p){
        if(p!=null){
            if(!productos.contains(p)){
                int op=0;
                if(p.isReceta()){
                    op=JOptionPane.showConfirmDialog(THIS,p.getNombre().toUpperCase()+" requiere receta medica, ¿Agregar?","",JOptionPane.YES_NO_OPTION);
                    
                    if(medico==null && op==0){
                        InputDoctor iMedico=new InputDoctor(THIS);
                        medico=iMedico.getMedico();
                        if(medico==null){
                            JOptionPane.showMessageDialog(this, "No se agrego a la lista");
                            return;
                        }
                        msjAlert.setBackground(Color.decode("#fff138"));
                        cedulaText.setText("Cedula: "+medico.getCedula());
                        medicoTex.setText("Nombre: "+medico.getNombre());
                        
                    }
                }
                if(op==0){
                    productos.add(p);
                    Item i=new Item();
                    i.setPrecioUnitario(p.getPrecioVenta());
                    i.setCompraUnitario(p.getPrecioCompra());
                    i.setCantidad(1);
                    i.setProductoId(p.getId());
                    items.add(i);
                }
                
            }else{
                for(Item it:items){
                    if(it.getProductoId()==p.getId()){
                        it.setCantidad(it.getCantidad()+1);
                        break;
                    }
                }
            }
            cb.setText("");
            crearTabla();
        }
    }
    private void cbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbActionPerformed
       
        Producto p=db.getProductoCB(cb.getText());
        agregarACarrito(p);
    }//GEN-LAST:event_cbActionPerformed

    private void jTable1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyPressed
        int row=0;
        int idAux=0;
        if(evt.getKeyChar()=='c' || evt.getKeyChar()=='C' || evt.getKeyChar()=='+' || evt.getKeyChar()=='-'){
            row=jTable1.getSelectedRow();
            idAux=(int)jTable1.getValueAt(row, 0);
        }
        if(evt.getKeyChar()=='c' || evt.getKeyChar()=='C'){
            
            inputCantidad iCantidad= new inputCantidad(THIS, (int)jTable1.getValueAt(row, 3));
            iCantidad.setVisible(true);
            for(Item item:items){
                if(item.getProductoId()==idAux){
                    if(iCantidad.getTotal()<=0){
                        items.remove(item);
                    }else{
                        item.setCantidad(iCantidad.getTotal());
                    }
                    
                    break;
                }
            }
            crearTabla();
        }else if (evt.getKeyChar()=='+' ) {
            
            for(Item item:items){
                if(item.getProductoId()==idAux){
                    item.setCantidad(item.getCantidad()+1);
                    break;
                }
            }
            
            crearTabla();
            jTable1.setRowSelectionInterval(row, row);
        }else if (evt.getKeyChar()=='-' ) {
            for(Item i:items){
                if(i.getProductoId()==idAux){
                    if(i.getCantidad()==1){
                        
                        for(Producto p:productos){
                            if(p.getId()==idAux){
                                productos.remove(p);
                            }
                        }
                        items.remove(i);
                        crearTabla();
                        
                    }else{
                        i.setCantidad(i.getCantidad()-1);
                        crearTabla();
                        jTable1.setRowSelectionInterval(row, row);
                        
                    }
                    
                }
            }
            
        }
        
    }//GEN-LAST:event_jTable1KeyPressed

    private void reset(){
        productos=new ArrayList<Producto>();
        items=new ArrayList<Item>();
        medico=null;
        crearTabla();
        msjAlert.setBackground(color);
        cedulaText.setText(" ");
        medicoTex.setText(" ");
        cb.requestFocus();
    }
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        
        reset();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        SearchProducto search=new SearchProducto(THIS);
        if(search.getProducto()==null)return;
        agregarACarrito(search.getProducto());
        inputCantidad iCantidad= new inputCantidad(THIS,0);
        iCantidad.setVisible(true);
        for(Item item:items){
            if(item.getProductoId()==search.getProducto().getId()){
                if(iCantidad.getTotal()<=0){
                    items.remove(item);
                }else{
                    item.setCantidad(iCantidad.getTotal());
                }
                break;
            }
        }
        crearTabla();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(total>0){
            
            Pago pago=new Pago(THIS, trabajador,(medico!=null)?medico.getCedula():null,items,total);
            if(pago.isPagado()){
                reset();
            }
        }
        
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JTextField cb;
    private javax.swing.JLabel cedulaText;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel medicoTex;
    private javax.swing.JPanel msjAlert;
    private javax.swing.JLabel totalText;
    // End of variables declaration//GEN-END:variables
}

