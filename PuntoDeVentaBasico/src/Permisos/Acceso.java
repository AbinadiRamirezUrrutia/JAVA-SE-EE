package Permisos;

import DataBase.Pojo.Permiso;
import DataBase.Pojo.Trabajador;
import java.util.List;

/**
 *
 * @author Abina
 */
public class Acceso {
    public static Trabajador ESTE_TRABAJADOR=null;
    public static String USUARIO=null;
    public static int LOGIN=1;
    public static int VENTA=2;
    public static int SURTIR=3;
    public static int EDITAR_PRODUCTO=4;
    public static int EDITAR_USUARIO=5;
    public static int INFORMACION=6;

    public static List<Permiso> getPermisos() {
        return permisos;
    }

    public static void setPermisos(List<Permiso> aPermisos) {
        permisos = aPermisos;
    }
    private Acceso(){
        
    }
    
    private static List<Permiso> permisos;
    /**
     * @param i llega un valor numerico
     * @return  y regresa su correspondiente en forma de cadena
     */
    private static String transAcceso(int i){
        switch(i){
            case 1:return "login";
            case 2:return "venta";
            case 3:return "surtir";
            case 4:return "editar producto";
            case 5:return "editar usuario";
            case 6:return "informacion";
        }
        return "";
    }
    /**
     * @param acceso verifica si tiene acceso al dato que esta llegando
     * @return       y regresa si lo tiene o no
     */
    public static boolean acceso(int acceso){
        for(Permiso p:permisos){
            if(p.getNombre().equalsIgnoreCase(transAcceso(acceso)) && p.isOn()){
                return true;
            }
        }
        return false;
    }
    
}
