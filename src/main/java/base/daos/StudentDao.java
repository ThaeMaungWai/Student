package base.daos;

import base.models.Course;
import base.models.Student;
import base.models.User;
import base.service.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Component

public class StudentDao {

 // Student Register or Create
 public int createStudent(Student user){
     int i;
     EntityManager entityManager = null;
     try {
         entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
         entityManager.getTransaction().begin();
         entityManager.persist(user);
         entityManager.getTransaction().commit();
         i = 1 ;
     } finally {
         assert entityManager != null;
         entityManager.close();
     }
     return i ;
 }


 // Get course with Name
    public List<Course> getCoursesByNames(List<String> courseNames) {
        EntityManager entityManager = null;
        try {
            entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
            TypedQuery<Course> query = entityManager.createQuery(
                    "SELECT c FROM Course c WHERE c.name IN :names", Course.class);
            query.setParameter("names", courseNames);
            return query.getResultList();
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }


    //Save Image
    public String saveImage(MultipartFile file, HttpServletRequest request) throws IOException {
        String image = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        String rootDirectory = request.getSession().getServletContext().getRealPath("/");
        String imagesDirectory = rootDirectory + "/WEB-INF/assets/image/"; // Update this with your desired directory

        Path directory = Paths.get(imagesDirectory);
        if (!Files.exists(directory)) {
            Files.createDirectories(directory);
        }

        Path imagePath = Paths.get(imagesDirectory, image);
        Files.write(imagePath, file.getBytes());

        return image; // Return the saved image file name
    }

    // increase id in register page
    public String getLatestStudentId() {
        String latestStudentId = "STU001"; // Initial student ID

        EntityManager entityManager = null;
        try {
            entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
            Query query = entityManager.createQuery("SELECT MAX(CAST(SUBSTRING(s.id, 4) AS UNSIGNED)) FROM Student s");
            BigInteger maxStudentNumber = (BigInteger) query.getSingleResult();

            if (maxStudentNumber != null) {
                int newStudentNumber = maxStudentNumber.intValue() + 1;

                if (maxStudentNumber.intValue() < 10) {
                    latestStudentId = "STU00" + newStudentNumber;
                } else if (maxStudentNumber.intValue() < 100) {
                    latestStudentId = "STU0" + newStudentNumber;
                } else {
                    latestStudentId = "STU" + newStudentNumber;
                }
            }
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
        return latestStudentId;
    }
    public List<Student> getAllStudents(){
        List<Student> students;
        EntityManager entityManager = null ;
        try {
            entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
            entityManager.getTransaction().begin();
            students = entityManager.createQuery("SELECT s FROM Student s" , Student.class).getResultList();
            entityManager.getTransaction().commit();
        }finally {
            assert entityManager != null;
            entityManager.close();
        }
        return students;
    }


    // Update call with Id
    public Student findStudentById(String studentId) {
        EntityManager entityManager = null;
        Student student = null;
        try {
            entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
            student = entityManager.find(Student.class, studentId);
            if (student == null) {
                // Handle the case where the user is not found, e.g., throw an exception
                throw new EntityNotFoundException("Student not found with ID: " + studentId);
            }
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
        return student;
    }
//Update data from id call method



    //Delete student

    public int deleteStudent(String studentId) {
        EntityManager entityManager = null;
        int deleteResult;
        try {
            entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
            entityManager.getTransaction().begin();

            Student existingStudent = entityManager.find(Student.class, studentId);

            if (existingStudent!= null) {
                entityManager.remove(existingStudent);
                entityManager.getTransaction().commit();
                deleteResult = 1;
            } else {
                entityManager.getTransaction().rollback();
                throw new EntityNotFoundException("Student not found with ID: " + studentId);
            }
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
        return deleteResult;
    }
}
