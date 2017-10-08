/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modulos;

import Configuracion.Conf;
import DataBase.Dao.DataBase;
import DataBase.Pojo.Producto;
import EstructuraRow.RowConsulta;
import ModeloTabla.ModelT;
import java.awt.Color;
import java.awt.Component;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import static javax.swing.SwingConstants.RIGHT;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import org.jfree.ui.ExtensionFileFilter;
import pdf.CSV;
import pdf.PDF;

/**
 *
 * @author Abina
 */
public class Reporte extends javax.swing.JPanel {
    private Conf conf;
    private ModelT ModeloTablaReporte;
    private ModelT ModeloTablaAlmacen;
    private ModelT ModeloTablaMedicamentoControlado;
    private ModelT ModeloTablaTop;
    JFileChooser jfcCSV=new JFileChooser();
    JFileChooser jfcPDF=new JFileChooser();
    private DataBase bd;
    private JFrame THIS;
    private float ganancia;
    private float total;
    /**
     * Creates new form Reporte
     */
    public Reporte(JFrame THIS) {
        initComponents();
        this.THIS=THIS;
        conf=Conf.getConf();
        ganancia=0;
        total=0;
        bd=DataBase.createDateBase();
        new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Reporte.class.getName()).log(Level.SEVERE, null, ex);
                }
                refrescarDatos();        
            }
            
        }.start();
        jfcCSV.setFileFilter(new ExtensionFileFilter("CSV", ".csv"));
        
        jfcPDF.setFileFilter(new ExtensionFileFilter("PDF", ".pdf"));
    }
    
    
    private void crearTablaMedicamentControlado(){
        Class classObejeto=java.lang.Object.class;
        Object titulo[]=new Object[]{"Cedula","Medico","Producto","Fecha"};
        Class tipoClases[]=new Class[titulo.length];
        for(int i=0;i<tipoClases.length;i++){
            tipoClases[i]=classObejeto;
        }
        
        ModeloTablaMedicamentoControlado=new ModelT(titulo,tipoClases);
        List<RowConsulta>  rr=new ArrayList<RowConsulta>();
        if(!ff.getText().isEmpty() || !fi.getText().isEmpty()){
            rr=bd.getMedicamentoControlado(nombre.getText(), fi.getText(), ff.getText());
            if(rr==null){
                rr=new ArrayList<RowConsulta>();
            }
        }
        int size=rr.size();
        for(int i=0;i<size;i++){
            RowConsulta dato=rr.get(i);
            ModeloTablaMedicamentoControlado.addRow(new Object[]{});
            ModeloTablaMedicamentoControlado.setValueAt(dato.getMedico().getCedula(), i, 0);
            ModeloTablaMedicamentoControlado.setValueAt(dato.getMedico().getNombre(), i, 1);
            ModeloTablaMedicamentoControlado.setValueAt(dato.getProducto().getNombre(), i, 2);
            
            ModeloTablaMedicamentoControlado.setValueAt(dato.getFecha(), i, 3);
            
        }
        tamablaMedicamentoControlado.setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component component=super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); 
                this.setHorizontalAlignment(RIGHT);
                
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
        tamablaMedicamentoControlado.setModel(ModeloTablaMedicamentoControlado);
    }
    
    private void crearTablaVentas(){
        Class classObejeto=java.lang.Object.class;
        Object titulo[]=new Object[]{"nombre","cantidad","fecha","precio","compra","invercion","total","Ganacia","Tipo de Pago"};
        Class tipoClases[]=new Class[9];
        for(int i=0;i<9;i++){
            tipoClases[i]=classObejeto;
        }
        
        ModeloTablaReporte=new ModelT(titulo,tipoClases);
        List<RowConsulta>  rr=new ArrayList<RowConsulta>();
        if(!ff.getText().isEmpty() || !fi.getText().isEmpty()){
            rr=bd.getReporte(nombre.getText(), fi.getText(), ff.getText());
            if(rr==null){
                rr=new ArrayList<RowConsulta>();
            }
        }
        int size=rr.size();
        ganancia=total=0;
        for(int i=0;i<size;i++){
            RowConsulta dato=rr.get(i);
            ModeloTablaReporte.addRow(new Object[]{});
            ModeloTablaReporte.setValueAt(dato.getProducto().getNombre(), i, 0);
            ModeloTablaReporte.setValueAt(dato.getItem().getCantidad(), i, 1);
            ModeloTablaReporte.setValueAt(dato.getVenta().getFecha(), i, 2);
            
            ModeloTablaReporte.setValueAt(conf.moneda(dato.getItem().getPrecioUnitario()), i, 3);
            ModeloTablaReporte.setValueAt(conf.moneda(dato.getItem().getCompraUnitario()), i, 4);
            ModeloTablaReporte.setValueAt(conf.moneda(dato.getInvertir()), i, 5);
            ModeloTablaReporte.setValueAt(conf.moneda(dato.getTotal()), i, 6);
            ModeloTablaReporte.setValueAt(conf.moneda(dato.getGanancia()), i, 7);
            ModeloTablaReporte.setValueAt((dato.getVenta().getFormaDePago().equalsIgnoreCase("<E>")?"Efectivo":dato.getVenta().getFormaDePago()), i, 8);
            total+=dato.getTotal();
            ganancia+=dato.getGanancia();
        }
        jLabelTotal.setText(conf.moneda(total));
        jLabelGanancia.setText(conf.moneda(ganancia));
        tabla.setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component component=super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); 
                this.setHorizontalAlignment(RIGHT);
                
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
        tabla.setModel(ModeloTablaReporte);
        tabla.getColumnModel().getColumn(0).setMinWidth(150);
        tabla.getColumnModel().getColumn(1).setMaxWidth(55);
        tabla.getColumnModel().getColumn(2).setMinWidth(110);
        tabla.getColumnModel().getColumn(2).setMaxWidth(110);
    }
    private void crearTablaTop(){
        Class classObejeto=java.lang.Object.class;
        Object titulo[]=new Object[]{"nombre","Vendido","Ganacia"};
        Class tipoClases[]=new Class[3];
        for(int i=0;i<3;i++){
            tipoClases[i]=classObejeto;
        }
        
        ModeloTablaTop=new ModelT(titulo,tipoClases);
        List<RowConsulta>  rr=new ArrayList<RowConsulta>();
        if(!ff.getText().isEmpty() || !fi.getText().isEmpty()){
            rr=bd.getTopVenta( fi.getText(), ff.getText());
            if(rr==null){
                rr=new ArrayList<RowConsulta>();
            }
        }
        int size=rr.size();
        for(int i=0;i<size;i++){
            RowConsulta dato=rr.get(i);
            ModeloTablaTop.addRow(new Object[]{});
            ModeloTablaTop.setValueAt(dato.getProducto().getNombre(), i, 0);
            ModeloTablaTop.setValueAt((int)dato.getItem().getCantidad(), i, 1);
            ModeloTablaTop.setValueAt(conf.moneda( dato.getGanancia()), i, 2);
            
            
        }
        jTableTop.setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component component=super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); 
                this.setHorizontalAlignment(RIGHT);
                
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
        jTableTop.setModel(ModeloTablaTop);
    }
    private void crearTablaAlmacen(){
        Class classObejeto=java.lang.Object.class;
        Object titulo[]=new Object[]{"Nombre","Precio","Ubicacion","Caducacion","Existencia"};
        Class tipoClases[]=new Class[5];
        for(int i=0;i<5;i++){
            tipoClases[i]=classObejeto;
        }
        
        ModeloTablaAlmacen=new ModelT(titulo,tipoClases);
        List<RowConsulta>  rr=new ArrayList<RowConsulta>();
        rr=bd.getInvetario();
        Integer size=rr.size();
        if(size==null)return;
        
        for(int i=0;i<size;i++){
            RowConsulta dato=rr.get(i);
            ModeloTablaAlmacen.addRow(new Object[]{});
            ModeloTablaAlmacen.setValueAt(dato.getProducto().getNombre(), i, 0);
            ModeloTablaAlmacen.setValueAt(dato.getProducto().getPrecioVenta(), i, 1);
            ModeloTablaAlmacen.setValueAt(dato.getLote().getUbicacion(), i, 2);
            
            ModeloTablaAlmacen.setValueAt(dato.getLote().getCaducacion(), i, 3);
            ModeloTablaAlmacen.setValueAt((dato.getLote().getExistencia()>1000000000)?"+1G":(int)dato.getLote().getExistencia(), i, 4);
            
        }
        tablaAlmacen.setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component component=super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); 
                this.setHorizontalAlignment(RIGHT);
                
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
        tablaAlmacen.setModel(ModeloTablaAlmacen);
        tablaAlmacen.getColumnModel().getColumn(0).setMinWidth(100);
    }
    private void exportarPDF(DefaultTableModel defaultTableModel, String titulo,File f,float colsize[]){
        PDF pdf=new PDF(titulo);
        pdf.report(defaultTableModel, f,80,9,colsize);
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
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        fi = new javax.swing.JFormattedTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        nombre = new javax.swing.JTextField();
        ff = new javax.swing.JFormattedTextField();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jPanel13 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabelTotal = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabelGanancia = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTableTop = new javax.swing.JTable();
        jPanel12 = new javax.swing.JPanel();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tamablaMedicamentoControlado = new javax.swing.JTable();
        jPanel11 = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tablaAlmacen = new javax.swing.JTable();
        jPanel10 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();

        tabla.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tabla);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/boton/excel16.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jButton1)
                .addGap(155, 155, 155))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        try {
            fi.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####-##-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Fecha Inicio");

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Fecha Fin");

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Producto");

        nombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombreActionPerformed(evt);
            }
        });

        try {
            ff.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####-##-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        ff.setToolTipText("");

        jButton13.setText("...");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jButton14.setText("...");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel1.setText("Consultas Ventas");

        jButton3.setText("Refrescar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(ff, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton14))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(fi, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jButton13))
                                    .addComponent(nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 27, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(86, 86, 86)
                .addComponent(jButton3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(14, 14, 14)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(fi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(ff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton14))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Consulta Ventana", jPanel2);

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLabel2.setText("Resumen:");

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Total:");

        jLabelTotal.setText("$0.00 MXN");

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Ganancia");

        jLabelGanancia.setText("$0.00 MXN");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelGanancia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabelTotal))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabelGanancia))
                .addContainerGap(95, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Resumen", jPanel13);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 822, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33))
        );

        jPanel1.add(jPanel4);

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Resumen de venta", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        jTableTop.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane4.setViewportView(jTableTop);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 12, Short.MAX_VALUE))
        );

        jPanel7.add(jPanel6);

        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/boton/excel16.png"))); // NOI18N
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/boton/pdf16.png"))); // NOI18N
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton9)
                .addContainerGap(269, Short.MAX_VALUE))
        );

        jPanel7.add(jPanel12);

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Control de producto", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        tamablaMedicamentoControlado.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(tamablaMedicamentoControlado);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 12, Short.MAX_VALUE))
        );

        jPanel7.add(jPanel8);

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/boton/excel16.png"))); // NOI18N
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/boton/pdf16.png"))); // NOI18N
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton7)
                .addContainerGap(269, Short.MAX_VALUE))
        );

        jPanel7.add(jPanel11);

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Inventario", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        tablaAlmacen.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane5.setViewportView(tablaAlmacen);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 12, Short.MAX_VALUE))
        );

        jPanel7.add(jPanel9);

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/boton/excel16.png"))); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/boton/pdf16.png"))); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5)
                .addContainerGap(269, Short.MAX_VALUE))
        );

        jPanel7.add(jPanel10);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    public void refrescarDatos(){
        crearTablaVentas();
        crearTablaAlmacen();
        crearTablaMedicamentControlado();
        crearTablaTop();
    }
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        refrescarDatos();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        Calendario calendario=null;
        if(fi.getText().equalsIgnoreCase("    -  -  ")){
            calendario=new Calendario(THIS);
        }else{
            try {
                int anio=Integer.parseInt(fi.getText().split("-")[0]);
                int mes=Integer.parseInt(fi.getText().split("-")[1]);
                calendario=new Calendario(THIS, anio, mes);
            } catch (Exception e) {
                calendario=new Calendario(THIS);
            }
        }
        if(calendario.getFecha()!=null){
            fi.setText(calendario.getFecha()+"");
        }
        refrescarDatos();
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        Calendario calendario=null;
        if(ff.getText().equalsIgnoreCase("    -  -  ")){
            calendario=new Calendario(THIS);
        }else{
            try {
                int anio=Integer.parseInt(ff.getText().split("-")[0]);
                int mes=Integer.parseInt(ff.getText().split("-")[1]);
                calendario=new Calendario(THIS, anio, mes);
            } catch (Exception e) {
                calendario=new Calendario(THIS);
            }
        }
        if(calendario.getFecha()!=null){
            ff.setText(calendario.getFecha()+"");
        }
        refrescarDatos();
    }//GEN-LAST:event_jButton14ActionPerformed

    private void nombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombreActionPerformed
        refrescarDatos();
    }//GEN-LAST:event_nombreActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        
        if(jfcPDF.showSaveDialog(this)==JFileChooser.APPROVE_OPTION){
            exportarPDF(ModeloTablaAlmacen, "Almacen", jfcPDF.getSelectedFile(),new float[]{2.0f,0.7f,1.8f,0.9f,0.8f});
        }
        
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        
        if(jfcPDF.showSaveDialog(this)==JFileChooser.APPROVE_OPTION){
            exportarPDF(ModeloTablaMedicamentoControlado, "Producto controlado", jfcPDF.getSelectedFile(),new float[]{0.7f,2.5f,2.0f,1.3f});
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        
        if(jfcPDF.showSaveDialog(this)==JFileChooser.APPROVE_OPTION){
            exportarPDF(ModeloTablaTop, "Resumen de ventas", jfcPDF.getSelectedFile(),new float[]{3f,1.0f,1.0f});
        }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        
        if(jfcCSV.showSaveDialog(this)==JFileChooser.APPROVE_OPTION){
            CSV csv=new CSV();
            csv.report(ModeloTablaAlmacen, jfcCSV.getSelectedFile());
        }
                
                
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        
        if(jfcCSV.showSaveDialog(this)==JFileChooser.APPROVE_OPTION){
            CSV csv=new CSV();
            csv.report(ModeloTablaMedicamentoControlado, jfcCSV.getSelectedFile());
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        
        if(jfcCSV.showSaveDialog(this)==JFileChooser.APPROVE_OPTION){
            CSV csv=new CSV();
            csv.report(ModeloTablaTop, jfcCSV.getSelectedFile());
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        if(jfcCSV.showSaveDialog(this)==JFileChooser.APPROVE_OPTION){
            CSV csv=new CSV();
            csv.report(ModeloTablaReporte, jfcCSV.getSelectedFile());
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFormattedTextField ff;
    private javax.swing.JFormattedTextField fi;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabelGanancia;
    private javax.swing.JLabel jLabelTotal;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTableTop;
    private javax.swing.JTextField nombre;
    private javax.swing.JTable tabla;
    private javax.swing.JTable tablaAlmacen;
    private javax.swing.JTable tamablaMedicamentoControlado;
    // End of variables declaration//GEN-END:variables
}
