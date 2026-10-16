package co.edu.unbosque.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import co.edu.unbosque.constant.GUIBotonConstant;
import co.edu.unbosque.constant.TipoProducto;
import co.edu.unbosque.model.AlmacenPanaderia;
import co.edu.unbosque.model.ProductoPanaderia;
import co.edu.unbosque.model.persistence.ArchivoPlanoDAO;
import co.edu.unbosque.model.persistence.DataMapper;
import co.edu.unbosque.model.persistence.ProductoDAO;
import co.edu.unbosque.model.persistence.ProductoPanaderiaDTO;
import co.edu.unbosque.view.VentanaFormulario;
import co.edu.unbosque.view.VentanaPrincipal;

public class Controlador implements ActionListener {

    private AlmacenPanaderia almacenPanaderia;
    private ProductoDAO productoDAO;
    private VentanaPrincipal ventanaPrincipal;
    private VentanaFormulario ventanaFormulario;

    public Controlador() {
        almacenPanaderia = new AlmacenPanaderia();
        productoDAO = new ArchivoPlanoDAO(); // Se inicializa el DAO

        ventanaPrincipal = new VentanaPrincipal(this);
        ventanaFormulario = new VentanaFormulario(ventanaPrincipal, this);

        cargarDatosDeArchivo(); // Carga inicial
    }

    public void ejecutar() {
        ventanaPrincipal.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();

        switch (comando) {
            case GUIBotonConstant.CREAR -> abrirFormularioCrear();
            case GUIBotonConstant.LEER -> leerProductos();
            case GUIBotonConstant.ACTUALIZAR -> abrirFormularioActualizar();
            case GUIBotonConstant.ELIMINAR -> eliminarProducto();
            case GUIBotonConstant.GUARDAR -> guardarProducto();
            case GUIBotonConstant.CANCELAR -> ventanaFormulario.cerrarFormulario();
            default -> ventanaPrincipal.mostrarError("Acción no reconocida.");
        }
    }

    //Metodos para la persistencia

    private void cargarDatosDeArchivo() {
        try {
            List<ProductoPanaderiaDTO> dtos = productoDAO.leerArchivo();
            for (ProductoPanaderiaDTO dto : dtos) {
                // Mapea DTO -> Entidad y guarda en el modelo
                almacenPanaderia.agregarProducto(DataMapper.productoDtoAProducto(dto));
            }
            leerProductos(); // Actualiza la tabla visualmente
        } catch (IOException e) {
            ventanaPrincipal.mostrarError("Error al leer el archivo de persistencia: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            ventanaPrincipal.mostrarError("El archivo contiene datos corruptos.");
        }
    }

    private void actualizarArchivo() {
        try {
            List<ProductoPanaderia> productos = almacenPanaderia.listarProductos();
            List<ProductoPanaderiaDTO> dtos = new ArrayList<>();

            for (ProductoPanaderia p : productos) {
                // Mapea Entidad -> DTO
                dtos.add(DataMapper.productoAProductoDTO(p));
            }

            productoDAO.escribirArchivo(dtos);
        } catch (IOException e) {
            ventanaPrincipal.mostrarError("Error al guardar los cambios en el disco: " + e.getMessage());
        }
    }

    //Metodos CRUD para la vista/GUI

    private void abrirFormularioCrear() {
        ventanaFormulario.configurarModoCrear();
        ventanaFormulario.setVisible(true);
    }

    private void leerProductos() {
        List<ProductoPanaderia> listaModelo = almacenPanaderia.listarProductos();
        List<Object[]> filasVista = new ArrayList<>();

        for (ProductoPanaderia producto : listaModelo) {
            filasVista.add(new Object[]{
                    producto.getNombreProducto(),
                    producto.getTipoProducto().name(),
                    producto.getCantidadProducto()
            });
        }
        ventanaPrincipal.actualizarTabla(filasVista);
    }

    //metodo para actualizar producto
    private void abrirFormularioActualizar() {
        String nombreSeleccionado = ventanaPrincipal.obtenerNombreSeleccionado();
        if (nombreSeleccionado == null) {
            ventanaPrincipal.mostrarError("Debe seleccionar un producto de la tabla.");
            return;
        }

        ProductoPanaderia producto = almacenPanaderia.buscarProducto(nombreSeleccionado);
        if (producto != null) {
            ventanaFormulario.configurarModoActualizar();
            ventanaFormulario.cargarDatos(
                    producto.getNombreProducto(),
                    producto.getTipoProducto().name(),
                    producto.getCantidadProducto()
            );
            ventanaFormulario.setVisible(true);
        }
    }

    private void eliminarProducto() {
        String nombreSeleccionado = ventanaPrincipal.obtenerNombreSeleccionado();
        if (nombreSeleccionado == null) {
            ventanaPrincipal.mostrarError("Debe seleccionar un producto de la tabla.");
            return;
        }

        boolean eliminado = almacenPanaderia.eliminarProducto(nombreSeleccionado);

        if (eliminado) {
            ventanaPrincipal.mostrarMensaje("Producto eliminado exitosamente.");
            actualizarArchivo(); // <-- Guardar en archivo
            leerProductos();     // <-- Actualizar vista
        } else {
            ventanaPrincipal.mostrarError("No se pudo eliminar el producto.");
        }
    }

    private void guardarProducto() {
        try {
            String nombre = ventanaFormulario.obtenerNombre();
            String tipoString = ventanaFormulario.obtenerTipo();
            int cantidad = Integer.parseInt(ventanaFormulario.obtenerCantidad());

            TipoProducto tipo = TipoProducto.valueOf(tipoString);
            ProductoPanaderia nuevoProducto = new ProductoPanaderia(nombre, tipo, cantidad);

            boolean exito;
            if (ventanaFormulario.obtenerNombre() != null && !ventanaFormulario.isNombreHabilitado()) {
                exito = almacenPanaderia.actualizarProducto(nombre, nuevoProducto);
            } else {
                exito = almacenPanaderia.agregarProducto(nuevoProducto);
            }

            if (exito) {
                ventanaFormulario.cerrarFormulario();
                ventanaPrincipal.mostrarMensaje("Operación guardada con éxito.");
                actualizarArchivo(); // <-- Guardar en archivo
                leerProductos();     // <-- Actualizar vista
            } else {
                ventanaPrincipal.mostrarError("Verifique los datos o si el producto ya existe.");
            }

        } catch (NumberFormatException ex) {
            ventanaPrincipal.mostrarError("La cantidad debe ser un número entero válido.");
        } catch (IllegalArgumentException ex) {
            ventanaPrincipal.mostrarError("Tipo de producto no válido.");
        }
    }
}