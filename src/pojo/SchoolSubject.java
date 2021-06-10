package pojo;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class SchoolSubject {
    private String subjectId;
    private String subjectname;
    private Integer credits;


    @Id
    @Column(name = "subjectID", nullable = false, length = 10)
    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }


    @Basic
    @Column(name = "subjectname", nullable = true, length = 200)
    public String getSubjectname() {
        return subjectname;
    }

    public void setSubjectname(String subjectname) {
        this.subjectname = subjectname;
    }

    @Basic
    @Column(name = "credits", nullable = true)
    public Integer getCredits() {
        return credits;
    }

    public void setCredits(Integer credits) {
        this.credits = credits;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SchoolSubject course = (SchoolSubject) o;
        return Objects.equals(subjectId, course.subjectId) &&
                Objects.equals(subjectname, course.subjectname) &&
                Objects.equals(credits, course.credits);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subjectId, subjectname, credits);
    }
}
