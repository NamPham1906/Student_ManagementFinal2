package ui.teacher_account.registerperiod;

import dao.RegisterPeriodDAO;
import ui.support;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Vector;

public class REGISTERPERIOD_ADD_UI extends JFrame {
    private JPanel REGISTERPERIOD_ADD_UI;
    private JButton saveButton;
    private JButton cancelButton;
    private JButton returnButton;
    private JLabel RegisterPeriodIDLabel;
    private JLabel startDayLabel;
    private JLabel endDayLabel;
    private JTextField registerPeriodIDTextField;
    private JTextField startDayTextField;
    private JTextField endDayTextField;

    public void disposeFrame(){
        this.dispose();
    }

    public REGISTERPERIOD_ADD_UI(JTable registerPeriodTables){
        super("ADD NEW REGISTER PERIOD");
        this.setContentPane(REGISTERPERIOD_ADD_UI);
        this.pack();
        String filePath = new File("").getAbsolutePath();
        ImageIcon img = new ImageIcon(filePath + "\\src\\ui\\pic\\register.png");
        this.setIconImage(img.getImage());
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        saveButton.setIcon(support.resizeImageIcon(filePath + "\\src\\ui\\pic\\save.png",50,50));
        cancelButton.setIcon(support.resizeImageIcon(filePath + "\\src\\ui\\pic\\delete.png",50,50));
        returnButton.setIcon(support.resizeImageIcon(filePath + "\\src\\ui\\pic\\return.png",50,50));

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Vector<String> input = new Vector<>();
                String newRegisterPeriodID = registerPeriodIDTextField.getText();
                if (newRegisterPeriodID.equals("")){
                    JOptionPane.showMessageDialog(rootPane, "Register Period ID must not be null!");
                }
                else {
                    input.add(newRegisterPeriodID);
                    input.add(startDayTextField.getText());
                    input.add(endDayTextField.getText());

                    if (new RegisterPeriodDAO().addRegisterPeriod(input)){
                        JOptionPane.showMessageDialog(REGISTERPERIOD_ADD_UI, "Add Successfully!");
                    }else{
                        JOptionPane.showMessageDialog(REGISTERPERIOD_ADD_UI, "Add failed!");
                    };
                    REGISTERPERIOD_UI.reFreshTable(registerPeriodTables);
                    disposeFrame();
                }

            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                disposeFrame();
            }
        });

        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                disposeFrame();
            }
        });

    }


}
