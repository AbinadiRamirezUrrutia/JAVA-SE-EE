/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBase.Dao;

import DataBase.Pojo.Lote;
import java.sql.PreparedStatement;

/**
 *
 * @author Abina
 */
public class DLote extends DaoI<Lote>{

    @Override
    public boolean Insert(Lote arg) {
        if(!conectar())return false;
        try {
            PreparedStatement pre=con.prepareStatement(
                    "insert into lote (producto_id,existencia,ubicacion,caducacion)values(?,?,?,?)");
            pre.setInt(1, arg.getProductoId());
            pre.setFloat(2, arg.getExistencia());
            pre.setString(3, arg.getUbicacion());
            pre.setString(4, arg.getCaducacion());
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
    public boolean Update(Lote o, Lote n) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean Delete(Lote arg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
