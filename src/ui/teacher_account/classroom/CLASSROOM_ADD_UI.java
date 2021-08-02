package ui.teacher_account.classroom;

import dao.ClassroomDAO;
import ui.support;
import ui.teacher_account.semester.SEMESTER_UI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Vector;

public class CLASSROOM_ADD_UI extends JFrame {
    private JPanel CLASSROOM_ADD_UI;
    private JButton saveButton;
    private JButton cancelButton;
    private JButton returnButton;
    private JLabel classIDLabel;
    private JLabel numberOfStudentsLabel;
    private JLabel numberOfMalesLabel;
    private JLabel numberOfFemalesLabel;
    private JTextField classIDTextField;
    private JTextField numberOfStudentTextField;
    private JTextField numberOfMalesTextField;
    private JTextField numberOfFemalesTextField;

    public void disposeFrame(){
        this.dispose();
    }

    public CLASSROOM_ADD_UI(JTable classroomTables){
        super("ADD NEW CLASS");
        this.setContentPane(CLASSROOM_ADD_UI);
        this.pack();
        String filePath = new File("").getAbsolutePath();
        ImageIcon img = new ImageIcon(filePath + "\\src\\ui\\pic\\class.png");
        this.setIconImage(img.getImage());
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        saveButton.setIcon(support.resizeImageIcon(filePath + "\\src\\ui\\pic\\save.png",50,50));
        cancelButton.setIcon(support.resizeImageIcon(filePath + "\\src\\ui\\pic\\delete.png",50,50));
        returnButton.setIcon(support.resizeImageIcon(filePath + "\\src\\ui\\pic\\return.png",50,50));


        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Vector<String> input = new Vector<>();
                String newClassroomID = classIDTextField.getText();
                if (newClassroomID.equals("")){
                    JOptionPane.showMessageDialog(rootPane, "Class ID must not be null!");
                }
                else {
                    input.add(newClassroomID);
                    input.add(numberOfStudentTextField.getText());
                    input.add(numberOfMalesTextField.getText());
                    input.add(numberOfFemalesTextField.getText());
                    if (new ClassroomDAO().addClassroom(input)){
                        JOptionPane.showMessageDialog(CLASSROOM_ADD_UI, "Add Successfully!");
                    }else{
                        JOptionPane.showMessageDialog(CLASSROOM_ADD_UI, "Add failed!");
                    };
                    CLASSROOM_UI.reFreshTable(classroomTables);
                    disposeFrame();
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                disposeFrame();
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
