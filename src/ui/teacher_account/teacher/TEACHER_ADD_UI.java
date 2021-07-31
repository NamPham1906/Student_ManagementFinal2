package ui.teacher_account.teacher;

import dao.TeacherDAO;
import ui.support;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Vector;

public class TEACHER_ADD_UI extends JFrame {
    private JPanel TEACHER_ADD_UI;
    private JButton saveButton;
    private JButton cancelButton;
    private JButton returnButton;
    private JLabel usernameLabel;
    private JLabel OccupationLabel;
    private JLabel birthdayLabel;
    private JLabel genderLabel;
    private JLabel passwordsLabel;
    private JLabel teacherIDLabel;
    private JLabel fullnameLabel;
    private JTextField usernameTextField;
    private JTextField birthdayTextField;
    private JTextField genderTextField;
    private JTextField passwordsTextField;
    private JTextField teacherIDTextField;
    private JTextField fullnameTextField;
    private JTextField occupationTextField;


    public void disposeFrame(){
        this.dispose();
    }

    public TEACHER_ADD_UI(JTable teacherTables){
        super("ADD NEW TEACHER");
        this.setContentPane(TEACHER_ADD_UI);
        this.pack();
        String filePath = new File("").getAbsolutePath();
        ImageIcon img = new ImageIcon(filePath + "\\src\\ui\\pic\\teacher.png");
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
                String newTeacherID = teacherIDTextField.getText();
                if (newTeacherID.equals("")){
                    JOptionPane.showMessageDialog(rootPane, "Teacher ID must not be null!");
                }
                else {
                    input.add(usernameTextField.getText());
                    input.add(passwordsTextField.getText());
                    input.add(newTeacherID);
                    input.add(fullnameTextField.getText());
                    input.add(occupationTextField.getText());
                    input.add(birthdayTextField.getText());
                    input.add(genderTextField.getText());

                    if (new TeacherDAO().addTeacher(input)){
                        JOptionPane.showMessageDialog(TEACHER_ADD_UI, "Add Successfully!");
                    }else{
                        JOptionPane.showMessageDialog(TEACHER_ADD_UI, "Add failed!");
                    };
                    TEACHER_UI.reFreshTable(teacherTables);
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
