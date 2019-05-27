package Forms;

import Clases.Constantes;
import Controladores.ControladorCliente;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

public class FormClienteSala extends ControladorCliente{

    String nombreDePartidaAUnirse;
    
    public FormClienteSala(){
        initComponents();
        nombreJugadorLabel.setText(miNombre);
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

        nombreJugadorLabel.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        nombreJugadorLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nombreJugadorLabel.setAlignmentX(5.0F);
        nombreJugadorLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        unirseAPartidaAleatoriabtn.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        unirseAPartidaAleatoriabtn.setText("UNIRSE A PARTIDA ALEATORIA");
        unirseAPartidaAleatoriabtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                unirseAPartidaAleatoriabtnActionPerformed(evt);
            }
        });

        unirsePartidaSeleccionadaBtn.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        unirsePartidaSeleccionadaBtn.setText("UNIRSE A PARTIDA SELECCIONADA");
        unirsePartidaSeleccionadaBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                unirsePartidaSeleccionadaBtnActionPerformed(evt);
            }
        });

        crearPartidaBtn.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        crearPartidaBtn.setText("CREAR PARTIDA");
        crearPartidaBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crearPartidaBtnActionPerformed(evt);
            }
        });

        tresTirosCheck.setText("TRES TIROS");
        tresTirosCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tresTirosCheckActionPerformed(evt);
            }
        });

        dosTirosCheck.setText("DOS TIROS-UN VOLTEO");
        dosTirosCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dosTirosCheckActionPerformed(evt);
            }
        });

        cantidadDeJugadoresCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2", "3", "4", "5" }));

        jLabel1.setText("CANTIDAD JUGADORES");

        listaDePartidasList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listaDePartidasListMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(listaDePartidasList);

        jButton1.setText("BUSCAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(nombreJugadorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 549, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(unirsePartidaSeleccionadaBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(unirseAPartidaAleatoriabtn))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buscarPartidaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(crearPartidaBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tresTirosCheck)
                    .addComponent(dosTirosCheck))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(cantidadDeJugadoresCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(nombreJugadorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(unirsePartidaSeleccionadaBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(unirseAPartidaAleatoriabtn, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(buscarPartidaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1))
                        .addGap(2, 2, 2)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(crearPartidaBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(tresTirosCheck)
                        .addGap(1, 1, 1)
                        .addComponent(dosTirosCheck))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel1)
                        .addGap(7, 7, 7)
                        .addComponent(cantidadDeJugadoresCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

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
        String partidaSeleccionada=listaDePartidasList.getSelectedValue().toString();
        //controladorUnirseAPartidaSeleccionada(partidaSeleccionada);
    }//GEN-LAST:event_unirsePartidaSeleccionadaBtnActionPerformed

    private void crearPartidaBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crearPartidaBtnActionPerformed
        if(!tresTirosCheck.isSelected()&&!dosTirosCheck.isSelected()){
            JOptionPane.showMessageDialog(rootPane,"DEBE SELECCIONAR UN TIPO DE JUEGO");
        }else{
            String cantidadMaximaJugadores = cantidadDeJugadoresCombo.getSelectedItem().toString();
            String tipoDeJuego = tresTirosCheck.isSelected()?
                    Constantes.PARTIDA_TRES_TIROS:Constantes.PARTIDA_DOS_TIROS;
            controladorUnirseAPartida(
                    Integer.parseInt(cantidadMaximaJugadores),
                    tipoDeJuego
            );
        }
    }//GEN-LAST:event_crearPartidaBtnActionPerformed

    private void unirseAPartidaAleatoriabtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_unirseAPartidaAleatoriabtnActionPerformed
        
    }//GEN-LAST:event_unirseAPartidaAleatoriabtnActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(!buscarPartidaTxt.getText().equals("")){
            cliente.sendMensaje(Constantes.SALA+Constantes.BUSCAR_PARTIDA+
                    "_"+buscarPartidaTxt.getText());
        }else{
            JOptionPane.showMessageDialog(rootPane,"DEBE INTRODUCIR UN NOMBRE DE PARTIDA PARA BUSCAR");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void listaDePartidasListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listaDePartidasListMouseClicked
        if(!listaDePartidasList.getSelectedValue().equals(" ")){
            unirsePartidaSeleccionadaBtn.setEnabled(true);
            nombreDePartidaAUnirse=listaDePartidasList.getSelectedValue();
        }
    }//GEN-LAST:event_listaDePartidasListMouseClicked

    public void mostrarListaDePartidas(String listaDePartidas) {
         DefaultListModel modelo=new DefaultListModel();
         if(listaDePartidas.equals("")){
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

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormClienteSala().setVisible(true);
            }
        });
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
}
