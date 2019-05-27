package Forms;

import Clases.Constantes;
import Controladores.ControladorCliente;
import Clases.Cubilete;
import Clases.Tablero;
import java.awt.Image;
import javax.swing.ImageIcon;
import com.google.gson.Gson;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public final class FormClienteJuego extends javax.swing.JFrame {
    
    ControladorCliente cliente;
    public Tablero tablero;
    public Cubilete cubilete;
    public int canTirosRealizados,tamGrande,tamPeque,aux;
    public String jugada;
    public boolean enTurno,puedePedirJugadas;
    public boolean[] modificable;
    public String[] listaJugadores;
    public Gson json;
    
    public FormClienteJuego(ControladorCliente nuevoCliente,
            String nombrePartida,String tipoDeJuego,int cantidadJugadores){
        initComponents();
        cliente=nuevoCliente;
        json=new Gson();
        tablero=new Tablero();
        tamGrande=imagenDado1.getWidth();
        tamPeque=tamGrande-20;
        canTirosRealizados=aux=0;
        jugador1.setText(cliente.getNombre());
        nombreDePartidaTxt.setText(nombreDePartidaTxt.getText()+nombrePartida);
        tipoJuegoTxt.setText(tipoJuegoTxt.getText()+tipoDeJuego);
        maximoJugadoresTxt.setText(maximoJugadoresTxt.getText()+
                cantidadJugadores+" Jugadores");
        mostrarDadosIniciales();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        botonLanzar = new javax.swing.JButton();
        imagenDado1 = new javax.swing.JLabel();
        imagenDado2 = new javax.swing.JLabel();
        imagenDado3 = new javax.swing.JLabel();
        imagenDado4 = new javax.swing.JLabel();
        imagenDado5 = new javax.swing.JLabel();
        jugadorEnTurno = new javax.swing.JLabel();
        jugador5 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tableroJugador1 = new javax.swing.JTable();
        jScrollPane9 = new javax.swing.JScrollPane();
        tableroJugador3 = new javax.swing.JTable();
        jScrollPane10 = new javax.swing.JScrollPane();
        tableroJugador2 = new javax.swing.JTable();
        jScrollPane11 = new javax.swing.JScrollPane();
        tableroJugador4 = new javax.swing.JTable();
        jScrollPane12 = new javax.swing.JScrollPane();
        tableroJugador5 = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        listaJugadas = new javax.swing.JList<>();
        jugador2 = new javax.swing.JLabel();
        jugador3 = new javax.swing.JLabel();
        jugador1 = new javax.swing.JLabel();
        jugador4 = new javax.swing.JLabel();
        ultimaJugadaJugador2 = new javax.swing.JLabel();
        ultimaJugadaJugador3 = new javax.swing.JLabel();
        ultimaJugadaJugador4 = new javax.swing.JLabel();
        ultimaJugadaJugador5 = new javax.swing.JLabel();
        ultimaJugadaJugador1 = new javax.swing.JLabel();
        listoParjugarBtn = new javax.swing.JButton();
        nombreDePartidaTxt = new javax.swing.JLabel();
        tipoJuegoTxt = new javax.swing.JLabel();
        maximoJugadoresTxt = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        botonLanzar.setBackground(java.awt.Color.cyan);
        botonLanzar.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        botonLanzar.setText("LANZAR DADOS");
        botonLanzar.setEnabled(false);
        botonLanzar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonLanzarActionPerformed(evt);
            }
        });
        getContentPane().add(botonLanzar, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 220, 240, 91));

        imagenDado1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Dado1.png"))); // NOI18N
        imagenDado1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                imagenDado1MouseClicked(evt);
            }
        });
        getContentPane().add(imagenDado1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 60, 68, 61));

        imagenDado2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Dado4.png"))); // NOI18N
        imagenDado2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                imagenDado2MouseClicked(evt);
            }
        });
        getContentPane().add(imagenDado2, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 150, 68, 61));

        imagenDado3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Dado2.png"))); // NOI18N
        imagenDado3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                imagenDado3MouseClicked(evt);
            }
        });
        getContentPane().add(imagenDado3, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 60, 68, 61));

        imagenDado4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Dado5.png"))); // NOI18N
        imagenDado4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                imagenDado4MouseClicked(evt);
            }
        });
        getContentPane().add(imagenDado4, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 150, 68, 61));

        imagenDado5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Dado3.png"))); // NOI18N
        imagenDado5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                imagenDado5MouseClicked(evt);
            }
        });
        getContentPane().add(imagenDado5, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 60, 68, 61));

        jugadorEnTurno.setBackground(new java.awt.Color(153, 153, 0));
        jugadorEnTurno.setFont(new java.awt.Font("Courier New", 1, 30)); // NOI18N
        jugadorEnTurno.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jugadorEnTurno.setText("jugadorEnTurno");
        getContentPane().add(jugadorEnTurno, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 10, 280, 40));

        jugador5.setBackground(new java.awt.Color(153, 153, 0));
        jugador5.setFont(new java.awt.Font("Courier New", 1, 20)); // NOI18N
        jugador5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jugador5.setText("jugador5");
        getContentPane().add(jugador5, new org.netbeans.lib.awtextra.AbsoluteConstraints(564, 210, 160, 30));

        tableroJugador1.setBackground(new java.awt.Color(247, 193, 152));
        tableroJugador1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 0));
        tableroJugador1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "", "", ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableroJugador1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tableroJugador1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tableroJugador1.setDoubleBuffered(true);
        tableroJugador1.setDragEnabled(true);
        tableroJugador1.setDropMode(javax.swing.DropMode.ON);
        tableroJugador1.setFocusable(false);
        tableroJugador1.setGridColor(new java.awt.Color(0, 0, 0));
        tableroJugador1.setMinimumSize(new java.awt.Dimension(45, 150));
        tableroJugador1.setOpaque(false);
        tableroJugador1.setPreferredSize(new java.awt.Dimension(75, 95));
        tableroJugador1.setRequestFocusEnabled(false);
        tableroJugador1.setRowHeight(25);
        tableroJugador1.setRowSelectionAllowed(false);
        tableroJugador1.setUpdateSelectionOnSort(false);
        tableroJugador1.setVerifyInputWhenFocusTarget(false);
        jScrollPane6.setViewportView(tableroJugador1);
        if (tableroJugador1.getColumnModel().getColumnCount() > 0) {
            tableroJugador1.getColumnModel().getColumn(0).setResizable(false);
            tableroJugador1.getColumnModel().getColumn(1).setResizable(false);
            tableroJugador1.getColumnModel().getColumn(2).setResizable(false);
        }

        getContentPane().add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(302, 342, 110, 140));

        tableroJugador3.setBackground(new java.awt.Color(247, 193, 152));
        tableroJugador3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 0));
        tableroJugador3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "", "", ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableroJugador3.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tableroJugador3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tableroJugador3.setDoubleBuffered(true);
        tableroJugador3.setDragEnabled(true);
        tableroJugador3.setDropMode(javax.swing.DropMode.ON);
        tableroJugador3.setFocusable(false);
        tableroJugador3.setGridColor(new java.awt.Color(0, 0, 0));
        tableroJugador3.setMinimumSize(new java.awt.Dimension(45, 150));
        tableroJugador3.setOpaque(false);
        tableroJugador3.setPreferredSize(new java.awt.Dimension(75, 95));
        tableroJugador3.setRequestFocusEnabled(false);
        tableroJugador3.setRowHeight(25);
        tableroJugador3.setRowSelectionAllowed(false);
        tableroJugador3.setUpdateSelectionOnSort(false);
        tableroJugador3.setVerifyInputWhenFocusTarget(false);
        jScrollPane9.setViewportView(tableroJugador3);
        if (tableroJugador3.getColumnModel().getColumnCount() > 0) {
            tableroJugador3.getColumnModel().getColumn(0).setResizable(false);
            tableroJugador3.getColumnModel().getColumn(1).setResizable(false);
            tableroJugador3.getColumnModel().getColumn(2).setResizable(false);
        }

        getContentPane().add(jScrollPane9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, 110, 150));

        tableroJugador2.setBackground(new java.awt.Color(247, 193, 152));
        tableroJugador2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "", "", ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableroJugador2.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tableroJugador2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tableroJugador2.setDoubleBuffered(true);
        tableroJugador2.setDragEnabled(true);
        tableroJugador2.setDropMode(javax.swing.DropMode.ON);
        tableroJugador2.setFocusable(false);
        tableroJugador2.setGridColor(new java.awt.Color(0, 0, 0));
        tableroJugador2.setMinimumSize(new java.awt.Dimension(45, 150));
        tableroJugador2.setOpaque(false);
        tableroJugador2.setPreferredSize(new java.awt.Dimension(75, 95));
        tableroJugador2.setRequestFocusEnabled(false);
        tableroJugador2.setRowHeight(25);
        tableroJugador2.setRowSelectionAllowed(false);
        tableroJugador2.setUpdateSelectionOnSort(false);
        tableroJugador2.setVerifyInputWhenFocusTarget(false);
        jScrollPane10.setViewportView(tableroJugador2);
        if (tableroJugador2.getColumnModel().getColumnCount() > 0) {
            tableroJugador2.getColumnModel().getColumn(0).setResizable(false);
            tableroJugador2.getColumnModel().getColumn(1).setResizable(false);
            tableroJugador2.getColumnModel().getColumn(2).setResizable(false);
        }

        getContentPane().add(jScrollPane10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 110, 140));

        tableroJugador4.setBackground(new java.awt.Color(247, 193, 152));
        tableroJugador4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 0));
        tableroJugador4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "", "", ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableroJugador4.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tableroJugador4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tableroJugador4.setDoubleBuffered(true);
        tableroJugador4.setDragEnabled(true);
        tableroJugador4.setDropMode(javax.swing.DropMode.ON);
        tableroJugador4.setFocusable(false);
        tableroJugador4.setGridColor(new java.awt.Color(0, 0, 0));
        tableroJugador4.setMinimumSize(new java.awt.Dimension(45, 150));
        tableroJugador4.setOpaque(false);
        tableroJugador4.setPreferredSize(new java.awt.Dimension(75, 95));
        tableroJugador4.setRequestFocusEnabled(false);
        tableroJugador4.setRowHeight(25);
        tableroJugador4.setRowSelectionAllowed(false);
        tableroJugador4.setUpdateSelectionOnSort(false);
        tableroJugador4.setVerifyInputWhenFocusTarget(false);
        jScrollPane11.setViewportView(tableroJugador4);
        if (tableroJugador4.getColumnModel().getColumnCount() > 0) {
            tableroJugador4.getColumnModel().getColumn(0).setResizable(false);
            tableroJugador4.getColumnModel().getColumn(1).setResizable(false);
            tableroJugador4.getColumnModel().getColumn(2).setResizable(false);
        }

        getContentPane().add(jScrollPane11, new org.netbeans.lib.awtextra.AbsoluteConstraints(604, 50, 110, 140));

        tableroJugador5.setBackground(new java.awt.Color(247, 193, 152));
        tableroJugador5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 0));
        tableroJugador5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "", "", ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableroJugador5.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tableroJugador5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tableroJugador5.setDoubleBuffered(true);
        tableroJugador5.setDragEnabled(true);
        tableroJugador5.setDropMode(javax.swing.DropMode.ON);
        tableroJugador5.setFocusable(false);
        tableroJugador5.setGridColor(new java.awt.Color(0, 0, 0));
        tableroJugador5.setMinimumSize(new java.awt.Dimension(45, 150));
        tableroJugador5.setOpaque(false);
        tableroJugador5.setPreferredSize(new java.awt.Dimension(75, 95));
        tableroJugador5.setRequestFocusEnabled(false);
        tableroJugador5.setRowHeight(25);
        tableroJugador5.setRowSelectionAllowed(false);
        tableroJugador5.setUpdateSelectionOnSort(false);
        tableroJugador5.setVerifyInputWhenFocusTarget(false);
        jScrollPane12.setViewportView(tableroJugador5);
        if (tableroJugador5.getColumnModel().getColumnCount() > 0) {
            tableroJugador5.getColumnModel().getColumn(0).setResizable(false);
            tableroJugador5.getColumnModel().getColumn(1).setResizable(false);
            tableroJugador5.getColumnModel().getColumn(2).setResizable(false);
        }

        getContentPane().add(jScrollPane12, new org.netbeans.lib.awtextra.AbsoluteConstraints(594, 260, 110, 140));

        listaJugadas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listaJugadasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(listaJugadas);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(412, 342, 130, 140));

        jugador2.setBackground(new java.awt.Color(153, 153, 0));
        jugador2.setFont(new java.awt.Font("Courier New", 1, 20)); // NOI18N
        jugador2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jugador2.setText("jugador2");
        jugador2.setPreferredSize(new java.awt.Dimension(80, 20));
        getContentPane().add(jugador2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 160, 30));

        jugador3.setBackground(new java.awt.Color(153, 153, 0));
        jugador3.setFont(new java.awt.Font("Courier New", 1, 20)); // NOI18N
        jugador3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jugador3.setText("jugador3");
        getContentPane().add(jugador3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 210, 160, 30));

        jugador1.setBackground(new java.awt.Color(153, 153, 0));
        jugador1.setFont(new java.awt.Font("Courier New", 1, 20)); // NOI18N
        jugador1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jugador1.setText("jugador1");
        getContentPane().add(jugador1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 310, 160, 30));

        jugador4.setBackground(new java.awt.Color(153, 153, 0));
        jugador4.setFont(new java.awt.Font("Courier New", 1, 20)); // NOI18N
        jugador4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jugador4.setText("jugador4");
        getContentPane().add(jugador4, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 0, 160, 30));

        ultimaJugadaJugador2.setText("ultimaJugadaJugador2");
        getContentPane().add(ultimaJugadaJugador2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        ultimaJugadaJugador3.setText("ultimaJugadaJugador3");
        getContentPane().add(ultimaJugadaJugador3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, -1, -1));

        ultimaJugadaJugador4.setText("ultimaJugadaJugador4");
        getContentPane().add(ultimaJugadaJugador4, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 30, -1, -1));

        ultimaJugadaJugador5.setText("ultimaJugadaJugador5");
        getContentPane().add(ultimaJugadaJugador5, new org.netbeans.lib.awtextra.AbsoluteConstraints(594, 240, -1, -1));

        ultimaJugadaJugador1.setText("ultimaJugadaJugador1");
        getContentPane().add(ultimaJugadaJugador1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 340, -1, -1));

        listoParjugarBtn.setBackground(java.awt.Color.green);
        listoParjugarBtn.setFont(new java.awt.Font("Ubuntu", 0, 15)); // NOI18N
        listoParjugarBtn.setText("LISTO");
        listoParjugarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listoParjugarBtnActionPerformed(evt);
            }
        });
        getContentPane().add(listoParjugarBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 400, 110, 70));

        nombreDePartidaTxt.setFont(new java.awt.Font("Ubuntu", 0, 10)); // NOI18N
        nombreDePartidaTxt.setText("Nombre de la Partida: ");
        getContentPane().add(nombreDePartidaTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 422, -1, -1));

        tipoJuegoTxt.setFont(new java.awt.Font("Ubuntu", 0, 10)); // NOI18N
        tipoJuegoTxt.setText("Tipo de Juego: ");
        getContentPane().add(tipoJuegoTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 440, -1, -1));

        maximoJugadoresTxt.setFont(new java.awt.Font("Ubuntu", 0, 10)); // NOI18N
        maximoJugadoresTxt.setText("Maximo de Jugadores: ");
        getContentPane().add(maximoJugadoresTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 458, -1, -1));

        jButton2.setBackground(java.awt.Color.red);
        jButton2.setText("ABANDONAR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 410, 150, 70));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonLanzarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonLanzarActionPerformed
        if (canTirosRealizados == 0) {
            cubilete=new Cubilete();
        }
        String cubileteJSON=json.toJson(cubilete);
        setTableroDeJugador1();
            String tableroJSON=json.toJson(tablero);
        if(puedePedirJugadas){
            cliente.controladorSolicitarListaDeJugadas(cubileteJSON);
        }else if (canTirosRealizados <= 2) {
            cliente.controladorGenerarDados(cubileteJSON);
        }else{
            cliente.controladorEnviarJugadaEscogida(jugada);
        }
        botonLanzar.setEnabled(false);
    }//GEN-LAST:event_botonLanzarActionPerformed
 
    private void imagenDado1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imagenDado1MouseClicked
        if(enTurno&&modificable[0])actualizarImagenDado(1);
    }//GEN-LAST:event_imagenDado1MouseClicked

    private void imagenDado2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imagenDado2MouseClicked
        if(enTurno&&modificable[1])actualizarImagenDado(2);
    }//GEN-LAST:event_imagenDado2MouseClicked

    private void imagenDado3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imagenDado3MouseClicked
        if(enTurno&&modificable[2])actualizarImagenDado(3);
    }//GEN-LAST:event_imagenDado3MouseClicked

    private void imagenDado4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imagenDado4MouseClicked
        if(enTurno&&modificable[3])actualizarImagenDado(4);
    }//GEN-LAST:event_imagenDado4MouseClicked

    private void imagenDado5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imagenDado5MouseClicked
        if(enTurno&&modificable[4])actualizarImagenDado(5);
    }//GEN-LAST:event_imagenDado5MouseClicked

    private void listaJugadasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listaJugadasMouseClicked
        if(enTurno&&canTirosRealizados==3){
            botonLanzar.setText("CONFIRMAR JUGADA");
            botonLanzar.setEnabled(true);
            jugada=listaJugadas.getSelectedValue();
        }
    }//GEN-LAST:event_listaJugadasMouseClicked

    private void listoParjugarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listoParjugarBtnActionPerformed
        if (aux == 0) {
            cliente.controladorListoParaJugar();
            jugadorEnTurno.setText("Esperando ...");
            aux=1;
        }else{
            cliente.controladorNoListoParaJugar();
            jugadorEnTurno.setText("jugadorEnTurno");
            aux=0;
        }
    }//GEN-LAST:event_listoParjugarBtnActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        cliente.controladorAbandonarPartida();
    }//GEN-LAST:event_jButton2ActionPerformed

    public void iniciarTurno(){
        mostrarDadosIniciales();
        cliente.controladorEnviarNombreJugadorEnTurno();
        enTurno = true;
        botonLanzar.setEnabled(true);
        canTirosRealizados = 0;
        puedePedirJugadas = false;
        botonLanzar.setText("LANZAR DADOS");
    }
    public void mostrarDados(String cubileteJSON) {
        canTirosRealizados++;
        aux = canTirosRealizados;
        setCubilete(cubileteJSON);
        actualizarDados();
        mostrarGif();
        Timer timer = new Timer();
        TimerTask tarea = new TimerTask() {
            @Override
            public void run() {
                if (canTirosRealizados == 3) {
                    todosSeleccionados();
                    if (enTurno) {
                        botonLanzar.setText("VER JUGADAS");
                        puedePedirJugadas = true;
                    }
                }
                actualizarDados();
                if (enTurno) {
                    botonLanzar.setEnabled(true);
                }
                timer.cancel();
            }
        };
        timer.schedule(tarea, 1000, 1000);
    }
    public void cambiarDado(String cubileteJSON) {
        cubilete=json.fromJson(cubileteJSON, Cubilete.class);
        actualizarDados();
    }
    public void cambiarJugadorEnTurno(String nombre){
        jugada = "";
        actualizarListaJugadas("");
        jugadorEnTurno.setText(nombre);
    }
    public void mostrarListaDeJugadas(String listaDeJugadas){
        actualizarListaJugadas(listaDeJugadas);
        if(enTurno&&canTirosRealizados==3){
            puedePedirJugadas = false;
            botonLanzar.setText("SELECCIONAR JUGADA");
        }
    }
    public void setTableroDeJugador1(){
        DefaultTableModel modelo=(DefaultTableModel) tableroJugador1.getModel();
        if(modelo.getValueAt(0,0).equals(" "))tablero.setAlUno(0);
        else if(modelo.getValueAt(0,0).equals("X"))tablero.setAlUno(-1);
        else tablero.setAlUno(Integer.parseInt((String) modelo.getValueAt(0,0)));
        if(modelo.getValueAt(0,1) .equals(" "))tablero.setEscalera(0);
        else if(modelo.getValueAt(0,1).equals("X"))tablero.setEscalera(-1);
        else tablero.setEscalera(Integer.parseInt((String) modelo.getValueAt(0,1)));
        if(modelo.getValueAt(0,2).equals(" "))tablero.setAlCuatro(0);
        else if(modelo.getValueAt(0,2).equals("X"))tablero.setAlCuatro(-1);
        else tablero.setAlCuatro(Integer.parseInt((String) modelo.getValueAt(0,2)));
        if(modelo.getValueAt(1,0).equals(" "))tablero.setAlDos(0);
        else if(modelo.getValueAt(1,0).equals("X"))tablero.setAlDos(-1);
        else tablero.setAlDos(Integer.parseInt((String) modelo.getValueAt(1,0)));
        if(modelo.getValueAt(1,1).equals(" "))tablero.setFull(0);
        else if(modelo.getValueAt(1,1).equals("X"))tablero.setFull(-1);
        else tablero.setFull(Integer.parseInt((String) modelo.getValueAt(1,1)));
        if(modelo.getValueAt(1,2).equals(" "))tablero.setAlCinco(0);
        else if(modelo.getValueAt(1,2).equals("X"))tablero.setAlCinco(-1);
        else tablero.setAlCinco(Integer.parseInt((String) modelo.getValueAt(1,2)));
        if(modelo.getValueAt(2,0).equals(" "))tablero.setAlTres(0);
        else if(modelo.getValueAt(2,0).equals("X"))tablero.setAlTres(-1);
        else tablero.setAlTres(Integer.parseInt((String) modelo.getValueAt(2,0)));
        if(modelo.getValueAt(2,1).equals(" "))tablero.setPoquer(0);
        else if(modelo.getValueAt(2,1).equals("X"))tablero.setPoquer(-1);
        else tablero.setPoquer(Integer.parseInt((String) modelo.getValueAt(2,1)));
        if(modelo.getValueAt(2,2).equals(" "))tablero.setAlSeis(0);
        else if(modelo.getValueAt(2,2).equals("X"))tablero.setAlSeis(-1);
        else tablero.setAlSeis(Integer.parseInt((String) modelo.getValueAt(2,2)));
        if(modelo.getValueAt(3,1).equals(" "))tablero.setGrande(0);
        else if(modelo.getValueAt(3,1).equals("X"))tablero.setGrande(-1);
        else tablero.setGrande(Integer.parseInt((String) modelo.getValueAt(3,1)));
        tableroJugador1.setModel(modelo);
    }
    public void mostrarNombreDeJugadores(String listaNombres){
        listoParjugarBtn.setEnabled(false);
        actualizarNombresJugadores(listaNombres);
        String[] nums = cliente.getId().split("R");
        setEnJugadores(false, Integer.parseInt(nums[1]),"");
        if(listaJugadores[0].contains(cliente.getId())){
            iniciarTurno();
        }
    }
    public void mostrarGanadores(String listaDeResultados,String maximaPuntuacion){
        listoParjugarBtn.setEnabled(true);
        String[]lista=listaDeResultados.split(",");
        int cantidadDeGanadores=0;
        String miTotal="";
        System.out.println(lista.length);
        for(int i=1;i<=lista.length;i++){
            String[]elementosA=lista[i-1].split("-");
            if(elementosA[2].equals(maximaPuntuacion))cantidadDeGanadores++;
            if(elementosA[0].equals(cliente.getId()))miTotal=elementosA[2];
        }
        for(int i=1;i<lista.length;i++){
            for(int j=i+1;j<=lista.length;j++){
                String[] elementosA=lista[i-1].split("-");
                String[]elementosB=lista[j-1].split("-");
                if(elementosB[2].compareTo(elementosA[2])>0){
                    String aux=lista[i-1];
                    lista[i-1]=lista[j-1];
                    lista[j-1]=aux;
                }
            }
        }
        String mensajeGanador="GANASTE !!!\n\n",mensajePerdedor="Perdiste ...\n\n";
        if(cantidadDeGanadores>1)mensajeGanador="EMPATE\n\n";
        String resultado="";
        if(miTotal.equals(maximaPuntuacion))resultado=mensajeGanador;
        else resultado=mensajePerdedor;
        for(int i=1;i<=lista.length;i++){
            String[] elemento=lista[i-1].split("-");
            if(elemento[0].equals(cliente.getId()))resultado=resultado+"--TÚ: ";
            else resultado=resultado+elemento[1]+": ";
            resultado=resultado+elemento[2];
            if(elemento[2].equals("260"))resultado=resultado+"(DORMIDA!!!)";
            resultado=resultado+"\n";
        }
        JOptionPane.showMessageDialog(rootPane,resultado);
    }
    
    public void setCubilete(String cubile){
        cubilete=json.fromJson(cubile, Cubilete.class);
        modificable[0]=!cubilete.getDado(0).fueElegido();
        modificable[1]=!cubilete.getDado(1).fueElegido();
        modificable[2]=!cubilete.getDado(2).fueElegido();
        modificable[3]=!cubilete.getDado(3).fueElegido();
        modificable[4]=!cubilete.getDado(4).fueElegido();
    }
    public void mostrarGif(){
        ImageIcon image = new ImageIcon(getClass().getResource(("/Imagenes/GIFMezcla.gif")));
        ImageIcon icon = new ImageIcon(image.getImage().
        getScaledInstance(tamGrande, tamGrande, Image.SCALE_DEFAULT));
        if(!cubilete.getDado(0).fueElegido){imagenDado1.setIcon(icon);}
        if(!cubilete.getDado(1).fueElegido){imagenDado2.setIcon(icon);}
        if(!cubilete.getDado(2).fueElegido){imagenDado3.setIcon(icon);}
        if(!cubilete.getDado(3).fueElegido){imagenDado4.setIcon(icon);}
        if(!cubilete.getDado(4).fueElegido){imagenDado5.setIcon(icon);}
    }
    public void actualizarImagenDado(int i){
        boolean b=cubilete.getDado(i-1).fueElegido();
        cubilete.getDado(i-1).setFueElegido(!b);
        String cubileteJSON=json.toJson(cubilete);
        cliente.controladorEnviarCambioDeDado(cubileteJSON);
        actualizarDados();
    }
    public void mostrarDadosIniciales(){
        modificable=new boolean[5];
        for(int i=1;i<=5;i++){
            modificable[i-1]=false;
        }
        ImageIcon image;ImageIcon icon;
        image = new ImageIcon(getClass().getResource(("/Imagenes/Dado1.png")));
        icon = new ImageIcon(image.getImage().
                getScaledInstance(tamGrande, tamGrande, Image.SCALE_DEFAULT));
        imagenDado1.setIcon(icon);
        
        image = new ImageIcon(getClass().getResource(("/Imagenes/Dado2.png")));
        icon = new ImageIcon(image.getImage().
                getScaledInstance(tamGrande, tamGrande, Image.SCALE_DEFAULT));
        imagenDado2.setIcon(icon);
        
        image = new ImageIcon(getClass().getResource(("/Imagenes/Dado3.png")));
        icon = new ImageIcon(image.getImage().
                getScaledInstance(tamGrande, tamGrande, Image.SCALE_DEFAULT));
        imagenDado3.setIcon(icon);
        
        image = new ImageIcon(getClass().getResource(("/Imagenes/Dado4.png")));
        icon = new ImageIcon(image.getImage().
                getScaledInstance(tamGrande, tamGrande, Image.SCALE_DEFAULT));
        imagenDado4.setIcon(icon);
        
        image = new ImageIcon(getClass().getResource(("/Imagenes/Dado5.png")));
        icon = new ImageIcon(image.getImage().
                getScaledInstance(tamGrande, tamGrande, Image.SCALE_DEFAULT));
        imagenDado5.setIcon(icon);
    }
    public void todosSeleccionados(){
        cubilete.seleccionarTodos();
        modificable[0]=false;
        modificable[1]=false;
        modificable[2]=false;
        modificable[3]=false;
        modificable[4]=false;
        todasImagenesPeque();
    }
    public void todasImagenesPeque() {
        ImageIcon image;ImageIcon icon;
        image = new ImageIcon(getClass().
                getResource(("/Imagenes/Dado" + cubilete.getDado(0).getValor() + ".png")));
        icon = new ImageIcon(image.getImage().getScaledInstance(
                tamPeque, tamPeque, Image.SCALE_DEFAULT));
        imagenDado1.setIcon(icon);
        
        image = new ImageIcon(getClass().
                getResource(("/Imagenes/Dado" + cubilete.getDado(1).getValor() + ".png")));
        icon = new ImageIcon(image.getImage().getScaledInstance(
                tamPeque, tamPeque, Image.SCALE_DEFAULT));
        imagenDado2.setIcon(icon);
        
        image = new ImageIcon(getClass().
                getResource(("/Imagenes/Dado" + cubilete.getDado(2).getValor() + ".png")));
        icon = new ImageIcon(image.getImage().getScaledInstance(
                tamPeque, tamPeque, Image.SCALE_DEFAULT));
        imagenDado3.setIcon(icon);
        
        image = new ImageIcon(getClass().
                getResource(("/Imagenes/Dado" + cubilete.getDado(3).getValor() + ".png")));
        icon = new ImageIcon(image.getImage().getScaledInstance(
                tamPeque, tamPeque, Image.SCALE_DEFAULT));
        imagenDado4.setIcon(icon);
        
        image = new ImageIcon(getClass().
                getResource(("/Imagenes/Dado" + cubilete.getDado(4).getValor() + ".png")));
        icon = new ImageIcon(image.getImage().getScaledInstance(
                tamPeque, tamPeque, Image.SCALE_DEFAULT));
        imagenDado5.setIcon(icon);
    }
    public void actualizarListaJugadas(String list){
        DefaultListModel listModelJugadores = new DefaultListModel();
        if(!list.equals("")){
            String[] jugadas=list.split(",");
            for(int i=1;i<=jugadas.length;i++){
                if(jugadas[i-1].contains(" al ")) {
                    listModelJugadores.addElement(jugadas[i - 1]);
                }else{
                    if(jugadas[i-1].equals(Constantes.JUGADA_GRANDE)
                            && jugada.equals(Constantes.JUGADA_DE_MANO)) {
                        listModelJugadores.addElement(Constantes.JUGADA_DORMIDA);
                    }else if(!jugadas[i-1].contains("Borrar")&&
                            jugada.equals(Constantes.JUGADA_DE_MANO)){
                        listModelJugadores.addElement(jugadas[i-1]+Constantes.JUGADA_DE_MANO);
                    }else{
                        listModelJugadores.addElement(jugadas[i - 1]);
                    }
                }
            }
        }else{
            listModelJugadores.addElement(" ");
        }
        listaJugadas.setModel(listModelJugadores);
    }
    public void actualizarDados() {
        ImageIcon icon;ImageIcon image;
        if(!cubilete.getDado(0).fueElegido()){
            image = new ImageIcon(getClass().getResource(
                    ("/Imagenes/Dado" + cubilete.getDado(0).getValor() + ".png")));
            icon = new ImageIcon(image.getImage().getScaledInstance(
                     tamGrande,tamGrande,Image.SCALE_DEFAULT));
        }else{
            image = new ImageIcon(getClass().getResource(
                    ("/Imagenes/Dado" + cubilete.getDado(0).getValor() + ".png")));
            icon = new ImageIcon(image.getImage().getScaledInstance(
                    tamPeque,tamPeque,Image.SCALE_DEFAULT));    
        }imagenDado1.setIcon(icon);
        
        if(!cubilete.getDado(1).fueElegido()){
            image = new ImageIcon(getClass().getResource(
                    ("/Imagenes/Dado" + cubilete.getDado(1).getValor() + ".png")));
            icon = new ImageIcon(image.getImage().getScaledInstance(
                     tamGrande,tamGrande,Image.SCALE_DEFAULT));
        }else{
            image = new ImageIcon(getClass().getResource(
                    ("/Imagenes/Dado" + cubilete.getDado(1).getValor() + ".png")));
            icon = new ImageIcon(image.getImage().getScaledInstance(
                    tamPeque,tamPeque,Image.SCALE_DEFAULT));    
        }imagenDado2.setIcon(icon);
        
        if(!cubilete.getDado(2).fueElegido()){
            image = new ImageIcon(getClass().getResource(
                    ("/Imagenes/Dado" + cubilete.getDado(2).getValor() + ".png")));
            icon = new ImageIcon(image.getImage().getScaledInstance(
                     tamGrande,tamGrande,Image.SCALE_DEFAULT));
        }else{
            image = new ImageIcon(getClass().getResource(
                    ("/Imagenes/Dado" + cubilete.getDado(2).getValor() + ".png")));
            icon = new ImageIcon(image.getImage().getScaledInstance(
                    tamPeque,tamPeque,Image.SCALE_DEFAULT));    
        }imagenDado3.setIcon(icon);
        
        if(!cubilete.getDado(3).fueElegido()){
            image = new ImageIcon(getClass().getResource(
                    ("/Imagenes/Dado" + cubilete.getDado(3).getValor() + ".png")));
            icon = new ImageIcon(image.getImage().getScaledInstance(
                     tamGrande,tamGrande,Image.SCALE_DEFAULT));
        }else{
            image = new ImageIcon(getClass().getResource(
                    ("/Imagenes/Dado" + cubilete.getDado(3).getValor() + ".png")));
            icon = new ImageIcon(image.getImage().getScaledInstance(
                    tamPeque,tamPeque,Image.SCALE_DEFAULT));    
        }imagenDado4.setIcon(icon);
        
        if(!cubilete.getDado(4).fueElegido()){
            image = new ImageIcon(getClass().getResource(
                    ("/Imagenes/Dado" + cubilete.getDado(4).getValor() + ".png")));
            icon = new ImageIcon(image.getImage().getScaledInstance(
                     tamGrande,tamGrande,Image.SCALE_DEFAULT));
        }else{
            image = new ImageIcon(getClass().getResource(
                    ("/Imagenes/Dado" + cubilete.getDado(4).getValor() + ".png")));
            icon = new ImageIcon(image.getImage().getScaledInstance(
                    tamPeque,tamPeque,Image.SCALE_DEFAULT));    
        }imagenDado5.setIcon(icon);
        if(cubilete.todosSeleccionados()){
            botonLanzar.setText("VER JUGADAS");
            if(canTirosRealizados==1){
                jugada=Constantes.JUGADA_DE_MANO;
            }
            puedePedirJugadas=true;
            canTirosRealizados=3;
        }else{
            botonLanzar.setText("LANZAR DADOS");
            jugada="";
            puedePedirJugadas=false;
            canTirosRealizados=aux;
        }
    }
    public void actualizarNombresJugadores(String lista){
        listaJugadores=lista.split(",");
        ultimaJugadaJugador1.setText("");
        jugador2.setText("");
        tableroJugador2.setVisible(false);
        ultimaJugadaJugador2.setText("");
        jugador3.setText("");
        tableroJugador3.setVisible(false);
        ultimaJugadaJugador3.setText("");
        jugador4.setText("");
        tableroJugador4.setVisible(false);
        ultimaJugadaJugador4.setText("");
        jugador5.setText("");
        tableroJugador5.setVisible(false);
        ultimaJugadaJugador5.setText("");
        setEnJugadores(true,0,"");
    }
    public void setEnJugadores(boolean ponerNombres,int numJugador,String ultimaJugada){
        for (int i = 1; i <= listaJugadores.length; i++) {
            String el=listaJugadores[i - 1];
            String nu=el.substring(7,8);
            String nom=el.substring(9,el.length());
            int numero = Integer.parseInt(nu);
            String[] miNum = cliente.getId().split("R");
            int miNumero = Integer.parseInt(miNum[1]);
            int dif=miNumero-numero;
            if(ponerNombres){
                setEnJugadores(false,numero," ");
            }
            if(dif>0){
                switch(dif){
                    case 1:
                        if(ponerNombres) {
                            jugador3.setText(nom);
                            jugador3.setVisible(true);
                            tableroJugador3.setVisible(true);
                        } else if(numero==numJugador){
                            actualizarTablero(3,ultimaJugada);
                        }
                        break;
                    case 2:
                        if(ponerNombres){
                            jugador2.setText(nom);
                            jugador2.setVisible(true);
                            tableroJugador2.setVisible(true);
                        } else if(numero==numJugador){
                            actualizarTablero(2,ultimaJugada);
                        }
                        break;
                    case 3:
                        if(ponerNombres){
                            jugador4.setText(nom);
                            jugador4.setVisible(true);
                            tableroJugador4.setVisible(true);
                        } else if(numero==numJugador){
                            actualizarTablero(4,ultimaJugada);
                        }
                        break;
                    case 4:
                        if(ponerNombres){
                            jugador5.setText(nom);
                            jugador5.setVisible(true);
                            tableroJugador5.setVisible(true);
                        } else if(numero==numJugador){
                            actualizarTablero(5,ultimaJugada);
                        }
                        break;
                }
            }else if(dif<0){
                switch(dif){
                    case -1:
                        if(ponerNombres){
                        jugador5.setText(nom);
                        jugador5.setVisible(true);
                        tableroJugador5.setVisible(true);
                        } else if(numero==numJugador){
                            actualizarTablero(5,ultimaJugada);
                        }
                        break;
                    case -2:
                        if(ponerNombres){
                            jugador4.setText(nom);
                            jugador4.setVisible(true);
                            tableroJugador4.setVisible(true);
                        } else if(numero==numJugador){
                            actualizarTablero(4,ultimaJugada);
                        }
                        break;
                    case -3:
                        if(ponerNombres){
                            jugador2.setText(nom);
                            jugador2.setVisible(true);
                            tableroJugador2.setVisible(true);
                        } else if(numero==numJugador){
                            actualizarTablero(2,ultimaJugada);
                        }
                        break;
                    case -4:
                        if(ponerNombres){
                            jugador3.setText(nom);
                            jugador3.setVisible(true);
                            tableroJugador3.setVisible(true);
                        } else if(numero==numJugador){
                            actualizarTablero(3,ultimaJugada);
                        }
                        break;
                }
            }else{
                if(!ponerNombres&&numero==numJugador){
                    actualizarTablero(1,ultimaJugada);
                }
            }
        }
    }
    public void actualizarTablero(int numTablero,String ultimaJugada){
        DefaultTableModel modelo=new DefaultTableModel();
        modelo.addColumn("");
        modelo.addColumn("");
        modelo.addColumn("");
        String[] filas=new String[3];
        if(tablero.getUno()==0)filas[0]=" ";
        else if(tablero.getUno()==-1)filas[0]="X";
        else filas[0]=Integer.toString(tablero.getUno());
        if(tablero.getEscalera()==0)filas[1]=" ";
        else if(tablero.getEscalera()==-1)filas[1]="X";
        else filas[1]=Integer.toString(tablero.getEscalera());
        if(tablero.getCuatro()==0)filas[2]=" ";
        else if(tablero.getCuatro()==-1)filas[2]="X";
        else filas[2]=Integer.toString(tablero.getCuatro());
        modelo.addRow(filas);
        
        if(tablero.getDos()==0)filas[0]=" ";
        else if(tablero.getDos()==-1)filas[0]="X";
        else filas[0]=Integer.toString(tablero.getDos());
        if(tablero.getFull()==0)filas[1]=" ";
        else if(tablero.getFull()==-1)filas[1]="X";
        else filas[1]=Integer.toString(tablero.getFull());
        if(tablero.getCinco()==0)filas[2]=" ";
        else if(tablero.getCinco()==-1)filas[2]="X";
        else filas[2]=Integer.toString(tablero.getCinco());
        modelo.addRow(filas);
        
        if(tablero.getTres()==0)filas[0]=" ";
        else if(tablero.getTres()==-1)filas[0]="X";
        else filas[0]=Integer.toString(tablero.getTres());
        if(tablero.getPoquer()==0)filas[1]=" ";
        else if(tablero.getPoquer()==-1)filas[1]="X";
        else filas[1]=Integer.toString(tablero.getPoquer());
        if(tablero.getSeis()==0)filas[2]=" ";
        else if(tablero.getSeis()==-1)filas[2]="X";
        else filas[2]=Integer.toString(tablero.getSeis());
        modelo.addRow(filas);
        
        filas[0]=" ";
        if(tablero.getGrande()==0)filas[1]=" ";
        else if(tablero.getGrande()==-1)filas[1]="X";
        else filas[1]=Integer.toString(tablero.getGrande());
        filas[2]=" ";
        modelo.addRow(filas);
        switch(numTablero){
            case 1:
                tableroJugador1.setModel(modelo);
                tableroJugador1.setFocusable(false);
                ultimaJugadaJugador1.setText(ultimaJugada);
                break;
            case 2:
                tableroJugador2.setModel(modelo);
                tableroJugador2.setFocusable(false);
                ultimaJugadaJugador2.setText(ultimaJugada);
                break;
            case 3:
                tableroJugador3.setModel(modelo);
                tableroJugador3.setFocusable(false);
                ultimaJugadaJugador3.setText(ultimaJugada);
                break;
            case 4:
                tableroJugador4.setModel(modelo);
                tableroJugador4.setFocusable(false);
                ultimaJugadaJugador4.setText(ultimaJugada);
                break;
            case 5:
                tableroJugador5.setModel(modelo);
                tableroJugador5.setFocusable(false);
                ultimaJugadaJugador5.setText(ultimaJugada);
                break;
        }
    }
    public void setTablero(String tableroJSON,String idJugador){
        tablero=json.fromJson(tableroJSON,Tablero.class);
        String[] num = idJugador.split("R");
        int numero = Integer.parseInt(num[1]);
        setEnJugadores(false, numero,tablero.getUltimaJugada());
        if(enTurno){
            cliente.controladorFinalizarTurno();
            enTurno=false;
        }
    }
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())){
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormClienteJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormClienteJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormClienteJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormClienteJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        /*java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormClienteJuego(null).setVisible(true);
            }
        });*/
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonLanzar;
    private javax.swing.JLabel imagenDado1;
    private javax.swing.JLabel imagenDado2;
    private javax.swing.JLabel imagenDado3;
    private javax.swing.JLabel imagenDado4;
    private javax.swing.JLabel imagenDado5;
    private javax.swing.JButton jButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JLabel jugador1;
    private javax.swing.JLabel jugador2;
    private javax.swing.JLabel jugador3;
    private javax.swing.JLabel jugador4;
    private javax.swing.JLabel jugador5;
    private javax.swing.JLabel jugadorEnTurno;
    private javax.swing.JList<String> listaJugadas;
    private javax.swing.JButton listoParjugarBtn;
    private javax.swing.JLabel maximoJugadoresTxt;
    private javax.swing.JLabel nombreDePartidaTxt;
    private javax.swing.JTable tableroJugador1;
    private javax.swing.JTable tableroJugador2;
    private javax.swing.JTable tableroJugador3;
    private javax.swing.JTable tableroJugador4;
    private javax.swing.JTable tableroJugador5;
    private javax.swing.JLabel tipoJuegoTxt;
    private javax.swing.JLabel ultimaJugadaJugador1;
    private javax.swing.JLabel ultimaJugadaJugador2;
    private javax.swing.JLabel ultimaJugadaJugador3;
    private javax.swing.JLabel ultimaJugadaJugador4;
    private javax.swing.JLabel ultimaJugadaJugador5;
    // End of variables declaration//GEN-END:variables

    public void setVisibleC(boolean b) {
        setVisible(b);
    }
}