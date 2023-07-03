/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package vista;

import controlador.DAO.EquipoDao;
import controlador.DAO.JugadorDao;
import controlador.ed.lista.ListaEnlazada;
import javax.swing.JOptionPane;
import modelo.Jugador;
import vista.modeloTabla.ModeloTablaJugador;
import vista.util.Utilidades;

public class FrmJugadores extends javax.swing.JDialog {

    private JugadorDao jd = new JugadorDao();
    private EquipoDao ed = new EquipoDao();
    private ModeloTablaJugador modelo = new ModeloTablaJugador();
    private Integer fila = -1;

    /**
     * Creates new form FrmJugadores
     */
    public FrmJugadores(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        CargarTabla();
        cargarCombo();
    }

    private void CargarTabla() {
        modelo.setDatos(jd.listar());
        tblTabla.setModel(modelo);
        tblTabla.updateUI();

    }

    private void limpiar() {
        txtDorsal.setText("");
        txtNombre.setText("");
        txtAlias.setText("");
        txtPrecio.setText("");
        txtBuscar.setText("");
        jd.setJugador(null);
        fila = -1;
        tblTabla.clearSelection();
        cbxCriterio.setSelectedIndex(0);
        CargarTabla();
        cargarCombo();
    }

    private void cargarCombo() {
        try {
            Utilidades.cargarEquipo(cbxEquipo, ed);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void guardar() {
        if (txtDorsal.getText().trim().isEmpty() || txtNombre.getText().trim().isEmpty()
                || txtAlias.getText().trim().isEmpty() || txtPrecio.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese todos los campos",
                    "ERROR", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                jd.getJugador().setPrecio(Double.parseDouble(txtPrecio.getText()));
                jd.getJugador().setDorsal(Integer.parseInt(txtDorsal.getText()));
                jd.getJugador().setNombre(txtNombre.getText());
                jd.getJugador().setAlias(txtAlias.getText());
                jd.getJugador().setId_jugador(ed.buscarPorNombre(cbxEquipo.getSelectedItem().toString()).getId());
                if (jd.getJugador().getId() != null) {
                    System.out.println(fila);
                    jd.modificar(fila);
                    limpiar();
                } else {
                    jd.guardar();
                    limpiar();
                }
                JOptionPane.showMessageDialog(null, "Guardado Correctamente",
                        "OK", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, e,
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void buscar() {
        String criterio = cbxCriterio.getSelectedItem().toString();
        String tipoBusqueda = cbxTipoDeBusqueda.getSelectedItem().toString();
        String texto = txtBuscar.getText();

        try {
            ListaEnlazada<Jugador> resultado;
            if (criterio.equalsIgnoreCase("Nombre")) {
                if (tipoBusqueda.equalsIgnoreCase("Lineal binaria")) {
                    resultado = jd.buscarPorNombre(texto);
                } else {
                    Jugador jugadorEncontrado = jd.buscarNombreBinario(texto);
                    resultado = new ListaEnlazada<>();
                    if (jugadorEncontrado != null) {
                        resultado.insertar(jugadorEncontrado);
                    }
                }
            } else if (criterio.equalsIgnoreCase("Dorsal")) {
                if (tipoBusqueda.equalsIgnoreCase("Lineal binaria")) {
                    resultado = jd.buscarDorsal(texto);
                } else {
                    resultado = jd.buscarDorsalBinario(texto);
                }
            } else if (criterio.equalsIgnoreCase("Alias")) {
                if (tipoBusqueda.equalsIgnoreCase("Lineal binaria")) {
                    resultado = jd.buscarPorAlias(texto);
                } else {
                    Jugador jugadorEncontrado = jd.buscarAliasBinario(texto);
                    resultado = new ListaEnlazada<>();
                    if (jugadorEncontrado != null) {
                        resultado.insertar(jugadorEncontrado);
                    }
                }
            } else {
                throw new IllegalArgumentException("Criterio de búsqueda inválido");
            }
            if (resultado.isEmpty()) {
                // No se encontraron jugadores, realiza alguna acción adecuada
                JOptionPane.showMessageDialog(this, "No se encontraron jugadores con el criterio de búsqueda especificado", "Información", JOptionPane.INFORMATION_MESSAGE);
            }
            modelo.setDatos(resultado);
            tblTabla.setModel(modelo);
            tblTabla.updateUI();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Escriba una busqueda que este de acuerdo al criterio ", "Información", JOptionPane.ERROR_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtPrecio = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtAlias = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cbxEquipo = new javax.swing.JComboBox<>();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        txtBuscar = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        cbxCriterio = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTabla = new javax.swing.JTable();
        btnSelec = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        cbxTipoDeBusqueda = new javax.swing.JComboBox<>();
        txtDorsal = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos de jugadores", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 24), new java.awt.Color(0, 0, 0))); // NOI18N

        jLabel1.setText("Nombre:");

        txtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreActionPerformed(evt);
            }
        });

        jLabel2.setText("Precio:");

        jLabel3.setText("Alias:");

        jLabel4.setText("Dorsal:");

        jLabel5.setText("Equipo:");

        cbxEquipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Buscar", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 24))); // NOI18N

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        jLabel6.setText("Criterio:");

        cbxCriterio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nombre", "Dorsal", "Alias" }));
        cbxCriterio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxCriterioActionPerformed(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Lista de Jugadores", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 24))); // NOI18N

        tblTabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblTabla);

        btnSelec.setText("Seleccionar");
        btnSelec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelecActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 614, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSelec)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSelec))
        );

