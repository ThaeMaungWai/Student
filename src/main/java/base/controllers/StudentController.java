package base.controllers;
import base.daos.CourseDao;
import base.daos.StudentDao;
import base.models.Course;
import base.models.Student;
import base.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
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
                                @RequestParam(value = "courseAttend", required = false) List<String> attendedCourses,
                                Model model, HttpServletRequest request) {
        try {
            // Retrieve courses based on their names from the list of attended courses
            List<Course> courses = studentDao.getCoursesByNames(attendedCourses);

            // Perform logic to save the image file to your project directory
            String imagePath = studentDao.saveImage(image,request); // Replace this with your logic to save the image

            // Set the image file path in the student object
            student.setImageFilePath(imagePath);

            // Associate the courses with the student object
            student.setCourses(new HashSet<>(courses));

            // Perform the student creation in your DAO or service
            int result = studentDao.createStudent(student);

            if (result == 1) {
                model.addAttribute("students", studentDao.getAllStudents());
                model.addAttribute("newStudent", new Student());

                // Successful insertion, redirect to a success page
                return "student/stud_view";
            } else {
                // Handle insertion failure
                // You can redirect to an error page or show an error message
                return "redirect:/students/stud_reg";
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Handle exceptions appropriately
            // Redirect to an error page or show an error message
            return "redirect:/students/error";
        }
    }

    @GetMapping("/studView")
    public String displayStudents(Model model) {
        model.addAttribute("students", studentDao.getAllStudents());
        model.addAttribute("newStudent", new Student());


        System.out.println(studentDao.getAllStudents());
        return "student/stud_view"; // Replace with the actual view name
    }
    //Update Student
    @GetMapping("/studentDetail")
    public String getStudentProfileForm(@RequestParam("id")String id , Model model){
        model.addAttribute("student",studentDao.findStudentById(id));
        return "student/stud_detail";
    }
//Delete Student
    @GetMapping("/studentDelete")
    public String studentDelete(@RequestParam("id")String id ){
        int result =  studentDao.deleteStudent(id);
        return "redirect:/studView";
    }

    // Update Student
    @PostMapping("/studUpdate")
    public String updateStudent(@ModelAttribute("students") Student student,
                                @RequestParam(value = "image", required = false) MultipartFile image,
                                @RequestParam(value = "courseAttend", required = false) List<String> attendedCourses,
                                Model model, HttpServletRequest request) {
        try {
            // Retrieve courses based on their names from the list of attended courses
            List<Course> courses = studentDao.getCoursesByNames(attendedCourses);

            if (image != null && !image.isEmpty()) {
                // Perform logic to save the updated image file to your project directory
                String imagePath = studentDao.saveImage(image, request);

                // Set the updated image file path in the student object
                student.setImageFilePath(imagePath);
            }

            // Associate the updated courses with the student object
            student.setCourses(new HashSet<>(courses));

            // Perform the student update in your DAO or service
            // This may involve merging the updated student object with the existing one
            int updateStudent = studentDao.updateStudent(student);

            if (updateStudent == 1) {
                model.addAttribute("student", studentDao.getAllStudents());
                model.addAttribute("newStudent", new Student());

                // Successful update, redirect to a success page
                return "student/stud_view";
            } else {
                // Handle update failure
                // You can redirect to an error page or show an error message
                return "redirect:/stud_reg";
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Handle exceptions appropriately
            // Redirect to an error page or show an error message
            return "redirect:/students/error";
        }
    }

    //Search Student By Name
    @PostMapping("/studentSearch")
    public String searchStudent(@ModelAttribute("student")Student student,Model model){
        if(!student.getId().isEmpty()){
            List<Student>students=studentDao.searchStudentById(student.getId());
            model.addAttribute("students",students);
            return "student/stud_view";
        }else if(!student.getName().isEmpty()){
            List<Student>studentList=studentDao.searchStudentByName(student.getName());
            model.addAttribute("students",studentList);
            return "student/stud_view";
        }else{
            return "redirect:/studView";
        }
    }



}
