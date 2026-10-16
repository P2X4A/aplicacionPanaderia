package co.edu.unbosque.model.persistence;

import java.io.IOException;
import java.util.List;

public interface ProductoDAO {
    List<ProductoPanaderiaDTO> leerArchivo() throws IOException;

    void escribirArchivo(List<ProductoPanaderiaDTO> listaDTO) throws IOException;
}