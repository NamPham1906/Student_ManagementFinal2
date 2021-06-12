package ui;

import dao.RegisterPeriodDAO;
import dao.SchoolSubjectDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Arrays;
import java.util.Vector;

public class REGISTERPERIOD_UI extends JFrame {
    private JPanel REGISTERPERIOD_UI;
    private JButton addButton;
    private JButton editButton;
    private JButton deleteButton;
    private JButton returnButton;
    private JTable registerPeriodTable;

    public void disposeFrame(){
        this.dispose();
    }
    public static void  reFreshTable(JTable subjectTables){
        String [] columnName = {"Register Period ID","Startday","Endday"};
        Vector <String> columnNames = new Vector<String>(Arrays.asList(columnName));
        subjectTables.setModel(new DefaultTableModel(RegisterPeriodDAO.extractData(),columnNames));
        TableColumnModel columns = subjectTables.getColumnModel();
        columns.getColumn(1).setMinWidth(200);
        for (int i = 0; i < 3; i++) {
            support.setColumnCentrer(columns.getColumn(i));
        }
    }
    public REGISTERPERIOD_UI(){
        super(" REGISTER PERIOD MANAGEMENT");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(REGISTERPERIOD_UI);
        this.pack();
        String filePath = new File("").getAbsolutePath();
        ImageIcon img = new ImageIcon(filePath + "\\src\\ui\\pic\\register.png");
        this.setIconImage(img.getImage());
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        addButton.setIcon(support.resizeImageIcon(filePath + "\\src\\ui\\pic\\add.png",50,50));
        editButton.setIcon(support.resizeImageIcon(filePath + "\\src\\ui\\pic\\find.png",50,50));
        deleteButton.setIcon(support.resizeImageIcon(filePath + "\\src\\ui\\pic\\delete.png",50,50));
        returnButton.setIcon(support.resizeImageIcon(filePath + "\\src\\ui\\pic\\return.png",50,50));


        reFreshTable(registerPeriodTable);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new REGISTERPERIOD_ADD_UI(registerPeriodTable);

            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow =  registerPeriodTable.getSelectedRow();
                if (selectedRow == -1){
                    JOptionPane.showMessageDialog(REGISTERPERIOD_UI, "No register period selected!");
                }
                else {
                   int confirm = support.confirmBox("Delete this register period?");
                   if (confirm == 0){
                        if (!RegisterPeriodDAO.deleteRegisterPeriod((String) registerPeriodTable.getValueAt(selectedRow,0))){
                            JOptionPane.showMessageDialog(rootPane, "Delete Failed!");
                        }
                       reFreshTable(registerPeriodTable);
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
                int selectedRow =  registerPeriodTable.getSelectedRow();
                if (selectedRow == -1){
                    JOptionPane.showMessageDialog(REGISTERPERIOD_UI, "No register period selected!");
                }
                else {
                    JFrame frame = new REGISTERPERIOD_EDIT_UI(registerPeriodTable,(String) registerPeriodTable.getValueAt(selectedRow,0));
                }
            }
        });
    }

}
