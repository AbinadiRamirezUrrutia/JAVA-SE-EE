/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DataBase.Dao;

import DataBase.Pojo.CajaOn;
import DataBase.Pojo.Producto;
import DataBase.Pojo.Trabajador;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class As {

    protected Trabajador asTrabajador(ResultSet rs) throws SQLException{
        Trabajador t=null;
        if(rs.next()){
            t=new Trabajador();
            t.setId(rs.getInt("id"));
            t.setNombre(rs.getString("nombre"));
            t.setApellido(rs.getString("apellido"));
            t.setDireccion(rs.getString("direccion"));
            t.setTelefono(rs.getString("telefono"));
            t.setCorreo(rs.getString("correo"));
            t.setSalario(rs.getFloat("salario"));
            t.setTrabajadorIdJefe(rs.getInt("trabajador_id_jefe"));
            t.setSucursalId(rs.getInt("sucursal_id"));
            t.setBorrado(rs.getBoolean("borrado"));
            return t;
        }
        return null;
    }
    protected Producto asProducto(ResultSet rs) throws SQLException{
        Producto p=null;
        if(rs.next()){
            p=new Producto();
            p.setId(rs.getInt("id"));
            p.setCodigoDeBarra(rs.getString("codigo_barra"));
            p.setNombre(rs.getString("nombre"));
            p.setPrecioCompra(rs.getFloat("precio_compra"));
            p.setPrecioVenta(rs.getFloat("precio_venta"));
            p.setActivo(rs.getString("activo"));
            p.setDescripcion(rs.getString("descripcion"));
            p.setReceta(rs.getBoolean("receta"));
            p.setMinimo(rs.getFloat("minimo"));
            p.setBorrado(rs.getBoolean("borrado"));
            p.setDepartamentoId(rs.getInt("departamento_id"));
            return p;
        }
        return null;
    }
    protected CajaOn asCajaOn(ResultSet rs) throws SQLException{
        CajaOn cajaOn=null;
        if(rs.next()){
            cajaOn=new CajaOn();
            cajaOn.setId(rs.getInt("id"));
            cajaOn.setCajaId(rs.getInt("caja_id"));
            cajaOn.setFechaInicio(rs.getString("fecha_inicio"));
            cajaOn.setFechaSalida(rs.getString("fecha_fin"));
            cajaOn.setInicioSaldo(rs.getFloat("inicio_saldo"));
            cajaOn.setTotal(rs.getFloat("total"));
            cajaOn.setTrabajadorId(rs.getInt("trabajador_id"));
            return cajaOn;
        }
        return null;
    }
}
