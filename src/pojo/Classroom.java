package pojo;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "class", schema = "crs", catalog = "")
public class Classroom {
    private String classId;
    private Integer numberofstudents;
    private Integer numberofmales;
    private Integer numberoffemales;

    @Id
    @Column(name = "classID", nullable = false, length = 10)
    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    @Basic
    @Column(name = "numberofstudents", nullable = true)
    public Integer getNumberofstudents() {
        return numberofstudents;
    }

    public void setNumberofstudents(Integer numberofstudents) {
        this.numberofstudents = numberofstudents;
    }

    @Basic
    @Column(name = "numberofmales", nullable = true)
    public Integer getNumberofmales() {
        return numberofmales;
    }

    public void setNumberofmales(Integer numberofmales) {
        this.numberofmales = numberofmales;
    }

    @Basic
    @Column(name = "numberoffemales", nullable = true)
    public Integer getNumberoffemales() {
        return numberoffemales;
    }

    public void setNumberoffemales(Integer numberoffemales) {
        this.numberoffemales = numberoffemales;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Classroom classroom = (Classroom) o;
        return Objects.equals(classId, classroom.classId) &&
                Objects.equals(numberofstudents, classroom.numberofstudents) &&
                Objects.equals(numberofmales, classroom.numberofmales) &&
                Objects.equals(numberoffemales, classroom.numberoffemales);
    }

    @Override
    public int hashCode() {
        return Objects.hash(classId, numberofstudents, numberofmales, numberoffemales);
    }
}
