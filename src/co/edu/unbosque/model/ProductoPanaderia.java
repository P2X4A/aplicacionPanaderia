package co.edu.unbosque.model;

import java.util.Locale;
import java.util.Objects;

import co.edu.unbosque.constant.TipoProducto;

public class ProductoPanaderia {

    private String nombreProducto;
    private TipoProducto tipoProducto;
    private int cantidadProducto;

    public ProductoPanaderia(String nombreProducto, TipoProducto tipoProducto, int cantidadProducto) {
        this.nombreProducto = nombreProducto;
        this.tipoProducto = tipoProducto;
        this.cantidadProducto = cantidadProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public TipoProducto getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(TipoProducto tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

    public int getCantidadProducto() {
        return cantidadProducto;
    }

    public void setCantidadProducto(int cantidadProducto) {
        this.cantidadProducto = cantidadProducto;
    }

    @Override
    public boolean equals(Object objeto) {
        if (this == objeto) {
            return true;
        }
        if (objeto == null || getClass() != objeto.getClass()) {
            return false;
        }
        ProductoPanaderia otroProducto = (ProductoPanaderia) objeto;
        return nombreProducto != null
                && otroProducto.nombreProducto != null
                && nombreProducto.equalsIgnoreCase(otroProducto.nombreProducto);
    }

    @Override
    public int hashCode() {
        if (nombreProducto == null) {
            return 0;
        }
        return Objects.hash(nombreProducto.toLowerCase(Locale.ROOT));
    }

    @Override
    public String toString() {
        return "Nombre Producto: " + nombreProducto
                + ", Tipo Producto: " + tipoProducto
                + ", Cantidad Producto: " + cantidadProducto;
    }
}