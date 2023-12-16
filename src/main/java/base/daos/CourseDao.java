package base.daos;

import base.models.Course;
import base.models.User;
import base.service.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Component
public class CourseDao {
    public int createCourse(Course course) {
        int i;
        EntityManager entityManager = null;
        try {
            entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(course);
            entityManager.getTransaction().commit();
            i = 1;
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
        return i;
    }

    public List<Course> getAllCourses() {
        List<Course> courses;
        EntityManager entityManager = null;
        try {
            entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
            entityManager.getTransaction().begin();
            courses = entityManager.createQuery("SELECT c FROM Course c", Course.class).getResultList();
            entityManager.getTransaction().commit();
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
        return courses;
    }

    public Course findCourseById(String courseId) {
        EntityManager entityManager = null;
        Course course = null;
        try {
            entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
            course = entityManager.find(Course.class, courseId);
            if (course == null) {
                throw new EntityNotFoundException("Course not found with ID: " + courseId);
            }
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
        return course;
    }

    public int updateCourse(Course updatedCourse) {
        EntityManager entityManager = null;
        int updateResult;
        try {
            entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
            entityManager.getTransaction().begin();

            Course existingCourse = entityManager.find(Course.class, updatedCourse.getId());

            if (existingCourse != null) {
                existingCourse.setName(updatedCourse.getName());
                // Update other attributes

                entityManager.getTransaction().commit();
                updateResult = 1;
            } else {
                entityManager.getTransaction().rollback();
                throw new EntityNotFoundException("Course not found with ID: " + updatedCourse.getId());
            }
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
        return updateResult;
    }

    public int deleteCourse(String courseId) {
        EntityManager entityManager = null;
        int deleteResult;
        try {
            entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
            entityManager.getTransaction().begin();

            Course existingCourse = entityManager.find(Course.class, courseId);

            if (existingCourse != null) {
                entityManager.remove(existingCourse);
                entityManager.getTransaction().commit();
                deleteResult = 1;
            } else {
                entityManager.getTransaction().rollback();
                throw new EntityNotFoundException("Course not found with ID: " + courseId);
            }
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
        return deleteResult;
    }
    public String getLatestCourseId() {
        String latestCourseId = "CUR001"; // Initial course ID

        EntityManager entityManager = null;
        try {
            entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
            Query query = entityManager.createQuery("SELECT MAX(CAST(SUBSTRING(c.id, 4) AS UNSIGNED)) FROM Course c");
            BigInteger maxCourseNumber = (BigInteger) query.getSingleResult();

            if (maxCourseNumber != null) {
                int newCourseNumber = maxCourseNumber.intValue() + 1;

                if (maxCourseNumber.intValue() < 10) {
                    latestCourseId = "CUR00" + newCourseNumber;
                } else if (maxCourseNumber.intValue() < 100) {
                    latestCourseId = "CUR0" + newCourseNumber;
                } else {
                    latestCourseId = "CUR" + newCourseNumber;
                }
            }
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
        return latestCourseId;
    }

    //Search Course
    public static List<Course> searchCourseById(String courseId) {
        List<Course> courses = new ArrayList<>();

        try (EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager()) {
            // Using JPA query to select a user by ID
            Query query = em.createQuery("SELECT c FROM Course c WHERE c.id = :courseId");
            query.setParameter("courseId", courseId);

            // Execute the query and get the result list
            List<Course> resultList = query.getResultList();

            // Add the result entities to the list
            courses.addAll(resultList);
        } catch (Exception e) {
            System.out.println("Searching Course by ID failed: " + e.getMessage());
            e.printStackTrace();
        }

        return courses;
    }

    public List<Course> searchCourseByName(String name) {
        List<Course> courses = new ArrayList<>();

        try (EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager()) {
            // Using JPA query to select users by name
            Query query = em.createQuery("SELECT c FROM Course c WHERE c.name LIKE :name");
            query.setParameter("name", "%" + name + "%");

            // Execute the query and get the result list
            List<Course> resultList = query.getResultList();

            // Add the result entities to the list
            courses.addAll(resultList);
        } catch (Exception e) {
            System.out.println("Searching user by name failed: " + e.getMessage());
            e.printStackTrace();
        }

        return courses;
    }

//For student register
    public List<Course> getCoursesByIds(List<String> courseIds) {
        EntityManager entityManager = null;
        try {
            entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
            TypedQuery<Course> query = entityManager.createQuery("SELECT c FROM Course c WHERE c.id IN :ids", Course.class);
            query.setParameter("ids", courseIds);
            return query.getResultList();
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }


}
