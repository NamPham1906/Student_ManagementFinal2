package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import dao.*;
import pojo.*;
import ui.student_account.STUDENT_MENU_UI;
import ui.teacher_account.TEACHER_MENU_UI;

import java.util.List;

public class LOGIN_UI extends JFrame {

    private  JPanel COURSE_REGISTRATION_SYSTEM;
    private  JLabel crsLabel;
    private  JLabel userLabel;
    private  JLabel passwordLabel;
    private  JTextField userTextField;
    private  JPasswordField passwordTextField;
    private  JButton loginButton;
    private  JLabel loginPicture;
    private JLabel loginInfoLabel;

    public void loginAction (){
        String user = userTextField.getText();
        char[] password = passwordTextField.getPassword();
        boolean studentisvalid = false;
        boolean teacherisvalid = false;

        List<Student> studentresults = StudentDAO.findUser(user);
        for (Student item:studentresults){
            studentisvalid = StudentDAO.checkPassword(password,item);
        }

        List<Teacher> teacherresults = TeacherDAO.findUser(user);
        for (Teacher item:teacherresults){
            teacherisvalid = TeacherDAO.checkPassword(password,item);
        }

        if (teacherisvalid && !studentisvalid){
            loginInfoLabel.setText("Đăng nhập thành công!");
            loginInfoLabel.setForeground(new Color(77,203,162));
            loginInfoLabel.setVisible(true);

            JFrame frame2 = new TEACHER_MENU_UI();
            this.dispose();

        } else if (!teacherisvalid && studentisvalid) {
                loginInfoLabel.setText("Đăng nhập thành công!");
                loginInfoLabel.setForeground(new Color(77, 203, 162));
                loginInfoLabel.setVisible(true);
                JFrame frame3 = new STUDENT_MENU_UI(user);
                this.dispose();

        } else {
            loginInfoLabel.setText("Sai tài khoản hoặc mật khẩu!");
            loginInfoLabel.setForeground(new Color(187,66,63));
            loginInfoLabel.setVisible(true);
        }
    }

    public LOGIN_UI() {
        super("COURSE REGISTRATION SYSTEM");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(COURSE_REGISTRATION_SYSTEM);
        this.pack();
        String filePath = new File("").getAbsolutePath();
        ImageIcon img = new ImageIcon(filePath + "\\src\\ui\\pic\\study.png");
        this.setIconImage(img.getImage());
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        ImageIcon imageIcon = new ImageIcon(img.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
        loginPicture.setIcon(imageIcon);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginAction();
            }
        });
        passwordTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                    loginAction();
                }
            }
        });
    }
}
