package co.edu.unbosque.model.persistence;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ArchivoPlanoDAO implements ProductoDAO {

    private final File archivo;

    public ArchivoPlanoDAO() {
        archivo = new File("productos_panaderia.txt");
    }

    //Este metodo se encarga de leer el archivo y obtener los productos con formato ";"
    @Override
    public List<ProductoPanaderiaDTO> leerArchivo() throws IOException {
        List<ProductoPanaderiaDTO> contenido = new ArrayList<>();

        if (!archivo.exists()) {
            return contenido;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(";");

                if (partes.length == 3) {
                    ProductoPanaderiaDTO dto = new ProductoPanaderiaDTO(
                            partes[0],
                            partes[1],
                            Integer.parseInt(partes[2])
                    );
                    contenido.add(dto);
                }
            }
        }
        return contenido;
    }

    //este metodo se encarga de guardar el archivo, no hace nuevos archivos solo lo sobre-escribe
    @Override
    public void escribirArchivo(List<ProductoPanaderiaDTO> listaDTO) throws IOException {
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(archivo, false))) {

            for (ProductoPanaderiaDTO dto : listaDTO) {
                //Usa el String de ProductoPanaderiaDTO  para guardarlo de una vez
                escritor.write(dto.toLineaArchivo());
                escritor.newLine();
            }
        }
    }
}