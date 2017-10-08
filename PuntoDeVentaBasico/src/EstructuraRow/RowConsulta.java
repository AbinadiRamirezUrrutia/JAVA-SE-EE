/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EstructuraRow;

import DataBase.Pojo.Item;
import DataBase.Pojo.Lote;
import DataBase.Pojo.Medico;
import DataBase.Pojo.Producto;
import DataBase.Pojo.Venta;

/**
 *
 * @author Abina
 */
public class RowConsulta {
    private Item item;
    private Venta venta;
    private Producto producto;
    private Lote lote;
    private Medico medico;
    private float invertir;
    private float total;
    private float ganancia;
    private String fecha;
    public RowConsulta() {
        item=new Item();
        venta=new Venta();
        producto=new Producto();
        lote=new Lote();
        medico=new Medico();
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public float getInvertir() {
        return invertir;
    }

    public void setInvertir(float invertir) {
        this.invertir = invertir;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public float getGanancia() {
        return ganancia;
    }

    public void setGanancia(float ganancia) {
        this.ganancia = ganancia;
    }

    public Lote getLote() {
        return lote;
    }

    public void setLote(Lote lote) {
        this.lote = lote;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
}
