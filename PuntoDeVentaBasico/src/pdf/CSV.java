/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdf;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Abina
 */
public class CSV {
    public void report(DefaultTableModel dm,File f){
        String url=f.getAbsolutePath();
        if(!url.endsWith(".csv")){
            f=new File(url+".csv");
        }
        
        String tex="";
        for(int i=0;i<dm.getColumnCount();i++){
            tex+=dm.getColumnName(i);
            if(i+1<dm.getColumnCount()){
                tex+=",";
            }
        }
        tex+="\r\n";
        for(int r=0;r<dm.getRowCount();r++){
            for(int c=0;c<dm.getColumnCount();c++){
                tex+=dm.getValueAt(r, c);
                if(c+1<dm.getColumnCount()){
                    tex+=",";
                }
            }
            if(r+1<dm.getRowCount()){
                tex+="\r\n";
            }
        }
        String file = f.getAbsolutePath(); 
        try {
            FileWriter fw=new FileWriter(f);
            fw.write(tex);
            fw.close();
            try{ 
                Runtime.getRuntime().exec("cmd /c start "+file);

            }catch(IOException e){
                e.printStackTrace();
            } 
        } catch (IOException ex) {
            Logger.getLogger(CSV.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
}
