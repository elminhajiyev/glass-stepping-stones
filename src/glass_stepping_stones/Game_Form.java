/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glass_stepping_stones;

import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Random;
import javafx.scene.layout.Border;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.border.MatteBorder;

/**
 *
 * @author ELmiN HaJiYeV
 */
public class Game_Form extends javax.swing.JFrame {

    /**
     * Creates new form Game_Form
     */
    
  
    //qara serhed yaradaq
    MatteBorder black_border = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.yellow);
    
    // get the images paths
    String footsteps = "\\/images/foot-steps.png";
    String nofootsteps = "\\/images/no-step.png";
    String cracks = "\\/images/cracks.png";
    
    //jpanel component
    Component[] comp1;
    Component [] comp2;
    
    Random random = new Random ();
    String randomImage;
    
    JLabel[][] labels;
    String[][] imagesOrder = new String [6][2];
    ArrayList<String> list = new ArrayList<>();
    
    int counter  = 0;
    boolean won = true;
    
    
    
    
    public Game_Form() {
        initComponents();
       //formun merkezi
       this.setLocationRelativeTo(null);
    
        
        //serhedleri teyin eleyek.
        jPanel_Start.setBorder(black_border);   
        jPanel_Finish.setBorder(black_border);
        jLabel_PlayerStart.setBorder(black_border);
        jLabel_PlayerFinish.setBorder(black_border);
      
        
        //set image 
        displayImage(footsteps, jLabel_PlayerStart);
        
        // jlabels-lari labels cedveline elave edek
        labels = new JLabel[][]{{jLabel_1_1,jLabel_1_2},{jLabel_2_1,jLabel_2_2},
                                {jLabel_3_1,jLabel_3_2},{jLabel_4_1,jLabel_4_2},
                                {jLabel_5_1,jLabel_5_2},{jLabel_6_1,jLabel_6_2},};
        
        //disable all labels
        for (JLabel[] lbls : labels){
            lbls[0].setEnabled(false);
            lbls[1].setEnabled(false);
        }
        
        // sekilleri liste elave edek
        list.add(footsteps);
        list.add(cracks);
        
        // get component from jpanel
        comp1 = jPanel2.getComponents();
        comp2 = jPanel3.getComponents();
        
        //populate table with images in random order
        randomImages();
        
        // add action to jlabels
        addAction();
        
          
    }
    //bize lazim olan funksiyalar
    
    
    //create a function to enable jlabels
    public void enableLabels(int index){
        if (index <= labels.length-1){
            JLabel[] lbls = labels[index];
            lbls[0].setEnabled(true);
            lbls[1].setEnabled(true);
        }
    }
    
    //random sekiller getiren funksiya yaradaq
    public void randomImages(){
        for (int i =0; i<labels.length; i++)
        {
            
           //get random images
           randomImage = list.get(random.nextInt(list.size()));
           imagesOrder[i][0] = randomImage;
           
           //get a different image
           if(randomImage.equals(footsteps))
           {
               imagesOrder[i][1]=cracks;
             
           }
           else{
               imagesOrder[i][1]=footsteps;
           }
            
            System.out.println(imagesOrder[i][1]);
            System.out.println(imagesOrder[i][0]);
            System.out.println("---------------");

        }
    }
    
   //kliklediyimiz zaman jlabel-e hereket elave etmek ucun funksiyani yazaq. 
    
    public void addAction(){
        enableLabels(counter);
        //jpanel2
        for(Component comp:comp1){
            
            if (comp instanceof JLabel){
                
                JLabel label = (JLabel) comp;
                label.addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseClicked(java.awt.event.MouseEvent evt){
                     
                        if(label.isEnabled()){
                            displayImage(imagesOrder[counter][1], label);
                            if (imagesOrder[counter][1].equals(cracks)){
                                
                                won=false;
                            }
                            //disable jlabel
                            label.setEnabled(false);
                            JLabel label = (JLabel) comp2[counter];
                            label.setEnabled(false);
                             if (counter == imagesOrder.length-1 && won == true){
                                 
                                 displayImage(footsteps, jLabel_PlayerFinish);
                                 jLabel_message.setText("Siz qazandınız");
                             }
                             else  if (won == false){
                                 
                                 
                                 jLabel_message.setText("Siz uduzdunuz");
                             }
                             
                        
                            counter++; 
                            enableLabels(counter);
                        }
                    }
                    
                    
                });
                
            }
        }
        
        //jpanel3
         for(Component comp:comp2){
            if (comp instanceof JLabel){
                JLabel label = (JLabel) comp;
                label.addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseClicked(java.awt.event.MouseEvent evt){
                        if(label.isEnabled()){
                            displayImage(imagesOrder[counter][0], label);
                             if (imagesOrder[counter][0].equals(cracks)){
                                
                                won=false;
                            }
                            //disable jlabel
                            label.setEnabled(false);
                            JLabel label = (JLabel) comp1[counter];
                            label.setEnabled(false);
                              if (counter == imagesOrder.length-1 && won == true){
                                 
                                 displayImage(footsteps, jLabel_PlayerFinish);
                                 jLabel_message.setText("Siz qazandınız");
                             }
                             else  if (won == false){
                                 
                                
                                 jLabel_message.setText("Siz uduzdunuz");
                             }
                            counter++; 
                            
                           enableLabels(counter);
                    }
                }});
               
              
            }
        }
        
    }
   
    //sekli gostermek ucun funnksiya
    
    
    public void displayImage(String imgPath, JLabel label){
        
        //get the image
        ImageIcon imgIco = new ImageIcon(getClass().getResource(imgPath));
        
        //make the image fit the given jlabel
       
        Image image = imgIco.getImage().getScaledInstance(label.getWidth(),label.getHeight(), Image.SCALE_SMOOTH);
        
        //set the image into the jlabel
        label.setIcon(new ImageIcon(image));
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel_Finish = new javax.swing.JPanel();
        jLabel_PlayerFinish = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel_6_1 = new javax.swing.JLabel();
        jLabel_3_1 = new javax.swing.JLabel();
        jLabel_4_1 = new javax.swing.JLabel();
        jLabel_5_1 = new javax.swing.JLabel();
        jLabel_2_1 = new javax.swing.JLabel();
        jLabel_1_1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel_2_2 = new javax.swing.JLabel();
        jLabel_3_2 = new javax.swing.JLabel();
        jLabel_4_2 = new javax.swing.JLabel();
        jLabel_1_2 = new javax.swing.JLabel();
        jLabel_5_2 = new javax.swing.JLabel();
        jLabel_6_2 = new javax.swing.JLabel();
        jPanel_Start = new javax.swing.JPanel();
        jLabel_PlayerStart = new javax.swing.JLabel();
        jButton_PlayAgain_ = new javax.swing.JButton();
        jLabel_message = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel_Finish.setBackground(new java.awt.Color(102, 102, 102));

        javax.swing.GroupLayout jPanel_FinishLayout = new javax.swing.GroupLayout(jPanel_Finish);
        jPanel_Finish.setLayout(jPanel_FinishLayout);
        jPanel_FinishLayout.setHorizontalGroup(
            jPanel_FinishLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_FinishLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel_PlayerFinish, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel_FinishLayout.setVerticalGroup(
            jPanel_FinishLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_FinishLayout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(jLabel_PlayerFinish, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(0, 0, 102));

        jLabel_6_1.setBackground(new java.awt.Color(255, 204, 204));
        jLabel_6_1.setOpaque(true);

        jLabel_3_1.setBackground(new java.awt.Color(255, 204, 204));
        jLabel_3_1.setOpaque(true);

        jLabel_4_1.setBackground(new java.awt.Color(255, 204, 204));
        jLabel_4_1.setOpaque(true);

        jLabel_5_1.setBackground(new java.awt.Color(255, 204, 204));
        jLabel_5_1.setOpaque(true);

        jLabel_2_1.setBackground(new java.awt.Color(255, 204, 204));
        jLabel_2_1.setOpaque(true);

        jLabel_1_1.setBackground(new java.awt.Color(255, 204, 204));
        jLabel_1_1.setOpaque(true);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel_1_1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel_2_1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel_3_1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel_4_1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel_5_1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel_6_1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel_3_1, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
                    .addComponent(jLabel_6_1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel_4_1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel_5_1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel_2_1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel_1_1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(0, 0, 102));

        jLabel_2_2.setBackground(new java.awt.Color(255, 204, 204));
        jLabel_2_2.setOpaque(true);

        jLabel_3_2.setBackground(new java.awt.Color(255, 204, 204));
        jLabel_3_2.setOpaque(true);

        jLabel_4_2.setBackground(new java.awt.Color(255, 204, 204));
        jLabel_4_2.setOpaque(true);

        jLabel_1_2.setBackground(new java.awt.Color(255, 204, 204));
        jLabel_1_2.setOpaque(true);

        jLabel_5_2.setBackground(new java.awt.Color(255, 204, 204));
        jLabel_5_2.setOpaque(true);

        jLabel_6_2.setBackground(new java.awt.Color(255, 204, 204));
        jLabel_6_2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel_6_2.setOpaque(true);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel_1_2, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel_2_2, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel_3_2, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel_4_2, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel_5_2, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel_6_2, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel_3_2, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
                    .addComponent(jLabel_2_2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel_4_2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel_5_2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel_1_2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel_6_2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel_Start.setBackground(new java.awt.Color(102, 102, 102));

        javax.swing.GroupLayout jPanel_StartLayout = new javax.swing.GroupLayout(jPanel_Start);
        jPanel_Start.setLayout(jPanel_StartLayout);
        jPanel_StartLayout.setHorizontalGroup(
            jPanel_StartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_StartLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel_PlayerStart, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel_StartLayout.setVerticalGroup(
            jPanel_StartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_StartLayout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(jLabel_PlayerStart, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton_PlayAgain_.setBackground(new java.awt.Color(0, 204, 0));
        jButton_PlayAgain_.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jButton_PlayAgain_.setForeground(new java.awt.Color(255, 255, 255));
        jButton_PlayAgain_.setText("Yenidən oyna");
        jButton_PlayAgain_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_PlayAgain_ActionPerformed(evt);
            }
        });

        jLabel_message.setFont(new java.awt.Font("Magneto", 0, 48)); // NOI18N
        jLabel_message.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jPanel_Start, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel_Finish, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(295, 295, 295)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton_PlayAgain_, javax.swing.GroupLayout.DEFAULT_SIZE, 555, Short.MAX_VALUE)
                            .addComponent(jLabel_message, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(65, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel_Start, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel_Finish, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(29, 29, 29)
                .addComponent(jLabel_message, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(jButton_PlayAgain_, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_PlayAgain_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_PlayAgain_ActionPerformed
        
        // her seyi sifirla
         //populate table with images in random order
        randomImages();
        //remove images from the jlabels
        for(JLabel[] lbls : labels){
            lbls[0].setIcon(null);
            lbls[1].setIcon(null);
        }
        jLabel_PlayerFinish.setIcon(null);
        counter = 0;
        won = true;
        jLabel_message.setText("");
        enableLabels(counter);
    }//GEN-LAST:event_jButton_PlayAgain_ActionPerformed

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
            java.util.logging.Logger.getLogger(Game_Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Game_Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Game_Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Game_Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Game_Form().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_PlayAgain_;
    private javax.swing.JLabel jLabel_1_1;
    private javax.swing.JLabel jLabel_1_2;
    private javax.swing.JLabel jLabel_2_1;
    private javax.swing.JLabel jLabel_2_2;
    private javax.swing.JLabel jLabel_3_1;
    private javax.swing.JLabel jLabel_3_2;
    private javax.swing.JLabel jLabel_4_1;
    private javax.swing.JLabel jLabel_4_2;
    private javax.swing.JLabel jLabel_5_1;
    private javax.swing.JLabel jLabel_5_2;
    private javax.swing.JLabel jLabel_6_1;
    private javax.swing.JLabel jLabel_6_2;
    private javax.swing.JLabel jLabel_PlayerFinish;
    private javax.swing.JLabel jLabel_PlayerStart;
    private javax.swing.JLabel jLabel_message;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel_Finish;
    private javax.swing.JPanel jPanel_Start;
    // End of variables declaration//GEN-END:variables
}
