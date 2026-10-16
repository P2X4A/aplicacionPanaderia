package co.edu.unbosque.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import co.edu.unbosque.constant.GUIBotonConstant;

public class VentanaPrincipal extends JFrame {

    private final ActionListener listener;

    private JButton botonCrear;
    private JButton botonLeer;
    private JButton botonActualizar;
    private JButton botonEliminar;

    private JTable tablaProductos;
    private DefaultTableModel modeloTabla;

    public VentanaPrincipal(ActionListener listener) {
        this.listener = listener;
        initComponents();
        configurarEventos();
    }

    private void initComponents() {
        setTitle("Gestión de productos - Panadería");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        String[] columnas = {"Nombre", "Tipo", "Cantidad"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };

        tablaProductos = new JTable(modeloTabla);
        tablaProductos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollTabla = new JScrollPane(tablaProductos);

        botonCrear = new JButton("Crear");
        botonLeer = new JButton("Leer");
        botonActualizar = new JButton("Actualizar");
        botonEliminar = new JButton("Eliminar");

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panelBotones.add(botonCrear);
        panelBotones.add(botonLeer);
        panelBotones.add(botonActualizar);
        panelBotones.add(botonEliminar);

        setLayout(new BorderLayout());
        add(panelBotones, BorderLayout.NORTH);
        add(scrollTabla, BorderLayout.CENTER);

        setSize(700, 500);
        setLocationRelativeTo(null);
    }

    private void configurarEventos() {
        botonCrear.setActionCommand(GUIBotonConstant.CREAR);
        botonLeer.setActionCommand(GUIBotonConstant.LEER);
        botonActualizar.setActionCommand(GUIBotonConstant.ACTUALIZAR);
        botonEliminar.setActionCommand(GUIBotonConstant.ELIMINAR);

        botonCrear.addActionListener(listener);
        botonLeer.addActionListener(listener);
        botonActualizar.addActionListener(listener);
        botonEliminar.addActionListener(listener);
    }

    public void actualizarTabla(List<Object[]> filas) {
        modeloTabla.setRowCount(0);
        for (Object[] fila : filas) {
            modeloTabla.addRow(fila);
        }
    }

    public int obtenerFilaSeleccionada() {
        return tablaProductos.getSelectedRow();
    }

    public String obtenerNombreSeleccionado() {
        int fila = obtenerFilaSeleccionada();
        if (fila == -1) {
            return null;
        }
        return (String) modeloTabla.getValueAt(fila, 0);
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Información", JOptionPane.INFORMATION_MESSAGE);
    }

    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }
}