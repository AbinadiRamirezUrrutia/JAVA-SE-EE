/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DataBase.Dao;

import DataBase.Pojo.CajaOn;
import DataBase.Pojo.Departamento;
import DataBase.Pojo.Item;
import DataBase.Pojo.Licencia;
import DataBase.Pojo.Medico;
import DataBase.Pojo.Permiso;
import DataBase.Pojo.Producto;
import DataBase.Pojo.Provedor;
import DataBase.Pojo.Trabajador;
import EstructuraRow.RowConsulta;
import EstructuraRow.RowSearchProductoVenta;
import java.io.IOException;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class SelectUpdate extends As{

    private DaoI daoI;
    
    public boolean testInternt(String web){
        Socket socket = null;
        try {
            socket=new Socket(web, 80);
            socket.close();
            return socket.isConnected();
        } catch (IOException ex) {
            return false;
        }
    }
    
    public SelectUpdate(){
        this.daoI=new DaoI() {
            @Override
            public boolean Insert(Object arg) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public boolean Update(Object o, Object n) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public boolean Delete(Object arg) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        
    }
    private Trabajador getTrabajador(){
        return DaoI.getEsteTrabajador();
    }
    private String getUsuario(){
        return DaoI.getUsuario();
    }
    
    private boolean conectar(){
        return daoI.conectar();
    }
    private Connection getConexion(){
        return daoI.getConexion();
    }
    private void desconectar(){
        daoI.desconectar();
    }
    
    public boolean productoJoinProvedor(Producto p1, Provedor p2){
        if(!conectar())return false;
        try {
            PreparedStatement pre=getConexion().prepareStatement(
                    "insert into producto_vs_provedor (producto_id,provedor_id) values (?,?);");
            pre.setInt(1, p1.getId());
            pre.setInt(2, p2.getId());
            pre.executeUpdate();
            getConexion().commit();
            desconectar();
            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return false;
    }

    public boolean deleteProductoJoinProvedor(Producto p1, Provedor p2){
        if(conectar())return false;
        try {
            PreparedStatement pre=getConexion().prepareStatement(
                    "delete producto_vs_provedor where producto_id= ? and  provedor_id=?;");
            pre.setInt(1, p1.getId());
            pre.setInt(2, p2.getId());
            pre.executeUpdate();
            getConexion().commit();
            desconectar();
            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return  false;
    }

    public List<Permiso> getPermiso(String usr,String psw){
        if(!conectar())return null;
        List<Permiso> permisos=new ArrayList<Permiso>();
        PreparedStatement ps=null;
        try {
            ps=getConexion()
                    .prepareStatement("select p.id, p.nombre, p.is_on from "
                            + "usuario u join permiso p on u.usr=p.usuario_usr where "
                            + "u.usr=? and u.psw=password(?)");
            ps.setString(1, usr);
            ps.setString(2, psw);
            ResultSet rs=ps.executeQuery();
            
            while(rs.next()){
                Permiso p=new Permiso();
                p.setId(rs.getInt("id"));
                p.setNombre(rs.getString("nombre"));
                p.setIsOn(rs.getBoolean("is_on"));
                p.setUsuarioUsr(usr);
                
                permisos.add(p);
            }
            rs.close();
            ps.close();
            getConexion().commit();
            desconectar();
            return permisos;
        } catch (SQLException ex) {
            try {
                ps.close();
                desconectar();
                Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex1) {
                Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        return null;
    }

    public String getUsuario(Trabajador trabajador){
        if(!conectar())return null;
        try {
            PreparedStatement pst=getConexion().prepareStatement("SELECT usr FROM `usuario` WHERE trabajador_id=?;");
            pst.setInt(1, trabajador.getId());
            ResultSet rs=pst.executeQuery();
            if(rs.next()){
                return rs.getString("usr");
            }
        } catch (Exception e) {
        }finally{
            desconectar();
        }
        return null;
    }
    
    public Trabajador getTrabajador(String usr,String psw){
        if(!conectar())return null;
        Trabajador trabajador=null;
        PreparedStatement ps;
        try {
            ps=getConexion().prepareStatement("select * from trabajador t where "
                    + "t.id=(select u.trabajador_id from usuario u where u.psw=password(?) and u.usr=?);");
            ps.setString(2, usr);
            ps.setString(1, psw);
            trabajador=asTrabajador(ps.executeQuery());
            getConexion().commit();
            ps.close();
            desconectar();
            return trabajador;
        } catch (SQLException ex) {
            desconectar();
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public Trabajador getJefe(String usr,String psw){
        if(!conectar())return null;
        Trabajador trabajador=null;
        PreparedStatement ps;
        try {
            ps=getConexion().prepareStatement("select t.id, t.nombre,t.apellido, t.direccion, t.telefono, t.correo,t.salario, t.trabajador_id_jefe, t.sucursal_id, t.borrado, usr \n" +
                                            "from trabajador t join usuario u on t.id=u.trabajador_id  \n" +
                                            "where t.borrado=false  and u.usr=? and u.psw=password(?)  and  t.id in (select trabajador_id_jefe from sucursal) ;");
            ps.setString(1, usr);
            ps.setString(2, psw);
            ResultSet rs=ps.executeQuery();
            trabajador=asTrabajador(rs);
            if(trabajador!=null){
                trabajador.setUsuario(rs.getString("usr"));
            }
            
            getConexion().commit();
            ps.close();
            desconectar();
            return trabajador;
        } catch (SQLException ex) {
            desconectar();
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    /**
     * Devuelve quien es el trabajador del apellido.  
     * @param apellido apellido.
     * @return trabajador.
     */
    
    public Trabajador getTrabajador(String apellido){
        if(!conectar())return null;
        Trabajador trabajador=null;
        PreparedStatement ps;
        try {
            ps=getConexion().prepareStatement("select * from trabajador t where "
                    + "t.apellido = ?;");
            
            ps.setString(1, apellido);
            trabajador=asTrabajador(ps.executeQuery());
            getConexion().commit();
            ps.close();
            desconectar();
            return trabajador;
        } catch (SQLException ex) {
            desconectar();
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<Permiso> getPermiso(String usario){
        if(!conectar())return null;
        List<Permiso> permisos=new ArrayList<Permiso>();
        try {
            ResultSet rs=getConexion().createStatement().executeQuery("SELECT * FROM permiso where id in (select p.id from usuario u join permiso p on u.usr=p.usuario_usr where u.usr='"+usario+"' );");
            while(rs.next()){
                Permiso p=new Permiso();
                p.setId(rs.getInt("id"));
                p.setNombre(rs.getString("nombre"));
                p.setIsOn(rs.getBoolean("is_on"));
                p.setUsuarioUsr(rs.getString("usuario_usr"));
                permisos.add(p);
            }
            desconectar();
            return permisos;
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
 
    public List<Provedor> getProvedores(String nambre){
        if(!conectar())return null;
        List<Provedor> provedors=new ArrayList<Provedor>();

        try {
            PreparedStatement ps=getConexion().prepareStatement("select * from provedor where nombre like ?;");
            ps.setString(1, nambre+"%");
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                Provedor p=new Provedor();
                p.setId(rs.getInt("id"));
                p.setNombre(rs.getString("nombre"));
                p.setTelefono(rs.getString("telefono"));
                p.setDireccion(rs.getString("direccion"));
                p.setSucursalId(rs.getInt("sucursal_id"));
                provedors.add(p);
            }
            desconectar();
            
            return provedors;
        } catch (SQLException ex) {
            
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        return null;
    }
 
    public boolean disponibleUsuario(String st){
        if(!conectar())return false;
        try {
            PreparedStatement ps=getConexion().prepareStatement("SELECT count(*) as num FROM usuario u where u.usr=?;");
            ps.setString(1, st);
            ResultSet r=ps.executeQuery();
            if(r.next()){
                return r.getInt("num")==0;
            }    
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            desconectar();
        }
        return false;
    }

 
    public boolean estaConectado(){
        if(!conectar())return false;
        desconectar();
        return true;
    }
    public boolean estaRegistradoPc(String macAddres){
        if(!conectar())return false;
        int num=0;
        try {
            PreparedStatement ps=getConexion().prepareStatement("select count(*) as num from licencia where mac = ? ;");
            ps.setString(1, macAddres);
            ResultSet rs=ps.executeQuery();
            if(!rs.next())return false;
            num=rs.getInt("num");
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            desconectar();
            return num==1;
        }
    }
    public boolean verificarlicencia(String macAddres,String licencia){
        if(!conectar())return false;
        int num=0;
        try {
            PreparedStatement ps=getConexion().prepareStatement("select count(*) as num from licencia where mac = ? and licencia = ?;");
            ps.setString(1, macAddres);
            ps.setString(2, licencia);
            ResultSet rs=ps.executeQuery();
            if(!rs.next())return false;
            num=rs.getInt("num");
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            desconectar();
            return num==1;
        }
    }
    public boolean verificarPermisoDeCoexion(String macAddres,String licencia){
        if(!conectar())return false;
        int num=0;
        try {
            PreparedStatement ps=getConexion().prepareStatement("select count(*) as num from licencia where mac = ? and licencia = ? and acceso=true; ");
            ps.setString(1, macAddres);
            ps.setString(2, licencia);
            ResultSet rs=ps.executeQuery();
            if(!rs.next())return false;
            num=rs.getInt("num");
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            desconectar();
            return num==1;
        }
    }
    
    public boolean altaPc(Licencia l, Trabajador jefe){
        if(!conectar())return false;
        try {
            PreparedStatement  ps=getConexion().prepareStatement("insert into licencia (mac,hostname,licencia,fecha) values(?,?,?,now());");
            ps.setString(1, l.getMac());
            ps.setString(2, l.getHostName());
            ps.setString(3, l.getLicencia());
            ps.executeUpdate();
            if(l.getNombreCaja()!=null){
                ps=getConexion().prepareStatement("insert into caja(nombre,sucursal_id,licencia_id) values(?,(select id from sucursal where trabajador_id_jefe=?),(select id from licencia where mac=?));");
                ps.setString(1, l.getNombreCaja());
                ps.setInt(2, jefe.getId());
                ps.setString(3, l.getMac());
                ps.executeUpdate();
            }
            getConexion().commit();
            return true;
        } catch (Exception e) {
            try {
                getConexion().rollback();
            } catch (SQLException ex) {
                Logger.getLogger(SelectUpdate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }finally{
            desconectar();
        }
        return false;
    }
    public Producto getProductoCB(String codigoBarra){
        if(!conectar())return null;
        Producto producto=null;
        try {
            PreparedStatement ps=getConexion().prepareStatement("select * from producto where codigo_barra=?;");
            ps.setString(1, codigoBarra);
            ResultSet rs=ps.executeQuery();
            producto=asProducto(rs);
            desconectar();
            
            return producto;
        } catch (SQLException ex) {
            
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }   
        return null;
    }
    public List<Producto> getProductoByNombre(String nombre){
        if(!conectar())return null;
        Producto aux=null;
        List<Producto> productos=new ArrayList<Producto>();
        try {
            PreparedStatement ps=getConexion().prepareStatement("select* from producto where nombre like ? limit 0,20");
            ps.setString(1, nombre+"%");
            ResultSet rs=ps.executeQuery();
            while((aux=asProducto(rs))!=null){
                productos.add(aux);
            }
            desconectar();
            return productos;
        } catch (SQLException ex) {
            
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }   
        return null;
    }
    public List<RowSearchProductoVenta> getProductoAndExistenciaByNombre(String nombre){
        if(!conectar())return null;
        RowSearchProductoVenta aux=null;
        List<RowSearchProductoVenta> productos=new ArrayList<RowSearchProductoVenta>();
        try {
            PreparedStatement ps=getConexion().prepareStatement("select a.id, nombre,a.precio_venta, sum(existencia) as num from producto as a join lote as b on b.producto_id = a.id where nombre like ?  GROUP BY a.id limit 0,20;");
            ps.setString(1, nombre+"%");
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                aux=new RowSearchProductoVenta();
                int id=rs.getInt("id");
                if(id==0)continue;
                aux.setId(id);
                aux.setNombre(rs.getString("nombre"));
                aux.setPrecioVenta(rs.getFloat("precio_venta"));
                aux.setExistencia(rs.getFloat("num"));
                productos.add(aux);
            }
            desconectar();
            return productos;
        } catch (SQLException ex) {
            
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }   
        return null;
    }
    public Producto getProductoById(int id){
        if(!conectar())return null;
        Producto aux=null;
       
        try {
            PreparedStatement ps=getConexion().prepareStatement("select * from producto where id = ?;");
            ps.setInt(1, id);
            ResultSet rs=ps.executeQuery();
            aux=asProducto(rs);
            return aux;
        } catch (SQLException ex) {
            
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }   finally{
            desconectar();
        }
        return null;
    }
    public Producto getProductoByCodigoDeBarra(String cb){
        if(!conectar())return null;
        Producto aux=null;
       
        try {
            PreparedStatement ps=getConexion().prepareStatement("select * from producto where codigo_barra = ?;");
            ps.setString(1, cb);
            ResultSet rs=ps.executeQuery();
            aux=asProducto(rs);
            return aux;
        } catch (SQLException ex) {
            
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }   finally{
            desconectar();
        }
        return null;
    }
    
    
    public List<Medico> getMedicosAll(){
        if(!conectar())return null;
        List<Medico> lista;
        try {
            PreparedStatement ps=getConexion().prepareStatement("select * from medico ;");

            ResultSet rs=ps.executeQuery();
            lista=new ArrayList<Medico>();
            while(rs.next()){
                Medico medico=new Medico();
                medico.setCedula(rs.getInt("cedula"));
                medico.setNombre(rs.getString("nombre"));
                lista.add(medico);
            }
            return lista;
        } catch (Exception e) {
            System.err.println("Error "+e.getMessage());
            return null;
        }finally{
            desconectar();
        }
        
    } 
    public Medico getMedicosCedula(int cedula){
        if(!conectar())return null;
        List<Medico> lista;
        try {
            PreparedStatement ps=getConexion().prepareStatement("select * from medico where cedula = ?;");
            ps.setInt(1, cedula);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                Medico medico=new Medico();
                medico.setCedula(rs.getInt("cedula"));
                medico.setNombre(rs.getString("nombre"));
                return medico;
            }
            return null;
        } catch (Exception e) {
            System.err.println("Error "+e.getMessage());
            return null;
        }finally{
            desconectar();
        }
    } 
    private boolean transaccionDeVenta(Trabajador empleado,List<Item> items, Medico medico){
        try {
            
        } catch (Exception e) {
        }
        return false;
    }
    public boolean pago(Trabajador trabajador,List<Item> items,float total,String ReferenciaPago){
        return pago(trabajador, null, items, total, ReferenciaPago);
    }
    public boolean pago(Trabajador trabajador,Integer idMeico,List<Item> items,float total,String ReferenciaPago){
        if(!conectar())return false;
        
        DateTimeFormatter formato=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String fecha=formato.format(LocalDateTime.now());
        try {
            PreparedStatement ps;
            ResultSet rs;
            ps=getConexion().prepareStatement("insert into venta (fecha,total,forma_pago) values( '"+fecha+"'  ,?,?);",PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setFloat(1,total );
            ps.setString(2,ReferenciaPago);
            ps.executeUpdate();
            rs=ps.getGeneratedKeys();
            rs.next();
            int ventaId=rs.getInt(1);
            ps.close();
            rs.close();
            for(Item i:items){
                float faltantePorBender;
                i.setVentaId(ventaId);
                ps=getConexion().prepareStatement("insert into item (cantidad,precio_unitario,venta_id,producto_id,compra_unitario)values(?,?,?,?,?);");
                ps.setFloat(1, i.getCantidad());
                ps.setFloat(2, i.getPrecioUnitario());
                ps.setInt(3, i.getVentaId());
                ps.setInt(4, i.getProductoId());
                ps.setFloat(5, i.getCompraUnitario());
                ps.executeUpdate();
                ps.close();
                faltantePorBender=i.getCantidad();
                PreparedStatement ps2;
                ResultSet rs2;
                while(faltantePorBender>0){
                    ps2=getConexion().prepareStatement("SELECT id, existencia FROM lote  where producto_id=? and existencia > 0 ORDER  BY caducacion ASC limit 1;");
                    ps2.setInt(1, i.getProductoId());
                    rs2=ps2.executeQuery();
                    if(!rs2.next()){
                        getConexion().rollback();
                        return false;
                    }
                    int idLote=rs2.getInt("id");
                    float existencia=rs2.getFloat("existencia");
                    ps2.close();
                    rs2.close();
                    if(existencia>=faltantePorBender){
                        ps2=getConexion().prepareStatement("update lote set existencia=existencia - ? where id = ?;");
                        ps2.setFloat(1, faltantePorBender);
                        ps2.setInt(2, idLote);
                        ps2.executeUpdate();
                        faltantePorBender=0;
                    }else{
                        ps2=getConexion().prepareStatement("update lote set existencia=existencia - ? where id = ?;");
                        ps2.setFloat(1, existencia);
                        ps2.setInt(2, idLote);
                        ps2.executeUpdate();
                        faltantePorBender=faltantePorBender-existencia;
                        
                    }
                    
                    ps2.close();
                }
                ps2=getConexion().prepareStatement("SELECT receta FROM producto where id=?;");
                ps2.setInt(1, i.getProductoId());
                rs2=ps2.executeQuery();
                rs2.next();

                if(rs2.getBoolean("receta")){
                    ps2.close();
                    ps2=getConexion().prepareStatement("insert into medicamento_controlado(producto_id,medico_cedula,fecha)values(?,?,'"+fecha+"');");
                    ps2.setInt(1,i.getProductoId());
                    ps2.setInt(2, idMeico);
                    ps2.executeUpdate();
                }
                rs2.close();
                ps2.close();
            }
            getConexion().commit();
            return true;
            
        } catch (SQLException ex) {
            try {
                getConexion().rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(SelectUpdate.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(SelectUpdate.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            desconectar();
        }
        return false;
    }
    public List<Departamento> getListAllDepartamento(){
        if(!conectar())
            return null;
        List<Departamento> departamentos=new ArrayList<Departamento>();
        try {
            PreparedStatement ps = getConexion().prepareStatement("select * from departamento;");
            ResultSet rs=ps.executeQuery();
            
            while(rs.next()){
                Departamento d=new Departamento();
                d.setId(rs.getInt("id"));
                d.setNombre(rs.getString("nombre"));
                departamentos.add(d);
            }
            return departamentos;
        } catch (Exception e) {
        }finally{
            desconectar();
        }
        return null;
    }
    public List<RowConsulta> getReporte(String nombreProducto,String fechaI, String fechaF){
        if(!conectar())
            return null;
        List<RowConsulta> rowReportes=new ArrayList<RowConsulta>();
        try {
            PreparedStatement ps = getConexion().prepareStatement("select b.nombre, a.cantidad, c.fecha,a.precio_unitario as precio, a.compra_unitario as compra ,a.compra_unitario * a.cantidad as invercion  ,a.precio_unitario*a.cantidad as total,a.precio_unitario*a.cantidad-a.compra_unitario * a.cantidad  as ganancia,  c.forma_pago  \n" +

                                                                "from item a join producto b on a.producto_id=b.id join venta c on a.venta_id=c.id\n" +

                                                                "where c.fecha >= ? and c.fecha <=? and b.nombre like ? ;");
            ps.setString(1, fechaI);
            ps.setString(2, fechaF);
            ps.setString(3, nombreProducto+"%");
            
            ResultSet rs=ps.executeQuery();
            
            while(rs.next()){
                RowConsulta rowReporte=new RowConsulta();
                rowReporte.getProducto().setNombre(rs.getString("nombre"));
                rowReporte.getItem().setCantidad(rs.getFloat("cantidad"));
                rowReporte.getVenta().setFecha(rs.getString("fecha"));
                rowReporte.getItem().setPrecioUnitario(rs.getFloat("precio"));
                rowReporte.getItem().setCompraUnitario(rs.getFloat("compra"));
                rowReporte.setInvertir(rs.getFloat("invercion"));
                rowReporte.setTotal(rs.getFloat("total"));
                rowReporte.setGanancia(rs.getFloat("ganancia"));
                rowReporte.getVenta().setFormaDePago(rs.getString("forma_pago"));
                rowReportes.add(rowReporte);
            }
            return rowReportes;
        } catch (Exception e) {
            System.out.println(e.getMessage()+"\n"+e.getLocalizedMessage());
        }finally{
            desconectar();
        }
        return null;
    }
    public List<RowConsulta> getTopVenta(String fechaI, String fechaF){
        if(!conectar())
            return null;
        List<RowConsulta> rowReportes=new ArrayList<RowConsulta>();
        try {
            PreparedStatement ps = getConexion().prepareStatement("select b.nombre,sum(a.cantidad) as cantidad ,sum(a.precio_unitario*a.cantidad-a.compra_unitario * a.cantidad ) as ganancia\n" +
                                                                "from item a join producto b on a.producto_id=b.id join venta c on a.venta_id=c.id  " +

                                                                "where c.fecha >= ? and c.fecha <=? GROUP BY b.nombre ORDER BY ganancia DESC ;");
            ps.setString(1, fechaI);
            ps.setString(2, fechaF);
            
            ResultSet rs=ps.executeQuery();
            
            while(rs.next()){
                RowConsulta rowReporte=new RowConsulta();
                rowReporte.getProducto().setNombre(rs.getString("nombre"));
                rowReporte.getItem().setCantidad(rs.getFloat("cantidad"));
                rowReporte.setGanancia(rs.getFloat("ganancia"));
                rowReportes.add(rowReporte);
            }
            return rowReportes;
        } catch (Exception e) {
            System.out.println(e.getMessage()+"\n"+e.getLocalizedMessage());
        }finally{
            desconectar();
        }
        return null;
    }
    public List<RowConsulta> getMedicamentoControlado(String nombreProducto,String fechaI, String fechaF){
        if(!conectar())
            return null;
        List<RowConsulta> rowReportes=new ArrayList<RowConsulta>();
        try {
            PreparedStatement ps = getConexion().prepareStatement("select a.cedula,a.nombre as medico,c.nombre as producto ,b.fecha from medico a join medicamento_controlado b on a.cedula = b.medico_cedula join producto c on b.producto_id=c.id\n" +
"where b.fecha >= ? and b.fecha <=? and c.nombre like ?;");
            ps.setString(1, fechaI);
            ps.setString(2, fechaF);
            ps.setString(3, nombreProducto+"%");
            
            ResultSet rs=ps.executeQuery();
            
            while(rs.next()){
                RowConsulta rowReporte=new RowConsulta();
                rowReporte.getMedico().setCedula(rs.getInt("cedula"));
                rowReporte.getMedico().setNombre(rs.getString("medico"));
                rowReporte.getProducto().setNombre(rs.getString("producto"));
                rowReporte.setFecha(rs.getString("fecha"));
                rowReportes.add(rowReporte);
                
            }
            return rowReportes;
        } catch (Exception e) {
            System.out.println(e.getMessage()+"\n"+e.getLocalizedMessage());
        }finally{
            desconectar();
        }
        return null;
    }

    
    public List<RowConsulta> getInvetario(){
        if(!conectar())
            return null;
        List<RowConsulta> rowReportes=new ArrayList<RowConsulta>();
        try {
            PreparedStatement ps = getConexion().prepareStatement("select  a.nombre, a.precio_venta,b.ubicacion,b.caducacion, b.existencia   from producto a join lote b on a.id = b.producto_id where existencia > 0;");

            
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                RowConsulta rowReporte=new RowConsulta();
                rowReporte.getProducto().setNombre(rs.getString("nombre"));
                rowReporte.getProducto().setPrecioVenta(rs.getFloat("precio_venta"));
                rowReporte.getLote().setUbicacion(rs.getString("ubicacion"));
                rowReporte.getLote().setCaducacion(rs.getString("caducacion"));
                rowReporte.getLote().setExistencia(rs.getFloat("existencia"));
                rowReportes.add(rowReporte);
            }
            return rowReportes;
        } catch (Exception e) {
            System.out.println(e.getMessage()+"\n e "+e.getLocalizedMessage());
        }finally{
            desconectar();
        }
        return null;
    }
    public List<RowConsulta> getCaducado(){
        if(!conectar())
            return null;
        List<RowConsulta> rowReportes=new ArrayList<RowConsulta>();
        try {
            
            LocalDate localDate=LocalDate.now();
            int mes=localDate.getMonthValue();
            int year=localDate.getYear();
            if(mes>=12){
                mes=1;
                year++;
            }else{
                mes++;
            }
            String fecha;
            if(mes<10){
                fecha=year+"-0"+mes+"-01";
            }else{
                fecha=year+"-"+mes+"-01";
            }
            PreparedStatement ps = getConexion().prepareStatement("select  b.id ,a.nombre, a.precio_venta,b.ubicacion,b.caducacion, b.existencia   from producto a join lote b on a.id = b.producto_id where existencia > 0 and  b.caducacion < '"+fecha+"';");

            
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                RowConsulta rowReporte=new RowConsulta();
                rowReporte.getLote().setId(rs.getInt("id"));
                rowReporte.getProducto().setNombre(rs.getString("nombre"));
                rowReporte.getProducto().setPrecioVenta(rs.getFloat("precio_venta"));
                rowReporte.getLote().setUbicacion(rs.getString("ubicacion"));
                rowReporte.getLote().setCaducacion(rs.getString("caducacion"));
                rowReporte.getLote().setExistencia(rs.getFloat("existencia"));
                rowReportes.add(rowReporte);
            }
            return rowReportes;
        } catch (Exception e) {
            System.out.println(e.getMessage()+"\n e "+e.getLocalizedMessage());
        }finally{
            desconectar();
        }
        return null;
    }
    public List<RowConsulta> getProductoAgotado(){
        if(!conectar())
            return null;
        List<RowConsulta> rowReportes=new ArrayList<RowConsulta>();
        List<Integer> ids=new ArrayList<>();
        try {
           
            PreparedStatement ps = getConexion().prepareStatement("SELECT a.id,sum(b.existencia) as cantidad,a.minimo from producto a join lote b on a.id=b.producto_id where b.existencia>=0 GROUP BY a.id;");
            
            
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                if(rs.getInt("cantidad")<=rs.getInt("minimo")){
                    ids.add(rs.getInt("id"));
                    
                }
            }
            for(int i=0;i<ids.size();i++){
                ps = getConexion().prepareStatement("select  a.id ,a.codigo_barra,a.nombre, a.precio_venta, a.precio_compra from producto a where a.id=?;");
                ps.setInt(1, ids.get(i));
                rs=ps.executeQuery();
                if(rs.next()){
                    RowConsulta rowReporte=new RowConsulta();
                    rowReporte.getProducto().setId(rs.getInt("id"));
                    rowReporte.getProducto().setCodigoDeBarra(rs.getString("codigo_barra"));
                    rowReporte.getProducto().setNombre(rs.getString("nombre"));
                    rowReporte.getProducto().setPrecioVenta(rs.getFloat("precio_venta"));
                    rowReporte.getProducto().setPrecioCompra(rs.getFloat("precio_compra"));
                    
                    rowReportes.add(rowReporte);
                }
            
            }
            
            return rowReportes;
        } catch (Exception e) {
            System.out.println(e.getMessage()+"\n e "+e.getLocalizedMessage());
        }finally{
            desconectar();
        }
        return null;
    }

    
    public void bajaLotePorCaducidad(int id){
        if(!conectar())
            return;
        try {
            PreparedStatement ps = getConexion().prepareStatement("select  b.existencia   from  lote b  where b.id = ?;");
            ps.setInt(1, id);
            ResultSet rs=ps.executeQuery();
            if(!rs.next()){
                
                return;
            }
            float cantidad=rs.getFloat("existencia");
            cantidad*=-1;
            ps = getConexion().prepareStatement("update  lote set existencia = ? where id=?;");
            ps.setFloat(1, cantidad);
            ps.setInt(2, id);
            
            ps.executeUpdate();
            getConexion().commit();
        } catch (SQLException ex) {
            Logger.getLogger(SelectUpdate.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            desconectar();
        }
    }
    public RowConsulta getDescripcionProducto(int id){
        if(!conectar())return null;
        RowConsulta descripcion=null;
        try {
            PreparedStatement ps=getConexion().prepareStatement("SELECT a.nombre,a.activo,a.descripcion,a.receta,b.ubicacion  FROM producto a JOIN lote b ON a.id=b.producto_id  WHERE a.id= ? AND b.existencia>0 ORDER BY b.caducacion ASC LIMIT 1;");
            ps.setInt(1, id);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                descripcion=new RowConsulta();
                descripcion.getProducto().setNombre(rs.getString("nombre"));
                descripcion.getProducto().setActivo(rs.getString("activo"));
                descripcion.getProducto().setDescripcion(rs.getString("descripcion"));
                descripcion.getProducto().setReceta(rs.getBoolean("receta"));
                descripcion.getLote().setUbicacion(rs.getString("ubicacion"));
                return descripcion;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(SelectUpdate.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            desconectar();
        }
        return null;
    }

}
