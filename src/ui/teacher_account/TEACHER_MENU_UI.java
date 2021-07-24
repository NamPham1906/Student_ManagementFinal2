package ui.teacher_account;

import ui.*;
import ui.teacher_account.classroom.CLASSROOM_UI;
import ui.teacher_account.course.COURSE_UI;
import ui.teacher_account.registerperiod.REGISTERPERIOD_UI;
import ui.teacher_account.semester.SEMESTER_UI;
import ui.teacher_account.student.STUDENT_UI;
import ui.teacher_account.subject.SUBJECT_UI;
import ui.teacher_account.teacher.TEACHER_UI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class TEACHER_MENU_UI extends JFrame {
    private JPanel MANAGEMENT_MENU;
    private JButton managmentAcountButton;
    private JButton studentAcountButton;
    private JButton subjectButton;
    private JButton semesterButton;
    private JButton classButton;
    private JButton courseButton;
    private JButton registerPeriodButton;
    private JButton logOutButton;

    public void disposeFrame(){
        this.dispose();
    }

    public TEACHER_MENU_UI(){
        super("COURSE REGISTRATION SYSTEM");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(MANAGEMENT_MENU);
        this.pack();
        String filePath = new File("").getAbsolutePath();
        ImageIcon img = new ImageIcon(filePath + "\\src\\ui\\pic\\study.png");
        this.setIconImage(img.getImage());
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        ImageIcon teacherImg = new ImageIcon(filePath + "\\src\\ui\\pic\\teacher.png");
        managmentAcountButton.setIcon(teacherImg);


        ImageIcon studentImg = new ImageIcon(filePath + "\\src\\ui\\pic\\student.png");
        studentAcountButton.setIcon(studentImg);

        ImageIcon subjectImg = new ImageIcon(filePath + "\\src\\ui\\pic\\subject.png");
        subjectButton.setIcon(subjectImg);

        ImageIcon  semesterImg = new ImageIcon(filePath + "\\src\\ui\\pic\\semester.png");
        semesterButton.setIcon(semesterImg);

        ImageIcon  classImg = new ImageIcon(filePath + "\\src\\ui\\pic\\class.png");
        classButton.setIcon(classImg);

        ImageIcon  courseImg = new ImageIcon(filePath + "\\src\\ui\\pic\\course.png");
        courseButton.setIcon(courseImg);

        ImageIcon  registerPeriodImg = new ImageIcon(filePath + "\\src\\ui\\pic\\register.png");
        registerPeriodButton.setIcon(registerPeriodImg);

        ImageIcon  logOutImg = new ImageIcon(filePath + "\\src\\ui\\pic\\logout.png");
        logOutButton.setIcon(logOutImg);


        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new LOGIN_UI();
                disposeFrame();
            }
        });

        courseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new COURSE_UI();
                disposeFrame();
            }
        });
        subjectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new SUBJECT_UI();
                disposeFrame();
            }
        });
        registerPeriodButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new REGISTERPERIOD_UI();
                disposeFrame();
            }
        });
        studentAcountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new STUDENT_UI();
                disposeFrame();
            }
        });
        managmentAcountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new TEACHER_UI();
                disposeFrame();
            }
        });
        classButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new CLASSROOM_UI();
                disposeFrame();
            }
        });
        semesterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new SEMESTER_UI();
                disposeFrame();
            }
        });
    }

}


