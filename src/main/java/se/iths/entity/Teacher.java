package se.iths.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Set;

@Entity
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    @NotEmpty
    private String email;

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.PERSIST)
    private List<Subject> subjects;

    public void addSubject(Subject subject) {
        subjects.add(subject);
        subject.setTeacher(this);
    }

    public Teacher() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Subject> getSubjects() {
        return (List<Subject>) subjects;
    }

    public void setSubjects(Set<Subject> subjects) {
        this.subjects = (List<Subject>) subjects;
    }
}
