package Forms;

import Clases.Constantes;
import Clases.Cubilete;
import java.awt.Image;
import javax.swing.ImageIcon;
import TSocket.TClient.Cliente.TSClientClienteSocket;
import TSocket.TClient.Cliente.TSocketInfo;
import com.google.gson.Gson;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.DefaultListModel;

public final class FormClienteJuego extends javax.swing.JFrame {
    
    TSClientClienteSocket cliente;
    Cubilete cubilete;
    int canTirosRealizados,tamGrande,tamPeque,aux;
    String jugada,miNombre;
    boolean enTurno,puedePedirJugadas;
    boolean[] modificable;
    
    public FormClienteJuego() {
        initComponents();
        miNombre="Jaime";
        tamGrande=imagenDado1.getWidth();
        tamPeque=tamGrande-20;
        mostrarDadosIniciales();
        canTirosRealizados=0;
        //cliente = new TSClientClienteSocket("192.168.43.121",9090){//ELITO
        //cliente = new TSClientClienteSocket("192.168.1.104",9090){//JROGE
        cliente = new TSClientClienteSocket("127.0.0.1",9090){//LOCAL
            @Override
            public void onRead(String mensaje){
                System.out.println(mensaje);
                String[] msj=mensaje.split("_");
                switch(msj[2]){
                    case Constantes.MOSTRAR_DADOS:
                        canTirosRealizados++;
                        aux=canTirosRealizados;
                        setCubilete(msj[3]);
                        actualizarDados();
                        mostrarGif();
                        Timer timer=new Timer();
                        TimerTask tarea = new TimerTask() {
                            @Override
                            public void run() {
                                
                                if (canTirosRealizados == 3) {
                                    todosSeleccionados();
                                    if(enTurno){
                                        botonLanzar.setText("VER JUGADAS");
                                        puedePedirJugadas=true;
                                    }
                                }
                                actualizarDados();
                                if(enTurno){
                                    botonLanzar.setEnabled(true);
                                }
                                timer.cancel();
                            }
                        };
                        timer.schedule(tarea, 1000, 1000);
                        break;
                    case Constantes.ES_TU_TURNO:
                        cliente.sendMensaje(Constantes.JUEGO+Constantes.NOMBRE_JUGADOR_EN_TURNO+"_"+miNombre);
                        enTurno=true;
                        botonLanzar.setEnabled(true);
                        canTirosRealizados=0;
                        puedePedirJugadas=false;
                        botonLanzar.setText("LANZAR DADOS");
                        break;
                    case Constantes.LISTA_DE_JUGADAS:
                        actualizarListaJugadas(msj[3]);
                        puedePedirJugadas = false;
                        botonLanzar.setText("SELECCIONAR JUGADA");
                        break;
                    case Constantes.NOMBRE_JUGADOR_EN_TURNO:
                        jugadorEnTurno.setText(msj[3]);
                        break;
                }
            }
            @Override
            public void onWrite(String mensaje){

            }
            @Override
            public void onConnected(TSocketInfo socketInfo){
                    System.out.println("CONECTADO");
            }
            @Override
            public void onDisconnect(TSocketInfo socketInfo){
                    System.out.println("DESCONECTADO");
            }
            @Override
            public void onReconnect(TSocketInfo socketInfo){
                    System.out.println("RECONECTADO");
            }
            @Override
            public void onError(int errorCode){
                
            }
        };
        cliente.connect();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        botonLanzar.setBackground(new java.awt.Color(0, 153, 153));
        botonLanzar.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        botonLanzar.setText("LANZAR DADOS");
        botonLanzar.setEnabled(false);
        botonLanzar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonLanzarActionPerformed(evt);
            }
        });

        imagenDado1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Dado1.jpg"))); // NOI18N
        imagenDado1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                imagenDado1MouseClicked(evt);
            }
        });

        imagenDado2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Dado2.png"))); // NOI18N
        imagenDado2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                imagenDado2MouseClicked(evt);
            }
        });

        imagenDado3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Dado3.png"))); // NOI18N
        imagenDado3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                imagenDado3MouseClicked(evt);
            }
        });

        imagenDado4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Dado4.png"))); // NOI18N
        imagenDado4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                imagenDado4MouseClicked(evt);
            }
        });

        imagenDado5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Dado5.png"))); // NOI18N
        imagenDado5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                imagenDado5MouseClicked(evt);
            }
        });

        jugadorEnTurno.setBackground(new java.awt.Color(153, 153, 0));
        jugadorEnTurno.setFont(new java.awt.Font("Courier New", 1, 30)); // NOI18N
        jugadorEnTurno.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jugadorEnTurno.setText("jugadorEnTurno");

        jugador5.setBackground(new java.awt.Color(153, 153, 0));
        jugador5.setFont(new java.awt.Font("Courier New", 1, 20)); // NOI18N
        jugador5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jugador5.setText("jugador5");

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
        tableroJugador1.setShowGrid(true);
        tableroJugador1.setUpdateSelectionOnSort(false);
        tableroJugador1.setVerifyInputWhenFocusTarget(false);
        jScrollPane6.setViewportView(tableroJugador1);
        if (tableroJugador1.getColumnModel().getColumnCount() > 0) {
            tableroJugador1.getColumnModel().getColumn(0).setResizable(false);
            tableroJugador1.getColumnModel().getColumn(1).setResizable(false);
            tableroJugador1.getColumnModel().getColumn(2).setResizable(false);
        }

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
        tableroJugador3.setShowGrid(true);
        tableroJugador3.setUpdateSelectionOnSort(false);
        tableroJugador3.setVerifyInputWhenFocusTarget(false);
        jScrollPane9.setViewportView(tableroJugador3);
        if (tableroJugador3.getColumnModel().getColumnCount() > 0) {
            tableroJugador3.getColumnModel().getColumn(0).setResizable(false);
            tableroJugador3.getColumnModel().getColumn(1).setResizable(false);
            tableroJugador3.getColumnModel().getColumn(2).setResizable(false);
        }

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
        tableroJugador2.setShowGrid(true);
        tableroJugador2.setUpdateSelectionOnSort(false);
        tableroJugador2.setVerifyInputWhenFocusTarget(false);
        jScrollPane10.setViewportView(tableroJugador2);
        if (tableroJugador2.getColumnModel().getColumnCount() > 0) {
            tableroJugador2.getColumnModel().getColumn(0).setResizable(false);
            tableroJugador2.getColumnModel().getColumn(1).setResizable(false);
            tableroJugador2.getColumnModel().getColumn(2).setResizable(false);
        }

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
        tableroJugador4.setShowGrid(true);
        tableroJugador4.setUpdateSelectionOnSort(false);
        tableroJugador4.setVerifyInputWhenFocusTarget(false);
        jScrollPane11.setViewportView(tableroJugador4);
        if (tableroJugador4.getColumnModel().getColumnCount() > 0) {
            tableroJugador4.getColumnModel().getColumn(0).setResizable(false);
            tableroJugador4.getColumnModel().getColumn(1).setResizable(false);
            tableroJugador4.getColumnModel().getColumn(2).setResizable(false);
        }

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
        tableroJugador5.setShowGrid(true);
        tableroJugador5.setUpdateSelectionOnSort(false);
        tableroJugador5.setVerifyInputWhenFocusTarget(false);
        jScrollPane12.setViewportView(tableroJugador5);
        if (tableroJugador5.getColumnModel().getColumnCount() > 0) {
            tableroJugador5.getColumnModel().getColumn(0).setResizable(false);
            tableroJugador5.getColumnModel().getColumn(1).setResizable(false);
            tableroJugador5.getColumnModel().getColumn(2).setResizable(false);
        }

        listaJugadas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listaJugadasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(listaJugadas);

        jugador2.setBackground(new java.awt.Color(153, 153, 0));
        jugador2.setFont(new java.awt.Font("Courier New", 1, 20)); // NOI18N
        jugador2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jugador2.setText("jugador2");
        jugador2.setPreferredSize(new java.awt.Dimension(80, 20));

        jugador3.setBackground(new java.awt.Color(153, 153, 0));
        jugador3.setFont(new java.awt.Font("Courier New", 1, 20)); // NOI18N
        jugador3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jugador3.setText("jugador3");

        jugador1.setBackground(new java.awt.Color(153, 153, 0));
        jugador1.setFont(new java.awt.Font("Courier New", 1, 20)); // NOI18N
        jugador1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jugador1.setText("jugador1");

        jugador4.setBackground(new java.awt.Color(153, 153, 0));
        jugador4.setFont(new java.awt.Font("Courier New", 1, 20)); // NOI18N
        jugador4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jugador4.setText("jugador4");

        ultimaJugadaJugador2.setText("ultimaJugadaJugador2");

        ultimaJugadaJugador3.setText("ultimaJugadaJugador3");

        ultimaJugadaJugador4.setText("ultimaJugadaJugador4");

        ultimaJugadaJugador5.setText("ultimaJugadaJugador5");

        ultimaJugadaJugador1.setText("ultimaJugadaJugador1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jugador2, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(110, 110, 110)
                .addComponent(jugadorEnTurno, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(150, 150, 150)
                .addComponent(jugador4, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ultimaJugadaJugador2))
                .addGap(140, 140, 140)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(imagenDado1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(imagenDado3, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(imagenDado2, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(2, 2, 2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(imagenDado5, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(imagenDado4, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(171, 171, 171)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ultimaJugadaJugador4)))
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jugador3, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(540, 540, 540)
                .addComponent(jugador5, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ultimaJugadaJugador3))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jugador1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ultimaJugadaJugador1)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addComponent(botonLanzar, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ultimaJugadaJugador5)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jugador2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jugadorEnTurno, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jugador4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ultimaJugadaJugador2))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ultimaJugadaJugador4))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(imagenDado1, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(imagenDado3, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(39, 39, 39)
                                .addComponent(imagenDado2, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(imagenDado5, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(39, 39, 39)
                                .addComponent(imagenDado4, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jugador3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jugador5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ultimaJugadaJugador3))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ultimaJugadaJugador5))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jugador1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(botonLanzar, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ultimaJugadaJugador1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonLanzarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonLanzarActionPerformed
        if (canTirosRealizados == 0) {
            cubilete=new Cubilete();
        }
        Gson json=new Gson();
        String cad=json.toJson(cubilete);
        if(puedePedirJugadas){
            cliente.sendMensaje(Constantes.JUEGO+Constantes.LISTA_DE_JUGADAS+"_"+cad);
        }else if (canTirosRealizados <= 2) {
            cliente.sendMensaje(Constantes.JUEGO+Constantes.GENERAR_DADOS+"_"+cad);
        }else{
            cliente.sendMensaje(Constantes.JUEGO+Constantes.JUGADA_ESCOGIDA+"_"+jugada);
            actualizarListaJugadas("");
            enTurno=false;
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
        if(enTurno){
            botonLanzar.setText("CONFIRMAR JUGADA");
            botonLanzar.setEnabled(true);
            jugada=listaJugadas.getSelectedValue();
        }
    }//GEN-LAST:event_listaJugadasMouseClicked

    public void setCubilete(String cubile){
        Gson json=new Gson();
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
    
    //Si altera valores-No altera tamaños
    public void actualizarImagenDado(int i){
        boolean b=cubilete.getDado(i-1).fueElegido();
        cubilete.getDado(i-1).setFueElegido(!b);
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
    
    //No altera valores-Si altera tamaños
    public void todosSeleccionados(){
        cubilete.seleccionarTodos();
        todasImagenesPeque();
    } 
    
    //No altera valores-Si altera tamaños
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
    
    public void actualizarListaJugadas(String list) {
        DefaultListModel listModelJugadores = new DefaultListModel();
        String[] jugadas = list.split(",");
        for (int i = 1; i <= jugadas.length; i++) {
            listModelJugadores.addElement(jugadas[i - 1]);
        }
        listaJugadas.setModel(listModelJugadores);
    }
    
    //Si altera valores-No altera tamaños
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
        if(cubilete.estanTodosElegidos()){
            botonLanzar.setText("VER JUGADAS");
            puedePedirJugadas=true;
            aux=canTirosRealizados;
            canTirosRealizados=3;
        }else{
            botonLanzar.setText("LANZAR DADOS");
            puedePedirJugadas=false;
            canTirosRealizados=aux;
        }
    }
    
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
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
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormClienteJuego().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonLanzar;
    private javax.swing.JLabel imagenDado1;
    private javax.swing.JLabel imagenDado2;
    private javax.swing.JLabel imagenDado3;
    private javax.swing.JLabel imagenDado4;
    private javax.swing.JLabel imagenDado5;
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
    private javax.swing.JTable tableroJugador1;
    private javax.swing.JTable tableroJugador2;
    private javax.swing.JTable tableroJugador3;
    private javax.swing.JTable tableroJugador4;
    private javax.swing.JTable tableroJugador5;
    private javax.swing.JLabel ultimaJugadaJugador1;
    private javax.swing.JLabel ultimaJugadaJugador2;
    private javax.swing.JLabel ultimaJugadaJugador3;
    private javax.swing.JLabel ultimaJugadaJugador4;
    private javax.swing.JLabel ultimaJugadaJugador5;
    // End of variables declaration//GEN-END:variables
}
