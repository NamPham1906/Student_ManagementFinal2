package ui;

import javax.swing.*;
import java.io.File;

public class MANAGEMENT_MENU_UI extends JFrame {
    private JPanel MANAGEMENT_MENU;
    private JButton managmentAcountButton;

    public MANAGEMENT_MENU_UI (){
        super("COURSE REGISTRATION SYSTEM");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(MANAGEMENT_MENU);
        this.pack();
        String filePath = new File("").getAbsolutePath();
        ImageIcon img = new ImageIcon(filePath + "\\src\\ui\\study.png");
        this.setIconImage(img.getImage());
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }


    public static void main(String[] args) {
        JFrame frame = new MANAGEMENT_MENU_UI();
    }

}
