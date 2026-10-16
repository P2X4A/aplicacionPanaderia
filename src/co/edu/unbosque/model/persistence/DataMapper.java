package co.edu.unbosque.model.persistence;

import co.edu.unbosque.constant.TipoProducto;
import co.edu.unbosque.model.ProductoPanaderia;

public class DataMapper {

    public static ProductoPanaderiaDTO productoAProductoDTO(ProductoPanaderia producto) {
        return new ProductoPanaderiaDTO(
                producto.getNombreProducto(),
                producto.getTipoProducto().name(),
                producto.getCantidadProducto()
        );
    }

    public static ProductoPanaderia productoDtoAProducto(ProductoPanaderiaDTO dto) {
        return new ProductoPanaderia(
                dto.getNombre(),
                TipoProducto.valueOf(dto.getTipo()),
                dto.getCantidad()
        );
    }
}