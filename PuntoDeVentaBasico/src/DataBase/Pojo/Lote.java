/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DataBase.Pojo;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class Lote {
    private Integer id;
    private float existencia;
    private String ubicacion;
    private String caducacion;
    private Integer productoId;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public float getExistencia() {
        return existencia;
    }

    public void setExistencia(float existencia) {
        this.existencia = existencia;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getCaducacion() {
        return caducacion;
    }

    public void setCaducacion(String caducacion) {
        this.caducacion = caducacion;
    }

    public Integer getProductoId() {
        return productoId;
    }

    public void setProductoId(Integer productoId) {
        this.productoId = productoId;
    }

    
}
