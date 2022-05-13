/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package formularios;

import entidades.Animal;
import entidades.Especie;
import enumeradores.Sexo;
import interfaces.INegocio;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.bson.types.ObjectId;

/**
 *
 * @author luisg
 */
public class FrmEditarAnimal extends javax.swing.JFrame {

    /**
     * Creates new form FrmEditarAnimal
     * @param especie
     * @param negocio
     */
    public FrmEditarAnimal(Especie especie, INegocio negocio) {
        this.negocio = negocio;
        this.especie = especie;

        
        initComponents();
        
        this.cajaCombinadaSexo.addItem("MACHO");
        this.cajaCombinadaSexo.addItem("HEMBRA");
        this.mostrarTablaAnimalesEspecie();
    }
    
    /**
     * Muestra la tabla de animales
     */
    private void mostrarTablaAnimalesEspecie(){
        List<Animal> listaAnimales = this.negocio.consultarAnimalesEspecie(this.especie.getId());
        DefaultTableModel modeloTabla = (DefaultTableModel) this.tablaAnimales.getModel();
        modeloTabla.setRowCount(0);
        listaAnimales.forEach(animal -> {
            Object[] fila = new Object[4];
            fila[0] = animal.getId();
            fila[1] = animal.getNombreAnimal();
            fila[2] = animal.getEdad();
            fila[3] = animal.getSexo();
            modeloTabla.addRow(fila);
        });
    }
    
    /**
     * Agrega un nuevo animal
     */
    private void clickBtnAgregar(){
        if(this.campoTextoNombre.getText().isEmpty() || this.campoTextoEdad.getText().isEmpty() || this.cajaCombinadaSexo.getSelectedItem() == null){
            JOptionPane.showMessageDialog(this, "Debe de llenar los campos para poder agregar un animal", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String nombre = this.campoTextoNombre.getText();
        
        boolean esEntero = this.validaEntero(this.campoTextoEdad.getText());
        if(!esEntero){
            JOptionPane.showMessageDialog(this, "La edad debe de ser un número entero", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int edad = Integer.parseInt(this.campoTextoEdad.getText());
        
        if(edad < 0 || edad > 150){
            JOptionPane.showMessageDialog(this, "La edad no puede ser un número negativo o mayor a 150", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        Animal animal = new Animal(nombre, edad);
        
        String sexo = (String) this.cajaCombinadaSexo.getSelectedItem();
        if(sexo.equalsIgnoreCase("MACHO")){
            animal.setSexo(Sexo.MACHO);
        }else{
            animal.setSexo(Sexo.HEMBRA);
        }
        
        List<Animal> lista = this.negocio.consultarAnimalesEspecie(this.especie.getId());
        for(Animal a : lista){
            if(a.getNombreAnimal().equalsIgnoreCase(nombre)){
                JOptionPane.showMessageDialog(this, "Un animal con el nombre: " + nombre + " ya existe", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }
        }
        
        animal.setId(new ObjectId());
        this.negocio.guardarAnimal(this.especie.getId(), animal);
        this.mostrarTablaAnimalesEspecie();
    }
    
    /**
     * Valida si la cadena s sólo contiene digitos.
     *
     * @param s Cadena a verificar
     * @return true si la cadena representa un entero, false en caso contrario.
     */
    private boolean validaEntero(String s) {
        CharSequence cadena = s.trim();
        // Define una expresión regular para una cadena con puros digitos
        String reCadena = "^\\d+$";
        // Compila la expresión regular a un patrón
        Pattern pattern = Pattern.compile(reCadena);
        // Crea un comparador para comparar la cadena contra el patrón
        Matcher matcher = pattern.matcher(cadena);
        // Si la cadena se ajusta al patrón
        if (matcher.matches()) {
            return true;
        }
        return false;
    }
    
    /**
     * Obtiene el id del animal a eliminar
     * @return id del animal a eliminar
     */
    private ObjectId getAnimalEliminar(){
        int indiceFilaSeleccionada = this.tablaAnimales.getSelectedRow();
        if(indiceFilaSeleccionada != -1){
            DefaultTableModel modelo = (DefaultTableModel) this.tablaAnimales.getModel();
            int indiceColumnaId = 0;
            ObjectId idAnimal = (ObjectId) modelo.getValueAt(indiceFilaSeleccionada, indiceColumnaId);
            return idAnimal;
        }else{
            return null;
        }
    }
    
    /**
     * Elimina un animal
     */
    private void clickBtnEliminar(){
        ObjectId idAnimal = this.getAnimalEliminar();
        if(idAnimal == null){
            JOptionPane.showMessageDialog(this, "Seleccione un animal de la tabla para poder eliminarlo", "Adevertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int opcionSeleccionada = JOptionPane.showConfirmDialog(this, "¿Está seguro de eliminar el animal con id: " + idAnimal + "?", "Confirmación", JOptionPane.YES_NO_OPTION);
        if(opcionSeleccionada == JOptionPane.NO_OPTION){
            return;
        }
        
        this.negocio.eliminarAnimal(this.especie.getId(), idAnimal);
        this.mostrarTablaAnimalesEspecie();
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
        campoTextoNombre = new javax.swing.JTextField();
        lblEdad = new javax.swing.JLabel();
        campoTextoEdad = new javax.swing.JTextField();
        lblSexo = new javax.swing.JLabel();
        cajaCombinadaSexo = new javax.swing.JComboBox<>();
        panelTablaAnimales = new javax.swing.JScrollPane();
        tablaAnimales = new javax.swing.JTable();
        btnAgregar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnRegresar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Añadir/Eliminar Animal");

        lblNombre.setText("Nombre");

        lblEdad.setText("Edad");

        lblSexo.setText("Sexo");

        tablaAnimales.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombre", "Edad", "Sexo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        panelTablaAnimales.setViewportView(tablaAnimales);

        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
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
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblSexo)
                    .addComponent(lblEdad)
                    .addComponent(lblNombre))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(campoTextoNombre)
                            .addComponent(campoTextoEdad)
                            .addComponent(cajaCombinadaSexo, 0, 213, Short.MAX_VALUE))
                        .addGap(87, 87, 87)
                        .addComponent(btnAgregar))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(btnEliminar)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnRegresar))
                        .addComponent(panelTablaAnimales, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(58, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombre)
                    .addComponent(campoTextoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEdad)
                    .addComponent(campoTextoEdad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAgregar))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSexo)
                    .addComponent(cajaCombinadaSexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(panelTablaAnimales, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEliminar)
                    .addComponent(btnRegresar))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Agrega un animal
     * @param evt 
     */
    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        this.clickBtnAgregar();
    }//GEN-LAST:event_btnAgregarActionPerformed

    /**
     * Elimina un animal
     * @param evt 
     */
    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        this.clickBtnEliminar();
    }//GEN-LAST:event_btnEliminarActionPerformed

    /**
     * Cierra el frame
     * @param evt 
     */
    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        this.dispose();
        FrmPrincipal.frmAnimal = null;
    }//GEN-LAST:event_btnRegresarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JComboBox<String> cajaCombinadaSexo;
    private javax.swing.JTextField campoTextoEdad;
    private javax.swing.JTextField campoTextoNombre;
    private javax.swing.JLabel lblEdad;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblSexo;
    private javax.swing.JScrollPane panelTablaAnimales;
    private javax.swing.JTable tablaAnimales;
    // End of variables declaration//GEN-END:variables
    private Especie especie;
    private INegocio negocio;
}
