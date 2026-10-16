package co.edu.unbosque.model.persistence;

public class ProductoPanaderiaDTO {

    private String nombre;
    private String tipo;
    private int cantidad;

    public ProductoPanaderiaDTO(String nombre, String tipo, int cantidad) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.cantidad = cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }


      //Formatea el objeto para ser guardado directamente en el archivo plano.
    public String toLineaArchivo() {
        return nombre + ";" + tipo + ";" + cantidad;
    }
}