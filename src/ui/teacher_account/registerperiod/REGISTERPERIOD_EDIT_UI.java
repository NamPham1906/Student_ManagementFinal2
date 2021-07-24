package ui.teacher_account.registerperiod;


import dao.RegisterPeriodDAO;
import pojo.RegisterPeriod;
import ui.support;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Vector;

public class REGISTERPERIOD_EDIT_UI extends JFrame {
    private JPanel REGISTERPERIOD_EDIT_UI;
    private JButton saveButton;
    private JButton cancelButton;
    private JButton returnButton;
    private JLabel registerPeriodIDLabel;
    private JLabel startdayLabel;
    private JLabel endDayLabel;
    private JTextField registerPeriodIDTextField;
    private JTextField startdayTextField;
    private JTextField endDayTextField;

    public void disposeFrame(){
        this.dispose();
    }

    public REGISTERPERIOD_EDIT_UI(JTable courseTables, String editRow){
        super("EDIT SELECTED REGISTER PERIOD");
        this.setContentPane(REGISTERPERIOD_EDIT_UI);
        this.pack();
        String filePath = new File("").getAbsolutePath();
        ImageIcon img = new ImageIcon(filePath + "\\src\\ui\\pic\\register.png");
        this.setIconImage(img.getImage());
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        saveButton.setIcon(support.resizeImageIcon(filePath + "\\src\\ui\\pic\\save.png",50,50));
        cancelButton.setIcon(support.resizeImageIcon(filePath + "\\src\\ui\\pic\\delete.png",50,50));
        returnButton.setIcon(support.resizeImageIcon(filePath + "\\src\\ui\\pic\\return.png",50,50));
        RegisterPeriod editRegisterPeriod =  RegisterPeriodDAO.findID(editRow).get(0);

        String oldSubjectIDVersion = editRegisterPeriod.getRegisterperiodId();
        registerPeriodIDTextField.setText(editRegisterPeriod.getRegisterperiodId());
        startdayTextField.setText(editRegisterPeriod.getStartday().toString());
        endDayTextField.setText(editRegisterPeriod.getEndday().toString());

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
                    input.add(startdayTextField.getText());
                    input.add(endDayTextField.getText());
                    if (new RegisterPeriodDAO().editRegisterPeriod(input,oldSubjectIDVersion)){
                        JOptionPane.showMessageDialog(REGISTERPERIOD_EDIT_UI, "Edit Successfully!");
                    }else{
                        JOptionPane.showMessageDialog(REGISTERPERIOD_EDIT_UI, "Edit failed!");
                    };
                    REGISTERPERIOD_UI.reFreshTable(courseTables);
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
