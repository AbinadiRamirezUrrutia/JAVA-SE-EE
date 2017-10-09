
package formatosql;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 *
 * @author Ramirez Urrutia Angel <abinanye@hotmail.com>
 */
public class FormatoSQL {

    public String formato(File f){
        String str="";
        try {
            FileReader fileReader=new FileReader(f);
            BufferedReader bufferedReader=new BufferedReader(fileReader);
            String linea;
            String auxiliar="";
            while((linea=bufferedReader.readLine())!=null){
                int i;
                for(i=0;i<linea.length() && linea.charAt(i)==' ';i++);
                
                int inicio=i;
                for(;i<linea.length() && linea.charAt(i)!=' ';i++);
                int fin=i;
                
                if(listaNegra(linea.substring(inicio, fin))){
                    if("ENGINE".equalsIgnoreCase(linea.substring(inicio, fin))){
                       
                        if(auxiliar.charAt(auxiliar.length()-3)==',' || auxiliar.charAt(auxiliar.length()-3)==')'){
                            auxiliar=auxiliar.substring(0, auxiliar.length()-3);
                        }
                        auxiliar+=") ";
                    }
                    auxiliar+=linea+"\n\r";
                }
                
            }
            fileReader.close();
            str=auxiliar;
        
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
