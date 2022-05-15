/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package formularios;

import entidades.Habitat;
import interfaces.INegocio;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * Interfaz gráfica para registrar hábitats
 *
 * @author luisg
 */
public class FrmRegistroHabitat extends javax.swing.JFrame {

    /**
     * Creates new form FrmRegistroHabitat
     *
     * @param negocio
     */
    public FrmRegistroHabitat(INegocio negocio) {
        this.negocio = negocio;

        initComponents();

        this.listaClimas = Arrays.asList("Ecuatorial", "Tropical seco", "Tropical húmedo", "Monzónico", "Árido", "Mediterráneo", "Oceánico", "Continental", "Chino", "Tundra", "Glacial", "Montaña");
        this.listaVegetaciones = Arrays.asList("Acuática", "Halófila", "Gipsófila");
        this.listaContinentesDisponibles = new ArrayList<>();
        this.listaContinentesDisponibles.addAll(Arrays.asList("América", "África", "Antártida", "Asia", "Europa", "Oceanía"));
        this.listaContinentesSeleccionados = new ArrayList<>();
        this.desplegarClimasVegetacionesContinentes();

        this.lblClima.setVisible(false);
        this.cajaCombinadaClima.setVisible(false);
        this.lblVegetacion.setVisible(false);
        this.cajaCombinadaVegetacion.setVisible(false);
        this.panelTablaContinentesDisponibles.setVisible(false);
        this.tablaContinentesDisponibles.setVisible(false);
        this.panelTablaContinentesSeleccionados.setVisible(false);
        this.tablaContinentesSeleccionados.setVisible(false);
        this.btnGuardarHabitat.setVisible(false);
        this.btnAgregarContinente.setVisible(false);
        this.btnEliminarContinente.setVisible(false);
    }

    /**
     * Llena las comboBoxes de climas y vegetaciones y llena la tabla de
     * continentes disponibles
     */
    private void desplegarClimasVegetacionesContinentes() {
        try {
            for (int i = 0; i < listaClimas.size(); i++) {
                String clima = listaClimas.get(i);
                cajaCombinadaClima.addItem(clima);
            }

            for (int i = 0; i < listaVegetaciones.size(); i++) {
                String vegetacion = listaVegetaciones.get(i);
                cajaCombinadaVegetacion.addItem(vegetacion);
            }

            this.llenarTablaDisponibles();
        } catch (Exception ex) {
            this.mostrarMensajeError();
        }
    }

