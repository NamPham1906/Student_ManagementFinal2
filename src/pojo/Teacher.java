package pojo;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;
import java.util.Objects;

@Entity
public class Teacher {
    private String username;
    private String passwords;
    private String teacherId;
    private String occupation;
    private String fullname;
    private Date birthday;
    private String gender;

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
    @Column(name = "teacherID", nullable = false, length = 10)
    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    @Basic
    @Column(name = "occupation", nullable = false, length = 50)
    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teacher teacher = (Teacher) o;
        return Objects.equals(username, teacher.username) &&
                Objects.equals(passwords, teacher.passwords) &&
                Objects.equals(teacherId, teacher.teacherId) &&
                Objects.equals(occupation, teacher.occupation) &&
                Objects.equals(fullname, teacher.fullname) &&
                Objects.equals(birthday, teacher.birthday) &&
                Objects.equals(gender, teacher.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, passwords, teacherId, occupation, fullname, birthday, gender);
    }
}
