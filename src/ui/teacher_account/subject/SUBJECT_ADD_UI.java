package ui.teacher_account.subject;

import dao.SchoolSubjectDAO;
import ui.support;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Vector;

public class SUBJECT_ADD_UI extends JFrame {
    private JPanel SUBJECT_ADD_UI;
    private JButton saveButton;
    private JButton cancelButton;
    private JButton returnButton;
    private JLabel schoolSubjectIDLabel;
    private JLabel subjectnameLabel;
    private JLabel creditLabel;
    private JTextField subjectIDTextField;
    private JTextField subjectnameTextField;
    private JTextField creditsTextField;

    public void disposeFrame(){
        this.dispose();
    }

    public SUBJECT_ADD_UI(JTable courseTables){
        super("ADD NEW SUBJECT");
        this.setContentPane(SUBJECT_ADD_UI);
        this.pack();
        String filePath = new File("").getAbsolutePath();
        ImageIcon img = new ImageIcon(filePath + "\\src\\ui\\pic\\subject.png");
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
                String newSubjectID = subjectIDTextField.getText();
                if (newSubjectID.equals("")){
                    JOptionPane.showMessageDialog(rootPane, "Subject ID must not be null!");
                }
                else {
                    input.add(newSubjectID);
                    input.add(subjectnameTextField.getText());
                    input.add(creditsTextField.getText());
                    if (new SchoolSubjectDAO().addSchoolSubject(input)){
                        JOptionPane.showMessageDialog(SUBJECT_ADD_UI, "Add Successfully!");
                    }else{
                        JOptionPane.showMessageDialog(SUBJECT_ADD_UI, "Add failed!");
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
