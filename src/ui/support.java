package ui;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.Image;

public class support {
    public static ImageIcon  resizeImageIcon (String filePath, int width, int height){
        ImageIcon addImg = new ImageIcon(filePath);
        Image result= addImg.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        return new ImageIcon(result);
    }
    public static void setColumnCentrer (TableColumn column){
        DefaultTableCellRenderer centerRender = new DefaultTableCellRenderer();
        centerRender.setHorizontalAlignment(JLabel.CENTER);
        column.setCellRenderer(centerRender);
    }
}
