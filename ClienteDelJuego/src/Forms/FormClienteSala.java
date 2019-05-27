package Forms;

import Clases.Constantes;
import Controladores.ControladorCliente;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

public class FormClienteSala extends javax.swing.JFrame{
    
    ControladorCliente cliente;
    
    public FormClienteSala(ControladorCliente nuevoCliente){
        initComponents();
        cliente=nuevoCliente;
        nombreJugadorLabel.setText(cliente.getNombre());
        tresTirosCheck.setSelected(true);
        unirseAPartidaAleatoriabtn.requestFocus();
        unirsePartidaSeleccionadaBtn.setEnabled(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        nombreJugadorLabel = new javax.swing.JLabel();
        unirseAPartidaAleatoriabtn = new javax.swing.JButton();
        unirsePartidaSeleccionadaBtn = new javax.swing.JButton();
        crearPartidaBtn = new javax.swing.JButton();
        buscarPartidaTxt = new javax.swing.JTextField();
        tresTirosCheck = new javax.swing.JRadioButton();
        dosTirosCheck = new javax.swing.JRadioButton();
        cantidadDeJugadoresCombo = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listaDePartidasList = new javax.swing.JList<>();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        nombreJugadorLabel.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        nombreJugadorLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nombreJugadorLabel.setAlignmentX(5.0F);
        nombreJugadorLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        getContentPane().add(nombreJugadorLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, 630, 43));

        unirseAPartidaAleatoriabtn.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        unirseAPartidaAleatoriabtn.setText("UNIRSE A PARTIDA ALEATORIA");
        unirseAPartidaAleatoriabtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                unirseAPartidaAleatoriabtnActionPerformed(evt);
            }
        });
        getContentPane().add(unirseAPartidaAleatoriabtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 146, 270, 56));

        unirsePartidaSeleccionadaBtn.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        unirsePartidaSeleccionadaBtn.setText("UNIRSE A PARTIDA SELECCIONADA");
        unirsePartidaSeleccionadaBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                unirsePartidaSeleccionadaBtnActionPerformed(evt);
            }
        });
        getContentPane().add(unirsePartidaSeleccionadaBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 78, 269, 56));

        crearPartidaBtn.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        crearPartidaBtn.setText("CREAR PARTIDA");
        crearPartidaBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crearPartidaBtnActionPerformed(evt);
            }
        });
        getContentPane().add(crearPartidaBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 269, 63));
        getContentPane().add(buscarPartidaTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(362, 80, 280, -1));

        tresTirosCheck.setText("TRES TIROS");
        tresTirosCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tresTirosCheckActionPerformed(evt);
            }
        });
        getContentPane().add(tresTirosCheck, new org.netbeans.lib.awtextra.AbsoluteConstraints(285, 228, -1, -1));

        dosTirosCheck.setText("DOS TIROS-UN VOLTEO");
        dosTirosCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dosTirosCheckActionPerformed(evt);
            }
        });
        getContentPane().add(dosTirosCheck, new org.netbeans.lib.awtextra.AbsoluteConstraints(285, 253, -1, -1));

        cantidadDeJugadoresCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2", "3", "4", "5" }));
        getContentPane().add(cantidadDeJugadoresCombo, new org.netbeans.lib.awtextra.AbsoluteConstraints(492, 256, 115, -1));

        jLabel1.setText("CANTIDAD JUGADORES");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(492, 232, -1, -1));

        listaDePartidasList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listaDePartidasListMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(listaDePartidasList);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 110, 350, 110));

        jButton1.setText("BUSCAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(289, 79, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tresTirosCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tresTirosCheckActionPerformed
        tresTirosCheck.setSelected(true);
        dosTirosCheck.setSelected(false);
    }//GEN-LAST:event_tresTirosCheckActionPerformed

    private void dosTirosCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dosTirosCheckActionPerformed
        tresTirosCheck.setSelected(false);
        dosTirosCheck.setSelected(true);
    }//GEN-LAST:event_dosTirosCheckActionPerformed

    private void unirsePartidaSeleccionadaBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_unirsePartidaSeleccionadaBtnActionPerformed
        String partidaSeleccionada=listaDePartidasList.getSelectedValue();
        if(Integer.parseInt(partidaSeleccionada.substring(
                partidaSeleccionada.length()-1,partidaSeleccionada.length()))>0){
            String[]elementos=partidaSeleccionada.split(" - ");
            cliente.controladorUnirseAPartidaSeleccionada(elementos[0]);
        }else{
            JOptionPane.showMessageDialog(rootPane,"NO HAY ESPACIO !!!");
        }
    }//GEN-LAST:event_unirsePartidaSeleccionadaBtnActionPerformed

    private void crearPartidaBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crearPartidaBtnActionPerformed
        if(!tresTirosCheck.isSelected()&&!dosTirosCheck.isSelected()){
            JOptionPane.showMessageDialog(rootPane,"DEBE SELECCIONAR UN TIPO DE JUEGO");
        }else{
            String cantidadMaximaJugadores = cantidadDeJugadoresCombo.getSelectedItem().toString();
            String tipoDeJuego = tresTirosCheck.isSelected()?
                    Constantes.PARTIDA_TRES_TIROS:Constantes.PARTIDA_DOS_TIROS;
            cliente.controladorCrearPartida(
                    Integer.parseInt(cantidadMaximaJugadores),
                    tipoDeJuego
            );
        }
    }//GEN-LAST:event_crearPartidaBtnActionPerformed

    private void unirseAPartidaAleatoriabtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_unirseAPartidaAleatoriabtnActionPerformed
        cliente.controladorUnirseAPartidaAleatoria();
    }//GEN-LAST:event_unirseAPartidaAleatoriabtnActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(!buscarPartidaTxt.getText().equals("")){
            String nombreDePartidABuscar=buscarPartidaTxt.getText();
            cliente.controladorBuscarPartida(nombreDePartidABuscar);
        }else{
            JOptionPane.showMessageDialog(rootPane,"DEBE INTRODUCIR UN NOMBRE DE PARTIDA PARA BUSCAR");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void listaDePartidasListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listaDePartidasListMouseClicked
        if(!listaDePartidasList.getSelectedValue().equals(" ")){
            unirsePartidaSeleccionadaBtn.setEnabled(true);
        }
    }//GEN-LAST:event_listaDePartidasListMouseClicked
    public void mostrarListaDePartidas(String listaDePartidas) {
         DefaultListModel modelo=new DefaultListModel();
         if(listaDePartidas.equals(" ")){
            JOptionPane.showMessageDialog(rootPane, "SIN RESULTADOS");
         }else{
            String[]partidas=listaDePartidas.split(",");
            for(int i=1;i<=partidas.length;i++){
                modelo.addElement(partidas[i-1]);
            }
         }
         listaDePartidasList.setModel(modelo);
    }
    
    
    public static void main(String args[]) {
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
            java.util.logging.Logger.getLogger(FormClienteSala.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormClienteSala.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormClienteSala.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormClienteSala.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormClienteSala().setVisible(true);
            }
        });*/
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField buscarPartidaTxt;
    private javax.swing.JComboBox<String> cantidadDeJugadoresCombo;
    private javax.swing.JButton crearPartidaBtn;
    private javax.swing.JRadioButton dosTirosCheck;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList<String> listaDePartidasList;
    private javax.swing.JLabel nombreJugadorLabel;
    private javax.swing.JRadioButton tresTirosCheck;
    private javax.swing.JButton unirseAPartidaAleatoriabtn;
    private javax.swing.JButton unirsePartidaSeleccionadaBtn;
    // End of variables declaration//GEN-END:variables

    public void setVisibleC(boolean b) {
        setVisible(b);
    }
}
