package com.kirim.pesan.lewat.wa.dan.telegram;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import it.auties.whatsapp.api.QrHandler;
import it.auties.whatsapp.api.Whatsapp;
import it.auties.whatsapp.model.jid.Jid;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Aplikasi extends javax.swing.JFrame {
    Whatsapp api;
    Telegram tele;
    
    public Aplikasi() {
        initComponents();
        
        LoadingWa loading = new LoadingWa(this, true);
        
        new Thread(() -> {
            loading.setVisible(true);
        }).start();
        
        new Thread(() -> {
            Consumer<String> QR = ((KodeQR) -> {
                try {
                    Map<EncodeHintType, ErrorCorrectionLevel> hashMap = new HashMap<EncodeHintType, ErrorCorrectionLevel>();
                    hashMap.put(EncodeHintType.ERROR_CORRECTION,ErrorCorrectionLevel.L);
                    createQR(KodeQR, "wa.png", "utf-8", hashMap, 200, 200);
                    ImageIcon qrcode = new ImageIcon("wa.png");
                    QRWA.setIcon(qrcode);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                
                loading.dispose();
            });
            
            try {
                tele = Telegram.MuatPengaturan();
                TokenBot.setText(tele.getToken());
            } catch (Exception e) {
                tele = new Telegram("", new HashMap<>());
            }
            
            api = Whatsapp.webBuilder()
            .lastConnection()
            .unregistered(QrHandler.toPlainString(QR))
            .addLoggedInListener(() -> {
                loading.dispose();
                PanelWA.remove(TempatQR);
                PanelWA.revalidate();
                PanelWA.repaint();
            })
            .connect()
            .join();
        }).start();
    }
    
    public static void createQR(String data, String path, String charset, Map hashMap, int height, int width) throws WriterException, IOException
    {
        BitMatrix matrix = new MultiFormatWriter().encode( new String(data.getBytes(charset), charset),BarcodeFormat.QR_CODE, width, height);
        MatrixToImageWriter.writeToPath(matrix, path.substring(path.lastIndexOf('.') + 1), Paths.get(path));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        PanelWA = new javax.swing.JPanel();
        TempatQR = new javax.swing.JPanel();
        QRWA = new javax.swing.JLabel();
        InteraksiWA = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        NoWA = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        PesanWA = new javax.swing.JTextArea();
        KirimWA = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        PanelTelegram = new javax.swing.JPanel();
        InteraksiTele = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        TokenBot = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        PesanTele = new javax.swing.JTextArea();
        KirimTele = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        UsernameTele = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Kirim Pesan");
        setResizable(false);

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        TempatQR.setBackground(java.awt.SystemColor.control);
        TempatQR.setBorder(javax.swing.BorderFactory.createTitledBorder("Scan QR Dulu"));

        QRWA.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout TempatQRLayout = new javax.swing.GroupLayout(TempatQR);
        TempatQR.setLayout(TempatQRLayout);
        TempatQRLayout.setHorizontalGroup(
            TempatQRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TempatQRLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(QRWA, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );
        TempatQRLayout.setVerticalGroup(
            TempatQRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TempatQRLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(QRWA, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jLabel2.setText("No Wa");

        jLabel3.setText("Pesan");

        PesanWA.setColumns(20);
        PesanWA.setRows(5);
        PesanWA.setText("hello world\ndikirim ke wa\nlewat software");
        jScrollPane1.setViewportView(PesanWA);

        KirimWA.setText("Kirim");
        KirimWA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                KirimWAActionPerformed(evt);
            }
        });

        jLabel7.setText("Dengan kode negara tanpa tanda +");

        javax.swing.GroupLayout InteraksiWALayout = new javax.swing.GroupLayout(InteraksiWA);
        InteraksiWA.setLayout(InteraksiWALayout);
        InteraksiWALayout.setHorizontalGroup(
            InteraksiWALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(InteraksiWALayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(InteraksiWALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, InteraksiWALayout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(jLabel7)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, InteraksiWALayout.createSequentialGroup()
                        .addGroup(InteraksiWALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(InteraksiWALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(KirimWA, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(NoWA)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
                .addGap(18, 18, 18))
        );
        InteraksiWALayout.setVerticalGroup(
            InteraksiWALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(InteraksiWALayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(InteraksiWALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(NoWA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addGroup(InteraksiWALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addComponent(KirimWA, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout PanelWALayout = new javax.swing.GroupLayout(PanelWA);
        PanelWA.setLayout(PanelWALayout);
        PanelWALayout.setHorizontalGroup(
            PanelWALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelWALayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(InteraksiWA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(TempatQR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        PanelWALayout.setVerticalGroup(
            PanelWALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelWALayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelWALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(TempatQR, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(InteraksiWA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Whatsapp", PanelWA);

        jLabel4.setText("Token Bot");

        jLabel5.setText("Pesan");

        PesanTele.setColumns(20);
        PesanTele.setRows(5);
        PesanTele.setText("hello world\ndikirim ke telegram\nlewat software");
        jScrollPane2.setViewportView(PesanTele);

        KirimTele.setText("Kirim");
        KirimTele.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                KirimTeleActionPerformed(evt);
            }
        });

        jLabel9.setText("Username Telegram");

        javax.swing.GroupLayout InteraksiTeleLayout = new javax.swing.GroupLayout(InteraksiTele);
        InteraksiTele.setLayout(InteraksiTeleLayout);
        InteraksiTeleLayout.setHorizontalGroup(
            InteraksiTeleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(InteraksiTeleLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(InteraksiTeleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4)
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addGroup(InteraksiTeleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(KirimTele, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE)
                    .addComponent(UsernameTele, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(TokenBot, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18))
        );
        InteraksiTeleLayout.setVerticalGroup(
            InteraksiTeleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(InteraksiTeleLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(InteraksiTeleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(TokenBot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(InteraksiTeleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(UsernameTele, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(InteraksiTeleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
                    .addGroup(InteraksiTeleLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addComponent(KirimTele, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout PanelTelegramLayout = new javax.swing.GroupLayout(PanelTelegram);
        PanelTelegram.setLayout(PanelTelegramLayout);
        PanelTelegramLayout.setHorizontalGroup(
            PanelTelegramLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelTelegramLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(InteraksiTele, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        PanelTelegramLayout.setVerticalGroup(
            PanelTelegramLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelTelegramLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(InteraksiTele, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Telegram", PanelTelegram);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void KirimWAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_KirimWAActionPerformed
        // TODO add your handling code here:
        KirimWA.setEnabled(false);
        try {
            Jid nomor = Jid.of(NoWA.getText());
            var kontak = api.store().addContact(nomor);
            api.sendChatMessage(kontak, PesanWA.getText());
            
            JOptionPane.showMessageDialog(this, "Pesan terkirim");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(this, "Pesan gagal terkirim");
        }
        KirimWA.setEnabled(true);
    }//GEN-LAST:event_KirimWAActionPerformed

    private void KirimTeleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_KirimTeleActionPerformed
        // TODO add your handling code here:
        new Thread(() -> {
            KirimTele.setEnabled(false);
            
            if (tele.getToken().isEmpty()) { tele.setToken(TokenBot.getText()); }
            
            try {
                Telegram.UpdateChatID(tele);
                Telegram.SimpanPengaturan(tele);
                tele.KirimPesan(UsernameTele.getText(), PesanTele.getText());
                JOptionPane.showMessageDialog(this, "Pesan terkirim");
            } catch (Exception e) {
                System.out.println(e.getMessage());
                JOptionPane.showMessageDialog(this, "Pesan gagal terkirim");
            }
            
            KirimTele.setEnabled(true);
        }).start();
    }//GEN-LAST:event_KirimTeleActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel InteraksiTele;
    private javax.swing.JPanel InteraksiWA;
    private javax.swing.JButton KirimTele;
    private javax.swing.JButton KirimWA;
    private javax.swing.JTextField NoWA;
    private javax.swing.JPanel PanelTelegram;
    private javax.swing.JPanel PanelWA;
    private javax.swing.JTextArea PesanTele;
    private javax.swing.JTextArea PesanWA;
    private javax.swing.JLabel QRWA;
    private javax.swing.JPanel TempatQR;
    private javax.swing.JTextField TokenBot;
    private javax.swing.JTextField UsernameTele;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    // End of variables declaration//GEN-END:variables
}