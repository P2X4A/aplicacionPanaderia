package co.edu.unbosque.model;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import co.edu.unbosque.constant.TipoProducto;

public class AlmacenPanaderia {

    private final Set<ProductoPanaderia> productos;

    public AlmacenPanaderia() {
        productos = new LinkedHashSet<>();
    }

    public boolean agregarProducto(ProductoPanaderia producto) {
        if (!datosValidos(producto)) return false;
        return productos.add(producto);
    }

    public List<ProductoPanaderia> listarProductos() {
        return new ArrayList<>(productos);
    }

    public ProductoPanaderia buscarProducto(String nombreProducto) {
        if (nombreProducto == null || nombreProducto.trim().isEmpty()) return null;
        for (ProductoPanaderia producto : productos) {
            if (producto.getNombreProducto().equalsIgnoreCase(nombreProducto.trim())) {
                return producto;
            }
        }
        return null;
    }

    public boolean actualizarProducto(String nombreProducto, ProductoPanaderia nuevoProducto) {
        ProductoPanaderia productoExistente = buscarProducto(nombreProducto);
        if (productoExistente == null || nuevoProducto == null
                || nuevoProducto.getTipoProducto() == null || nuevoProducto.getCantidadProducto() <= 0) {
            return false;
        }
        productos.remove(productoExistente);
        nuevoProducto.setNombreProducto(productoExistente.getNombreProducto());
        return productos.add(nuevoProducto);
    }

    public boolean eliminarProducto(String nombreProducto) {
        ProductoPanaderia producto = buscarProducto(nombreProducto);
        if (producto == null) return false;
        return productos.remove(producto);
    }

    private boolean datosValidos(ProductoPanaderia producto) {
        return producto != null
                && producto.getNombreProducto() != null
                && !producto.getNombreProducto().trim().isEmpty()
                && producto.getTipoProducto() != null
                && producto.getCantidadProducto() > 0;
    }
}