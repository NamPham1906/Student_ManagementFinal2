package ui.teacher_account.semester;

import dao.SemesterDAO;
import pojo.Semester;
import ui.support;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Vector;

public class SEMESTER_EDIT_UI extends JFrame {
    private JPanel SEMESTER_EDIT_UI;
    private JButton saveButton;
    private JButton cancelButton;
    private JButton returnButton;
    private JLabel semesterIDLabel;
    private JLabel semesterLabel;
    private JLabel schoolYearLabel;
    private JLabel startDateLabel;
    private JLabel endDateLabel;
    private JTextField semesterIDTextField;
    private JTextField endDateTextField;
    private JTextField semesterTextField;
    private JTextField schoolYearTextField;
    private JTextField startDateTextField;

    public void disposeFrame(){
        this.dispose();
    }

    public SEMESTER_EDIT_UI(JTable semesterTables, String editRow){
        super("EDIT SELECTED SEMESTER");
        this.setContentPane(SEMESTER_EDIT_UI);
        this.pack();
        String filePath = new File("").getAbsolutePath();
        ImageIcon img = new ImageIcon(filePath + "\\src\\ui\\pic\\semester.png");
        this.setIconImage(img.getImage());
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        saveButton.setIcon(support.resizeImageIcon(filePath + "\\src\\ui\\pic\\save.png",50,50));
        cancelButton.setIcon(support.resizeImageIcon(filePath + "\\src\\ui\\pic\\delete.png",50,50));
        returnButton.setIcon(support.resizeImageIcon(filePath + "\\src\\ui\\pic\\return.png",50,50));
        Semester editSemester = SemesterDAO.findID(editRow).get(0);

        String oldSemesterIDVersion = editSemester.getSemesterId();
        semesterIDTextField.setText(editSemester.getSemesterId());
        semesterTextField.setText(editSemester.getSemestername());
        schoolYearTextField.setText(editSemester.getSchoolyear().toString());
        startDateTextField.setText(editSemester.getStartday().toString());
        endDateTextField.setText(editSemester.getEndday().toString());

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Vector<String> input = new Vector<>();
                String newSemesterID = semesterIDTextField.getText();
                if (newSemesterID.equals("")){
                    JOptionPane.showMessageDialog(rootPane, "Semester ID must not be null!");
                }
                else {
                    input.add(newSemesterID);
                    input.add(semesterTextField.getText());
                    input.add(schoolYearTextField.getText());
                    input.add(startDateTextField.getText());
                    input.add(endDateTextField.getText());
                    if (new SemesterDAO().editSemester(input,oldSemesterIDVersion)){
                        JOptionPane.showMessageDialog(SEMESTER_EDIT_UI, "Edit Successfully!");
                    }else{
                        JOptionPane.showMessageDialog(SEMESTER_EDIT_UI, "Edit failed!");
                    };
                    SEMESTER_UI.reFreshTable(semesterTables);
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
