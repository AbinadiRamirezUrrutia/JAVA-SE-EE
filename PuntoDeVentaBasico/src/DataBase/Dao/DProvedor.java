
package DataBase.Dao;
import DataBase.Pojo.Provedor;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static DataBase.Dao.DaoI.ESTE_TRABAJADOR;

public class DProvedor extends DaoI<Provedor>{
/**
 * 
 * @param arg Recibe el objeto de tipo Provedor a insertar en la BD
 * @return regresa un booleano para indicar que se inserta o no correctamente
 */
    @Override
    public boolean Insert(Provedor arg) {
        if(!conectar())return false;
        try {
            PreparedStatement pre=con.prepareStatement(
                    "insert into provedor(nombre, telefono,direccion,sucursal_id) values (?,?,?,?);");
            pre.setString(1, arg.getNombre());
            pre.setString(2, arg.getTelefono());
            pre.setString(3, arg.getDireccion());
            pre.setInt(4, arg.getSucursalId());
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
    public boolean Delete(Provedor arg) {
        if(!conectar())return false;
        try {
            PreparedStatement pre=con.prepareStatement(
                    "delete from  provedor where id=?;");
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
    public boolean Update(Provedor o, Provedor n) {
        if(!conectar() || !o.equals(n))return false;
        int id=o.getId();
        if(!o.getNombre().equals(n.getNombre())){
            preUpdate(id, o.getNombre(), n.getNombre(),"nombre");
        }
        if(!o.getTelefono().equals(n.getTelefono())){
            preUpdate(id, o.getTelefono(), n.getTelefono(),"telefono");
        }
        if(!o.getDireccion().equals(n.getDireccion())){
            preUpdate(id, o.getDireccion(), n.getDireccion(),"direccion");
        }
        if(o.getSucursalId()!=n.getSucursalId()){
            preUpdate(id, o.getSucursalId()+"", n.getSucursalId()+"","sucursal_id");
        }
        
        try {
            con.commit();
        } catch (SQLException ex) {
            Logger.getLogger(DProvedor.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            desconectar();
        }
        return true;
    }

    private boolean preUpdate(int id,String  o,String  n,String atributo){
        try {
            PreparedStatement pre=con.prepareStatement("update provedor set "+atributo+" = ? where id= ?");
            pre.setString(1, n);
            pre.setInt(2, id);
            pre.executeUpdate();
            PreparedStatement pre2=con.prepareStatement("insert into  update_provedor (fecha,atributo,viejo,nuevo,producto_id,usuario_usr) values(now(),'"+atributo+"',?,?,?,? ) ;");
            pre2.setString(1, o);
            pre2.setString(2, n);
            pre2.setInt(3, id);
            pre2.setString(4, USUARIO);
            pre2.executeUpdate();
            return true;
        } catch (SQLException ex) {
            try {
                this.con.rollback();
                desconectar();
                Logger.getLogger(DProducto.class.getName()).log(Level.SEVERE, null, ex);
                
            } catch (SQLException ex1) {
                Logger.getLogger(DProducto.class.getName()).log(Level.SEVERE, null, ex1);
            }finally{
                return false;
            }
        }
    }
    
}