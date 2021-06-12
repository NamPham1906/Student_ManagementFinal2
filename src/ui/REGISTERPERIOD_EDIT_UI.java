package ui;


import dao.SchoolSubjectDAO;
import pojo.SchoolSubject;
import ui.SUBJECT_UI;
import ui.support;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Vector;

public class REGISTERPERIOD_EDIT_UI extends JFrame {
    private JPanel SUBJECT_EDIT_UI;
    private JButton saveButton;
    private JButton cancelButton;
    private JButton returnButton;
    private JLabel subjectIDLabel;
    private JLabel subjectLabel;
    private JLabel creditsLabel;
    private JTextField subjectIDTextField;
    private JTextField subjectTextField;
    private JTextField creaditsTextField;

    public void disposeFrame(){
        this.dispose();
    }

    public REGISTERPERIOD_EDIT_UI(JTable courseTables, String editRow){
        super("EDIT REGISTER PERIOD");
        this.setContentPane(SUBJECT_EDIT_UI);
        this.pack();
        String filePath = new File("").getAbsolutePath();
        ImageIcon img = new ImageIcon(filePath + "\\src\\ui\\pic\\register.png");
        this.setIconImage(img.getImage());
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        saveButton.setIcon(support.resizeImageIcon(filePath + "\\src\\ui\\pic\\save.png",50,50));
        cancelButton.setIcon(support.resizeImageIcon(filePath + "\\src\\ui\\pic\\delete.png",50,50));
        returnButton.setIcon(support.resizeImageIcon(filePath + "\\src\\ui\\pic\\return.png",50,50));
        SchoolSubject editSubject = SchoolSubjectDAO.findID(editRow).get(0);

        String oldSubjectIDVersion = editSubject.getSubjectId();
        subjectIDTextField.setText(editSubject.getSubjectId());
        subjectTextField.setText(editSubject.getSubjectname());
        creaditsTextField.setText(editSubject.getCredits().toString());

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Vector<String> input = new Vector<>();
                String newSubjectID = subjectIDTextField.getText();
                if (newSubjectID.equals("")){
                    JOptionPane.showMessageDialog(rootPane, "Subject ID must not be null!");
                }
                else {
                    input.add(newSubjectID);
                    input.add(subjectTextField.getText());
                    input.add(creaditsTextField.getText());
                    if (new SchoolSubjectDAO().editSchoolSubject(input,oldSubjectIDVersion)){
                        JOptionPane.showMessageDialog(SUBJECT_EDIT_UI, "Edit Successfully!");
                    }else{
                        JOptionPane.showMessageDialog(SUBJECT_EDIT_UI, "Edit failed!");
                    };
                    SUBJECT_UI.reFreshTable(courseTables);
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
