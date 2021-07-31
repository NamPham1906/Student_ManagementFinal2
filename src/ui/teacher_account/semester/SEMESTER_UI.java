package ui.teacher_account.semester;

import dao.SemesterDAO;
import ui.support;
import ui.teacher_account.TEACHER_MENU_UI

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Arrays;
import java.util.Vector;

public class SEMESTER_UI extends JFrame {
    private JPanel SEMESTER_UI;
    private JButton addButton;
    private JButton editButton;
    private JButton deleteButton;
    private JButton returnButton;
    private JTable semesterTable;

    public void disposeFrame(){
        this.dispose();
    }
    public static void  reFreshTable(JTable semesterTables){
        String [] columnName = {"Semester ID","Semester","School year","Start date","End date"};
        Vector <String> columnNames = new Vector<String>(Arrays.asList(columnName));
        semesterTables.setModel(new DefaultTableModel(SemesterDAO.extractData(),columnNames));
        TableColumnModel columns = semesterTables.getColumnModel();
        columns.getColumn(1).setMinWidth(200);
        columns.getColumn(2).setMinWidth(200);
        columns.getColumn(3).setMinWidth(200);
        for (int i = 0; i < 5; i++) {
            support.setColumnCentrer(columns.getColumn(i));
        }
    }
    public SEMESTER_UI(){
        super("COURSE REGISTRATION SYSTEM | SEMESTER MANAGEMENT");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(SEMESTER_UI);
        this.pack();
        String filePath = new File("").getAbsolutePath();
        ImageIcon img = new ImageIcon(filePath + "\\src\\ui\\pic\\semester.png");
        this.setIconImage(img.getImage());
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        addButton.setIcon(support.resizeImageIcon(filePath + "\\src\\ui\\pic\\add.png",50,50));
        editButton.setIcon(support.resizeImageIcon(filePath + "\\src\\ui\\pic\\find.png",50,50));
        deleteButton.setIcon(support.resizeImageIcon(filePath + "\\src\\ui\\pic\\delete.png",50,50));
        returnButton.setIcon(support.resizeImageIcon(filePath + "\\src\\ui\\pic\\return.png",50,50));

        reFreshTable(semesterTable);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new SEMESTER_ADD_UI(semesterTable);
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow =  semesterTable.getSelectedRow();
                if (selectedRow == -1){
                    JOptionPane.showMessageDialog(SEMESTER_UI, "No semester selected!");
                }
                else {
                    JFrame frame = new SEMESTER_EDIT_UI(semesterTable,(String) semesterTable.getValueAt(selectedRow,0));
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow =  semesterTable.getSelectedRow();
                if (selectedRow == -1){
                    JOptionPane.showMessageDialog(SEMESTER_UI, "No semester selected!");
                }
                else {
                    int confirm = support.confirmBox("Delete this semester?");
                    if (confirm == 0){
                        if (!SemesterDAO.deleteSemester((String) semesterTable.getValueAt(selectedRow,0))){
                            JOptionPane.showMessageDialog(rootPane, "Delete Failed!");
                        }
                        reFreshTable(semesterTable);
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
    }

}
