/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBase.Dao;

import DataBase.Pojo.Permiso;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Abina
 */
public class DPermiso extends DaoI<Permiso>{
/**
 * 
 * @param arg Recibe el objeto de tipo Permiso a insertar
 * @return regresa un booleano para indicar que se inserto o no correctamente
 */
    @Override
    public boolean Insert(Permiso arg) {
        if(!conectar())return false;
        try {
            PreparedStatement pre=con.prepareStatement(
                    "Insert into permiso "
                            + "(nombre,is_on,usuario_usr) "
                            + "values (?,?,?);");
            pre.setString(1, arg.getNombre());
            pre.setBoolean(2, arg.isOn());
            pre.setString(3, arg.getUsuarioUsr());
            pre.executeUpdate();
            con.commit();
            desconectar();
            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return false;
    }


/**
 * 
 * @param arg Recibe el objeto de tipo Permiso a eliminar
 * @return regresa un booleano para indicar que se elimino o no correctamente
 */
    @Override
    public boolean Delete(Permiso arg) {
        if(!conectar())return false;
        try {
            PreparedStatement pre=con.prepareStatement(
                    "delete from  permiso where id=?;");
            pre.setInt(1, arg.getId());
            pre.executeUpdate();
            con.commit();
            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean Update(Permiso o, Permiso n) {
        
        if(!conectar() || !o.equals(n))return false;
        if(o.isOn()!=n.isOn()){
            try {
                PreparedStatement ps;
                ps = con.prepareStatement("update permiso is_on= ? where id = ?; ");
                ps.setBoolean(1, n.isOn());
                ps.setInt(2, o.getId());
                ps.executeQuery();
                ps=con.prepareStatement("inser into update_permiso (fecha,viejo,nuevo,persmiso_id,usuario_usr) values(now(),?,?,?,?);");
                ps.setBoolean(1, o.isOn());
                ps.setBoolean(2, n.isOn());
                ps.setInt(3, o.getId());
                ps.setString(4, USUARIO);
                ps.executeQuery();
                con.commit();
                return true;
            } catch (SQLException ex) {
                try {
                    con.rollback();
                    
                } catch (SQLException ex1) {
                    Logger.getLogger(DPermiso.class.getName()).log(Level.SEVERE, null, ex1);
                }
                Logger.getLogger(DPermiso.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                desconectar();
            }
        }
        
        return false;
    }
   
    
}
