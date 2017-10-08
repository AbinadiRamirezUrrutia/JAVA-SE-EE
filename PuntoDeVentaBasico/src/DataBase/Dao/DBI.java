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

/**
 *
 * @author Abina
 * Estandarizan todo los dao.
 */
public interface DBI {
    public boolean insert(Item arg);
    public boolean insert(Permiso arg);
    public boolean insert(Producto arg);
    public boolean insert(Provedor arg);
    public boolean insert(Surte arg);
    public boolean insert(Trabajador arg);
    public boolean insert(Venta arg);
    public boolean insert(Medico arg);
    public boolean insert(Lote arg);
    public boolean insert(Departamento arg);
    
    public boolean Update(Item o,Item n);
    public boolean Update(Permiso o,Permiso n);
    public boolean Update(Producto o,Producto n);
    public boolean Update(Provedor o,Provedor n);
    public boolean Update(Surte o,Surte n);
    public boolean Update(Trabajador o,Trabajador n);
    public boolean Update(Venta o,Venta n);
    
    
    public boolean delete(Item arg);
    public boolean delete(Permiso arg);
    public boolean delete(Producto arg);
    public boolean delete(Provedor arg);
    public boolean delete(Surte arg);
    public boolean delete(Trabajador arg);
    public boolean delete(Venta arg);
    public boolean delete(Medico arg);
}
