/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DataBase.Dao;

import DataBase.Pojo.Medico;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class Dmedico extends DaoI<Medico>{

    @Override
    public boolean Insert(Medico arg) {
        if(!conectar())return false;
        try{
            PreparedStatement ps=getConexion().prepareStatement("insert into medico (cedula,nombre) values (?,?)");
            ps.setInt(1, arg.getCedula());
            ps.setString(2, arg.getNombre());
            ps.executeUpdate();
            getConexion().commit();
            return true;
        }catch(Exception e){
            try {
                getConexion().rollback();
            } catch (SQLException ex) {
                Logger.getLogger(Dmedico.class.getName()).log(Level.SEVERE, null, ex);
            }
        }finally{
            desconectar();
        }
        return false;
    }

    @Override
    public boolean Update(Medico o, Medico n) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean Delete(Medico arg) {
        if(!conectar())return false;
        try {
            PreparedStatement pre=con.prepareStatement(
                    "delete from  medico where cedula=?;");
            pre.setInt(1, arg.getCedula());
            pre.executeUpdate();
            con.commit();
            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return false;
    }

}
