/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmacia;

import DataBase.Pojo.Trabajador;
import Modulos.Caducado;
import Modulos.Home;
import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;
import java.awt.Image;
import java.io.File;
import java.io.FileWriter;
import javax.swing.ImageIcon;
import javax.swing.UIManager;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class Farmacia {

    private static File f=new File("start.dll");
    
    /**
     * @param args the command line arguments
     * @throws javax.swing.UnsupportedLookAndFeelException
     */
    static boolean esperando=true;
    public static void main(String[] args) throws Exception {

        if(f.exists()){
            return;
        }else{
            f.createNewFile();
        }
        UIManager.setLookAndFeel(new WindowsLookAndFeel());
        Trabajador tr=new Trabajador();
        Home h=new Home(tr, null);
        
        Image img=new ImageIcon(h.getClass().getResource("/imagen/icono.png")).getImage();
        h.setIconImage(img);
        h.setVisible(true);
        
    }

    public static File getF() {
        return f;
    }

    public static void setF(File aF) {
        f = aF;
    }
    
}
