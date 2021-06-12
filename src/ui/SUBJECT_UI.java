package ui;

import dao.CourseDAO;
import dao.SchoolSubjectDAO;
import pojo.SchoolSubject;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Arrays;
import java.util.Vector;

public class SUBJECT_UI extends JFrame {
    private JPanel SUBJECT_UI;
    private JButton addButton;
    private JButton editButton;
    private JButton deleteButton;
    private JButton returnButton;
    private JTable subjectTable;

    public void disposeFrame(){
        this.dispose();
    }
    public static void  reFreshTable(JTable subjectTables){
        String [] columnName = {"Subject ID","Subject","Credits"};
        Vector <String> columnNames = new Vector<String>(Arrays.asList(columnName));
        subjectTables.setModel(new DefaultTableModel(SchoolSubjectDAO.extractData(),columnNames));
        TableColumnModel columns = subjectTables.getColumnModel();
        columns.getColumn(1).setMinWidth(200);
        for (int i = 0; i < 3; i++) {
            support.setColumnCentrer(columns.getColumn(i));
        }
    }
    public SUBJECT_UI(){
        super("SUBJECT BOARD");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(SUBJECT_UI);
        this.pack();
        String filePath = new File("").getAbsolutePath();
        ImageIcon img = new ImageIcon(filePath + "\\src\\ui\\pic\\subject.png");
        this.setIconImage(img.getImage());
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        addButton.setIcon(support.resizeImageIcon(filePath + "\\src\\ui\\pic\\add.png",50,50));
        editButton.setIcon(support.resizeImageIcon(filePath + "\\src\\ui\\pic\\find.png",50,50));
        deleteButton.setIcon(support.resizeImageIcon(filePath + "\\src\\ui\\pic\\delete.png",50,50));
        returnButton.setIcon(support.resizeImageIcon(filePath + "\\src\\ui\\pic\\return.png",50,50));


        reFreshTable(subjectTable);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new SUBJECT_ADD_UI(subjectTable);

            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow =  subjectTable.getSelectedRow();
                if (selectedRow == -1){
                    JOptionPane.showMessageDialog(SUBJECT_UI, "No subject selected!");
                }
                else {
                   int confirm = support.confirmBox("Delete this subject?");
                   if (confirm == 0){
                        if (!SchoolSubjectDAO.deleteSchoolSubject((String) subjectTable.getValueAt(selectedRow,0))){
                            JOptionPane.showMessageDialog(rootPane, "Delete Failed!");
                        }
                       reFreshTable(subjectTable);
                   }

                }
            }
        });

        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new MANAGEMENT_MENU_UI();
                disposeFrame();
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow =  subjectTable.getSelectedRow();
                if (selectedRow == -1){
                    JOptionPane.showMessageDialog(SUBJECT_UI, "No course selected!");
                }
                else {
                    JFrame frame = new SUBJECT_EDIT_UI(subjectTable,(String) subjectTable.getValueAt(selectedRow,0));
                }
            }
        });
    }

}
