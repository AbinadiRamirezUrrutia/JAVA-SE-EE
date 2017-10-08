package DataBase.Pojo;

import java.util.Objects;

public class Item {
    private float cantidad;
    private float precioUnitario;
    private int ventaId;
    private int productoId;
    private Integer id=null;
    private float compraUnitaria;
    public float getCantidad() {
        return cantidad;
    }

    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }

    public float getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(float precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public int getVentaId() {
        return ventaId;
    }

    public void setVentaId(int ventaId) {
        this.ventaId = ventaId;
    }

    public int getProductoId() {
        return productoId;
    }

    public void setProductoId(int productoId) {
        this.productoId = productoId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final Item other = (Item) obj;
        if(this.id==null && other.id==null){
            if (!Objects.equals(this.productoId, other.productoId)) {
                return false;
            }  
        }else{
            if(this.id==null || other.id==null){
                return false;
            }
            if (!Objects.equals(this.id, other.id)) {
                return false;
            }  
        }
        
        return true;
    }

    @Override
    public String toString() {
        return "Item{" + "cantidad=" + cantidad + ", precioUnitario=" + precioUnitario + ", ventaId=" + ventaId + ", productoId=" + productoId + ", id=" + id + '}';
    }

    public float getCompraUnitario() {
        return compraUnitaria;
    }

    public void setCompraUnitario(float compraUnitaria) {
        this.compraUnitaria = compraUnitaria;
    }
 
    
}
