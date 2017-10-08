/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package farmacia;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class LicenciaConfig {
   public static String getMacAddres(){
       String macaddres=null;
	InetAddress ip;
	try {
            ip = InetAddress.getLocalHost();
            NetworkInterface network = NetworkInterface.getByInetAddress(ip);
            byte[] mac = network.getHardwareAddress();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < mac.length; i++) {
                sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "+" : ""));
            }
            macaddres=sb.toString();
	} catch (UnknownHostException e) {

            e.printStackTrace();

	} catch (SocketException e){

            e.printStackTrace();
	}
        return macaddres;
   }
   public  String encrip(String str){
       String encriptamiento="";
       for(int i=0;i<str.length();i++){
           encriptamiento+=(char)(str.charAt(i)+desplasar(i, str.length()));
       }
       return encriptamiento;
   }
   public  String desencrip(String str){
       String encriptamiento="";
       for(int i=0;i<str.length();i++){
           encriptamiento+=(char)(str.charAt(i)-desplasar(i, str.length()));
       }
       return encriptamiento;
   }
   private int desplasar(int index,int sizeStr){
       int modulo=5;
       if((((index+1)*sizeStr)%modulo)==0)modulo++;
       int signo=(index%2==0)?1:-1;
       return (((index+1)*sizeStr)%modulo)*signo;
   }
   public String crearlicencia(){
       String str="";
       String mac=getMacAddres();
       for(int i=0;i<11;i++){
           char caracter=mac.charAt(mac.length()-i-1);
           if(caracter=='+'){
               str+="-";
           }else{
               str+=(int)caracter+"";
           }
       }
       return str;
   }
   
}
