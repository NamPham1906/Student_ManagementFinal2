package gui;
import javax.swing.*;
import java.io.File;
public class Frame {
    public void MainFrame(){
        JFrame frame = new JFrame("COURSE REGISTRATION SYSTEM");
        frame.setSize(1000,500);
        frame.setVisible(true);
        String filePath = new File("").getAbsolutePath();
        ImageIcon img = new ImageIcon(filePath + "\\src\\gui\\study.png");
        frame.setIconImage(img.getImage());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
