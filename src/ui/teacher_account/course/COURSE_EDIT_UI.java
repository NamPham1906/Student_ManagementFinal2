package ui.teacher_account.course;

import dao.CourseDAO;
import dao.SchoolSubjectDAO;
import dao.SemesterDAO;
import dao.TeacherDAO;
import pojo.Course;
import ui.support;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Vector;

public class COURSE_EDIT_UI extends JFrame {
    private JPanel COURSE_EDIT_UI;
    private JButton saveButton;
    private JButton cancelButton;
    private JButton returnButton;
    private JLabel courseIDLabel;
    private JLabel semesterLabel;
    private JLabel subjectIDLabel;
    private JLabel teacherIDLabel;
    private JLabel roomLabel;
    private JLabel weekdayLabel;
    private JLabel shiftLabel;
    private JTextField courseIDTextField;
    private JTextField roomTextField;
    private JTextField weekdayTextField;
    private JTextField shiftTextField;
    private JComboBox semesterIDcomboBox;
    private JComboBox subjectIDComboBox;
    private JComboBox teacherIDComboBox;

    public void disposeFrame(){
        this.dispose();
    }

    public COURSE_EDIT_UI(JTable courseTables, String editRow){
        super("EDIT SELECTED COURSE");
        this.setContentPane(COURSE_EDIT_UI);
        this.pack();
        String filePath = new File("").getAbsolutePath();
        ImageIcon img = new ImageIcon(filePath + "\\src\\ui\\pic\\course.png");
        this.setIconImage(img.getImage());
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        saveButton.setIcon(support.resizeImageIcon(filePath + "\\src\\ui\\pic\\save.png",50,50));
        cancelButton.setIcon(support.resizeImageIcon(filePath + "\\src\\ui\\pic\\delete.png",50,50));
        returnButton.setIcon(support.resizeImageIcon(filePath + "\\src\\ui\\pic\\return.png",50,50));
        Course editCourse = CourseDAO.findID(editRow).get(0);


        semesterIDcomboBox.setModel(new DefaultComboBoxModel(SemesterDAO.extractAllSemesterID()));
        subjectIDComboBox.setModel(new DefaultComboBoxModel(SchoolSubjectDAO.extractAllSchoolSubjectID()));
        teacherIDComboBox.setModel(new DefaultComboBoxModel(TeacherDAO.extractAllTeacherID()));

        String oldCourseIDVersion = editCourse.getCourseId();
        courseIDTextField.setText(editCourse.getCourseId());
        semesterIDcomboBox.setSelectedItem(editCourse.getSemester().getSemesterId());
        subjectIDComboBox.setSelectedItem(editCourse.getSchoolSubject().getSubjectId());
        teacherIDComboBox.setSelectedItem(editCourse.getTeacher().getTeacherId());
        roomTextField.setText(editCourse.getRoomnum());
        weekdayTextField.setText(editCourse.getWeekday().toString());
        shiftTextField.setText(editCourse.getShift().toString());


        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Vector<String> input = new Vector<>();
                String newCourseID = courseIDTextField.getText();
                if (newCourseID.equals("")){
                    JOptionPane.showMessageDialog(rootPane, "Course ID must not be null!");
                }
                else {
                    input.add(newCourseID);
                    input.add((String) semesterIDcomboBox.getSelectedItem());
                    input.add((String) subjectIDComboBox.getSelectedItem());
                    input.add((String) teacherIDComboBox.getSelectedItem());
                    input.add(roomTextField.getText());
                    input.add(weekdayTextField.getText());
                    input.add(shiftTextField.getText());
                    if (new CourseDAO().editCourse(input,oldCourseIDVersion)){
                        JOptionPane.showMessageDialog(COURSE_EDIT_UI, "Edit Successfully!");
                    }else{
                        JOptionPane.showMessageDialog(COURSE_EDIT_UI, "Edit failed!");
                    };
                    COURSE_UI.reFreshTable(courseTables);
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
