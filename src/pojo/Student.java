package pojo;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Student {
    private String username;
    private String passwords;
    private String studentId;
    private Classroom classroom;
    private String fullname;
    private Date birthday;
    private String gender;
    private Set<Course> registeredCourses = new HashSet<Course>(0);

    @Basic
    @Column(name = "username", nullable = true, length = 50)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    @Basic
    @Column(name = "passwords", nullable = false, length = 50)
    public String getPasswords() {
        return passwords;
    }

    public void setPasswords(String passwords) {
        this.passwords = passwords;
    }


    @Id
    @Column(name = "studentID", nullable = false, length = 10)
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }


    @Basic
    public Classroom getClassroom() { return classroom; }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }


    @Basic
    @Column(name = "fullname", nullable = true, length = 50)
    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }


    @Basic
    @Column(name = "birthday", nullable = true)
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }


    @Basic
    @Column(name = "gender", nullable = true, length = 3)
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Basic
    public Set<Course> getRegisteredCourses() {
        return registeredCourses;
    }

    public void setRegisteredCourses (Set<Course> registeredCourses) {
        this.registeredCourses = registeredCourses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(username, student.username) &&
                Objects.equals(passwords, student.passwords) &&
                Objects.equals(studentId, student.studentId) &&
                Objects.equals(classroom, student.classroom) &&
                Objects.equals(fullname, student.fullname) &&
                Objects.equals(birthday, student.birthday) &&
                Objects.equals(gender, student.gender) &&
                Objects.equals(registeredCourses, student.registeredCourses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, passwords, studentId,classroom,fullname, birthday, gender, registeredCourses);
    }
}
