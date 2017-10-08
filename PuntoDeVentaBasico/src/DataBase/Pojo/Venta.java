
package DataBase.Pojo;

public class Venta {
    
    private Integer id;
    private String fecha;
    private float total;
    private String formaDePago;
    private int cajaOnId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public String getFormaDePago() {
        return formaDePago;
    }

    public void setFormaDePago(String formaDePago) {
        this.formaDePago = formaDePago;
    }

 
    public int getCajaOnId() {
        return cajaOnId;
    }

    public void setCajaOnId(int cajaOnId) {
        this.cajaOnId = cajaOnId;
    }
 
}
