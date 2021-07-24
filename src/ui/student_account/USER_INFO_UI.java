package ui.student_account;

import dao.*;
import pojo.Student;
import ui.support;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Vector;

public class USER_INFO_UI extends JFrame {
    private JPanel COURSE_EDIT_UI;
    private JButton saveButton;
    private JButton cancelButton;
    private JButton returnButton;
    private JLabel studentidIDLabel;
    private JLabel classroom;
    private JLabel user;
    private JLabel password;
    private JLabel gender;
    private JLabel name;
    private JLabel birthday;
    private JTextField bd;
    private JTextField gendertype;
    private JComboBox classtext;
    private JTextField fullname;
    private JTextField username;
    private JTextField pw;
    private JLabel idValue;

    public void disposeFrame(){
        this.dispose();
    }

    public USER_INFO_UI(String user){
        super("YOUR ACCOUNT");
        this.setContentPane(COURSE_EDIT_UI);
        this.pack();
        String filePath = new File("").getAbsolutePath();
        ImageIcon img = new ImageIcon(filePath + "\\src\\ui\\pic\\student.png");
        this.setIconImage(img.getImage());
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        saveButton.setIcon(support.resizeImageIcon(filePath + "\\src\\ui\\pic\\save.png",50,50));
        cancelButton.setIcon(support.resizeImageIcon(filePath + "\\src\\ui\\pic\\delete.png",50,50));
        returnButton.setIcon(support.resizeImageIcon(filePath + "\\src\\ui\\pic\\return.png",50,50));
        Student editCourse = StudentDAO.findUser(user).get(0);


        classtext.setModel(new DefaultComboBoxModel(ClassroomDAO.extractAllClassID()));

        String oldCourseIDVersion = editCourse.getStudentId();
        idValue.setText(editCourse.getStudentId());
        username.setText(editCourse.getUsername());
        pw.setText(editCourse.getPasswords());
        fullname.setText(editCourse.getFullname());
        classtext.setSelectedItem(editCourse.getClassroom().getClassId());
        bd.setText(editCourse.getBirthday().toString());
        gendertype.setText(editCourse.getGender());

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Vector<String> input = new Vector<>();
                String newCourseID = idValue.getText();
                if (newCourseID.equals("")){
                    JOptionPane.showMessageDialog(rootPane, "Your ID must not be null!");
                }
                else {
                    input.add(username.getText());
                    input.add(pw.getText());
                    input.add(newCourseID);
                    input.add(fullname.getText());
                    input.add((String) classtext.getSelectedItem());
                    input.add(bd.getText());
                    input.add(gendertype.getText());
                    if (new StudentDAO().editStudent(input,oldCourseIDVersion)){
                        JOptionPane.showMessageDialog(COURSE_EDIT_UI, "Edit Successfully!");
                    }else{
                        JOptionPane.showMessageDialog(COURSE_EDIT_UI, "Edit failed!");
                    };

                    JFrame frame = new STUDENT_MENU_UI(user);
                    disposeFrame();

                }

            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JFrame frame = new STUDENT_MENU_UI(user);
                disposeFrame();
            }
        });

        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new STUDENT_MENU_UI(user);
                disposeFrame();
            }
        });

    }

}
