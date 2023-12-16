package base.controllers;

import base.daos.CourseDao;
import base.models.Course;
import base.models.User;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CourseController {
    @Autowired
    CourseDao courseDao;

    @GetMapping("/courseReg")
    public String getCourseRegForm(Model model) {
        Course course = new Course();
        course.setId(courseDao.getLatestCourseId());        // Set course ID or any other default values if needed
        model.addAttribute("course", course);
        return "course/course_reg"; // Assuming a view for course registration
    }

    @PostMapping("/courseReg")
    public String registerCourse(@ModelAttribute("course") Course course) {
        try {
            courseDao.createCourse(course);
            return "redirect:/courseView";
        }catch(DataIntegrityViolationException e){
            return "course/course_reg";
        }

    }

    @GetMapping("/courseView")
    public String viewAllCourses(Model model) {
        List<Course> courses = courseDao.getAllCourses();
        model.addAttribute("courses", courses);
        return "course/course_view"; // Assuming a view for displaying all courses
    }
    @GetMapping("/courseDelete")
    public String courseDelete(@RequestParam("id")String id ){
        int result =  courseDao.deleteCourse(id);
        return "redirect:/courseView";
    }

    //Update course Name

    @GetMapping("/courseDetail")
    public String getCourseNameForm(@RequestParam("id")String id , Model model){
        model.addAttribute("course",courseDao.findCourseById(id));
        return "course/course_detail";
    }
    @PostMapping("/courseDetail")
    public String CourseNameUpdate(@ModelAttribute("course") Course course, Model model){
        courseDao.updateCourse(course);
        model.addAttribute("courses",course);
        return "redirect:/courseView";
    }


    @PostMapping("/courseSearch")
    public String searchCourse(@ModelAttribute("course")Course course,Model model){
        if(!course.getId().isEmpty()){
            List<Course>courses=CourseDao.searchCourseById(course.getId());
            model.addAttribute("courses",courses);
            return "course/course_view";
        }else if(!course.getName().isEmpty()){
            List<Course>courseList=courseDao.searchCourseByName(course.getName());
            model.addAttribute("courses",courseList);
            return "course/course_view";
        }else{
            return "redirect:/courseView";
        }
    }

}
