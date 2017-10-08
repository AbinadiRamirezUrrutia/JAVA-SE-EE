/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Configuracion;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/**
 *
 * @author Abina
 */
public class Conf {
    private static Conf conf=null;
    private final File file=new File("configuracion.xml");
    SAXReader sax;
    Document doc;
    Element root;
    XMLWriter write;
    private final String NAME="name";
    private final String ACTUALIZACION="actualizacion";
    private final String VERIFICAR="verificar";
    
    private final String PREFIJO="prefijo";
    private final String SUFIJO="sufijo";
    
    private final String PRODUCTO="producto";
    private final String VERIFICAR_EXISTENCIA="verificarExistencia";
    
    
    // Sistema
    private String nombrePropietario;
    // actualizaciones
    private boolean buscarActualizacion;
    // Producto
    private int verificarExistencia;
    private String prefijo ,sufijo ;
    
    private Conf() {

        try {
            sax=new SAXReader();
            doc=sax.read(file);
            root=doc.getRootElement();
            inicia();
        } catch (DocumentException ex) {
            Logger.getLogger(Conf.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static Conf getConf(){
        if(conf==null){
            conf=new Conf();
        }
        return conf;
    }
    
    
    public String moneda(float moneda){
        return prefijo+String.format("%.2f ", moneda)+sufijo;
    }
    public void inicia() throws DocumentException{
        
        
        // Sistema
        nombrePropietario=root.attributeValue(NAME);
        // Actualisaciones
        Element update=root.element(ACTUALIZACION);
        buscarActualizacion=update.element(VERIFICAR).getText().equalsIgnoreCase("1");
        // Producto
        Element producto=root.element(PRODUCTO);
        prefijo=producto.attribute(PREFIJO).getText();
        sufijo=producto.attribute(SUFIJO).getText();
        verificarExistencia=Integer.parseInt(producto.element(VERIFICAR_EXISTENCIA).getText());

    }

    private void guardar(){
        try {
            write=new XMLWriter(new FileWriter(file));
            write.write(doc);
            write.close();
        } catch (IOException ex) {
            Logger.getLogger(Conf.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public String getNombrePropietario() {
        return nombrePropietario;
    }

    public void setNombrePropietario(String nombrePropietario) {
        root.attribute(NAME).setText(nombrePropietario);
        this.nombrePropietario = nombrePropietario;
        guardar();
    }

    public boolean isBuscarActualizacion() {
        return buscarActualizacion;
    }

    public void setBuscarActualizacion(boolean buscarActualizacion) {
        
        root.element(ACTUALIZACION).element(VERIFICAR).setText(((buscarActualizacion)?1:0)+"");
        this.buscarActualizacion = buscarActualizacion;
        guardar();
    }

    public int getVerificarExistencia() {
        return verificarExistencia;
    }

    public void setVerificarExistencia(int verificarExistencia) {
        root.element(PRODUCTO).element(VERIFICAR_EXISTENCIA).setText(verificarExistencia+"");
        this.verificarExistencia = verificarExistencia;
        guardar();
    }

    public String getPrefijo() {
        return prefijo;
    }

    public void setPrefijo(String prefijo) {
        root.element(PRODUCTO).attribute(PREFIJO).setText(prefijo);
        this.prefijo = prefijo;
        guardar();
    }

    public String getSufijo() {
        return sufijo;
    }

    public void setSufijo(String sufijo) {
        root.element(PRODUCTO).attribute(SUFIJO).setText(sufijo);
        this.sufijo = sufijo;
        guardar();
    }
    
    
}
