/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package formularios;

import entidades.Dia;
import entidades.Guia;
import entidades.Itinerario;
import entidades.Zona;
import interfaces.INegocio;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import org.bson.types.ObjectId;

/**
 *
 * @author PC OSCAR
 */
public class FrmRegistroItinerario extends javax.swing.JFrame {

    /**
     * Creates new form FrmRegistroItinerario
     *
     * @param negocio
     */
    public FrmRegistroItinerario(INegocio negocio) {
        this.negocio = negocio;
        initComponents();
        this.setLocationRelativeTo(null);

        this.setTitle("Registro/Consulta de Itinerario");

        this.lblDuracion.setVisible(true);
        this.campoTextoDuracion.setEditable(false);
        this.lblLongitud.setVisible(true);
        this.campoTextoLongitud.setEditable(false);
        this.lblVisitantes.setVisible(true);
        this.campoTextoVisitantes.setEditable(false);
        this.lblGuia.setVisible(true);
        this.cajaCombinadaGuias.setEnabled(false);
        this.lblEspecies.setVisible(false);
        this.campoTextoNumEspecies.setVisible(false);
        this.btnGuardarItinerario.setEnabled(false);
        this.tablaZonas.setEnabled(false);
        this.tablaDiasSemana.setEnabled(false);

        this.recuperarGuias();
        this.recuperarZonas();

        if (listaGuias.isEmpty()) {
            mostrarAdvertenciaListaGuias();
            deshabilitarCampos();
        }

        if (listaZonas.isEmpty()) {
            mostrarAdvertenciaListaZonas();
            deshabilitarCampos();
        }

        if (!listaGuias.isEmpty() && !listaZonas.isEmpty()) {
            this.llenarComboGuias();
            this.llenarListaZonas();
            this.llenarListaDias();
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelTablaZonas = new javax.swing.JScrollPane();
        tablaZonas = new javax.swing.JTable();
        panelTablaDiasSemana = new javax.swing.JScrollPane();
        tablaDiasSemana = new javax.swing.JTable();
        lblDias = new javax.swing.JLabel();
        lblZonas = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        campoTextoNombre = new javax.swing.JTextField();
        lblDuracion = new javax.swing.JLabel();
        btnBuscarItinerarioNombre = new javax.swing.JButton();
        campoTextoDuracion = new javax.swing.JTextField();
        lblLongitud = new javax.swing.JLabel();
        campoTextoLongitud = new javax.swing.JTextField();
        lblVisitantes = new javax.swing.JLabel();
        campoTextoVisitantes = new javax.swing.JTextField();
        btnGuardarItinerario = new javax.swing.JButton();
        btnRegresar = new javax.swing.JButton();
        lblGuia = new javax.swing.JLabel();
        cajaCombinadaGuias = new javax.swing.JComboBox<>();
        lblEspecies = new javax.swing.JLabel();
        campoTextoNumEspecies = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        tablaZonas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Extensión", "Seleccionar"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Double.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        panelTablaZonas.setViewportView(tablaZonas);

        tablaDiasSemana.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Lunes", null},
                {"Martes", null},
                {"Miércoles", null},
                {"Jueves", null},
                {"Viernes", null},
                {"Sábado", null},
                {"Domingo", null}
            },
            new String [] {
                "Día", "Seleccionar"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        panelTablaDiasSemana.setViewportView(tablaDiasSemana);
        if (tablaDiasSemana.getColumnModel().getColumnCount() > 0) {
            tablaDiasSemana.getColumnModel().getColumn(0).setResizable(false);
            tablaDiasSemana.getColumnModel().getColumn(1).setResizable(false);
        }

        lblDias.setText("Días de la semana:");

        lblZonas.setText("Zonas disponibles:");

        jLabel4.setText("Nombre:");

        lblDuracion.setText("Duración (minutos):");

        btnBuscarItinerarioNombre.setText("Verificar");
        btnBuscarItinerarioNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarItinerarioNombreActionPerformed(evt);
            }
        });

        lblLongitud.setText("Longitud (metros):");

        lblVisitantes.setText("Visitantes máximos:");

        btnGuardarItinerario.setText("Guardar itinerario");
        btnGuardarItinerario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarItinerarioActionPerformed(evt);
            }
        });

        btnRegresar.setText("Regresar al menú");
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });

        lblGuia.setText("Guía:");

        lblEspecies.setText("Número de especies: ");

        campoTextoNumEspecies.setEditable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblDuracion)
                                    .addComponent(lblLongitud)
                                    .addComponent(lblVisitantes)
                                    .addComponent(lblGuia, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(campoTextoLongitud)
                                    .addComponent(campoTextoVisitantes)
                                    .addComponent(campoTextoDuracion)
                                    .addComponent(cajaCombinadaGuias, javax.swing.GroupLayout.Alignment.TRAILING, 0, 298, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(btnGuardarItinerario, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnRegresar, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(lblEspecies)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoTextoNumEspecies))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(91, 91, 91)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoTextoNombre)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnBuscarItinerarioNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblZonas)
                    .addComponent(lblDias)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(panelTablaDiasSemana, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(panelTablaZonas, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(31, 31, 31))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(42, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(campoTextoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBuscarItinerarioNombre)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblDuracion)
                            .addComponent(campoTextoDuracion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblLongitud)
                            .addComponent(campoTextoLongitud, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblVisitantes)
                            .addComponent(campoTextoVisitantes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblZonas)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelTablaZonas, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblDias)))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblGuia)
                            .addComponent(cajaCombinadaGuias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(campoTextoNumEspecies, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblEspecies))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnGuardarItinerario)
                            .addComponent(btnRegresar)))
                    .addComponent(panelTablaDiasSemana, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(27, 27, 27))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarItinerarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarItinerarioActionPerformed
        // TODO add your handling code here:

        this.listaZonasSeleccionadas = new ArrayList<Zona>();

        for (int i = 0; i < tablaZonas.getRowCount(); i++) {
            if (this.isSelected(i, 2, tablaZonas)) {
                this.listaZonasSeleccionadas.add(this.listaZonas.get(i));
            }
        }

        this.verificaCampos();

        if (!estaVerificado) {
            return;
        }

        // Validar zonas
        if (this.listaZonasSeleccionadas.size() < 1) {
            JOptionPane.showMessageDialog(this, "Necesitas seleccionar al menos una zona", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Validar dias
        this.llenarListaDiasHoras();

        if (this.listaDiasHoras.size() < 1) {
            JOptionPane.showMessageDialog(this, "Necesitas seleccionar al menos un día", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Guia guia = (Guia) this.cajaCombinadaGuias.getSelectedItem();
        if (guia == null) {
            JOptionPane.showMessageDialog(this, "Hubo un error al obtener el guía seleccionado", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        this.muestraEspacioHoras();

        if (!this.listoDias) {
            return;
        }

        if (this.campoTextoNombre.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Se necesita ingresar un nombre de itinerario para verificar si existen duplicados", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String nombre = this.campoTextoNombre.getText();
        Itinerario itinerario = this.negocio.verificarNombreItinerario(nombre);
        if (itinerario == null) {

            int respuesta = JOptionPane.showConfirmDialog(this, "¿Desea confirmar el registro del Itinerario?", "Confirmar registro de Itinerario", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (respuesta != 0) {
                return;
            }

            itinerario = new Itinerario(new ObjectId(), this.campoTextoNombre.getText(), Integer.parseInt(this.campoTextoDuracion.getText()),
                    Double.parseDouble(this.campoTextoLongitud.getText()), Integer.parseInt(this.campoTextoVisitantes.getText()), this.listaZonasSeleccionadas, this.listaDiasHoras);

            Guia guiaRegistro = (Guia) this.cajaCombinadaGuias.getSelectedItem();

            this.negocio.guardarItinerario(itinerario);
            this.negocio.agregarItinerario(guiaRegistro.getId(), itinerario);
            this.muestraMensajeExitoso();
            this.limpiarCampos();
        } else {
            this.mostrarMensajeItinerarioExistente();
            this.mostrarDatosItinerario(itinerario);
        }
    }//GEN-LAST:event_btnGuardarItinerarioActionPerformed

    /**
     * Limpia los campos del formulario
     */
    private void limpiarCampos() {
        this.campoTextoDuracion.setText("");
        this.campoTextoLongitud.setText("");
        this.campoTextoVisitantes.setText("");
        this.campoTextoNumEspecies.setText("");
        this.recuperarGuias();
        this.recuperarZonas();
        this.llenarListaZonas();
        this.llenarListaDias();
    }

    /**
     * Muestra un mensaje acerca del nombre del itinerario respecto a uno
     * existente
     */
    private void mostrarMensajeItinerarioExistente() {
        JOptionPane.showMessageDialog(this, "El nombre de itinerario dado ya está registrado", "Advertencia", JOptionPane.WARNING_MESSAGE);
    }

    private void muestraMensajeExitoso() {
        JOptionPane.showMessageDialog(this, "El itinerario se guardó satifactoriamente", "Información", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Llena la lista de días y horas
     */
    private void llenarListaDiasHoras() {
        this.listaDiasHoras = new ArrayList<Dia>();

        for (int i = 0; i < tablaDiasSemana.getRowCount(); i++) {
            if (this.isSelected(i, 1, tablaDiasSemana)) {
                this.listaDiasHoras.add(this.listaDias.get(i));
            }
        }
    }

    /**
     * Método que verifica si una casilla está seleccionada
     *
     * @param row La fila
     * @param column La columna
     * @param table La tabla sobe la que se está trabajando
     * @return Verdadero si está seleccionada, falso de forma contraria.
     */
    public boolean isSelected(int row, int column, JTable table) {

        if (table.getValueAt(row, column) != null) {
            return (boolean) table.getValueAt(row, column) != false;
        }
        return false;
    }

    /**
     * Muestra el cuadro de dialogo para introducir la hora de cada día
     */
    private void muestraEspacioHoras() {
        this.listoDias = false;
        List<String> horas = new ArrayList<String>();
        String hora;

        if (this.listaDiasHoras.size() < 1) {
            JOptionPane.showMessageDialog(this, "Ocurrió un error al recuperar la lista de dias seleccionados", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        for (int i = 0; i < listaDiasHoras.size(); i++) {
            hora = JOptionPane.showInputDialog(this, ("Ingresa la hora del itinerario para el día " + listaDiasHoras.get(i).getDescripcion() + " (formato HH:mm de 24 horas): "), "Entrada", JOptionPane.INFORMATION_MESSAGE);
            if (hora == null) {
                JOptionPane.showMessageDialog(this, "No se puede omitir el registro de hora para alguno de los días, intente de nuevo", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (!validarHora(hora)) {
                JOptionPane.showMessageDialog(this, "La hora ingresada no cuenta con el formato HH:mm de 24 horas, intente de nuevo", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }
            horas.add(hora);
        }

        for (int y = 0; y < listaDiasHoras.size(); y++) {
            this.listaDiasHoras.get(y).setHora(horas.get(y));
        }

        this.listoDias = true;
    }

    /**
     * Valida que la hora tenga el formato especificado
     *
     * @param hora La hora en cuestión
     * @return Verdadero si tiene el formato deseado, falso de forma contraria.
     */
    private boolean validarHora(String hora) {
        if (hora == null || hora.isBlank()) {
            return false;
        }

        String horaRegex = "^([0-9]|0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$";

        Pattern pattern = Pattern.compile(horaRegex);

        if (pattern.matcher(hora).matches()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Verifica que los campos sean válidos
     */
    private void verificaCampos() {
        this.estaVerificado = false;
        if (this.campoTextoNombre.getText().isBlank() || this.campoTextoLongitud.getText().isBlank() || this.campoTextoDuracion.getText().isBlank() || this.campoTextoVisitantes.getText().isBlank()) {
            mostrarMensajeErrorCampos();
            return;
        }

        Float duracion, longitud;
        Integer visitantes;

        // Validaciones formato campos correcto
        try {
            duracion = Float.parseFloat(this.campoTextoDuracion.getText());
        } catch (NumberFormatException nf) {
            JOptionPane.showMessageDialog(this, "La duración debe ser una entrada numérica", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            longitud = Float.parseFloat(this.campoTextoLongitud.getText());
        } catch (NumberFormatException nf) {
            JOptionPane.showMessageDialog(this, "La longitud debe ser una entrada numérica", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            visitantes = Integer.parseInt(this.campoTextoVisitantes.getText());
        } catch (NumberFormatException nf) {
            JOptionPane.showMessageDialog(this, "La cantidad de visitantes debe ser un número entero", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validaciones entrada negativa.
        if (duracion <= 0) {
            JOptionPane.showMessageDialog(this, "La duración no puede ser negativa o vacía, vuelva a intentar", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (longitud <= 0) {
            JOptionPane.showMessageDialog(this, "La longitud no puede ser negativa o vacía, vuelva a intentar", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (visitantes <= 0) {
            JOptionPane.showMessageDialog(this, "Debe de aceptar al menos 1 visitante, vuelva a intentar", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Validar entrada adecuada
        if (duracion > 90) {
            JOptionPane.showMessageDialog(this, "Se necesita una duración menor a 90 (1h y 30m), vuelva a intentar", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (longitud > 1500) {
            JOptionPane.showMessageDialog(this, "La longitud debe de tener un máximo de 1500 metros (1.5km), vuelva a intentar", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (visitantes > 30) {
            JOptionPane.showMessageDialog(this, "La cantidad máxima de visitantes es de 30, vuelva a intentar con menos visitantes", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        this.estaVerificado = true;
    }

    /**
     * Muestra mensaje de error de campos vacíos
     */
    private void mostrarMensajeErrorCampos() {
        JOptionPane.showMessageDialog(this, "No pueden existir campos vacíos", "Error", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Deshabilita los campos
     */
    private void deshabilitarCampos() {
        this.lblDuracion.setVisible(true);
        this.campoTextoDuracion.setEditable(false);
        this.lblLongitud.setVisible(true);
        this.campoTextoLongitud.setEditable(false);
        this.lblVisitantes.setVisible(true);
        this.campoTextoVisitantes.setEditable(false);
        this.lblGuia.setVisible(true);
        this.cajaCombinadaGuias.setEnabled(false);
        this.lblEspecies.setVisible(true);
        this.campoTextoNumEspecies.setVisible(true);
        this.btnGuardarItinerario.setEnabled(false);
        this.tablaZonas.setEnabled(false);
        this.tablaDiasSemana.setEnabled(false);

    }

    /**
     * Muestra los campos de consulta
     */
    private void mostrarCamposConsulta() {
        this.deshabilitarCampos();
        this.lblZonas.setText("Zonas del itinerario:");
        this.lblDias.setText("Días del itinerario:");
    }

    /**
     * Llena la lista de zonas.
     */
    private void llenarListaZonas() {
        DefaultTableModel modeloTabla = (DefaultTableModel) this.tablaZonas.getModel();
        modeloTabla.setRowCount(0);

        JTableHeader titulo = tablaZonas.getTableHeader();
        TableColumnModel colMod = titulo.getColumnModel();
        TableColumn tabCol = colMod.getColumn(2);
        tabCol.setHeaderValue("Seleccionar");

        listaZonas.forEach(zona -> {
            Object[] fila = new Object[3];
            fila[0] = zona.getNombre();
            fila[1] = zona.getExtension();
            modeloTabla.addRow(fila);
        });

        agregarCheckBox(2, tablaZonas);
    }

    /**
     * Agrega las checkboxes a la tabla
     *
     * @param columna La columan sobre la que se agregarán
     * @param table La tabla sobre la que agregarán
     */
    private void agregarCheckBox(int columna, JTable table) {
        TableColumn tc = table.getColumnModel().getColumn(columna);
        tc.setCellEditor(table.getDefaultEditor(Boolean.class));
        tc.setCellRenderer(table.getDefaultRenderer(Boolean.class));
    }

    /**
     * Llena la lista de días.
     */
    private void llenarListaDias() {

        this.listaDias = new ArrayList<Dia>();
        listaDias.add(new Dia("Lunes"));
        listaDias.add(new Dia("Martes"));
        listaDias.add(new Dia("Miércoles"));
        listaDias.add(new Dia("Jueves"));
        listaDias.add(new Dia("Viernes"));
        listaDias.add(new Dia("Sábado"));
        listaDias.add(new Dia("Domingo"));

        DefaultTableModel modeloTabla = (DefaultTableModel) this.tablaDiasSemana.getModel();

        JTableHeader titulo = tablaDiasSemana.getTableHeader();
        TableColumnModel colMod = titulo.getColumnModel();
        TableColumn tabCol = colMod.getColumn(1);
        tabCol.setHeaderValue("Seleccionar");

        modeloTabla.setRowCount(0);
        listaDias.forEach(dia -> {
            Object[] fila = new Object[2];
            fila[0] = dia.getDescripcion();
            modeloTabla.addRow(fila);
        });

        agregarCheckBox(1, tablaDiasSemana);
    }

    /**
     * Activa los campos de entrada.
     */
    private void activarCamposEntrada() {
        if (!listaZonas.isEmpty() && !listaGuias.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El itinerario no se encuentra registrado, se iniciará registro de uno nuevo", "Información", JOptionPane.INFORMATION_MESSAGE);
            this.lblDuracion.setVisible(true);
            this.campoTextoDuracion.setEditable(true);
            this.lblLongitud.setVisible(true);
            this.campoTextoLongitud.setEditable(true);
            this.lblVisitantes.setVisible(true);
            this.campoTextoVisitantes.setEditable(true);
            this.lblGuia.setVisible(true);
            this.cajaCombinadaGuias.setEnabled(true);
            this.lblEspecies.setVisible(false);
            this.campoTextoNumEspecies.setVisible(false);
            this.btnGuardarItinerario.setEnabled(true);

            this.lblZonas.setText("Zonas disponibles:");
            this.lblDias.setText("Días de la semana:");
            this.limpiarCampos();
            this.mostrarCajasVerificacionZonas();
        } else {
            JOptionPane.showMessageDialog(this, "Se necesitan guías y zonas registrados habilitar el registro de itinerario", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Muestra las cajas de verificación de zonas.
     */
    private void mostrarCajasVerificacionZonas() {
        this.tablaZonas.setEnabled(true);
        this.tablaDiasSemana.setEnabled(true);
    }

    /**
     * Botón para regresar al menú principal
     *
     * @param evt
     */
    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        // TODO add your handling code here:
        this.regresarMenu();
        FrmPrincipal.frmItinerario = null;
    }//GEN-LAST:event_btnRegresarActionPerformed

    /**
     * Verificar el nombre del itinerario.
     *
     * @param evt Evento
     */
    private void btnBuscarItinerarioNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarItinerarioNombreActionPerformed
        // TODO add your handling code here:
        this.clickBtnBuscarItinerarioNombre();
    }//GEN-LAST:event_btnBuscarItinerarioNombreActionPerformed

    /**
     * Botón buscar itinerario
     */
    private void clickBtnBuscarItinerarioNombre() {
        if (this.campoTextoNombre.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Se necesita ingresar un nombre para verificarlo", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String nombre = this.campoTextoNombre.getText();
        Itinerario itinerario = this.negocio.verificarNombreItinerario(nombre);
        if (itinerario == null) {
            this.activarCamposEntrada();
            this.llenarComboGuias();
        } else {
            this.mostrarMensajeItinerarioExistente();
            this.mostrarDatosItinerario(itinerario);
        }
    }

    /**
     * Muestra los datos de un itinerario
     *
     * @param itinerario
     */
    private void mostrarDatosItinerario(Itinerario itinerario) {

        this.mostrarCamposConsulta();
        this.llenarCamposItinerario(itinerario);
        this.llenarTablaDiasConsulta(itinerario);
        this.llenarTablaZonasConsulta(itinerario);
        this.mostrarGuia(itinerario);
    }

    /**
     * Muestra el guía del itinerario
     *
     * @param itinerario El itinerario en cuestión
     */
    private void mostrarGuia(Itinerario itinerario) {
        DefaultComboBoxModel modeloGuias = new DefaultComboBoxModel();
        Guia guia = this.negocio.consultarGuia(itinerario.getId());

        if (guia == null) {
            JOptionPane.showMessageDialog(this, "No se pudo recuperar el guía del itinerario", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        modeloGuias.addElement(guia);
        cajaCombinadaGuias.setModel(modeloGuias);
    }

    /**
     * Quita una checkbox
     *
     * @param columna La columna de la tabla
     * @param table La tabla en cuestión
     */
    private void quitarCheckBox(int columna, JTable table) {
        TableColumn tc = table.getColumnModel().getColumn(columna);
        tc.setCellEditor(table.getDefaultEditor(String.class));
        tc.setCellRenderer(table.getDefaultRenderer(String.class));
    }

    /**
     * Llena la tabla diasSemana
     *
     * @param itinerario El itinerario de donde se extraerán los datos.
     */
    private void llenarTablaDiasConsulta(Itinerario itinerario) {

        DefaultTableModel modeloTabla = (DefaultTableModel) this.tablaDiasSemana.getModel();
        modeloTabla.setRowCount(0);

        this.quitarCheckBox(1, tablaDiasSemana);

        JTableHeader titulo = tablaDiasSemana.getTableHeader();
        TableColumnModel colMod = titulo.getColumnModel();
        TableColumn tabCol = colMod.getColumn(1);
        tabCol.setHeaderValue("Hora");

        itinerario.getDiasRecorrido().forEach(dia -> {
            Object[] fila = new Object[2];
            fila[0] = dia.getDescripcion();
            fila[1] = dia.getHora();
            modeloTabla.addRow(fila);
        });
    }

    /**
     * Llena la tabla zonas
     *
     * @param itinerario el itinerario de donde se llenarán
     */
    private void llenarTablaZonasConsulta(Itinerario itinerario) {
        DefaultTableModel modeloTabla = (DefaultTableModel) this.tablaZonas.getModel();
        modeloTabla.setRowCount(0);

        JTableHeader titulo = tablaZonas.getTableHeader();
        TableColumnModel colMod = titulo.getColumnModel();
        TableColumn tabCol = colMod.getColumn(2);
        tabCol.setHeaderValue("Seleccionado");

        itinerario.getZonasRecorridas().forEach(zona -> {
            Object[] fila = new Object[3];
            fila[0] = zona.getNombre();
            fila[1] = zona.getExtension();
            fila[2] = true;
            modeloTabla.addRow(fila);
        });
    }

    /**
     * Muestra un mensaje de error al recuperar zonas.
     */
    private void mostrarMensajeErrorZonasSeleccionadas() {
        JOptionPane.showMessageDialog(this, "Hubo un error al recuperar las zonas", "Error", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Muestra un mensaje de error al recuperar guias.
     */
    private void mostrarMensajeErrorGuias() {
        JOptionPane.showMessageDialog(this, "Hubo un error al recuperar los guías", "Error", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Muestra una advertencia de las zonas registradas.
     */
    private void mostrarAdvertenciaListaZonas() {
        JOptionPane.showMessageDialog(this, "No existen zonas registradas", "Error", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Muestra una advertencia sobre los guías
     */
    private void mostrarAdvertenciaListaGuias() {
        JOptionPane.showMessageDialog(this, "No existen guías registrados", "Error", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Obtiene los guías
     */
    private void recuperarGuias() {
        try {
            this.listaGuias = this.negocio.consultarGuias();
        } catch (Exception ex) {
            this.mostrarMensajeErrorGuias();
        }
    }

    /**
     * Obtiene las zonas.
     */
    private void recuperarZonas() {
        try {
            this.listaZonas = this.negocio.consultarZonas();
        } catch (Exception ex) {
            this.mostrarMensajeErrorZonasSeleccionadas();
        }
    }

    /**
     * Llena la comboBox de guías
     */
    private void llenarComboGuias() {
        List<Guia> listaGuias = this.listaGuias;
        DefaultComboBoxModel modeloGuias = new DefaultComboBoxModel();
        listaGuias.forEach(guia -> {
            modeloGuias.addElement(guia);
        });
        cajaCombinadaGuias.setModel(modeloGuias);
    }

    /**
     * Regresa al menú principal
     */
    private void regresarMenu() {
        this.dispose();
        FrmPrincipal.frmItinerario = null;
    }

    /**
     * Llenna los campos del itinerario.
     *
     * @param itinerario El itinerario existente.
     */
    private void llenarCamposItinerario(Itinerario itinerario) {
        this.campoTextoDuracion.setText(String.valueOf(itinerario.getDuracionRecorrido()));
        this.campoTextoLongitud.setText(String.valueOf(itinerario.getLongitud()));
        this.campoTextoVisitantes.setText(String.valueOf(itinerario.getMaximoVisitantes()));
        this.campoTextoNumEspecies.setText(String.valueOf(itinerario.getNumEspecies()));
    }

    /**
     * Declaración de atributos
     */
    private INegocio negocio;
    private List<Guia> listaGuias;
    private List<Zona> listaZonas, listaZonasSeleccionadas;
    private List<Dia> listaDias, listaDiasHoras;
    private boolean listoDias = false;
    private boolean estaVerificado = false;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscarItinerarioNombre;
    private javax.swing.JButton btnGuardarItinerario;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JComboBox<String> cajaCombinadaGuias;
    private javax.swing.JTextField campoTextoDuracion;
    private javax.swing.JTextField campoTextoLongitud;
    private javax.swing.JTextField campoTextoNombre;
    private javax.swing.JTextField campoTextoNumEspecies;
    private javax.swing.JTextField campoTextoVisitantes;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel lblDias;
    private javax.swing.JLabel lblDuracion;
    private javax.swing.JLabel lblEspecies;
    private javax.swing.JLabel lblGuia;
    private javax.swing.JLabel lblLongitud;
    private javax.swing.JLabel lblVisitantes;
    private javax.swing.JLabel lblZonas;
    private javax.swing.JScrollPane panelTablaDiasSemana;
    private javax.swing.JScrollPane panelTablaZonas;
    private javax.swing.JTable tablaDiasSemana;
    private javax.swing.JTable tablaZonas;
    // End of variables declaration//GEN-END:variables

}
