/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formatosql;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class FormatoSQL {

    public String formato(File f){
        String str="";
        try {
            FileReader fr=new FileReader(f);
            BufferedReader br=new BufferedReader(fr);
            String ln;
            String aux="";
            while((ln=br.readLine())!=null){
                int i;
                for(i=0;i<ln.length() && ln.charAt(i)==' ';i++);
                
                int inicio=i;
                for(;i<ln.length() && ln.charAt(i)!=' ';i++);
                int fin=i;
                
                if(listaNegra(ln.substring(inicio, fin))){
                    if("ENGINE".equalsIgnoreCase(ln.substring(inicio, fin))){
                       
                        if(aux.charAt(aux.length()-3)==',' || aux.charAt(aux.length()-3)==')'){
                            aux=aux.substring(0, aux.length()-3);
                        }
                        aux+=") ";
                    }
                    aux+=ln+"\n\r";
                }
                
            }
            fr.close();
            str=aux;
        
        } catch (Exception e) {
            System.out.println("Error "+e.getMessage());
        }
        return str;
    }
    private boolean listaNegra(String str){
        switch(str){
            case "INDEX":return false;
            case "CONSTRAINT":return false;
            case "REFERENCES":return false;
            case "ON":return false;
            case "SET":return false;
            case "FOREIGN":return false;
        }
        return true;
    }
    
}
