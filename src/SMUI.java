import javax.swing.*;
import java.awt.*;
import java.io.File;

public class SMUI extends JFrame {

    private JPanel COURSE_REGISTRATION_SYSTEM;
    private JLabel CRST;
    private JLabel USER;
    private JLabel PASSWORD;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton LOGINB;
    private JLabel PIC;

    public SMUI(String title) {
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(COURSE_REGISTRATION_SYSTEM);
        this.pack();
        String filePath = new File("").getAbsolutePath();
        ImageIcon img = new ImageIcon(filePath + "\\src\\study.png");
        this.setIconImage(img.getImage());
        this.setVisible(true);
        ImageIcon imageIcon = new ImageIcon(img.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
        PIC.setIcon(imageIcon);
    }

    public static void main(String[] args) {
        JFrame frame = new SMUI("COURSE REGISTRATION SYSTEM");


    }

}
