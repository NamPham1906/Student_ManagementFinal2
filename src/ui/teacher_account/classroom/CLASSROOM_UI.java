package ui.teacher_account.classroom;

import dao.ClassroomDAO;
import dao.CourseDAO;
import ui.support;
import ui.teacher_account.TEACHER_MENU_UI;
import ui.teacher_account.course.COURSE_ADD_UI;
import ui.teacher_account.course.COURSE_EDIT_UI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Arrays;
import java.util.Vector;

public class CLASSROOM_UI extends JFrame {
    private JPanel SEMESTER_UI;
    private JButton addButton;
    private JButton editButton;
    private JButton deleteButton;
    private JButton returnButton;
    private JTable courseTable;

    public void disposeFrame(){
        this.dispose();
    }
    public static void  reFreshTable(JTable courseTables){
        String [] columnName = {"Class ID","Number of students","Number of males","Number of females"};
        Vector <String> columnNames = new Vector<String>(Arrays.asList(columnName));
        courseTables.setModel(new DefaultTableModel(ClassroomDAO.extractData(),columnNames));
        TableColumnModel columns = courseTables.getColumnModel();
        columns.getColumn(1).setMinWidth(200);
        columns.getColumn(2).setMinWidth(200);
        columns.getColumn(3).setMinWidth(200);
        for (int i = 0; i < 4; i++) {
            support.setColumnCentrer(columns.getColumn(i));
        }
    }
    public CLASSROOM_UI(){
        super("COURSE REGISTRATION SYSTEM | CLASS MANAGEMENT");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(SEMESTER_UI);
        this.pack();
        String filePath = new File("").getAbsolutePath();
        ImageIcon img = new ImageIcon(filePath + "\\src\\ui\\pic\\class.png");
        this.setIconImage(img.getImage());
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        addButton.setIcon(support.resizeImageIcon(filePath + "\\src\\ui\\pic\\add.png",50,50));
        editButton.setIcon(support.resizeImageIcon(filePath + "\\src\\ui\\pic\\find.png",50,50));
        deleteButton.setIcon(support.resizeImageIcon(filePath + "\\src\\ui\\pic\\delete.png",50,50));
        returnButton.setIcon(support.resizeImageIcon(filePath + "\\src\\ui\\pic\\return.png",50,50));


        reFreshTable(courseTable);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new COURSE_ADD_UI(courseTable);

            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow =  courseTable.getSelectedRow();
                if (selectedRow == -1){
                    JOptionPane.showMessageDialog(SEMESTER_UI, "No course selected!");
                }
                else {
                   int confirm = support.confirmBox("Delete this course?");
                   if (confirm == 0){
                        if (!CourseDAO.deleteCourse((String)courseTable.getValueAt(selectedRow,0))){
                            JOptionPane.showMessageDialog(rootPane, "Delete Failed!");
                        }
                       reFreshTable(courseTable);
                   }

                }
            }
        });


        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow =  courseTable.getSelectedRow();
                if (selectedRow == -1){
                    JOptionPane.showMessageDialog(SEMESTER_UI, "No course selected!");
                }
                else {
                    JFrame frame = new COURSE_EDIT_UI(courseTable,(String)courseTable.getValueAt(selectedRow,0));
                }
            }
        });
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new TEACHER_MENU_UI();
                disposeFrame();
            }
        });
    }

}
