package co.edu.unbosque.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import co.edu.unbosque.constant.GUIBotonConstant;
import co.edu.unbosque.constant.TipoProducto;

public class VentanaFormulario extends JDialog {

    private final ActionListener listener;

    private JTextField campoNombre;
    private JComboBox<TipoProducto> comboTipo;
    private JTextField campoCantidad;

    private JButton botonGuardar;
    private JButton botonCancelar;

    public VentanaFormulario(JFrame padre, ActionListener listener) {
        super(padre, true);
        this.listener = listener;
        initComponents();
        configurarEventos();
    }

    private void initComponents() {
        setTitle("Formulario de producto");
        setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);

        campoNombre = new JTextField();
        comboTipo = new JComboBox<>(TipoProducto.values());
        campoCantidad = new JTextField();

        botonGuardar = new JButton("Guardar");
        botonCancelar = new JButton("Cancelar");

        JPanel panelCampos = new JPanel(new GridLayout(3, 2, 8, 8));
        panelCampos.setBorder(BorderFactory.createEmptyBorder(15, 15, 10, 15));

        panelCampos.add(new JLabel("Nombre:"));
        panelCampos.add(campoNombre);
        panelCampos.add(new JLabel("Tipo:"));
        panelCampos.add(comboTipo);
        panelCampos.add(new JLabel("Cantidad:"));
        panelCampos.add(campoCantidad);

        JPanel panelBotones = new JPanel();
        panelBotones.add(botonGuardar);
        panelBotones.add(botonCancelar);

        setLayout(new BorderLayout());
        add(panelCampos, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(getParent());
    }

    private void configurarEventos() {
        botonGuardar.setActionCommand(GUIBotonConstant.GUARDAR);
        botonCancelar.setActionCommand(GUIBotonConstant.CANCELAR);

        botonGuardar.addActionListener(listener);
        botonCancelar.addActionListener(listener);
    }

    public String obtenerNombre() {
        return campoNombre.getText().trim();
    }

    public String obtenerTipo() {
        return comboTipo.getSelectedItem().toString();
    }

    public String obtenerCantidad() {
        return campoCantidad.getText().trim();
    }

    public void cargarDatos(String nombre, String tipo, int cantidad) {
        campoNombre.setText(nombre);
        comboTipo.setSelectedItem(TipoProducto.valueOf(tipo));
        campoCantidad.setText(String.valueOf(cantidad));
    }

    public void limpiarCampos() {
        campoNombre.setText("");
        comboTipo.setSelectedIndex(0);
        campoCantidad.setText("");
    }

    public void configurarModoCrear() {
        setTitle("Crear Producto");
        limpiarCampos();
        campoNombre.setEnabled(true);
    }

    public void configurarModoActualizar() {
        setTitle("Actualizar Producto");
        campoNombre.setEnabled(false);
    }

    public boolean isNombreHabilitado() {
        return campoNombre.isEnabled();
    }

    public void cerrarFormulario() {
        setVisible(false);
    }
}