    /**
     * Muestra mensaje de error al activar las comboBoxes y llenar la tabla de
     * continentes disponibles
     */
    private void mostrarMensajeError() {
        JOptionPane.showMessageDialog(this, "Hubo un error al recuperar los climas, vegetaciones y/o continentes", "Error", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Llena la tabla de continentes disponibles
     */
    private void llenarTablaDisponibles() {
        DefaultTableModel modeloTabla = (DefaultTableModel) this.tablaContinentesDisponibles.getModel();
        modeloTabla.setRowCount(0);
        this.listaContinentesDisponibles.forEach(continente -> {
            Object[] fila = new Object[1];
            fila[0] = continente;
            modeloTabla.addRow(fila);
        });
    }

    /**
     * Llena la tabla de continentes seleccionados
     */
    private void llenarTablaSeleccionados() {
        DefaultTableModel modeloTabla = (DefaultTableModel) this.tablaContinentesSeleccionados.getModel();
        modeloTabla.setRowCount(0);
        this.listaContinentesSeleccionados.forEach(continente -> {
            Object[] fila = new Object[1];
            fila[0] = continente;
            modeloTabla.addRow(fila);
        });
    }

    /**
     * Verifica que no haya otro hábitat en la base de datos con el mismo
     * nombre. En caso de que no halla se activarán todos los campos, en caso de
     * que si se mostrarán los datos del hábitat
     */
    private void clickBtnVerificarNombreHabitat() {
        if(this.campoTextoNombre.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "El campo nombre está vacío", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if(this.campoTextoNombre.getText().length() > 100){
            JOptionPane.showMessageDialog(this, "El campo nombre está muy largo", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        Habitat habitat = this.negocio.verificarNombreHabitat(this.campoTextoNombre.getText());
        if (habitat == null) {
            this.activarCampos();
            this.campoTextoNombre.setEditable(false);
            this.btnVerificarNombreHabitat.setVisible(false);
        } else {
            this.mostrarMensajeRepeticion();
            this.activarCampos();
            this.panelTablaContinentesDisponibles.setVisible(false);
            this.tablaContinentesDisponibles.setVisible(false);
            this.btnGuardarHabitat.setVisible(false);
            this.btnAgregarContinente.setVisible(false);
            this.btnEliminarContinente.setVisible(false);
            this.btnVerificarNombreHabitat.setVisible(false);
            this.campoTextoNombre.setText(habitat.getNombre());
            this.campoTextoNombre.setEditable(false);
            this.cajaCombinadaClima.setSelectedItem(habitat.getClima());
            this.cajaCombinadaClima.setEnabled(false);
            this.cajaCombinadaVegetacion.setSelectedItem(habitat.getTipoVegetacion());
            this.cajaCombinadaVegetacion.setEnabled(false);
            this.listaContinentesSeleccionados = habitat.getContinentes();
            this.llenarTablaSeleccionados();
        }
    }

    /**
     * Mostrar mensaje hábitat repetido
     */
    private void mostrarMensajeRepeticion() {
        JOptionPane.showMessageDialog(this, "El hábitat no puede ser dado de alta debido a que ya se encuentra registrado.", "Información", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Activa los campos para añadir un hábitat
     */
    private void activarCampos() {
        this.lblClima.setVisible(true);
        this.cajaCombinadaClima.setVisible(true);
        this.lblVegetacion.setVisible(true);
        this.cajaCombinadaVegetacion.setVisible(true);
        this.panelTablaContinentesDisponibles.setVisible(true);
        this.tablaContinentesDisponibles.setVisible(true);
        this.panelTablaContinentesSeleccionados.setVisible(true);
        this.tablaContinentesSeleccionados.setVisible(true);
        this.btnGuardarHabitat.setVisible(true);
        this.btnAgregarContinente.setVisible(true);
        this.btnEliminarContinente.setVisible(true);
        this.btnEliminarContinente.setEnabled(false);
    }

    /**
     * Se activa cuando el botonGuardarHabitat es presionado, en el caso de que
     * los campos estén vacíos se muestra un mensaje advirtiendo al usuario de
     * esto, en el caso contrario se mandará el hábitat a la base de datos
     */
    private void clickBtnGuardarHabitat() {
        if (this.campoTextoNombre.getText().isEmpty() || this.cajaCombinadaClima.getSelectedItem() == null || this.cajaCombinadaVegetacion.getSelectedItem() == null || this.listaContinentesSeleccionados.size() < 1) {
            this.mostrarMensajeCamposVacios();
            return;
        }
        if(this.campoTextoNombre.getText().length() > 100){
            JOptionPane.showMessageDialog(this, "El campo nombre está muy largo, no debe de tener más de 100 caracteres", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String nombre = this.campoTextoNombre.getText();
        String clima = (String) this.cajaCombinadaClima.getSelectedItem();
        String vegetacion = (String) this.cajaCombinadaVegetacion.getSelectedItem();
        List<String> continentes = this.listaContinentesSeleccionados;
        Habitat habitat = new Habitat(nombre, clima, vegetacion, continentes);
        boolean seAgrego = this.negocio.guardarHabitat(habitat);
        if (seAgrego) {
            this.mostrarMensajeConfirmacion();
        }
    }

    /**
     * Muestra mensaje de campos vacíos
     */
    private void mostrarMensajeCamposVacios() {
        JOptionPane.showMessageDialog(this, "Deben de llenarse el campo nombre y seleccionar clima, vegetación y continentes", "Advertencia", JOptionPane.WARNING_MESSAGE);
    }

    /**
     * Muestra mensaje de que el hábitat se a agregado exitosamente
     */
    private void mostrarMensajeConfirmacion() {
        JOptionPane.showMessageDialog(this, "El hábitat se ha guardado exitosamente", "Información", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Obtiene el continente a seleccionar de la tabla de continentes
     * disponibles
     *
     * @return continente a seleccionar
     */
    private String getContinenteASeleccionar() {
        int indiceFilaSeleccionada = this.tablaContinentesDisponibles.getSelectedRow();
        if (indiceFilaSeleccionada != -1) {
            DefaultTableModel modelo = (DefaultTableModel) this.tablaContinentesDisponibles.getModel();
            int indiceColumnaContinente = 0;
            String continenteASeleccionar = (String) modelo.getValueAt(indiceFilaSeleccionada, indiceColumnaContinente);
            return continenteASeleccionar;
        } else {
            return null;
        }
    }

    /**
     * Obtiene el continente a seleccionar de la tabla de continentes
     * seleccionados
     *
     * @return contienente a eliminar
     */
    private String getContinenteAEliminar() {
        int indiceFilaSeleccionada = this.tablaContinentesSeleccionados.getSelectedRow();
        if (indiceFilaSeleccionada != -1) {
            DefaultTableModel modelo = (DefaultTableModel) this.tablaContinentesSeleccionados.getModel();
            int indiceColumnaContinente = 0;
            String continenteAEliminar = (String) modelo.getValueAt(indiceFilaSeleccionada, indiceColumnaContinente);
            return continenteAEliminar;
        } else {
            return null;
        }
    }

    /**
     * Agrega un continente a la lista de continentes seleccionados y elimina
     * uno de la lista de continentes disponibles
     */
    private void agregarContinenteLista() {
        String continenteSeleccionado = this.getContinenteASeleccionar();
        this.listaContinentesSeleccionados.add(continenteSeleccionado);
        this.listaContinentesDisponibles.remove(continenteSeleccionado);
        this.actualizarLista();
    }

    /**
     * Elimina un continente de la lista de continentes seleccionados y lo
     * devuelve a la lista de continentes disponibles
     */
    private void eliminarContinenteLista() {
        String continenteSeleccionado = this.getContinenteAEliminar();
        this.listaContinentesDisponibles.add(continenteSeleccionado);
        this.listaContinentesSeleccionados.remove(continenteSeleccionado);
        this.actualizarLista();
    }

    /**
     * Actualiza las listas de continentes
     */
    private void actualizarLista() {
        this.llenarTablaDisponibles();
        this.llenarTablaSeleccionados();
        if (this.tablaContinentesDisponibles.getRowCount() == 0) {
            this.inhabilitarBtnAgregar();
        }
        if (this.tablaContinentesDisponibles.getRowCount() > 0) {
            this.habilitarBtnAgregar();
        }
        if (this.tablaContinentesSeleccionados.getRowCount() == 0) {
            this.inhabilitarBtnEliminar();
        }
        if (this.tablaContinentesSeleccionados.getRowCount() > 0) {
            this.habilitarBtnEliminar();
        }
    }

    /**
     * Habilita el btnAgregarContinente
     */
    private void habilitarBtnAgregar() {
        this.btnAgregarContinente.setEnabled(true);
    }

    /**
     * Inhabilita el btnAgregarContinente
     */
    private void inhabilitarBtnAgregar() {
        this.btnAgregarContinente.setEnabled(false);
    }

    /**
     * Habilita el btnEliminarContinente
     */
    private void habilitarBtnEliminar() {
        this.btnEliminarContinente.setEnabled(true);
    }

    /**
     * Inhabilitar el btnEliminarContinente
     */
    private void inhabilitarBtnEliminar() {
        this.btnEliminarContinente.setEnabled(false);
    }

    /**
     * Se activa cuando el btnAgregarContinente es presionado
     */
    private void clickBtnAgregarContinente() {
        this.agregarContinenteLista();
    }

    /**
     * Se activa cuando el btnEliminarContinente es presionado
     */
    private void clickBtnEliminarContinente() {
        this.eliminarContinenteLista();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblNombre = new javax.swing.JLabel();
        lblClima = new javax.swing.JLabel();
        lblVegetacion = new javax.swing.JLabel();
        campoTextoNombre = new javax.swing.JTextField();
        btnVerificarNombreHabitat = new javax.swing.JButton();
        btnGuardarHabitat = new javax.swing.JButton();
        panelTablaContinentesDisponibles = new javax.swing.JScrollPane();
        tablaContinentesDisponibles = new javax.swing.JTable();
        panelTablaContinentesSeleccionados = new javax.swing.JScrollPane();
        tablaContinentesSeleccionados = new javax.swing.JTable();
        btnAgregarContinente = new javax.swing.JButton();
        btnEliminarContinente = new javax.swing.JButton();
        cajaCombinadaClima = new javax.swing.JComboBox<>();
        cajaCombinadaVegetacion = new javax.swing.JComboBox<>();
        btnRegresar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Registro de Hábitats");

        lblNombre.setText("Nombre");

        lblClima.setText("Clima");

        lblVegetacion.setText("Tipo de vegetación");

        btnVerificarNombreHabitat.setText("Verificar Nombre");
        btnVerificarNombreHabitat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerificarNombreHabitatActionPerformed(evt);
            }
        });

        btnGuardarHabitat.setText("Guardar Hábitat");
        btnGuardarHabitat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarHabitatActionPerformed(evt);
            }
        });

        tablaContinentesDisponibles.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Continentes disponibles"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        panelTablaContinentesDisponibles.setViewportView(tablaContinentesDisponibles);

        tablaContinentesSeleccionados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Continentes seleccionados"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        panelTablaContinentesSeleccionados.setViewportView(tablaContinentesSeleccionados);

        btnAgregarContinente.setText("Agregar");
        btnAgregarContinente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarContinenteActionPerformed(evt);
            }
        });

        btnEliminarContinente.setText("Eliminar");
        btnEliminarContinente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarContinenteActionPerformed(evt);
            }
        });

