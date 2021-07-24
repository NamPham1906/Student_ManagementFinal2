package ui.teacher_account.student;

import dao.ClassroomDAO;
import dao.StudentDAO;
import pojo.Student;
import ui.support;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Vector;

public class STUDENT_EDIT_UI extends JFrame {
    private JPanel STUDENT_EDIT_UI;
    private JButton saveButton;
    private JButton cancelButton;
    private JButton returnButton;
    private JLabel studentidIDLabel;
    private JLabel classroomLabel;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JLabel genderLabel;
    private JLabel fullnameLabel;
    private JLabel birthdayLabel;
    private JTextField studentidIDTextField;
    private JTextField birthdayTextField;
    private JTextField genderTextField;
    private JComboBox classScroll;
    private JTextField fullnameTextField;
    private JTextField usernameTextField;
    private JTextField passwordTextField;

    public void disposeFrame(){
        this.dispose();
    }

    public STUDENT_EDIT_UI(JTable studentTables, String editRow){
        super("EDIT SELECTED STUDENT");
        this.setContentPane(STUDENT_EDIT_UI);
        this.pack();
        String filePath = new File("").getAbsolutePath();
        ImageIcon img = new ImageIcon(filePath + "\\src\\ui\\pic\\student.png");
        this.setIconImage(img.getImage());
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        saveButton.setIcon(support.resizeImageIcon(filePath + "\\src\\ui\\pic\\save.png",50,50));
        cancelButton.setIcon(support.resizeImageIcon(filePath + "\\src\\ui\\pic\\delete.png",50,50));
        returnButton.setIcon(support.resizeImageIcon(filePath + "\\src\\ui\\pic\\return.png",50,50));
        Student editCourse = StudentDAO.findID(editRow).get(0);


        classScroll.setModel(new DefaultComboBoxModel(ClassroomDAO.extractAllClassID()));

        String oldCourseIDVersion = editCourse.getStudentId();
        usernameTextField.setText(editCourse.getUsername());
        passwordTextField.setText(editCourse.getPasswords());
        studentidIDTextField.setText(editCourse.getStudentId());
        fullnameTextField.setText(editCourse.getFullname());
        classScroll.setSelectedItem(editCourse.getClassroom().getClassId());
        birthdayTextField.setText(editCourse.getBirthday().toString());
        genderTextField.setText(editCourse.getGender());

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Vector<String> input = new Vector<>();
                String newStudentID = studentidIDTextField.getText();
                if (newStudentID.equals("")){
                    JOptionPane.showMessageDialog(rootPane, "Student ID must not be null!");
                }
                else {
                    input.add(usernameTextField.getText());
                    input.add(passwordTextField.getText());
                    input.add(newStudentID);
                    input.add(fullnameTextField.getText());
                    input.add((String) classScroll.getSelectedItem());
                    input.add(birthdayTextField.getText());
                    input.add(genderTextField.getText());
                    if (new StudentDAO().editStudent(input,oldCourseIDVersion)){
                        JOptionPane.showMessageDialog(STUDENT_EDIT_UI, "Edit Successfully!");
                    }else{
                        JOptionPane.showMessageDialog(STUDENT_EDIT_UI, "Edit failed!");
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
