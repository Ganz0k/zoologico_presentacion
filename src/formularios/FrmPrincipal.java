/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package formularios;

import entidades.Cuidador;
import entidades.Especie;
import entidades.Habitat;
import factory.FabricaNegocios;
import interfaces.INegocio;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.bson.types.ObjectId;

/**
 *
 * @author luisg
 */
public class FrmPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form FrmPrincipal
     */
    public FrmPrincipal() {
        this.negocio = FabricaNegocios.crearFNegocio();

        initComponents();

        this.listaHabitats = new ArrayList<>();
        this.listaCuidadores = new ArrayList<>();
        this.lblNombre.setVisible(false);
        this.campoTextoNombre.setVisible(false);
        this.lblNombreCientifico.setVisible(false);
        this.campoTextoNombreCientifico.setVisible(false);
        this.lblDescripcion.setVisible(false);
        this.campoTextoDescripcion.setVisible(false);
        this.lblHabitat.setVisible(false);
        this.cajaCombinadaHabitats.setVisible(false);
        this.lblCuidador.setVisible(false);
        this.cajaCombinadaCuidadores.setVisible(false);
        this.btnVerificarNombreEspecie.setVisible(false);
        this.btnGuardarEspecie.setVisible(false);
        this.btnEditarAnimales.setVisible(false);
    }

    /**
     * Llena las listas de habitats y cuidadores para llenar sus respectivas
     * cajas combinadas
     */
    private void clickBtnRegistrarActualizarEspecie() {
        try {
            this.listaHabitats = this.negocio.consultarHabitats();
            for (int i = 0; i < listaHabitats.size(); i++) {
                Habitat habitat = listaHabitats.get(i);
                cajaCombinadaHabitats.addItem(habitat.getId() + ", " + habitat.getNombre());
            }
        } catch (Exception ex) {
            this.mostrarMensajeErrorHabitats();
        }

        try {
            this.listaCuidadores = this.negocio.consultarCuidadores();
            for (int i = 0; i < listaCuidadores.size(); i++) {
                Cuidador cuidador = listaCuidadores.get(i);
                cajaCombinadaCuidadores.addItem(cuidador.getId() + ", " + cuidador.getNombre());
            }
        } catch (Exception ex) {
            this.mostrarMensajeErrorCuidadores();
        }

        this.lblNombre.setVisible(true);
        this.campoTextoNombre.setVisible(true);
        this.btnVerificarNombreEspecie.setVisible(true);
        this.btnRegistrarHabitat.setVisible(false);
        this.btnRegistrarActualizarEspecie.setVisible(false);
        this.btnRegistrarActualizarItinerarios.setVisible(false);
    }

    /**
     * Muestra mensaje de error al recuperar los hábitats
     */
    private void mostrarMensajeErrorHabitats() {
        JOptionPane.showMessageDialog(this, "Hubo un error al recuperar los hábitats", "Error", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Muestra mensaje de error al recuperar los cuidadores
     */
    private void mostrarMensajeErrorCuidadores() {
        JOptionPane.showMessageDialog(this, "Hubo un error al recuperar los cuidadores", "Error", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Manda a llamar el método verificarNombreEspecie de la interfaz INegocio
     */
    private void clickBtnVerificarNombreEspecie() {
        if (this.campoTextoNombre.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El campo nombre está vacío", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String nombre = this.campoTextoNombre.getText();
        Especie especie = this.negocio.consultarEspecie(nombre);
        if (especie == null) {
            this.activarCamposEspecie();
        } else {
            this.mostrarMensajeEspecieExistente();
            this.mostrarDatosEspecie(especie);
        }
    }

    /**
     * Activa los campos para registrar una especie
     */
    private void activarCamposEspecie() {
        this.lblNombreCientifico.setVisible(true);
        this.campoTextoNombreCientifico.setVisible(true);
        this.lblDescripcion.setVisible(true);
        this.campoTextoDescripcion.setVisible(true);
        this.lblHabitat.setVisible(true);
        this.cajaCombinadaHabitats.setVisible(true);
        this.lblCuidador.setVisible(true);
        this.cajaCombinadaCuidadores.setVisible(true);
        this.btnGuardarEspecie.setVisible(true);
        this.btnEditarAnimales.setVisible(true);
        this.btnVerificarNombreEspecie.setVisible(false);
        this.btnRegistrarHabitat.setVisible(false);
        this.btnRegistrarActualizarItinerarios.setVisible(false);
        this.btnRegistrarActualizarEspecie.setVisible(false);
    }

    /**
     * Muestra mensaje de especie ya existente
     */
    private void mostrarMensajeEspecieExistente() {
        JOptionPane.showMessageDialog(this, "La especie no puede ser dada de alta debido a que ya se encuentra una registrada.", "Información", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Muestra los datos de la especie ya registrada
     *
     * @param especie especie a mostrar
     */
    private void mostrarDatosEspecie(Especie especie) {
        this.activarCamposEspecie();
        this.campoTextoNombreCientifico.setText(especie.getNombreCientifico());
        this.campoTextoDescripcion.setText(especie.getDescripcion());
        this.cajaCombinadaCuidadores.setEnabled(true);
        this.cajaCombinadaHabitats.setEnabled(true);
        this.campoTextoNombre.setEditable(false);
        this.campoTextoNombreCientifico.setEditable(false);
        this.campoTextoDescripcion.setEditable(false);
        this.btnGuardarEspecie.setEnabled(true);
        this.btnGuardarEspecie.setText("Editar");
    }

    /**
     * Guarda la especie en la base de datos
     */
    private void clickBtnGuardarEspecie() {
        if (this.campoTextoNombre.getText().isEmpty() || this.campoTextoNombreCientifico.getText().isEmpty() || this.campoTextoDescripcion.getText().isEmpty() || this.cajaCombinadaCuidadores.getSelectedItem() == null || this.cajaCombinadaHabitats.getSelectedItem() == null) {
            this.mostrarMensajeCamposVacios();
            return;
        }
        String nombre = this.campoTextoNombre.getText();
        String nombreCientifico = this.campoTextoNombreCientifico.getText();
        String descripcion = this.campoTextoDescripcion.getText();
        Especie especie = new Especie(new ObjectId(), nombre, nombreCientifico, descripcion);
        Especie bEspecie = this.negocio.consultarEspecie(especie);

        if (bEspecie != null) {
            this.mostrarMensajeEspecieRepetida(bEspecie);

            int indiceCuidadorSeleccionado = this.cajaCombinadaCuidadores.getSelectedIndex();
            ObjectId idCuidador = this.listaCuidadores.get(indiceCuidadorSeleccionado).getId();
            this.negocio.agregarEspecieCuidador(idCuidador, new Especie(bEspecie.getId(), bEspecie.getNombreEspaniol()));

            int indiceHabitatSeleccionado = this.cajaCombinadaHabitats.getSelectedIndex();
            ObjectId idHabitat = this.listaHabitats.get(indiceHabitatSeleccionado).getId();
            this.negocio.agregarEspecieHabitat(idHabitat, bEspecie.getId());
            return;
        }
        boolean seAgrego = this.negocio.guardarEspecie(especie);
        if (seAgrego) {
            this.mostrarMensajeEspecieGuardada(especie);

            int indiceCuidadorSeleccionado = this.cajaCombinadaCuidadores.getSelectedIndex();
            ObjectId idCuidador = this.listaCuidadores.get(indiceCuidadorSeleccionado).getId();
            this.negocio.agregarEspecieCuidador(idCuidador, new Especie(especie.getId(), especie.getNombreEspaniol()));

            int indiceHabitatSeleccionado = this.cajaCombinadaHabitats.getSelectedIndex();
            ObjectId idHabitat = this.listaHabitats.get(indiceHabitatSeleccionado).getId();
            this.negocio.agregarEspecieHabitat(idHabitat, especie.getId());
        }
    }

    /**
     * Muestra mensaje campos vaciós
     */
    private void mostrarMensajeCamposVacios() {
        JOptionPane.showMessageDialog(this, "Deben llenarse todos los campos para poder registrar la especie", "Advertencia", JOptionPane.WARNING_MESSAGE);
    }

    /**
     * Muestra mensaje de especie repetida
     *
     * @param especie especie repetida
     */
    private void mostrarMensajeEspecieRepetida(Especie especie) {
        JOptionPane.showMessageDialog(this, "Ya existe una especie con el nombre científico: " + especie.getNombreCientifico(), "Información", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Muestra mensaje especie guardada
     *
     * @param especie esepecie guardada
     */
    private void mostrarMensajeEspecieGuardada(Especie especie) {
        JOptionPane.showMessageDialog(this, "La especie se ha guardado exitosamente, su ID es el siguiente: " + especie.getId(), "Información", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     *
     */
    private void clickBtnRegistrarActualizarItinerario() {
        if (frmItinerario != null) {

        } else {
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    frmItinerario = new FrmRegistroItinerario(negocio);
                    frmItinerario.setVisible(true);
                }
            });
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

        btnRegistrarHabitat = new javax.swing.JButton();
        btnRegistrarActualizarEspecie = new javax.swing.JButton();
        btnRegistrarActualizarItinerarios = new javax.swing.JButton();
        lblNombre = new javax.swing.JLabel();
        campoTextoNombre = new javax.swing.JTextField();
        lblNombreCientifico = new javax.swing.JLabel();
        campoTextoNombreCientifico = new javax.swing.JTextField();
        lblDescripcion = new javax.swing.JLabel();
        campoTextoDescripcion = new javax.swing.JTextField();
        btnVerificarNombreEspecie = new javax.swing.JButton();
        btnGuardarEspecie = new javax.swing.JButton();
        lblHabitat = new javax.swing.JLabel();
        lblCuidador = new javax.swing.JLabel();
        cajaCombinadaHabitats = new javax.swing.JComboBox<>();
        cajaCombinadaCuidadores = new javax.swing.JComboBox<>();
        btnEditarAnimales = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Menú Principal");

        btnRegistrarHabitat.setText("Registrar Hábitat");
        btnRegistrarHabitat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarHabitatActionPerformed(evt);
            }
        });

        btnRegistrarActualizarEspecie.setText("Registro/Actualización Especies");
        btnRegistrarActualizarEspecie.setToolTipText("");
        btnRegistrarActualizarEspecie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActualizarEspecieActionPerformed(evt);
            }
        });

        btnRegistrarActualizarItinerarios.setText("Registro/Actualización Itinerarios");
        btnRegistrarActualizarItinerarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActualizarItinerariosActionPerformed(evt);
            }
        });

        lblNombre.setText("Nombre");

        lblNombreCientifico.setText("Nombre científico");

        lblDescripcion.setText("Descripción");

        btnVerificarNombreEspecie.setText("Verificar Nombre");
        btnVerificarNombreEspecie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerificarNombreEspecieActionPerformed(evt);
            }
        });

        btnGuardarEspecie.setText("Guardar");
        btnGuardarEspecie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarEspecieActionPerformed(evt);
            }
        });

        lblHabitat.setText("Hábitat");

        lblCuidador.setText("Cuidador");

        btnEditarAnimales.setText("Editar Animales");
        btnEditarAnimales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarAnimalesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblNombre)
                            .addComponent(lblNombreCientifico)
                            .addComponent(lblDescripcion)
                            .addComponent(lblHabitat)
                            .addComponent(lblCuidador))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnGuardarEspecie)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnEditarAnimales))
                            .addComponent(campoTextoNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
                            .addComponent(campoTextoNombreCientifico)
                            .addComponent(campoTextoDescripcion)
                            .addComponent(cajaCombinadaHabitats, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cajaCombinadaCuidadores, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                        .addComponent(btnVerificarNombreEspecie))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnRegistrarActualizarEspecie, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnRegistrarHabitat, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnRegistrarActualizarItinerarios)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnRegistrarHabitat))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(btnRegistrarActualizarItinerarios)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRegistrarActualizarEspecie)
                .addGap(64, 64, 64)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombre)
                    .addComponent(campoTextoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVerificarNombreEspecie))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombreCientifico)
                    .addComponent(campoTextoNombreCientifico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDescripcion)
                    .addComponent(campoTextoDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblHabitat)
                    .addComponent(cajaCombinadaHabitats, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCuidador)
                    .addComponent(cajaCombinadaCuidadores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardarEspecie)
                    .addComponent(btnEditarAnimales))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Crea un objeto FrmRegistroHabitat al ser presionado el
     * btnRegistrarHabitat
     *
     * @param evt
     */
    private void btnRegistrarHabitatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarHabitatActionPerformed
        if (frmHabitat != null) {

        } else {
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    frmHabitat = new FrmRegistroHabitat(negocio);
                    frmHabitat.setVisible(true);
                }
            });
        }
    }//GEN-LAST:event_btnRegistrarHabitatActionPerformed

    /**
     * Inicia el caso de uso de Registrar especie
     *
     * @param evt
     */
    private void btnRegistrarActualizarEspecieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActualizarEspecieActionPerformed
        this.clickBtnRegistrarActualizarEspecie();
        this.setTitle("Registrar/Actualizar Especie");
    }//GEN-LAST:event_btnRegistrarActualizarEspecieActionPerformed

    /**
     * Verifica el nombre de la especie
     *
     * @param evt
     */
    private void btnVerificarNombreEspecieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerificarNombreEspecieActionPerformed
        this.clickBtnVerificarNombreEspecie();
    }//GEN-LAST:event_btnVerificarNombreEspecieActionPerformed

    /**
     * Guarda una especie
     *
     * @param evt
     */
    private void btnGuardarEspecieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarEspecieActionPerformed
        this.clickBtnGuardarEspecie();
    }//GEN-LAST:event_btnGuardarEspecieActionPerformed

    /**
     * Crea un objeto FrmEditarAnimal
     *
     * @param evt
     */
    private void btnEditarAnimalesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarAnimalesActionPerformed
        String nombre = this.campoTextoNombre.getText();
        Especie especie = this.negocio.consultarEspecie(nombre);

        if (especie == null) {
            JOptionPane.showMessageDialog(this, "Necesita ingresar el nombre de una especie existente para editar sus animales", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (frmAnimal != null) {

        } else {
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    frmAnimal = new FrmEditarAnimal(especie, negocio);
                    frmAnimal.setVisible(true);
                }
            });
        }
    }//GEN-LAST:event_btnEditarAnimalesActionPerformed

    private void btnRegistrarActualizarItinerariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActualizarItinerariosActionPerformed
        // TODO add your handling code here:
        this.clickBtnRegistrarActualizarItinerario();
    }//GEN-LAST:event_btnRegistrarActualizarItinerariosActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEditarAnimales;
    private javax.swing.JButton btnGuardarEspecie;
    private javax.swing.JButton btnRegistrarActualizarEspecie;
    private javax.swing.JButton btnRegistrarActualizarItinerarios;
    private javax.swing.JButton btnRegistrarHabitat;
    private javax.swing.JButton btnVerificarNombreEspecie;
    private javax.swing.JComboBox<String> cajaCombinadaCuidadores;
    private javax.swing.JComboBox<String> cajaCombinadaHabitats;
    private javax.swing.JTextField campoTextoDescripcion;
    private javax.swing.JTextField campoTextoNombre;
    private javax.swing.JTextField campoTextoNombreCientifico;
    private javax.swing.JLabel lblCuidador;
    private javax.swing.JLabel lblDescripcion;
    private javax.swing.JLabel lblHabitat;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblNombreCientifico;
    // End of variables declaration//GEN-END:variables
    public static FrmRegistroHabitat frmHabitat;
    public static FrmEditarAnimal frmAnimal;
    public static FrmRegistroItinerario frmItinerario;
    private INegocio negocio;
    private List<Habitat> listaHabitats;
    private List<Cuidador> listaCuidadores;
}
