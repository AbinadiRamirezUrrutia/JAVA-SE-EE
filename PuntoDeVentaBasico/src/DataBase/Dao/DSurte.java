
package DataBase.Dao;

import DataBase.Pojo.Surte;
import java.sql.PreparedStatement;

public class DSurte extends DaoI<Surte>{
/**
 * 
 * @param arg Recibe el objeto de tipo Surte a insertar en la BD
 * @return regresa un booleano para indicar que se inserta o no correctamente
 */
    @Override
    public boolean Insert(Surte arg) {
        if(!conectar())return false;
        try {
            PreparedStatement pre=con.prepareStatement(
                    "insert into surte(cantidad, fecha,trabajador_id) values (?,now(),?);");
            pre.setFloat(1, arg.getCantidad());
            pre.setInt(2, arg.getTrabajadorId());
            
            pre.executeUpdate();
            con.commit();
            desconectar();
            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return false;
    }



    @Override
    public boolean Delete(Surte arg) {
        if(!conectar())return false;
        try {
            PreparedStatement pre=con.prepareStatement(
                    "delete from  surte where id=?;");
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
    public boolean Update(Surte o, Surte n) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
