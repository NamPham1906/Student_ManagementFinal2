package ui.student_account;

import dao.CourseDAO;
import dao.RegisterPeriodDAO;
import ui.support;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Arrays;
import java.util.Vector;

public class COURSE_RESIGN_UI extends JFrame {
    private JPanel COURSE_UI;
    private JButton addButton;
    private JButton returnButton;
    private JTable courseTable;

    public void disposeFrame(){
        this.dispose();
    }
    public static void  reFreshTable(JTable courseTables){
        String [] columnName = {"Coure ID","School Year","Semester","Subject ID","Subject","Credits","Lecturer", "Room","Weekday","Shift"};
        Vector <String> columnNames = new Vector<String>(Arrays.asList(columnName));
        courseTables.setModel(new DefaultTableModel(CourseDAO.extractData(),columnNames));
        TableColumnModel columns = courseTables.getColumnModel();
        columns.getColumn(4).setMinWidth(200);
        columns.getColumn(6).setMinWidth(200);
        for (int i = 0; i < 10; i++) {
            support.setColumnCentrer(columns.getColumn(i));
        }
    }
    public COURSE_RESIGN_UI(String registerperiodid){
        super("COURSE REGISTER");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(COURSE_UI);
        this.pack();
        String filePath = new File("").getAbsolutePath();
        ImageIcon img = new ImageIcon(filePath + "\\src\\ui\\pic\\course.png");
        this.setIconImage(img.getImage());
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        addButton.setIcon(support.resizeImageIcon(filePath + "\\src\\ui\\pic\\add.png",50,50));
        returnButton.setIcon(support.resizeImageIcon(filePath + "\\src\\ui\\pic\\return.png",50,50));


        reFreshTable(courseTable);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow =  courseTable.getSelectedRow();
                if (selectedRow == -1){
                    JOptionPane.showMessageDialog(COURSE_UI, "No course selected!");
                }
                else {
                   int confirm = support.confirmBox("Add this course?");
                   if (confirm == 0){
                        if (!RegisterPeriodDAO.addCourseToPeriod((String)courseTable.getValueAt(selectedRow,0),registerperiodid)){
                            JOptionPane.showMessageDialog(rootPane, "Add Failed!");
                        }
                       reFreshTable(courseTable);
                   }

                }
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
