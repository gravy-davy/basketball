/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basketball;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author robpo
 */
public class MainFrame extends javax.swing.JFrame {

    private Team playerTeam;
    private int viewRosterPageNumber;
    private PlayerCreator pc = new PlayerCreator();
    
    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        initComponents();
        playerTeam = new Team();
        
        
        playerTeam.getRoster().add(pc.generatePlayerByPosition("PG"));
        playerTeam.getRoster().add(pc.generatePlayerByPosition("SF"));
        playerTeam.getRoster().add(pc.generatePlayerByPosition("PG"));
        viewRosterPageNumber = 0;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        jButton19 = new javax.swing.JButton();
        viewPlayersPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.CardLayout());

        jButton19.setText("View roster");
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(353, 353, 353)
                .addComponent(jButton19)
                .addContainerGap(510, Short.MAX_VALUE))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(213, 213, 213)
                .addComponent(jButton19)
                .addContainerGap(343, Short.MAX_VALUE))
        );

        getContentPane().add(mainPanel, "card2");

        viewPlayersPanel.setPreferredSize(new java.awt.Dimension(955, 581));

        jLabel1.setText("jLabel1");

        jLabel2.setText("jLabel1");

        jLabel3.setText("jLabel1");

        jLabel4.setText("jLabel1");

        jLabel5.setText("jLabel1");

        jLabel6.setText("jLabel1");

        jLabel7.setText("jLabel1");

        jLabel8.setText("jLabel1");

        jLabel9.setText("jLabel1");

        jLabel10.setText("jLabel1");

        jLabel11.setText("jLabel1");

        jLabel12.setText("jLabel1");

        jLabel13.setText("jLabel1");

        jLabel14.setText("jLabel1");

        jLabel15.setText("jLabel1");

        jButton1.setText("Add to subs");

        jButton2.setText("Add to starters");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Details");

        jButton4.setText("Add to starters");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Add to subs");

        jButton6.setText("Details");

        jButton7.setText("Add to starters");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setText("Add to subs");

        jButton9.setText("Details");

        jButton10.setText("Add to starters");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton11.setText("Add to subs");

        jButton12.setText("Details");

        jButton13.setText("Add to starters");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jButton14.setText("Add to subs");

        jButton15.setText("Details");

        jButton16.setText("Next page");

        jButton17.setText("Prev page");

        jButton18.setText("Main menu");

        javax.swing.GroupLayout viewPlayersPanelLayout = new javax.swing.GroupLayout(viewPlayersPanel);
        viewPlayersPanel.setLayout(viewPlayersPanelLayout);
        viewPlayersPanelLayout.setHorizontalGroup(
            viewPlayersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(viewPlayersPanelLayout.createSequentialGroup()
                .addGroup(viewPlayersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(viewPlayersPanelLayout.createSequentialGroup()
                        .addGroup(viewPlayersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(viewPlayersPanelLayout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(viewPlayersPanelLayout.createSequentialGroup()
                                .addGap(66, 66, 66)
                                .addComponent(jButton3)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(viewPlayersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(viewPlayersPanelLayout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jButton6)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(viewPlayersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(viewPlayersPanelLayout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jButton9)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(viewPlayersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(viewPlayersPanelLayout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jButton12)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(viewPlayersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(viewPlayersPanelLayout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jButton15))
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(viewPlayersPanelLayout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addGroup(viewPlayersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(viewPlayersPanelLayout.createSequentialGroup()
                                .addGroup(viewPlayersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(viewPlayersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(viewPlayersPanelLayout.createSequentialGroup()
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(viewPlayersPanelLayout.createSequentialGroup()
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(viewPlayersPanelLayout.createSequentialGroup()
                                .addGroup(viewPlayersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(viewPlayersPanelLayout.createSequentialGroup()
                                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(viewPlayersPanelLayout.createSequentialGroup()
                                        .addComponent(jButton2)
                                        .addGap(53, 53, 53)
                                        .addComponent(jButton4)))
                                .addGap(60, 60, 60)
                                .addGroup(viewPlayersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton7))
                                .addGap(61, 61, 61)
                                .addGroup(viewPlayersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton10))
                                .addGap(62, 62, 62)
                                .addGroup(viewPlayersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton13))))))
                .addContainerGap(81, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, viewPlayersPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton17)
                .addGap(10, 10, 10)
                .addComponent(jButton18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton16)
                .addGap(65, 65, 65))
        );
        viewPlayersPanelLayout.setVerticalGroup(
            viewPlayersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(viewPlayersPanelLayout.createSequentialGroup()
                .addGap(108, 108, 108)
                .addGroup(viewPlayersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(83, 83, 83)
                .addGroup(viewPlayersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 105, Short.MAX_VALUE)
                .addGroup(viewPlayersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, viewPlayersPanelLayout.createSequentialGroup()
                        .addGroup(viewPlayersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2)
                            .addComponent(jButton4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(viewPlayersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jButton5)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, viewPlayersPanelLayout.createSequentialGroup()
                        .addComponent(jButton7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton8))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, viewPlayersPanelLayout.createSequentialGroup()
                        .addComponent(jButton10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton11))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, viewPlayersPanelLayout.createSequentialGroup()
                        .addComponent(jButton13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton14)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(viewPlayersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(viewPlayersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jButton6)
                    .addComponent(jButton9)
                    .addComponent(jButton12)
                    .addComponent(jButton15))
                .addGap(30, 30, 30)
                .addGroup(viewPlayersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton16)
                    .addComponent(jButton17)
                    .addComponent(jButton18))
                .addGap(19, 19, 19))
        );

        getContentPane().add(viewPlayersPanel, "card3");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int index = viewRosterPageNumber * 5 + 0;
        Player p = playerTeam.getRoster().get(index);
        
        addPlayerToStarters(p);
        
        setupViewRosterPanel();
        switchToAnotherPanel(viewPlayersPanel, viewPlayersPanel);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        setupViewRosterPanel();
        switchToAnotherPanel(mainPanel, viewPlayersPanel);
    }//GEN-LAST:event_jButton19ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        int index = viewRosterPageNumber * 5 + 1;
        Player p = playerTeam.getRoster().get(index);
        
        addPlayerToStarters(p);
        
        setupViewRosterPanel();
        switchToAnotherPanel(viewPlayersPanel, viewPlayersPanel);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        int index = viewRosterPageNumber * 5 + 2;
        Player p = playerTeam.getRoster().get(index);
        
        addPlayerToStarters(p);
        
        setupViewRosterPanel();
        switchToAnotherPanel(viewPlayersPanel, viewPlayersPanel);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        int index = viewRosterPageNumber * 5 + 3;
        Player p = playerTeam.getRoster().get(index);
        
        addPlayerToStarters(p);
        
        setupViewRosterPanel();
        switchToAnotherPanel(viewPlayersPanel, viewPlayersPanel);
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        int index = viewRosterPageNumber * 5 + 4;
        Player p = playerTeam.getRoster().get(index);
        
        addPlayerToStarters(p);
        
        setupViewRosterPanel();
        switchToAnotherPanel(viewPlayersPanel, viewPlayersPanel);
    }//GEN-LAST:event_jButton13ActionPerformed

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
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }
    
    private void addPlayerToStarters(Player p){
        Player starterThere;
        
        if(p.getPosition().equalsIgnoreCase("PG")){
            if(playerTeam.getSquad()[0] != null){
                starterThere = playerTeam.getSquad()[0];
                playerTeam.getRoster().add(starterThere);
            }
            playerTeam.getSquad()[0] = p;
        }else if(p.getPosition().equalsIgnoreCase("SG")){
            if(playerTeam.getSquad()[1] != null){
                starterThere = playerTeam.getSquad()[1];
                playerTeam.getRoster().add(starterThere);
            }
            playerTeam.getSquad()[1] = p;
        }else if(p.getPosition().equalsIgnoreCase("SF")){
            if(playerTeam.getSquad()[2] != null){
                starterThere = playerTeam.getSquad()[2];
                playerTeam.getRoster().add(starterThere);
            }
            playerTeam.getSquad()[2] = p;
        }else if(p.getPosition().equalsIgnoreCase("PF")){
            if(playerTeam.getSquad()[3] != null){
                starterThere = playerTeam.getSquad()[3];
                playerTeam.getRoster().add(starterThere);
            }
            playerTeam.getSquad()[3] = p;
        }else{
            if(playerTeam.getSquad()[4] != null){
                starterThere = playerTeam.getSquad()[4];
                playerTeam.getRoster().add(starterThere);
            }
            playerTeam.getSquad()[4] = p;
        }
        
        playerTeam.getRoster().remove(p);
    }
    
    /**
     * Switches from one panel to another
     * 
     * @param currentPanel
     * @param newPanel 
     */
    public void switchToAnotherPanel(JPanel currentPanel, JPanel newPanel){
        setContentPane(newPanel);
        currentPanel.hide();
        newPanel.show();
        getContentPane().revalidate();
        getContentPane().repaint();
    }
    
    private void setupViewRosterPanel(){
        // this will eventually print the overall as well
        try{
            int index = viewRosterPageNumber * 5 + 0;
            Player p = playerTeam.getRoster().get(index);
            jLabel11.setText(p.getName() + " - " + p.getPosition());
            jButton1.show();
            jButton2.show();
            jButton3.show();
        }catch(Exception e){
            jLabel11.setText("Empty");
            jButton1.hide();
            jButton2.hide();
            jButton3.hide();
        }
        
        try{
            int index = viewRosterPageNumber * 5 + 1;
            Player p = playerTeam.getRoster().get(index);
            jLabel12.setText(p.getName() + " - " + p.getPosition());
            jButton4.show();
            jButton5.show();
            jButton6.show();
        }catch(Exception e){
            jLabel12.setText("Empty");
            jButton4.hide();
            jButton5.hide();
            jButton6.hide();
        }
        
        try{
            int index = viewRosterPageNumber * 5 + 2;
            Player p = playerTeam.getRoster().get(index);
            jLabel13.setText(p.getName() + " - " + p.getPosition());
            jButton7.show();
            jButton8.show();
            jButton9.show();
        }catch(Exception e){
            jLabel13.setText("Empty");
            jButton7.hide();
            jButton8.hide();
            jButton9.hide();
        }
        
        try{
            int index = viewRosterPageNumber * 5 + 3;
            Player p = playerTeam.getRoster().get(index);
            jLabel14.setText(p.getName() + " - " + p.getPosition());
            jButton10.show();
            jButton11.show();
            jButton12.show();
        }catch(Exception e){
            jLabel14.setText("Empty");
            jButton10.hide();
            jButton11.hide();
            jButton12.hide();
        }
        
        try{
            int index = viewRosterPageNumber * 5 + 4;
            Player p = playerTeam.getRoster().get(index);
            jLabel15.setText(p.getName() + " - " + p.getPosition());
            jButton13.show();
            jButton14.show();
            jButton15.show();
        }catch(Exception e){
            jLabel15.setText("Empty");
            jButton13.hide();
            jButton14.hide();
            jButton15.hide();
        }
        
        
        
        try{
            Player p = playerTeam.getSquad()[0];
            jLabel1.setText(p.getName() + " - " + p.getPosition());
        }catch(Exception e){
            jLabel1.setText("Empty");
        }
        
        try{
            Player p = playerTeam.getSquad()[1];
            jLabel2.setText(p.getName() + " - " + p.getPosition());
        }catch(Exception e){
            jLabel2.setText("Empty");
        }
        
        try{
            Player p = playerTeam.getSquad()[2];
            jLabel3.setText(p.getName() + " - " + p.getPosition());
        }catch(Exception e){
            jLabel3.setText("Empty");
        }
        
        try{
            Player p = playerTeam.getSquad()[3];
            jLabel4.setText(p.getName() + " - " + p.getPosition());
        }catch(Exception e){
            jLabel4.setText("Empty");
        }
        
        try{
            Player p = playerTeam.getSquad()[4];
            jLabel5.setText(p.getName() + " - " + p.getPosition());
        }catch(Exception e){
            jLabel5.setText("Empty");
        }
        
        
        
        try{
            Player p = playerTeam.getBench()[0];
            jLabel6.setText(p.getName() + " - " + p.getPosition());
        }catch(Exception e){
            jLabel6.setText("Empty");
        }
        
        try{
            Player p = playerTeam.getBench()[1];
            jLabel7.setText(p.getName() + " - " + p.getPosition());
        }catch(Exception e){
            jLabel7.setText("Empty");
        }
        
        try{
            Player p = playerTeam.getBench()[2];
            jLabel8.setText(p.getName() + " - " + p.getPosition());
        }catch(Exception e){
            jLabel8.setText("Empty");
        }
        
        try{
            Player p = playerTeam.getBench()[3];
            jLabel9.setText(p.getName() + " - " + p.getPosition());
        }catch(Exception e){
            jLabel9.setText("Empty");
        }
        
        try{
            Player p = playerTeam.getBench()[4];
            jLabel10.setText(p.getName() + " - " + p.getPosition());
        }catch(Exception e){
            jLabel10.setText("Empty");
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JPanel viewPlayersPanel;
    // End of variables declaration//GEN-END:variables
}
