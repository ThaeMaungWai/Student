package base.controllers;
import base.daos.CourseDao;
import base.daos.StudentDao;
import base.models.Course;
import base.models.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashSet;
import java.util.List;


@Controller
public class StudentController {
    @Autowired
    StudentDao studentDao;

    @Autowired
    CourseDao courseDao;
    @GetMapping("/studReg")
    public String registerStudent(Model model){
        Student student = new Student();
        student.setId(studentDao.getLatestStudentId());
        model.addAttribute("students",student);
        return "student/stud_reg";
    }


    @PostMapping("/stud_reg")
    public String createStudent(@ModelAttribute("student") Student student,
                                @RequestParam("image") MultipartFile image,
                                @RequestParam(value = "courseAttend", required = false) List<String> attendedCourses) {
        try {
            // Retrieve courses based on their names from the list of attended courses
            List<Course> courses = studentDao.getCoursesByNames(attendedCourses);

            // Perform logic to save the image file to your project directory
            String imagePath = studentDao.saveImage(image); // Replace this with your logic to save the image

            // Set the image file path in the student object
            student.setImageFilePath(imagePath);

            // Associate the courses with the student object
            student.setCourses(new HashSet<>(courses));

            // Perform the student creation in your DAO or service
            int result = studentDao.createStudent(student);

            if (result == 1) {
                // Successful insertion, redirect to a success page
                return "student/stud_view";
            } else {
                // Handle insertion failure
                // You can redirect to an error page or show an error message
                return "redirect:/students/error";
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Handle exceptions appropriately
            // Redirect to an error page or show an error message
            return "redirect:/students/error";
        }
    }



}
