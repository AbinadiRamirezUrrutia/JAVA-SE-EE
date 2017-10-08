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
public class Medico {
    private int cedula;
    private String nombre;

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return  cedula + " " + nombre;
    }
    
}
