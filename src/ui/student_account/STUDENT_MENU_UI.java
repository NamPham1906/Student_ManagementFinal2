package ui.student_account;

import dao.StudentDAO;
import ui.LOGIN_UI;
import ui.teacher_account.registerperiod.COURSE_REGISTER_UI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

    public class STUDENT_MENU_UI extends JFrame {
        private JPanel MANAGEMENT_MENU;
        private JButton studentAcountButton;
        private JButton courseButton;
        private JButton registerPeriodButton;
        private JButton logOutButton;

        public void disposeFrame(){
            this.dispose();
        }

        public STUDENT_MENU_UI(String user){
            super("COURSE REGISTRATION SYSTEM");
            String student_id = StudentDAO.findUser(user).get(0).getStudentId();
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setContentPane(MANAGEMENT_MENU);
            this.pack();
            String filePath = new File("").getAbsolutePath();
            ImageIcon img = new ImageIcon(filePath + "\\src\\ui\\pic\\study.png");
            this.setIconImage(img.getImage());
            this.setLocationRelativeTo(null);
            this.setVisible(true);

            ImageIcon studentImg = new ImageIcon(filePath + "\\src\\ui\\pic\\student.png");
            studentAcountButton.setIcon(studentImg);


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
                    JFrame frame = new COURSE_REGISTER_UI(user);
                    disposeFrame();
                }
            });
            registerPeriodButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JFrame frame = new COURSE_RESULT_UI(user);
                    disposeFrame();
                }
            });
            studentAcountButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JFrame frame = new USER_INFO_UI(user);
                    disposeFrame();
                }
            });
        }

    }

