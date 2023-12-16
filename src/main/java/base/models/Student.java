package base.models;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Student {

    @Id
    @NotEmpty
    private String id;
    @NotBlank
    private String name;

    @NotBlank
    private String dob;
    @NotBlank
    private String gender;
    @NotBlank
    private String phone;
    @NotBlank
    private String education;
    @NotBlank
    @Column(name = "image_file_path")
    private String imageFilePath; // Assuming this holds the path to the image file

    @ManyToMany
    @JoinTable(
            name = "student_courses",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private Set<Course> courses;

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dob=" + dob +
                ", gender='" + gender + '\'' +
                ", phone='" + phone + '\'' +
                ", education='" + education + '\'' +
                ", imageFilePath='" + imageFilePath + '\'' +
                ", courses=" + courses +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getImageFilePath() {
        return imageFilePath;
    }

    public void setImageFilePath(String imageFilePath) {
        this.imageFilePath = imageFilePath;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }
}
