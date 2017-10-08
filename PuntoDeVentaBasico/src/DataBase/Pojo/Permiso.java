package DataBase.Pojo;

import java.util.Objects;

public class Permiso {
    private Integer id;
    private String nombre;
    private boolean isOn;
    private String usuarioUsr;
/** @return regresa el valor de Id
 */
    public Integer getId() {
        return id;
    }
/** @param id coloca el valor a Id
 */
    public void setId(Integer id) {
        this.id = id;
    }
/** @return regresa el valor de Nombre
 */
    public String getNombre() {
        return nombre;
    }
/** @param nombre coloca el valor a nombre
 */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
/** @return regresa el valor de isOn en forma booleana
 */
    public boolean isOn() {
        return isOn;
    }
/** @param isOn coloca el valor a isOn
 */
    public void setIsOn(boolean isOn) {
        this.isOn = isOn;
    }
/** @return regresa el valor de UsuarioUsr
 */
    public String getUsuarioUsr() {
        return usuarioUsr;
    }
/** @param usuarioUsr coloca el valor a usuarioUsr
 */
    public void setUsuarioUsr(String usuarioUsr) {
        this.usuarioUsr = usuarioUsr;
    }    
/** @return Regresa la informacion del registro en forma de cadena
 */
    @Override
    public String toString() {
        return "Permiso{" + "id=" + id + ", nombre=" + nombre + ", isOn=" + isOn + ", usuarioUsr=" + usuarioUsr + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.id);
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
        final Permiso other = (Permiso) obj;
        if (!Objects.equals(this.usuarioUsr, other.usuarioUsr)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
}
