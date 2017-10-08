/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBase.Dao;

import DataBase.Pojo.Departamento;
import java.sql.PreparedStatement;

/**
 *
 * @author Abina
 */
public class DPartamento extends DaoI<Departamento>{

    @Override
    public boolean Insert(Departamento arg) {
        if(!conectar())return false;
        try {
            PreparedStatement pre=con.prepareStatement(
                    "insert into departamento (nombre)values(?)");
            
            pre.setString(1, arg.getNombre());
            
            pre.executeUpdate();
            con.commit();
            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }finally{
            desconectar();
        }
    }

    @Override
    public boolean Update(Departamento o, Departamento n) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean Delete(Departamento arg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
