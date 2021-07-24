package ui.teacher_account.student;

import dao.*;
import ui.support;
import ui.teacher_account.course.COURSE_UI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Vector;

public class STUDENT_ADD_UI extends JFrame {
    private JPanel STUDENT_ADD_UI;
    private JButton saveButton;
    private JButton cancelButton;
    private JButton returnButton;
    private JLabel usernameLabel;
    private JLabel classLabel;
    private JLabel birthdayLabel;
    private JLabel genderLabel;
    private JLabel passwordsLabel;
    private JLabel studentIDLabel;
    private JLabel fullnameLabel;
    private JTextField usernameTextField;
    private JTextField birthdayTextField;
    private JTextField genderTextField;
    private JTextField passwordsTextField;
    private JComboBox classScroll;
    private JTextField studentIDTextField;
    private JTextField fullnameTextField;


    public void disposeFrame(){
        this.dispose();
    }

    public STUDENT_ADD_UI(JTable studentTables){
        super("ADD NEW STUDENT");
        this.setContentPane(STUDENT_ADD_UI);
        this.pack();
        String filePath = new File("").getAbsolutePath();
        ImageIcon img = new ImageIcon(filePath + "\\src\\ui\\pic\\student.png");
        this.setIconImage(img.getImage());
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        saveButton.setIcon(support.resizeImageIcon(filePath + "\\src\\ui\\pic\\save.png",50,50));
        cancelButton.setIcon(support.resizeImageIcon(filePath + "\\src\\ui\\pic\\delete.png",50,50));
        returnButton.setIcon(support.resizeImageIcon(filePath + "\\src\\ui\\pic\\return.png",50,50));

        classScroll.setModel(new DefaultComboBoxModel(ClassroomDAO.extractAllClassID()));

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Vector<String> input = new Vector<>();
                String newStudentID = studentIDTextField.getText();
                if (newStudentID.equals("")){
                    JOptionPane.showMessageDialog(rootPane, "Student ID must not be null!");
                }
                else {
                    input.add(usernameTextField.getText());
                    input.add(passwordsTextField.getText());
                    input.add(newStudentID);
                    input.add(fullnameTextField.getText());
                    input.add((String) classScroll.getSelectedItem());
                    input.add(birthdayTextField.getText());
                    input.add(genderTextField.getText());

                    if (new StudentDAO().addStudent(input)){
                        JOptionPane.showMessageDialog(STUDENT_ADD_UI, "Add Successfully!");
                    }else{
                        JOptionPane.showMessageDialog(STUDENT_ADD_UI, "Add failed!");
                    };
                    STUDENT_UI.reFreshTable(studentTables);
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
