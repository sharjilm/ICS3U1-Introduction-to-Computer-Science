/*
 * U2_A4_FastFoodView.java
 */

package u2_a4_fastfood;

import org.jdesktop.application.Action;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;
import org.jdesktop.application.TaskMonitor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import javax.swing.Icon;
import javax.swing.JDialog;
import javax.swing.JFrame;

/**
 * The application's main frame.
 */
import java.text.*;
public class U2_A4_FastFoodView extends FrameView {

    public U2_A4_FastFoodView(SingleFrameApplication app) {
        super(app);

        initComponents();

        // status bar initialization - message timeout, idle icon and busy animation, etc
        ResourceMap resourceMap = getResourceMap();
        int messageTimeout = resourceMap.getInteger("StatusBar.messageTimeout");
        messageTimer = new Timer(messageTimeout, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                statusMessageLabel.setText("");
            }
        });
        messageTimer.setRepeats(false);
        int busyAnimationRate = resourceMap.getInteger("StatusBar.busyAnimationRate");
        for (int i = 0; i < busyIcons.length; i++) {
            busyIcons[i] = resourceMap.getIcon("StatusBar.busyIcons[" + i + "]");
        }
        busyIconTimer = new Timer(busyAnimationRate, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                busyIconIndex = (busyIconIndex + 1) % busyIcons.length;
                statusAnimationLabel.setIcon(busyIcons[busyIconIndex]);
            }
        });
        idleIcon = resourceMap.getIcon("StatusBar.idleIcon");
        statusAnimationLabel.setIcon(idleIcon);
        progressBar.setVisible(false);

        // connecting action tasks to status bar via TaskMonitor
        TaskMonitor taskMonitor = new TaskMonitor(getApplication().getContext());
        taskMonitor.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                String propertyName = evt.getPropertyName();
                if ("started".equals(propertyName)) {
                    if (!busyIconTimer.isRunning()) {
                        statusAnimationLabel.setIcon(busyIcons[0]);
                        busyIconIndex = 0;
                        busyIconTimer.start();
                    }
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(true);
                } else if ("done".equals(propertyName)) {
                    busyIconTimer.stop();
                    statusAnimationLabel.setIcon(idleIcon);
                    progressBar.setVisible(false);
                    progressBar.setValue(0);
                } else if ("message".equals(propertyName)) {
                    String text = (String)(evt.getNewValue());
                    statusMessageLabel.setText((text == null) ? "" : text);
                    messageTimer.restart();
                } else if ("progress".equals(propertyName)) {
                    int value = (Integer)(evt.getNewValue());
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(false);
                    progressBar.setValue(value);
                }
            }
        });
    }

    @Action
    public void showAboutBox() {
        if (aboutBox == null) {
            JFrame mainFrame = U2_A4_FastFoodApp.getApplication().getMainFrame();
            aboutBox = new U2_A4_FastFoodAboutBox(mainFrame);
            aboutBox.setLocationRelativeTo(mainFrame);
        }
        U2_A4_FastFoodApp.getApplication().show(aboutBox);
    }

    /** 
     * Created by Sharjil Mohsin
     * on March 11th, 2016
     * to create an order application that lets the employee enter the
     * number of burgers, fries, and soft drinks that a customer orders and
     * to let the employee enter the amount he/she received from the customer
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        titleLabel = new javax.swing.JLabel();
        nameLabel1 = new javax.swing.JLabel();
        nameLabel2 = new javax.swing.JLabel();
        nameLabel3 = new javax.swing.JLabel();
        nameLabel4 = new javax.swing.JLabel();
        numberInput1 = new javax.swing.JTextField();
        numberInput2 = new javax.swing.JTextField();
        numberInput3 = new javax.swing.JTextField();
        numberInput4 = new javax.swing.JTextField();
        enterButton = new javax.swing.JButton();
        divider = new javax.swing.JLabel();
        outputLabel1 = new javax.swing.JLabel();
        outputLabel2 = new javax.swing.JLabel();
        outputLabel3 = new javax.swing.JLabel();
        outputLabel4 = new javax.swing.JLabel();
        menuBar = new javax.swing.JMenuBar();
        javax.swing.JMenu fileMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem exitMenuItem = new javax.swing.JMenuItem();
        javax.swing.JMenu helpMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem aboutMenuItem = new javax.swing.JMenuItem();
        statusPanel = new javax.swing.JPanel();
        javax.swing.JSeparator statusPanelSeparator = new javax.swing.JSeparator();
        statusMessageLabel = new javax.swing.JLabel();
        statusAnimationLabel = new javax.swing.JLabel();
        progressBar = new javax.swing.JProgressBar();

        mainPanel.setName("mainPanel"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(u2_a4_fastfood.U2_A4_FastFoodApp.class).getContext().getResourceMap(U2_A4_FastFoodView.class);
        titleLabel.setFont(resourceMap.getFont("titleLabel.font")); // NOI18N
        titleLabel.setText(resourceMap.getString("titleLabel.text")); // NOI18N
        titleLabel.setName("titleLabel"); // NOI18N

        nameLabel1.setText(resourceMap.getString("nameLabel1.text")); // NOI18N
        nameLabel1.setName("nameLabel1"); // NOI18N

        nameLabel2.setText(resourceMap.getString("nameLabel2.text")); // NOI18N
        nameLabel2.setName("nameLabel2"); // NOI18N

        nameLabel3.setText(resourceMap.getString("nameLabel3.text")); // NOI18N
        nameLabel3.setName("nameLabel3"); // NOI18N

        nameLabel4.setText(resourceMap.getString("nameLabel4.text")); // NOI18N
        nameLabel4.setName("nameLabel4"); // NOI18N

        numberInput1.setText(resourceMap.getString("numberInput1.text")); // NOI18N
        numberInput1.setName("numberInput1"); // NOI18N

        numberInput2.setText(resourceMap.getString("numberInput2.text")); // NOI18N
        numberInput2.setName("numberInput2"); // NOI18N

        numberInput3.setText(resourceMap.getString("numberInput3.text")); // NOI18N
        numberInput3.setName("numberInput3"); // NOI18N

        numberInput4.setText(resourceMap.getString("numberInput4.text")); // NOI18N
        numberInput4.setName("numberInput4"); // NOI18N

        enterButton.setText(resourceMap.getString("enterButton.text")); // NOI18N
        enterButton.setName("enterButton"); // NOI18N
        enterButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enterButtonActionPerformed(evt);
            }
        });

        divider.setText(resourceMap.getString("divider.text")); // NOI18N
        divider.setName("divider"); // NOI18N

        outputLabel1.setText(resourceMap.getString("outputLabel1.text")); // NOI18N
        outputLabel1.setName("outputLabel1"); // NOI18N

        outputLabel2.setText(resourceMap.getString("outputLabel2.text")); // NOI18N
        outputLabel2.setName("outputLabel2"); // NOI18N

        outputLabel3.setText(resourceMap.getString("outputLabel3.text")); // NOI18N
        outputLabel3.setName("outputLabel3"); // NOI18N

        outputLabel4.setText(resourceMap.getString("outputLabel4.text")); // NOI18N
        outputLabel4.setName("outputLabel4"); // NOI18N

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                            .addComponent(titleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 392, Short.MAX_VALUE)
                            .addContainerGap())
                        .addGroup(mainPanelLayout.createSequentialGroup()
                            .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(nameLabel2)
                                .addComponent(nameLabel1)
                                .addComponent(nameLabel3)
                                .addComponent(nameLabel4))
                            .addGap(165, 165, 165)
                            .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(numberInput1)
                                .addComponent(numberInput3)
                                .addComponent(numberInput4, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
                                .addComponent(numberInput2, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addContainerGap(26, Short.MAX_VALUE))
                        .addGroup(mainPanelLayout.createSequentialGroup()
                            .addComponent(divider, javax.swing.GroupLayout.DEFAULT_SIZE, 387, Short.MAX_VALUE)
                            .addGap(15, 15, 15))
                        .addGroup(mainPanelLayout.createSequentialGroup()
                            .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(outputLabel1)
                                .addComponent(outputLabel2)
                                .addComponent(outputLabel3)
                                .addComponent(outputLabel4))
                            .addContainerGap(309, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                        .addComponent(enterButton)
                        .addGap(55, 55, 55))))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(titleLabel)
                .addGap(18, 18, 18)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameLabel1)
                    .addComponent(numberInput1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameLabel2)
                    .addComponent(numberInput2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameLabel3)
                    .addComponent(numberInput3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameLabel4)
                    .addComponent(numberInput4, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(divider)
                .addGap(18, 18, 18)
                .addComponent(outputLabel1)
                .addGap(26, 26, 26)
                .addComponent(outputLabel2)
                .addGap(26, 26, 26)
                .addComponent(outputLabel3)
                .addGap(26, 26, 26)
                .addComponent(outputLabel4)
                .addGap(18, 18, 18)
                .addComponent(enterButton)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        menuBar.setName("menuBar"); // NOI18N

        fileMenu.setText(resourceMap.getString("fileMenu.text")); // NOI18N
        fileMenu.setName("fileMenu"); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(u2_a4_fastfood.U2_A4_FastFoodApp.class).getContext().getActionMap(U2_A4_FastFoodView.class, this);
        exitMenuItem.setAction(actionMap.get("quit")); // NOI18N
        exitMenuItem.setName("exitMenuItem"); // NOI18N
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        helpMenu.setText(resourceMap.getString("helpMenu.text")); // NOI18N
        helpMenu.setName("helpMenu"); // NOI18N

        aboutMenuItem.setAction(actionMap.get("showAboutBox")); // NOI18N
        aboutMenuItem.setName("aboutMenuItem"); // NOI18N
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        statusPanel.setName("statusPanel"); // NOI18N

        statusPanelSeparator.setName("statusPanelSeparator"); // NOI18N

        statusMessageLabel.setName("statusMessageLabel"); // NOI18N

        statusAnimationLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        statusAnimationLabel.setName("statusAnimationLabel"); // NOI18N

        progressBar.setName("progressBar"); // NOI18N

        javax.swing.GroupLayout statusPanelLayout = new javax.swing.GroupLayout(statusPanel);
        statusPanel.setLayout(statusPanelLayout);
        statusPanelLayout.setHorizontalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(statusPanelSeparator, javax.swing.GroupLayout.DEFAULT_SIZE, 412, Short.MAX_VALUE)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(statusMessageLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 242, Short.MAX_VALUE)
                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statusAnimationLabel)
                .addContainerGap())
        );
        statusPanelLayout.setVerticalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addComponent(statusPanelSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(statusMessageLabel)
                    .addComponent(statusAnimationLabel)
                    .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3))
        );

        setComponent(mainPanel);
        setMenuBar(menuBar);
        setStatusBar(statusPanel);
    }// </editor-fold>//GEN-END:initComponents

    private void enterButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enterButtonActionPerformed
        int burgers, fries, softdrinks;
        double tendered, total, tax, finaltotal, change;
        DecimalFormat x = new DecimalFormat ("###.00");

        burgers = Integer.parseInt(numberInput1.getText());
        // The number of burgers the customer ordered.
        fries = Integer.parseInt(numberInput2.getText());
        // The number of fries the customer ordered.
        softdrinks = Integer.parseInt(numberInput3.getText());
        // The number of soft drinks the customer ordered.
        tendered = Double.parseDouble(numberInput4.getText());
        // The amount of money the customer paid.

        total = (2.49 * burgers) + (1.89 * fries) + (0.99 * softdrinks);
        // The total price of the order before tax.
        tax = 0.13 * total;
        // The tax amount (HST) of the order.
        finaltotal = total + tax;
        // The total price of the order including the tax.
        change = tendered - finaltotal;
        // The change the customer receives after payment.

        outputLabel1.setText("Total before taxes: $" + x.format(total));
        outputLabel2.setText("Tax: $" + x.format(tax));
        outputLabel3.setText("Final total: $" + x.format(finaltotal));
        outputLabel4.setText("Change: $" + x.format(change));


    }//GEN-LAST:event_enterButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel divider;
    private javax.swing.JButton enterButton;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JLabel nameLabel1;
    private javax.swing.JLabel nameLabel2;
    private javax.swing.JLabel nameLabel3;
    private javax.swing.JLabel nameLabel4;
    private javax.swing.JTextField numberInput1;
    private javax.swing.JTextField numberInput2;
    private javax.swing.JTextField numberInput3;
    private javax.swing.JTextField numberInput4;
    private javax.swing.JLabel outputLabel1;
    private javax.swing.JLabel outputLabel2;
    private javax.swing.JLabel outputLabel3;
    private javax.swing.JLabel outputLabel4;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JLabel statusAnimationLabel;
    private javax.swing.JLabel statusMessageLabel;
    private javax.swing.JPanel statusPanel;
    private javax.swing.JLabel titleLabel;
    // End of variables declaration//GEN-END:variables

    private final Timer messageTimer;
    private final Timer busyIconTimer;
    private final Icon idleIcon;
    private final Icon[] busyIcons = new Icon[15];
    private int busyIconIndex = 0;

    private JDialog aboutBox;
}
