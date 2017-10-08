
package DataBase.Dao;

import DataBase.Pojo.Item;
import java.sql.PreparedStatement;

public class DItem extends DaoI<Item>{
/**
 * 
 * @param arg Recibe el objeto de tipo item a insertar
 * @return regresa un booleano para indicar que se inserto o no correctamente
 */
    @Override
    public boolean Insert(Item arg) {
        if(!conectar())return false;
        try {
            PreparedStatement pre=con.prepareStatement(
                    "insert into item (cantidad, precio_Unitario, venta_id, producto_id) values (?,?,?,?);");
            pre.setFloat(1, arg.getCantidad());
            pre.setFloat(2, arg.getPrecioUnitario());
            pre.setInt(3, arg.getVentaId());
            pre.setInt(4, arg.getProductoId());
            
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
 * @param arg Recibe el objeto de tipo item a eliminar
 * @return regresa un booleano para indicar que se elimino o no correctamente
 */
    @Override
    public boolean Delete(Item arg) {
        if(!conectar())return false;
        try {
            PreparedStatement pre=con.prepareStatement(
                    "delete item  provedor where id=?;");
            pre.setInt(1, arg.getId());
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

    @Override
    public boolean Update(Item o, Item n) {
        throw new UnsupportedOperationException("No soportado la actualisacion de item"); //To change body of generated methods, choose Tools | Templates.
    }
    
}