        btnRegresar.setText("Regresar");
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblVegetacion)
                                    .addComponent(lblNombre, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblClima, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(campoTextoNombre)
                                    .addComponent(cajaCombinadaClima, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cajaCombinadaVegetacion, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnGuardarHabitat)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                                .addComponent(btnVerificarNombreHabitat))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(103, 103, 103)
                        .addComponent(btnRegresar)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelTablaContinentesSeleccionados, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
                    .addComponent(panelTablaContinentesDisponibles, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAgregarContinente)
                    .addComponent(btnEliminarContinente))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(panelTablaContinentesDisponibles, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(63, 63, 63)
                                .addComponent(btnAgregarContinente)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(panelTablaContinentesSeleccionados, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btnEliminarContinente)
                                .addGap(65, 65, 65))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNombre)
                            .addComponent(campoTextoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblClima)
                            .addComponent(cajaCombinadaClima, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblVegetacion)
                            .addComponent(cajaCombinadaVegetacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnVerificarNombreHabitat)
                            .addComponent(btnGuardarHabitat))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnRegresar)))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Método para cuando el btnVerificarNombreHabitat es presionado
     *
     * @param evt
     */
    private void btnVerificarNombreHabitatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerificarNombreHabitatActionPerformed
        this.clickBtnVerificarNombreHabitat();
    }//GEN-LAST:event_btnVerificarNombreHabitatActionPerformed

    /**
     * Método para cuando el btnAgregarContinente es presionado
     *
     * @param evt
     */
    private void btnAgregarContinenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarContinenteActionPerformed
        this.clickBtnAgregarContinente();
    }//GEN-LAST:event_btnAgregarContinenteActionPerformed

    /**
     * Método para cuando el btnGuardarHabitat es presionado
     *
     * @param evt
     */
    private void btnGuardarHabitatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarHabitatActionPerformed
        this.clickBtnGuardarHabitat();
    }//GEN-LAST:event_btnGuardarHabitatActionPerformed

    /**
     * Método para cuando el btnEliminarContinente
     *
     * @param evt
     */
    private void btnEliminarContinenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarContinenteActionPerformed
        this.clickBtnEliminarContinente();
    }//GEN-LAST:event_btnEliminarContinenteActionPerformed

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        this.dispose();
        FrmPrincipal.frmHabitat = null;
    }//GEN-LAST:event_btnRegresarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarContinente;
    private javax.swing.JButton btnEliminarContinente;
    private javax.swing.JButton btnGuardarHabitat;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JButton btnVerificarNombreHabitat;
    private javax.swing.JComboBox<String> cajaCombinadaClima;
    private javax.swing.JComboBox<String> cajaCombinadaVegetacion;
    private javax.swing.JTextField campoTextoNombre;
    private javax.swing.JLabel lblClima;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblVegetacion;
    private javax.swing.JScrollPane panelTablaContinentesDisponibles;
    private javax.swing.JScrollPane panelTablaContinentesSeleccionados;
    private javax.swing.JTable tablaContinentesDisponibles;
    private javax.swing.JTable tablaContinentesSeleccionados;
    // End of variables declaration//GEN-END:variables
    private INegocio negocio;
    private List<String> listaClimas;
    private List<String> listaVegetaciones;
    private List<String> listaContinentesDisponibles;
    private List<String> listaContinentesSeleccionados;
}
