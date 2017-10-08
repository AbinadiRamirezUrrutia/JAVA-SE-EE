package DataBase.Dao;

import DataBase.Pojo.Departamento;
import DataBase.Pojo.Item;
import DataBase.Pojo.Lote;
import DataBase.Pojo.Medico;
import DataBase.Pojo.Permiso;
import DataBase.Pojo.Producto;
import DataBase.Pojo.Provedor;
import DataBase.Pojo.Surte;
import DataBase.Pojo.Trabajador;
import DataBase.Pojo.Venta;

public class DataBase extends SelectUpdate implements DBI{
    private static DataBase dataBase=null;
    
    private final DItem dItem;
    private final DProducto dProducto;
    private final DProvedor dProvedor;
    private final DSurte dSurte;
    private final DTrabajador dTrabajador;
    private final DVenta dVenta;
    private final DPermiso dPermiso;
    private final Dmedico dmedico;
    private final DLote dLote;
    private final DPartamento dpartamento;
    
 
    private DataBase() {
        super();
        dItem=new DItem();
        dProducto=new DProducto();
        dProvedor=new DProvedor();
        dSurte=new DSurte();
        dTrabajador=new DTrabajador();
        dVenta=new DVenta();
        dPermiso=new DPermiso();
        dmedico=new Dmedico();
        dLote=new DLote();
        dpartamento=new DPartamento();
    }
    /**
     * permite realizar operaciones CRUD y evita la concurrencia.
     * @return DataBase Instancia la base de datos.  
     */
    
    
    public static DataBase createDateBase(){  
        if(dataBase==null){                  
            dataBase=new DataBase();
        }
        return dataBase;
    }

    @Override
    public boolean insert(Item arg) {
        return dItem.Insert(arg);
    }

    @Override
    public boolean insert(Permiso arg) {
        return dPermiso.Insert(arg);
    }

    @Override
    public boolean insert(Producto arg) {
        return dProducto.Insert(arg);
    }

    @Override
    public boolean insert(Provedor arg) {
        return dProvedor.Insert(arg);
    }

    @Override
    public boolean insert(Surte arg) {
        return dSurte.Insert(arg);
    }

    @Override
    public boolean insert(Trabajador arg) {
        return dTrabajador.Insert(arg);
    }


    @Override
    public boolean delete(Item arg) {
        return dItem.Delete(arg);
    }

    @Override
    public boolean delete(Permiso arg) {
        return dPermiso.Delete(arg);
    }

    @Override
    public boolean delete(Producto arg) {
        return dProducto.Delete(arg);
    }

    @Override
    public boolean delete(Provedor arg) {
        return dProvedor.Delete(arg);
    }

    @Override
    public boolean delete(Surte arg) {
        return dSurte.Delete(arg);
    }

    @Override
    public boolean delete(Trabajador arg) {
        return dTrabajador.Delete(arg);
    }

    
    @Override
    public boolean insert(Venta arg) {
        return dVenta.Insert(arg);
    }

    @Override
    public boolean delete(Venta arg) {
        return dVenta.Delete(arg);
    }

    @Override
    public boolean Update(Item o, Item n) {
        return dItem.Update(o, n);
    }

    @Override
    public boolean Update(Permiso o, Permiso n) {
        return dPermiso.Update(o, n);
    }

    @Override
    public boolean Update(Producto o, Producto n) {
        return dProducto.Update(o, n);
    }

    @Override
    public boolean Update(Provedor o, Provedor n) {
        return dProvedor.Update(o, n);
    }

    @Override
    public boolean Update(Surte o, Surte n) {
        return dSurte.Update(o, n);
    }

    @Override
    public boolean Update(Trabajador o, Trabajador n) {
        return dTrabajador.Update(o, n);
    }



    @Override
    public boolean Update(Venta o, Venta n) {
        return dVenta.Update(o, n);
    }

    @Override
    public boolean insert(Medico arg) {
        return dmedico.Insert(arg);
    }

    @Override
    public boolean delete(Medico arg) {
        return dmedico.Delete(arg);
    }

    @Override
    public boolean insert(Lote arg) {
        return dLote.Insert(arg);
    }

    @Override
    public boolean insert(Departamento arg) {
        return dpartamento.Insert(arg);
    }

}
