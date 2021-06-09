package pojo;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;
import java.util.Objects;

@Entity
public class Semester {
    private String semesterId;
    private String semestername;
    private Integer schoolyear;
    private Date startday;
    private Date endday;

    @Id
    @Column(name = "semesterID", nullable = false, length = 10)
    public String getSemesterId() {
        return semesterId;
    }

    public void setSemesterId(String semesterId) {
        this.semesterId = semesterId;
    }

    @Basic
    @Column(name = "semestername", nullable = true, length = 4)
    public String getSemestername() {
        return semestername;
    }

    public void setSemestername(String semestername) {
        this.semestername = semestername;
    }

    @Basic
    @Column(name = "schoolyear", nullable = true)
    public Integer getSchoolyear() {
        return schoolyear;
    }

    public void setSchoolyear(Integer schoolyear) {
        this.schoolyear = schoolyear;
    }

    @Basic
    @Column(name = "startday", nullable = true)
    public Date getStartday() {
        return startday;
    }

    public void setStartday(Date startday) {
        this.startday = startday;
    }

    @Basic
    @Column(name = "endday", nullable = true)
    public Date getEndday() {
        return endday;
    }

    public void setEndday(Date endday) {
        this.endday = endday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Semester semester = (Semester) o;
        return Objects.equals(semesterId, semester.semesterId) &&
                Objects.equals(semestername, semester.semestername) &&
                Objects.equals(schoolyear, semester.schoolyear) &&
                Objects.equals(startday, semester.startday) &&
                Objects.equals(endday, semester.endday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(semesterId, semestername, schoolyear, startday, endday);
    }
}
