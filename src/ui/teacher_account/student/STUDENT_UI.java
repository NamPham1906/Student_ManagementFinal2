package ui.teacher_account.student;

import dao.StudentDAO;
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

public class STUDENT_UI extends JFrame {
    private JPanel STUDENT_UI;
    private JButton addButton;
    private JButton editButton;
    private JButton deleteButton;
    private JButton returnButton;
    private JTable studentTable;

    public void disposeFrame(){
        this.dispose();
    }
    public static void  reFreshTable(JTable studentTables){
        String [] columnName = {"User Name","Passwords","Student ID","Fullname","Class","Birthday","Gender"};
        Vector <String> columnNames = new Vector<String>(Arrays.asList(columnName));
        studentTables.setModel(new DefaultTableModel(StudentDAO.extractData(),columnNames));
        TableColumnModel columns = studentTables.getColumnModel();
        columns.getColumn(3).setMinWidth(200);
        columns.getColumn(6).setMinWidth(200);
        for (int i = 0; i < 7; i++) {
            support.setColumnCentrer(columns.getColumn(i));
        }
    }
    public STUDENT_UI(){
        super("COURSE REGISTRATION SYSTEM | STUDENT ACCOUNTS");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(STUDENT_UI);
        this.pack();
        String filePath = new File("").getAbsolutePath();
        ImageIcon img = new ImageIcon(filePath + "\\src\\ui\\pic\\student.png");
        this.setIconImage(img.getImage());
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        addButton.setIcon(support.resizeImageIcon(filePath + "\\src\\ui\\pic\\add.png",50,50));
        editButton.setIcon(support.resizeImageIcon(filePath + "\\src\\ui\\pic\\find.png",50,50));
        deleteButton.setIcon(support.resizeImageIcon(filePath + "\\src\\ui\\pic\\delete.png",50,50));
        returnButton.setIcon(support.resizeImageIcon(filePath + "\\src\\ui\\pic\\return.png",50,50));


        reFreshTable(studentTable);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new STUDENT_ADD_UI(studentTable);

            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow =  studentTable.getSelectedRow();
                if (selectedRow == -1){
                    JOptionPane.showMessageDialog(STUDENT_UI, "No student selected!");
                }
                else {
                   int confirm = support.confirmBox("Delete this student?");
                   if (confirm == 0){
                        if (!StudentDAO.deleteStudent((String) studentTable.getValueAt(selectedRow,0))){
                            JOptionPane.showMessageDialog(rootPane, "Delete Failed!");
                        }
                       reFreshTable(studentTable);
                   }
                }
            }
        });


        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow =  studentTable.getSelectedRow();
                if (selectedRow == -1){
                    JOptionPane.showMessageDialog(STUDENT_UI, "No student selected!");
                }
                else {
                    JFrame frame = new STUDENT_EDIT_UI(studentTable,(String) studentTable.getValueAt(selectedRow,2));
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
