package pojo;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;
import java.util.Objects;
import java.util.Set;
import java.util.HashSet;
import java.util.Vector;

@Entity
public class RegisterPeriod {
    private String registerperiodId;
    private Date startday;
    private Date endday;
    private Set<Course> courses = new HashSet<Course>(0);

    @Id
    @Column(name = "registerperiodID", nullable = false, length = 10)
    public String getRegisterperiodId() {
        return registerperiodId;
    }

    public void setRegisterperiodId(String registerperiodId) {
        this.registerperiodId = registerperiodId;
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

    @Basic
    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegisterPeriod that = (RegisterPeriod) o;
        return Objects.equals(registerperiodId, that.registerperiodId) &&
                Objects.equals(startday, that.startday) &&
                Objects.equals(endday, that.endday) &&
                Objects.equals(courses, that.courses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(registerperiodId, startday, endday,courses);
    }



}
