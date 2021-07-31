package ui.teacher_account.teacher;

import dao.TeacherDAO;
import pojo.Teacher;
import ui.support;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Vector;

public class TEACHER_EDIT_UI extends JFrame {
    private JPanel TEACHER_EDIT_UI;
    private JButton saveButton;
    private JButton cancelButton;
    private JButton returnButton;
    private JLabel teacheridIDLabel;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JLabel genderLabel;
    private JLabel fullnameLabel;
    private JLabel birthdayLabel;
    private JLabel occupationLabel;
    private JTextField teacheridIDTextField;
    private JTextField birthdayTextField;
    private JTextField genderTextField;
    private JTextField fullnameTextField;
    private JTextField usernameTextField;
    private JTextField passwordTextField;
    private JTextField occupationTextField;

    public void disposeFrame(){
        this.dispose();
    }

    public TEACHER_EDIT_UI(JTable teacherTables, String editRow){
        super("EDIT SELECTED TEACHER");
        this.setContentPane(TEACHER_EDIT_UI);
        this.pack();
        String filePath = new File("").getAbsolutePath();
        ImageIcon img = new ImageIcon(filePath + "\\src\\ui\\pic\\teacher.png");
        this.setIconImage(img.getImage());
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        saveButton.setIcon(support.resizeImageIcon(filePath + "\\src\\ui\\pic\\save.png",50,50));
        cancelButton.setIcon(support.resizeImageIcon(filePath + "\\src\\ui\\pic\\delete.png",50,50));
        returnButton.setIcon(support.resizeImageIcon(filePath + "\\src\\ui\\pic\\return.png",50,50));

        Teacher editTeacher = TeacherDAO.findID(editRow).get(0);
        String oldTeacherIDVersion = editTeacher.getTeacherId();
        usernameTextField.setText(editTeacher.getUsername());
        passwordTextField.setText(editTeacher.getPasswords());
        teacheridIDTextField.setText(editTeacher.getTeacherId());
        fullnameTextField.setText(editTeacher.getFullname());
        occupationTextField.setText(editTeacher.getOccupation());
        birthdayTextField.setText(editTeacher.getBirthday().toString());
        genderTextField.setText(editTeacher.getGender());

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Vector<String> input = new Vector<>();
                String newTeacherID = teacheridIDTextField.getText();
                if (newTeacherID.equals("")){
                    JOptionPane.showMessageDialog(rootPane, "Teacher ID must not be null!");
                }
                else {
                    input.add(usernameTextField.getText());
                    input.add(passwordTextField.getText());
                    input.add(newTeacherID);
                    input.add(fullnameTextField.getText());
                    input.add(occupationTextField.getText());
                    input.add(birthdayTextField.getText());
                    input.add(genderTextField.getText());
                    if (new TeacherDAO().editTeacher(input,oldTeacherIDVersion)){
                        JOptionPane.showMessageDialog(TEACHER_EDIT_UI, "Edit Successfully!");
                    }else{
                        JOptionPane.showMessageDialog(TEACHER_EDIT_UI, "Edit failed!");
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