        jLabel7.setText("Tipo de busqueda:");

        cbxTipoDeBusqueda.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Lineal binaria", "Binaria" }));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(cbxCriterio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnBuscar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbxTipoDeBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar)
                    .addComponent(jLabel6)
                    .addComponent(cbxCriterio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(cbxTipoDeBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        txtDorsal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDorsalActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtNombre)
                        .addComponent(txtPrecio, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE))
                    .addComponent(cbxEquipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtAlias, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                            .addComponent(txtDorsal)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnGuardar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 81, Short.MAX_VALUE)
                        .addComponent(btnCancelar)))
                .addGap(21, 21, 21))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txtAlias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txtDorsal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cbxEquipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGuardar)
                    .addComponent(btnCancelar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        guardar();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        limpiar();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnSelecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelecActionPerformed
        fila = tblTabla.getSelectedRow();
        if (fila >= 0) {
            try {
                Integer id = modelo.getDatos().get(fila).getId();
                jd.setJugador(jd.obtener(id));
                txtDorsal.setText(String.valueOf(jd.getJugador().getDorsal()));
                txtNombre.setText(jd.getJugador().getNombre());
                txtAlias.setText(jd.getJugador().getAlias());
                txtPrecio.setText(String.valueOf(jd.getJugador().getPrecio()));
                cbxEquipo.setSelectedItem(ed.obtener(jd.getJugador().getId_jugador()).getNombre().toUpperCase());
            } catch (Exception e) {
            }

        } else {
            JOptionPane.showMessageDialog(null, "Escoja el Registro de la tabla",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnSelecActionPerformed

    private void cbxCriterioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxCriterioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxCriterioActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        buscar();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void txtDorsalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDorsalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDorsalActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmJugadores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmJugadores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmJugadores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmJugadores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FrmJugadores dialog = new FrmJugadores(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnSelec;
    private javax.swing.JComboBox<String> cbxCriterio;
    private javax.swing.JComboBox<String> cbxEquipo;
    private javax.swing.JComboBox<String> cbxTipoDeBusqueda;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblTabla;
    private javax.swing.JTextField txtAlias;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtDorsal;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtPrecio;
    // End of variables declaration//GEN-END:variables
}
