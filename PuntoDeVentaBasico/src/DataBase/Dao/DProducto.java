
package DataBase.Dao;

import DataBase.Pojo.Producto;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DProducto extends DaoI<Producto>{
/**
 * 
 * @param arg Recibe el objeto de tipo producto a insertar en la BD
 * @return regresa un booleano para indicar que se inserta o no correctamente
 */
    @Override
    public boolean Insert(Producto arg) {
        if(!conectar())return false;
        try {
            
            PreparedStatement pre=null;
                    if(!arg.getCodigoDeBarra().isEmpty()){
                        pre=con.prepareStatement("select count(*) as num from producto where codigo_barra = ?");
                        pre.setString(1, arg.getCodigoDeBarra());
                        ResultSet rs=pre.executeQuery();
                        if(rs.next()){
                            if(rs.getInt("num")>0){
                                return false;
                            }
                        }
                    }
                    pre=con.prepareStatement(
                    "insert into producto ("
                            + "codigo_barra, "
                            + "nombre, "
                            + "precio_compra, "
                            + "precio_venta, "
                            + "activo, "
                            + "receta, "
                            + "descripcion, "
                            + "departamento_id, "
                            + "minimo) "
                            + "values (?,?,?,?,?,?,?,?,?);");
            pre.setString(1, arg.getCodigoDeBarra());
            pre.setString(2, arg.getNombre());
            pre.setFloat(3, arg.getPrecioCompra());
            pre.setFloat(4, arg.getPrecioVenta());
            pre.setString(5, arg.getActivo());
            pre.setBoolean(6, arg.isReceta());
            pre.setString(7, arg.getDescripcion());
            pre.setInt(8, arg.getDepartamentoId());
            pre.setFloat(9, arg.getMinimo());
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
 * @param arg Recibe el objeto de tipo producto a eliminar en la BD
 * @return regresa un booleano para indicar que se eliminar o no correctamente
 */
    @Override
    public boolean Delete(Producto arg) {
        if(!conectar())return false;
        try {
            PreparedStatement pre=con.prepareStatement(
                    "delete from  producto where id=?;");
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

    
    private boolean preUpdate(int id,String  o,String  n,String atributo){
        try {
            PreparedStatement pre=con.prepareStatement("update producto set "+atributo+" = ? where id= ?");
            pre.setString(1, n);
            pre.setInt(2, id);
            pre.executeUpdate();
/*            
            PreparedStatement pre2=con.prepareStatement("insert into  update_producto (fecha,atributo,viejo,nuevo,producto_id,usuario_usr) values(now(),'"+atributo+"',?,?,?,? ) ;");
            pre2.setString(1, o);
            pre2.setString(2, n);
            pre2.setInt(3, id);
            pre2.setString(4, USUARIO);
            pre2.executeUpdate();
*/
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

    @Override
    public boolean Update(Producto o, Producto n) {
        if(!conectar() || !o.equals(n))return false;
        int id=o.getId();
        if(n.getNombre()!=null && !o.getNombre().equals(n.getNombre())){
            if(!preUpdate(id,o.getNombre(),n.getNombre(),"nombre"))return false;
        }
        if(o.getPrecioCompra()!=n.getPrecioCompra()){
            if(!preUpdate(id,o.getPrecioCompra()+"",n.getPrecioCompra()+"","precio_compra"))return false;
        }
        if(o.getPrecioVenta()!=n.getPrecioVenta()){
            if(!preUpdate(id,o.getPrecioVenta()+"",n.getPrecioVenta()+"","precio_venta"))return false;
        }
        if(n.getActivo()!=null && !o.getActivo().equals(n.getActivo())){
            if(!preUpdate(id,o.getActivo(),n.getActivo(),"activo"))return false;
        }
        if(n.getDescripcion()!=null && !o.getDescripcion().equalsIgnoreCase(n.getDescripcion())){
            if(!preUpdate(id,o.getDescripcion(),n.getDescripcion(),"descripcion"))return false;
        }
        if(o.isBorrado()!=n.isBorrado()){
            if(!preUpdate(id,o.isBorrado()+"",n.isBorrado()+"","borrado"))return false;
        }
        if(o.getMinimo()!=n.getMinimo()){
            if(!preUpdate(id,o.getMinimo()+"",n.getMinimo()+"","minimo"))return false;
        }
        try {
            this.con.commit();
        } catch (SQLException ex) {
            Logger.getLogger(DProducto.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            desconectar();
        }
        return true;
    }


    
}