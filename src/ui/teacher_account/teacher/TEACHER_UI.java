package ui.teacher_account.teacher;

import dao.CourseDAO;
import dao.TeacherDAO;
import ui.support;
import ui.teacher_account.TEACHER_MENU_UI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Arrays;
import java.util.Vector;

public class TEACHER_UI extends JFrame {
    private JPanel TEACHER_UI;
    private JButton addButton;
    private JButton editButton;
    private JButton deleteButton;
    private JButton returnButton;
    private JTable teacherTable;

    public void disposeFrame(){
        this.dispose();
    }
    public static void  reFreshTable(JTable teacherTables){
        String [] columnName = {"User Name","Passwords","Teacher ID","Fullname","Occupation","Birthday","Gender"};
        Vector <String> columnNames = new Vector<String>(Arrays.asList(columnName));
        teacherTables.setModel(new DefaultTableModel(TeacherDAO.extractData(),columnNames));
        TableColumnModel columns = teacherTables.getColumnModel();
        columns.getColumn(4).setMinWidth(200);
        columns.getColumn(3).setMinWidth(200);
        for (int i = 0; i < 7; i++) {
            support.setColumnCentrer(columns.getColumn(i));
        }
    }
    public TEACHER_UI(){
        super("COURSE REGISTRATION SYSTEM | TEACHER ACCOUNTS");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(TEACHER_UI);
        this.pack();
        String filePath = new File("").getAbsolutePath();
        ImageIcon img = new ImageIcon(filePath + "\\src\\ui\\pic\\teacher.png");
        this.setIconImage(img.getImage());
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        addButton.setIcon(support.resizeImageIcon(filePath + "\\src\\ui\\pic\\add.png",50,50));
        editButton.setIcon(support.resizeImageIcon(filePath + "\\src\\ui\\pic\\find.png",50,50));
        deleteButton.setIcon(support.resizeImageIcon(filePath + "\\src\\ui\\pic\\delete.png",50,50));
        returnButton.setIcon(support.resizeImageIcon(filePath + "\\src\\ui\\pic\\return.png",50,50));

        reFreshTable(teacherTable);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new TEACHER_ADD_UI(teacherTable);
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow =  teacherTable.getSelectedRow();
                if (selectedRow == -1){
                    JOptionPane.showMessageDialog(TEACHER_UI, "No teacher selected!");
                }
                else {
                   int confirm = support.confirmBox("Delete this teacher?");
                   if (confirm == 0){
                        if (!TeacherDAO.deleteTeacher((String) teacherTable.getValueAt(selectedRow,2))){
                            JOptionPane.showMessageDialog(rootPane, "Delete Failed!");
                        }
                       reFreshTable(teacherTable);
                   }
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

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow =  teacherTable.getSelectedRow();
                if (selectedRow == -1){
                    JOptionPane.showMessageDialog(TEACHER_UI, "No teacher selected!");
                }
                else {
                    JFrame frame = new TEACHER_EDIT_UI(teacherTable,(String) teacherTable.getValueAt(selectedRow,2));
                }
            }
        });
    }

}
