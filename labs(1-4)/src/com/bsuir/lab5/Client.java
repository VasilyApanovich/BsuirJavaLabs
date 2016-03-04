package com.bsuir.lab5;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by v.apanovich on 04.03.2016.
 */
public class Client extends javax.swing.JFrame implements Runnable {

    private DataOutputStream outputStream;
    private DataInputStream inputStream;
    private Socket socket;
    private String userName;
    private DefaultListModel listModel = new DefaultListModel();

    public Client() {
        initComponents();
        screen.setEditable(false);
        acc_list.setVisibleRowCount(6);
        listModel.addElement(userName);
        startChat();
    }

    @Override
    public void run() {

        try {

            while(true){
                String message = inputStream.readUTF();
                if (message.equals("**quit**")) {
                    socket.close();
                    break;
                }
                if (message.startsWith("*")) {
                    String[] usersList = message.substring(2, message.length() - 1).split(", ");
                    acc_list.setModel(new javax.swing.AbstractListModel() {
                                          String[] strings = usersList;
                                          public int getSize() { return strings.length; }
                                          public Object getElementAt(int i) { return strings[i];
                                          }});
                    acc_list.setSelectedIndex(0);
                } else {
                    screen.append(message + "\n");
                }
            }

        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void startChat() {
        try {
            socket = new Socket("127.0.0.1", 23);

            userName = JOptionPane.showInputDialog("Enter your Nickname : ");
            setTitle("Chat Client: " + userName);

            inputStream = new DataInputStream(socket.getInputStream());
            outputStream = new DataOutputStream(socket.getOutputStream());
            outputStream.writeUTF(userName);
            new Thread(Client.this).start();

        } catch (UnknownHostException ex) {
            JOptionPane.showMessageDialog(rootPane, "Socket can not connected", "ERROR", JOptionPane.ERROR_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(rootPane, "Socket can not connected", "ERROR", JOptionPane.ERROR_MESSAGE);
        }

    }




    private void write(){

        try {
            textField.setCaretPosition(0);
            String str = textField.getText();
            String sendTo = acc_list.getSelectedValue().toString();
            outputStream.writeUTF(sendTo);
            outputStream.writeUTF(userName + " : " + str);
            screen.append(userName + " : " + str + "\n");
            textField.setText("");
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void Send_ButtonActionPerformed(java.awt.event.ActionEvent evt) {
        if (!textField.getText().isEmpty()) {
            write();
        }
    }

    private void disconnectBtnClick(java.awt.event.ActionEvent evt) {
        try {
            outputStream.writeUTF("**quit**");
            this.setVisible(false);
            System.exit(0);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void Text_FieldKeyPressed(java.awt.event.KeyEvent evt) {
        if(evt.getKeyCode()== KeyEvent.VK_ENTER){
            evt.consume();
            write();
        }
    }


    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }


        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new Client().setVisible(true));
    }


    private javax.swing.JTextArea screen;
    private javax.swing.JButton sendButton;
    private javax.swing.JTextPane textField;
    private javax.swing.JList acc_list;
    private javax.swing.JButton disconnectButton;
    private javax.swing.JColorChooser font_chooser;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;

    private void initComponents() {

        font_chooser = new javax.swing.JColorChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        screen = new javax.swing.JTextArea();
        sendButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        acc_list = new javax.swing.JList();
        disconnectButton = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        textField = new javax.swing.JTextPane();

        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Chat Client");
        setResizable(false);

        screen.setColumns(20);
        screen.setRows(5);
        screen.setFont(new Font("My font", Font.PLAIN, 11));
        jScrollPane1.setViewportView(screen);

        sendButton.setText("Send");
        sendButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Send_ButtonActionPerformed(evt);
            }
        });

        acc_list.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        jScrollPane2.setViewportView(acc_list);

        disconnectButton.setText("Disconnect");
        disconnectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                disconnectBtnClick(evt);
            }
        });

        textField.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        textField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Text_FieldKeyPressed(evt);
            }
        });
        jScrollPane3.setViewportView(textField);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 383, Short.MAX_VALUE)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 383, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(disconnectButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
                                        .addComponent(sendButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(sendButton, javax.swing.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE)
                                        .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(disconnectButton, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(11, 11, 11))
        );

        pack();
    }

}
