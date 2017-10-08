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
public class Trabajador {
    private Integer id;
    private String nombre;
    private String apellido;
    private String direccion;
    private String telefono;
    private String correo;
    private float salario;
    private int trabajadorIdJefe;
    private int sucursalId;
    private boolean borrado;
    private String usuario;
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
/** @return regresa el valor de Apellido
 */
    public String getApellido() {
        return apellido;
    }
/** @param apellido  Coloca el valor a Apellido
 */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
/** @return regresa el valor de Direccion 
 */
    public String getDireccion() {
        return direccion;
    }
/** @param direccion Coloca el valor a Dirrecion
 */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
/** @return regresa el valor de Telefono
 */
    public String getTelefono() {
        return telefono;
    }
/** @param telefono Coloca el valor a Telefono
 */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
/** @return regresa el valor de Correo
 */
    public String getCorreo() {
        return correo;
    }
/** @param correo Coloca el valor a cCorreo
 */
    public void setCorreo(String correo) {
        this.correo = correo;
    }
/** @return regresa el valor de Salario
 */
    public float getSalario() {
        return salario;
    }
/** @param salario  Coloca el valor a Salario
 */
    public void setSalario(float salario) {
        this.salario = salario;
    }
/** @return regresa la informacion del trabajdo en forma de cade
 */
    @Override
    public String toString() {
        return "Trabajador{" + "id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", direccion=" + direccion + ", telefono=" + telefono + ", correo=" + correo + ", salario=" + salario + '}';
    }

    public int getTrabajadorIdJefe() {
        return trabajadorIdJefe;
    }

    public void setTrabajadorIdJefe(int trabajadorIdJefe) {
        this.trabajadorIdJefe = trabajadorIdJefe;
    }

    public int getSucursalId() {
        return sucursalId;
    }

    public void setSucursalId(int sucursalId) {
        this.sucursalId = sucursalId;
    }

    public boolean isBorrado() {
        return borrado;
    }

    public void setBorrado(boolean borrado) {
        this.borrado = borrado;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.id);
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
        final Trabajador other = (Trabajador) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

   
    
}
