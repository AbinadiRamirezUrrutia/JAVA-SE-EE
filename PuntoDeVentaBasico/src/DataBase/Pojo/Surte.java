/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBase.Pojo;

/**
 *
 * @author Abina
 */
public class Surte {
    private Integer id;
    private float cantidad;
    private String fecha;
    private int trabajadorId;
    
/** @return regresa el valor de id 
 */
    public Integer getId() {
        return id;
    }
/** @param id Coloca el valor a Id
 */
    public void setId(Integer id) {
        this.id = id;
    }
/** @return regresa el valor de id 
 */    
    public float getCantidad() {
        return cantidad;
    }
/** @param cantidad Coloca el valor a cantidad
 */
    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }
/** @return regresa el valor de Fecha
 */
    public String getFecha() {
        return fecha;
    }
/** @param fecha Coloca el valor a Fecha
 */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

/** @return regresa el valor de TrabajadorId
 */
    public int getTrabajadorId() {
        return trabajadorId;
    }
/** @param trabajadorId Coloca el valor a TrabajadorId
 */
    public void setTrabajadorId(int trabajadorId) {
        this.trabajadorId = trabajadorId;
    }
    
}
