package DataBase.Dao;

import DataBase.Pojo.Trabajador;
import Permisos.Acceso;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DaoI<T> {
    protected Connection con =null;
    private String url="jdbc:sqlite:database.db";
    private String user="root";
    private String password="";
    protected static Trabajador ESTE_TRABAJADOR;
    protected static String USUARIO;
    
    
    public DaoI() {
        ESTE_TRABAJADOR=Acceso.ESTE_TRABAJADOR;
        USUARIO=Acceso.USUARIO;
    }
    
    
    
    public Connection getConexion(){
        return con;
    }
            
    /**
     * Instacia la conexion de la base de datos y devuelve VERDAERO o FALSO si pudo hacer conexiones
     * @return si esta conectado.
     */
    public boolean conectar(){
        try {
            ESTE_TRABAJADOR=Acceso.ESTE_TRABAJADOR;
            USUARIO=Acceso.USUARIO;
            con=DriverManager.getConnection(url);
            con.setAutoCommit(false);
            return true;
        } catch (SQLException ex) {
            System.err.println("Error al tratar de conextar: "+ex.getMessage());
            return false;
        }
    }

    public static Trabajador getEsteTrabajador() {
        return ESTE_TRABAJADOR;
    }
    public static String getUsuario(){
        return USUARIO;
    }
    
    
    /**
     * Desconecta la base de datos y pierde la referencia 
     */
    public void desconectar(){
        try {
            con.close();
        } catch (SQLException ex) {
            
        }
    }
    
    public abstract boolean Insert(T arg);
    public abstract boolean Update(T o,T n);
    public abstract boolean Delete(T arg);
}
