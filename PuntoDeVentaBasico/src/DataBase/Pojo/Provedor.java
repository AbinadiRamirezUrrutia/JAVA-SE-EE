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
public class Provedor {
    private Integer id;
    private String nombre;
    private String telefono;
    private String direccion;
    private int sucursalId;
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
/** @return regresa el valor de Nombre
 */
    public String getNombre() {
        return nombre;
    }
/** @param nombre Coloca el valor a Nombre
 */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
/** @return regresa el valor de Telefono
 */
    public String getTelefono() {
        return telefono;
    }
/**
 * 
 * @param telefono Coloca el valor a Telefono
 */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
/** @return regresa el valor de Direccion
 */
    public String getDireccion() {
        return direccion;
    }
/** @param direccion Coloca el valor a Direccion
 */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getSucursalId() {
        return sucursalId;
    }

    public void setSucursalId(int sucursalId) {
        this.sucursalId = sucursalId;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.id);
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
        final Provedor other = (Provedor) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
}
