/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package formularios;

import entidades.Dia;
import entidades.Guia;
import entidades.Itinerario;
import entidades.Queja;
import entidades.Visitante;
import factory.FabricaNegocios;
import interfaces.INegocio;
import java.util.List;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author julio
 */
public class FrmRegistroQuejas extends javax.swing.JFrame {

    /**
     * Creates new form frmQuejas
     */
    public FrmRegistroQuejas() {
        initComponents();
        this.negocio = FabricaNegocios.crearFNegocio();
        this.cargarCajaItinerarios();
        this.limpiarCampos();
        setIconImage(new ImageIcon(getClass().getResource("/icons/Tiger-icon.png")).getImage());

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblVisitante = new javax.swing.JLabel();
        lblItinerario = new javax.swing.JLabel();
        cajaCombinadaItinerarios = new javax.swing.JComboBox<>();
        lblQueja = new javax.swing.JLabel();
        lblFechas = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtQueja = new javax.swing.JTextArea();
        cajaCombinadaDias = new javax.swing.JComboBox<>();
        txtVisitante = new javax.swing.JTextField();
        lblGuia = new javax.swing.JLabel();
        txtGuia = new javax.swing.JTextField();
        lblHoras = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        lblTelefono = new javax.swing.JLabel();
        btnEnviarQueja = new javax.swing.JButton();
        txtTelefono = new javax.swing.JFormattedTextField();
        lblSugerencia = new javax.swing.JLabel();
        txtHora = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Registro Quejas");
        setBackground(new java.awt.Color(41, 80, 109));

        lblVisitante.setText("Visitante");

        lblItinerario.setText("Itinerario");

        cajaCombinadaItinerarios.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cajaCombinadaItinerariosItemStateChanged(evt);
            }
        });

        lblQueja.setText("Queja");

        lblFechas.setText("Días");

        txtQueja.setColumns(20);
        txtQueja.setRows(5);
        jScrollPane1.setViewportView(txtQueja);

        cajaCombinadaDias.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cajaCombinadaDiasItemStateChanged(evt);
            }
        });

        txtVisitante.setText(" ");
        txtVisitante.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtVisitanteKeyTyped(evt);
            }
        });

        lblGuia.setText("Guía");

        txtGuia.setEditable(false);

        lblHoras.setText("Hora");

        lblEmail.setText("E-mail");

        lblTelefono.setText("Teléfono");

        btnEnviarQueja.setText("Enviar queja/sugerencia");
        btnEnviarQueja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviarQuejaActionPerformed(evt);
            }
        });

        txtTelefono.setColumns(12);
        try {
            txtTelefono.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(###)-###-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtTelefono.setToolTipText("0000000000");

        lblSugerencia.setText("*No necesitas introducir tu nombre, tu queja puede ser anónima.");

        txtHora.setEditable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(lblItinerario)
                                        .addComponent(lblGuia)
                                        .addComponent(lblHoras)
                                        .addComponent(lblFechas)
                                        .addComponent(lblQueja))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(15, 15, 15)
                                        .addComponent(lblEmail))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblVisitante, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblTelefono, javax.swing.GroupLayout.Alignment.TRAILING))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtVisitante)
                            .addComponent(txtGuia, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cajaCombinadaDias, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cajaCombinadaItinerarios, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1)
                            .addComponent(txtEmail)
                            .addComponent(txtTelefono)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtHora, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(73, Short.MAX_VALUE)
                        .addComponent(lblSugerencia)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnEnviarQueja)
                .addGap(131, 131, 131))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblItinerario)
                            .addComponent(cajaCombinadaItinerarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19)
                        .addComponent(lblFechas))
                    .addComponent(cajaCombinadaDias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblHoras)
                    .addComponent(txtHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtGuia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblGuia))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblQueja))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEmail)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblTelefono)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblVisitante)
                    .addComponent(txtVisitante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSugerencia)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addComponent(btnEnviarQueja)
                .addGap(26, 26, 26))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Método que verifica campos y guarda la queja
     *
     * @param evt El evento que describe la pulsación del botón
     */
    private void btnEnviarQuejaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnviarQuejaActionPerformed
        if (cajaCombinadaItinerarios.getSelectedIndex() == -1) {
            this.mostrarMensajeError("Por favor, seleccione un itinerario.");
            return;
        }

        if (cajaCombinadaDias.getSelectedIndex() == -1) {
            this.mostrarMensajeError("Por favor, seleccione una fecha.");
            return;
        }

        if (txtQueja.getText().isBlank()) {
            this.mostrarMensajeError("Por favor, escriba su queja o sugerencia.");
            return;
        }

        if (txtEmail.getText().isBlank()) {
            this.mostrarMensajeError("Por favor, introduzca su email.");
            return;
        }

        if (txtTelefono.getText().equals("(   )-   -    ")) {
            this.mostrarMensajeError("Por favor, introduzca su número telefónico.");
            return;
        }

        if (validarEmail(txtEmail.getText())) {
        } else {
            this.mostrarMensajeError("Formato de email inválido.");
            return;
        }

        Itinerario itinerario = (Itinerario) cajaCombinadaItinerarios.getSelectedItem();

        Guia guia = null;
        try {
            guia = negocio.consultarGuia(itinerario.getId());
            txtGuia.setText(guia.getNombre());
        } catch (Exception e) {
            mostrarMensajeError("No se pudo recuperar el guía");
        }

        Visitante visitante = new Visitante(txtEmail.getText(),
                txtTelefono.getText(), txtVisitante.getText());
        Queja queja = new Queja(txtQueja.getText(), visitante, itinerario, guia);

        boolean seAgrego = negocio.guardarQueja(queja);

        if (seAgrego) {
            this.mostrarMensajeConfirmacion();
        } else {
            this.mostrarMensajeError("No se pudo agregar la queja.");
        }

    }//GEN-LAST:event_btnEnviarQuejaActionPerformed

    /**
     * Muestra mensaje de que el hábitat se a agregado exitosamente
     */
    private void mostrarMensajeConfirmacion() {
        JOptionPane.showMessageDialog(this, "La queja se ha guardado exitosamente", "Información", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Método que valida cadenas de texto para ver si son emails
     *
     * @param email La cadena de texto a validar
     * @return Verdadero si la cadena de texto tiene formato de email, falso si
     * está en blanco o no tiene formato de email.
     */
    private boolean validarEmail(String email) {
        if (email == null || email.isBlank()) {
            return false;
        }

        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

        Pattern pattern = Pattern.compile(emailRegex);

        if (pattern.matcher(email).matches()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Método que valida que solo se introduzcan caracteres alfabéticos en el
     * campo de nombre
     *
     * @param evt El evento que se crea cuando se introduce un caracter.
     */
    private void txtVisitanteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtVisitanteKeyTyped
        char c = evt.getKeyChar();

        if (Character.isSpaceChar(c)) {
            return;
        }

        if (!Character.isAlphabetic(c)) {
            evt.consume();
        }
    }//GEN-LAST:event_txtVisitanteKeyTyped

    /**
     * Método que llena la cajaCombinadaDías según el itinerario seleccionado
     *
     * @param evt
     */
    private void cajaCombinadaItinerariosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cajaCombinadaItinerariosItemStateChanged
        Itinerario itinerario;
        itinerario = (Itinerario) cajaCombinadaItinerarios.getSelectedItem();

        if (itinerario == null) {
            return;
        }

        try {
            List<Dia> dias = itinerario.getDiasRecorrido();

            cajaCombinadaDias.removeAllItems();
            for (int i = 0; i < dias.size(); i++) {

                Dia dia = (Dia) dias.get(i);
                cajaCombinadaDias.addItem(dia);

            }
            cajaCombinadaDias.setSelectedIndex(NINGUNO);
        } catch (Exception ex) {
            this.mostrarMensajeError("Hubo un error al cargar los días");
        }
        limpiarCampos();
    }//GEN-LAST:event_cajaCombinadaItinerariosItemStateChanged

    /**
     * Actualiza los campos según el día seleccionado del Itinerario
     *
     * @param evt
     */
    private void cajaCombinadaDiasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cajaCombinadaDiasItemStateChanged
        Itinerario itinerario;
        itinerario = (Itinerario) cajaCombinadaItinerarios.getSelectedItem();

        try {
            if (cajaCombinadaDias.getSelectedIndex() != NINGUNO) {
                txtGuia.setText(negocio.consultarGuia(itinerario.getId()).getNombre());
            }
        } catch (Exception e) {
            mostrarMensajeError("Error al obtener el guía");
        }

        try {
            if (cajaCombinadaDias.getSelectedIndex() != NINGUNO) {
                txtHora.setText(((Dia) cajaCombinadaDias.getSelectedItem()).getHora());
            }
        } catch (Exception e) {
            this.mostrarMensajeError("Error al establecer la hora.");

        }

    }//GEN-LAST:event_cajaCombinadaDiasItemStateChanged

    /**
     * Asegura que los campos estén limpios cuando se ejecute el programa por
     * primera vez
     *
     */
    private void limpiarCampos() {
        String nada = "";
        txtGuia.setText(nada);
        txtHora.setText(nada);
    }

    /**
     * Carga la caja de itinerarios
     */
    private void cargarCajaItinerarios() {

        List listaItinerarios = negocio.consultarItinerarios();

        try {
            for (int i = 0; i < listaItinerarios.size(); i++) {
                Itinerario itinerario = (Itinerario) listaItinerarios.get(i);
                if (negocio.consultarGuia(itinerario.getId()) != null) {
                    cajaCombinadaItinerarios.addItem(itinerario);

                }
            }
            cajaCombinadaItinerarios.setSelectedIndex(NINGUNO);
        } catch (Exception ex) {
            this.mostrarMensajeError("Hubo un error al cargar los itinerarios");
        }

    }

    /**
     * Muestra mensaje de error al activar las comboBoxes y llenar la tabla de
     * continentes disponibles
     */
    private void mostrarMensajeError(String error) {
        JOptionPane.showMessageDialog(this, error, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private final int NINGUNO = -1;
    private INegocio negocio;


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEnviarQueja;
    private javax.swing.JComboBox<Dia> cajaCombinadaDias;
    private javax.swing.JComboBox<Itinerario> cajaCombinadaItinerarios;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblFechas;
    private javax.swing.JLabel lblGuia;
    private javax.swing.JLabel lblHoras;
    private javax.swing.JLabel lblItinerario;
    private javax.swing.JLabel lblQueja;
    private javax.swing.JLabel lblSugerencia;
    private javax.swing.JLabel lblTelefono;
    private javax.swing.JLabel lblVisitante;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtGuia;
    private javax.swing.JTextField txtHora;
    private javax.swing.JTextArea txtQueja;
    private javax.swing.JFormattedTextField txtTelefono;
    private javax.swing.JTextField txtVisitante;
    // End of variables declaration//GEN-END:variables

}
