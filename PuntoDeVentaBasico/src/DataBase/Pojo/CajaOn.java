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
public class CajaOn {
    private Integer id;
    private String fechaInicio;
    private String fechaSalida;
    private float inicioSaldo;
    private float total;
    private int cajaId;
    private int trabajadorId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(String fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public float getInicioSaldo() {
        return inicioSaldo;
    }

    public void setInicioSaldo(float inicioSaldo) {
        this.inicioSaldo = inicioSaldo;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public int getCajaId() {
        return cajaId;
    }

    public void setCajaId(int cajaId) {
        this.cajaId = cajaId;
    }

    public int getTrabajadorId() {
        return trabajadorId;
    }

    public void setTrabajadorId(int trabajadorId) {
        this.trabajadorId = trabajadorId;
    }
    
}
