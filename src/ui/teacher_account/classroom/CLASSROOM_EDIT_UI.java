package ui.teacher_account.classroom;

import dao.ClassroomDAO;
import pojo.Classroom;
import ui.support;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Vector;

public class CLASSROOM_EDIT_UI extends JFrame {
    private JPanel CLASSROOM_EDIT_UI;
    private JButton saveButton;
    private JButton cancelButton;
    private JButton returnButton;
    private JLabel classIDLabel;
    private JLabel numberOfStudentsLabel;
    private JLabel numberOfMalesLabel;
    private JLabel numberOfFemalesLabel;
    private JTextField classIDTextField;
    private JTextField numberOfStudentsTextField;
    private JTextField numberOfMalesTextField;
    private JTextField numberOfFemalesTextField;

    public void disposeFrame(){
        this.dispose();
    }

    public CLASSROOM_EDIT_UI(JTable classroomTables, String editRow){
        super("EDIT SELECTED CLASS");
        this.setContentPane(CLASSROOM_EDIT_UI);
        this.pack();
        String filePath = new File("").getAbsolutePath();
        ImageIcon img = new ImageIcon(filePath + "\\src\\ui\\pic\\class.png");
        this.setIconImage(img.getImage());
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        saveButton.setIcon(support.resizeImageIcon(filePath + "\\src\\ui\\pic\\save.png",50,50));
        cancelButton.setIcon(support.resizeImageIcon(filePath + "\\src\\ui\\pic\\delete.png",50,50));
        returnButton.setIcon(support.resizeImageIcon(filePath + "\\src\\ui\\pic\\return.png",50,50));
        Classroom editClassroom = ClassroomDAO.findID(editRow).get(0);

        String oldClassroomIDVersion = editClassroom.getClassId();
        classIDTextField.setText(editClassroom.getClassId());
        numberOfStudentsTextField.setText(editClassroom.getNumberofstudents().toString());
        numberOfMalesTextField.setText(editClassroom.getNumberofmales().toString());
        numberOfFemalesTextField.setText(editClassroom.getNumberoffemales().toString());

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
                    input.add(numberOfStudentsTextField.getText());
                    input.add(numberOfMalesTextField.getText());
                    input.add(numberOfFemalesTextField.getText());
                    if (new ClassroomDAO().editClassroom(input,oldClassroomIDVersion)){
                        JOptionPane.showMessageDialog(CLASSROOM_EDIT_UI, "Edit Successfully!");
                    }else{
                        JOptionPane.showMessageDialog(CLASSROOM_EDIT_UI, "Edit failed!");
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
