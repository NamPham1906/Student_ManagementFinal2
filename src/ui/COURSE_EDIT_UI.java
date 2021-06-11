package ui;

import dao.CourseDAO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Vector;

public class COURSE_EDIT_UI extends JFrame {
    private JPanel COURSE_UI;
    private JButton saveButton;
    private JButton cancelButton;
    private JButton returnButton;
    private JLabel courseIDLabel;
    private JLabel semesterLabel;
    private JLabel subjectIDLabel;
    private JLabel teacherIDLabel;
    private JLabel roomLabel;
    private JLabel weekdayLabel;
    private JLabel shiftLabel;
    private JTextField courseIDTextField;
    private JTextField semesterIDTextField;
    private JTextField SubjectIDTextField;
    private JTextField TeacherIDTextField;
    private JTextField roomTextField;
    private JTextField weekdayTextField;
    private JTextField shiftTextField;


    public void disposeFrame(){
        this.dispose();
    }

    public COURSE_EDIT_UI(){
        super("ADD NEW COURSE");
        this.setContentPane(COURSE_UI);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        String filePath = new File("").getAbsolutePath();
        ImageIcon img = new ImageIcon(filePath + "\\src\\ui\\pic\\course.png");
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
                input.add(courseIDTextField.getText());
                input.add(semesterIDTextField.getText());
                input.add(SubjectIDTextField.getText());
                input.add(TeacherIDTextField.getText());
                input.add(roomTextField.getText());
                input.add(weekdayTextField.getText());
                input.add(roomTextField.getText());
                input.add(shiftTextField.getText());
                new CourseDAO().addCourse(input);
                disposeFrame();
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
