package pojo;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Course {
    private String courseId;
    private Semester semester;
    private SchoolSubject schoolSubject;

    private Teacher teacher;
    private String roomnum;
    private Integer weekday;
    private Integer shift;
    private Set<Registerperiod> registerperiods = new HashSet<Registerperiod>(0);
    private Set<Student> students = new HashSet<Student>(0);

    @Id
    @Column(name = "courseID", nullable = false, length = 10)
    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    @Basic
    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    @Basic
    public SchoolSubject getSchoolSubject() {
        return schoolSubject;
    }

    public void setSchoolSubject(SchoolSubject schoolSubject) {
        this.schoolSubject = schoolSubject;
    }

    @Basic
    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) { this.teacher = teacher; }

    @Basic
    @Column(name = "roomnum", nullable = true, length = 10)
    public String getRoomnum() {
        return roomnum;
    }

    public void setRoomnum(String roomnum) {
        this.roomnum = roomnum;
    }

    @Basic
    @Column(name = "weekday", nullable = true)
    public Integer getWeekday() {
        return weekday;
    }

    public void setWeekday(Integer weekday) {
        this.weekday = weekday;
    }

    @Basic
    @Column(name = "shift", nullable = true)
    public Integer getShift() {
        return shift;
    }

    public void setShift(Integer shift) {
        this.shift = shift;
    }


    @Basic
    public Set<Registerperiod> getRegisterperiods() {
        return registerperiods;
    }

    public void setRegisterperiods (Set<Registerperiod> registerperiods) {
        this.registerperiods = registerperiods;
    }


    @Basic
    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents (Set<Student> students) {
        this.students = students;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equals(courseId, course.courseId) &&
                Objects.equals(semester, course.semester) &&
                Objects.equals(schoolSubject, course.schoolSubject) &&
                Objects.equals(teacher, course.teacher) &&
                Objects.equals(roomnum, course.roomnum) &&
                Objects.equals(weekday, course.weekday) &&
                Objects.equals(shift, course.shift) &&
                Objects.equals(registerperiods, course.registerperiods) &&
                Objects.equals(students, course.students);

    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId, semester, schoolSubject, teacher, roomnum, weekday, shift, registerperiods, students);
    }
}
