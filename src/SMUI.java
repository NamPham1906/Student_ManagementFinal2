import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import dao.*;
import pojo.*;
import java.util.List;

public class SMUI extends JFrame {

    private  JPanel COURSE_REGISTRATION_SYSTEM;
    private  JLabel crsLabel;
    private  JLabel userLabel;
    private  JLabel passwordLabel;
    private  JTextField userTextField;
    private  JPasswordField passwordTextField;
    private  JButton loginButton;
    private  JLabel loginPicture;
    private JLabel loginInfoLabel;

    public SMUI() {
        super("COURSE REGISTRATION SYSTEM");

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(COURSE_REGISTRATION_SYSTEM);
        this.pack();
        String filePath = new File("").getAbsolutePath();
        ImageIcon img = new ImageIcon(filePath + "\\src\\study.png");
        this.setIconImage(img.getImage());
        this.setVisible(true);
        ImageIcon imageIcon = new ImageIcon(img.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
        loginPicture.setIcon(imageIcon);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String user = userTextField.getText();
                char[] password = passwordTextField.getPassword();
                boolean isvalid = false;

                List<Student> results = StudentDAO.findUser(user);
                for (Student item:results){
                    isvalid = StudentDAO.checkPassword(password,item);
                }
                if (isvalid){
                    loginInfoLabel.setText("Đăng nhập thành công!");
                    loginInfoLabel.setForeground(new Color(77,203,162));
                    loginInfoLabel.setVisible(true);
                }else{
                    loginInfoLabel.setText("Sai tài khoản hoặc mật khẩu!");
                    loginInfoLabel.setForeground(new Color(187,66,63));
                    loginInfoLabel.setVisible(true);
                }


            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new SMUI();


    }

}
