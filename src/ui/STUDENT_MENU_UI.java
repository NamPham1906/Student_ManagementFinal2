package ui;

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

        public STUDENT_MENU_UI(){
            super("COURSE REGISTRATION SYSTEM");
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
        }

    }

