package Forms;

import Clases.Constantes;
import Clases.Cubilete;
import Clases.constNames;
import java.awt.Image;
import javax.swing.ImageIcon;
import TSocket.TClient.Cliente.TSClientClienteSocket;
import TSocket.TClient.Cliente.TSocketInfo;
import com.google.gson.Gson;
import java.util.Timer;
import java.util.TimerTask;

public final class FormClienteJuego extends javax.swing.JFrame {
    
    TSClientClienteSocket cliente;
    Cubilete cubilete;
    int canTirosRealizados,tamGrande,tamPeque;
    boolean enTurno;
    boolean[] modificable;
    
    public FormClienteJuego() {
        enTurno = true;
        canTirosRealizados = 0;
        initComponents();
        botonLanzar.setEnabled(false);
        tamGrande=imagenDado1.getWidth();
        tamPeque=tamGrande-20;
        enTurno=false;
        mostrarDadosIniciales();        
        //cliente = new TSClientClienteSocket("192.168.43.121",9090){//ELITO
        //cliente = new TSClientClienteSocket("192.168.1.104",9090){//JROGE
        cliente = new TSClientClienteSocket("127.0.0.1",9090){//JROGE
            @Override
            public void onRead(String mensaje){
                System.out.println(mensaje);
                String[] msj=mensaje.split("_");
                switch(msj[2]){
                    case constNames.MOSTRAR_DADOS:
                        setCubilete(msj[3]);
                        //AÑADIR CAMBIO DE TAMAÑO POR DADOactualizarDados();
                        mostrarGif();
                        Timer timer=new Timer();
                        TimerTask tarea = new TimerTask() {
                            @Override
                            public void run() {
                                timer.cancel();
                                canTirosRealizados++;
                                if (canTirosRealizados == 3) {
                                    todosSeleccionados();
                                    if (enTurno) {
                                        botonLanzar.setText("FINALIZAR TURNO");
                                    }
                                }
                                if(enTurno){botonLanzar.setEnabled(true);}
                                actualizarDados();
                            }
                        };
                        timer.schedule(tarea, 1000, 1000);
                        break;
                    case constNames.ES_TU_TURNO:
                        enTurno=true;
                        botonLanzar.setEnabled(true);
                        canTirosRealizados=0;
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tableroJugador1 = new javax.swing.JTable();
        jScrollPane9 = new javax.swing.JScrollPane();
        tableroJugador2 = new javax.swing.JTable();
        jScrollPane10 = new javax.swing.JScrollPane();
        tableroJugador3 = new javax.swing.JTable();
        jScrollPane11 = new javax.swing.JScrollPane();
        tableroJugador4 = new javax.swing.JTable();
        jScrollPane12 = new javax.swing.JScrollPane();
        tableroJugador5 = new javax.swing.JTable();

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

        jLabel1.setBackground(new java.awt.Color(153, 153, 0));
        jLabel1.setFont(new java.awt.Font("Courier New", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("JUGADOR");

        jLabel2.setBackground(new java.awt.Color(153, 153, 0));
        jLabel2.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("JROGE");

        jLabel3.setBackground(new java.awt.Color(153, 153, 0));
        jLabel3.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("JOSELITO");

        jLabel4.setBackground(new java.awt.Color(153, 153, 0));
        jLabel4.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("MARCO64");

        jLabel5.setBackground(new java.awt.Color(153, 153, 0));
        jLabel5.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("JUAN");

        jLabel6.setBackground(new java.awt.Color(153, 153, 0));
        jLabel6.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("MARTA");

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

        tableroJugador2.setBackground(new java.awt.Color(247, 193, 152));
        tableroJugador2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 0));
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
        jScrollPane9.setViewportView(tableroJugador2);
        if (tableroJugador2.getColumnModel().getColumnCount() > 0) {
            tableroJugador2.getColumnModel().getColumn(0).setResizable(false);
            tableroJugador2.getColumnModel().getColumn(1).setResizable(false);
            tableroJugador2.getColumnModel().getColumn(2).setResizable(false);
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
        jScrollPane10.setViewportView(tableroJugador3);
        if (tableroJugador3.getColumnModel().getColumnCount() > 0) {
            tableroJugador3.getColumnModel().getColumn(0).setResizable(false);
            tableroJugador3.getColumnModel().getColumn(1).setResizable(false);
            tableroJugador3.getColumnModel().getColumn(2).setResizable(false);
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(imagenDado1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(42, 42, 42)
                                .addComponent(imagenDado3, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32)
                                .addComponent(imagenDado5, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(42, 42, 42)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(80, 80, 80)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(imagenDado2, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(62, 62, 62)
                                .addComponent(imagenDado4, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(botonLanzar, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(3, 3, 3)
                        .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jLabel3))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(imagenDado1, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(imagenDado3, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(imagenDado5, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel5)
                        .addGap(3, 3, 3)
                        .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(3, 3, 3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(jLabel2)
                        .addGap(3, 3, 3)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(imagenDado2, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(imagenDado4, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(39, 39, 39)
                        .addComponent(botonLanzar, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(3, 3, 3)
                        .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonLanzarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonLanzarActionPerformed
        if (canTirosRealizados == 0) {
            cubilete=new Cubilete();
        }
        if (canTirosRealizados <= 2) {
            botonLanzar.setEnabled(false);
            Gson json=new Gson();
            String cad=json.toJson(cubilete);
            cliente.sendMensaje(constNames.JUEGO+constNames.GENERAR_DADOS
                    + "_" + cad);
        } else if (canTirosRealizados == 3) {
            cliente.sendMensaje(Constantes.JUEGO+Constantes.TERMINE);
            enTurno=false;
            botonLanzar.setEnabled(false);
            botonLanzar.setText("LANZAR DADOS");
        }
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
        cubilete.todosSeleccionados();
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTable tableroJugador1;
    private javax.swing.JTable tableroJugador2;
    private javax.swing.JTable tableroJugador3;
    private javax.swing.JTable tableroJugador4;
    private javax.swing.JTable tableroJugador5;
    // End of variables declaration//GEN-END:variables
}
