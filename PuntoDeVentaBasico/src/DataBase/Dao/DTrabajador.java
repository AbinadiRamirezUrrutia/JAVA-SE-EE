/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBase.Dao;

import DataBase.Pojo.Trabajador;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static DataBase.Dao.DaoI.ESTE_TRABAJADOR;

/**
 *
 * @author Abina
 */
public class DTrabajador extends DaoI<Trabajador>{
/**
 * 
 * @param arg Recibe el objeto de tipo Trabajador a insertar en la BD
 * @return regresa un booleano para indicar que se inserta o no correctamente
 */
    @Override
    public boolean Insert(Trabajador arg) {
        if(!conectar())return false;
        try {
            PreparedStatement pre=con.prepareStatement(
                    "insert into trabajador "
                            + "(nombre,apellido,direccion,telefono,correo,salario,trabajador_id_jefe,sucursal_id) "
                            + "values(?,?,?,?,?,?,?,?);");
            pre.setString(1, arg.getNombre());
            pre.setString(2, arg.getApellido());
            pre.setString(3, arg.getDireccion());
            pre.setString(4, arg.getTelefono());
            pre.setString(5, arg.getCorreo());
            pre.setFloat(6, arg.getSalario()); 
            pre.setInt(7, arg.getTrabajadorIdJefe());
            pre.setInt(8, arg.getSucursalId());
            
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
 * @param arg Recibe el objeto de tipo Trabajador a eliminar en la BD
 * @return regresa un booleano para indicar que se elimina o no correctamente
 */
    @Override
    public boolean Delete(Trabajador arg) {
        if(!conectar())return false;
        try {
            PreparedStatement pre=con.prepareStatement(
                    "delete from  trabajador where id=?;");
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
    public boolean Update(Trabajador o, Trabajador n) {
        if(!conectar() || !o.equals(n))return false;
        int id=o.getId();
        if(n.getNombre()!=null && o.getNombre().equals(n.getNombre())){
            if(preUpdate(0, o.getNombre(), n.getNombre(), "nombre"))return false;
        }
        if(n.getApellido()!=null && o.getApellido().equals(n.getApellido())){
            if(preUpdate(0, o.getApellido(), n.getApellido(), "apellido"))return false;
        }
        if(n.getDireccion()!=null && o.getDireccion().equals(n.getDireccion())){
            if(preUpdate(0, o.getDireccion(), n.getDireccion(), "direccino"))return false;
        }
        if(n.getTelefono()!=null && o.getTelefono().equals(n.getTelefono())){
            if(preUpdate(0, o.getTelefono(), n.getTelefono(), "telefono"))return false;
        }
        if(n.getCorreo()!=null && o.getCorreo().equals(n.getCorreo())){
            if(preUpdate(0, o.getCorreo(), n.getCorreo(), "correo"))return false;
        }
        if(o.getSalario()!=n.getSalario()){
            if(preUpdate(0, o.getSalario()+"", n.getSalario()+"", "salario"))return false;
        }
        if(o.getTrabajadorIdJefe()!=n.getTrabajadorIdJefe()){
            if(preUpdate(0, o.getTrabajadorIdJefe()+"", n.getTrabajadorIdJefe()+"", "trabajador_id_jefe"))return false;
        }
        if(o.getSucursalId()!=n.getSucursalId()){
            if(preUpdate(0, o.getSucursalId()+"", n.getSucursalId()+"", "sucursal_id"))return false;
        }
        if(o.isBorrado()!=n.isBorrado()){
            if(preUpdate(0, o.isBorrado()+"", n.isBorrado()+"", "borrado"))return false;
        }
        try {
            con.commit();
        } catch (SQLException ex) {
            Logger.getLogger(DTrabajador.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            desconectar();
        }
        return true;
    }
    private boolean preUpdate(int id,String  o,String  n,String atributo){
        try {
            PreparedStatement pre=con.prepareStatement("update trabajador set "+atributo+" = ? where id= ?");
            pre.setString(1, n);
            pre.setInt(2, id);
            pre.executeUpdate();
            PreparedStatement pre2=con.prepareStatement("insert into  update_trabajador (fecha,atributo,viejo,nuevo,producto_id,usuario_usr) values(now(),'"+atributo+"',?,?,?,? ) ;");
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
