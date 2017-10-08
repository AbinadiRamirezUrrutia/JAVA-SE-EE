/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBase.Dao;

import DataBase.Pojo.Venta;
import java.sql.PreparedStatement;

/**
 *
 * @author Abina
 */
public class DVenta extends DaoI<Venta>{
/**
 * 
 * @param arg Recibe el objeto de tipo Venta a insertar en la BD
 * @return regresa un booleano para indicar que se inserta o no correctamente
 */
    @Override
    public boolean Insert(Venta arg) {
        if(!conectar())return false;
        try {
            PreparedStatement pre=con.prepareStatement(
                    "insert into venta(fecha,total,forma_pago,caja_on_id) values(now(),?,?,?);");
            pre.setFloat(1, arg.getTotal());
            pre.setString(2, arg.getFormaDePago());
            pre.setInt(2, arg.getCajaOnId());          
            pre.executeUpdate();
            con.commit();
            
            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }finally{
            desconectar();
        }
        return false;
    }

/**
 * 
 * @param arg Recibe el objeto de tipo Venta a eliminar en la BD
 * @return regresa un booleano para indicar que se elimina o no correctamente
 */

    @Override
    public boolean Delete(Venta arg) {
        if(!conectar())return false;
        try {
            PreparedStatement pre=con.prepareStatement(
                    "delete from  venta where id=?;");
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
    public boolean Update(Venta o, Venta n) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
