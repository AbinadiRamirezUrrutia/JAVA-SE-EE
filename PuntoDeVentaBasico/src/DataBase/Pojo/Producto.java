/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBase.Pojo;

import java.util.Objects;

/**
 *
 * @author Abina
 */
public class Producto {
    private Integer id;
    private String codigoDeBarra;
    private String nombre;
    private float precioCompra;
    private float precioVenta;
    private String activo;
    private String descripcion;
    private boolean receta;
    private boolean borrado;
    private int departamentoId;
    private float minimo;
    


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigoDeBarra() {
        return codigoDeBarra;
    }

    public void setCodigoDeBarra(String codigoDeBarra) {
        this.codigoDeBarra = codigoDeBarra;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(float precioCompra) {
        this.precioCompra = precioCompra;
    }

    public float getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(float precioVenta) {
        this.precioVenta = precioVenta;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isReceta() {
        return receta;
    }

    public void setReceta(boolean receta) {
        this.receta = receta;
    }

    public boolean isBorrado() {
        return borrado;
    }

    public void setBorrado(boolean borrado) {
        this.borrado = borrado;
    }

    public int getDepartamentoId() {
        return departamentoId;
    }

    public void setDepartamentoId(int departamentoId) {
        this.departamentoId = departamentoId;
    }


    public Producto clonar(){
        Producto aux=new Producto();
        aux.setId(this.id);
        aux.setCodigoDeBarra(this.codigoDeBarra);
        aux.setNombre(this.nombre);
        aux.setPrecioCompra(this.precioCompra);
        aux.setPrecioVenta(this.precioVenta);
        aux.setActivo(this.activo);
        aux.setDescripcion(this.descripcion);
        aux.setReceta(this.receta);
        aux.setBorrado(this.borrado);
        aux.setMinimo(this.minimo);
        aux.setDepartamentoId(this.departamentoId);
        return aux;
    }

    @Override
    public String toString() {
        return "Producto{" + "id=" + id + ", codigoDeBarra=" + codigoDeBarra + ", nombre=" + nombre + ", precioCompra=" + precioCompra + ", precioVenta=" + precioVenta + ", activo=" + activo + ", descripcion=" + descripcion + ", receta=" + receta + ", borrado=" + borrado + ", departamentoId=" + departamentoId + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Producto other = (Producto) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    public float getMinimo() {
        return minimo;
    }

    public void setMinimo(float minimo) {
        this.minimo = minimo;
    }
    
}
