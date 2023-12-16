package base.daos;

import base.models.User;
import base.service.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.Query;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserDao {


    //user create method
    public int createUser(User user){
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

    //user view method
    public List<User> getAllUsers(){
        List<User> users;
        EntityManager entityManager = null ;
        try {
            entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
            entityManager.getTransaction().begin();
            users = entityManager.createQuery("SELECT u FROM User u" , User.class).getResultList();
            entityManager.getTransaction().commit();
        }finally {
            assert entityManager != null;
            entityManager.close();
        }
        return users;
    }

    //getUserById
    public User findUserById(String userId) {
        EntityManager entityManager = null;
        User user = null;
        try {
            entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
            user = entityManager.find(User.class, userId);
            if (user == null) {
                // Handle the case where the user is not found, e.g., throw an exception
                throw new EntityNotFoundException("User not found with ID: " + userId);
            }
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
        return user;
    }

    //update user
    public int updateUser(User updatedUser) {
        EntityManager entityManager = null;
        int updateResult;
        try {
            entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
            entityManager.getTransaction().begin();

            // Find the existing user by ID
            User existingUser = entityManager.find(User.class, updatedUser.getId());

            if (existingUser != null) {
                // Update the existing user with the new values
                existingUser.setName(updatedUser.getName());
                existingUser.setEmail(updatedUser.getEmail());
                // ... other attributes

                // Changes will be persisted when the transaction is committed
                entityManager.getTransaction().commit();

                // Indicate that the update was successful
                updateResult = 1;
            } else {
                // Handle the case where the user with the specified ID is not found
                // You might want to throw an exception or handle this case differently
                entityManager.getTransaction().rollback();
                throw new EntityNotFoundException("User not found with ID: " + updatedUser.getId());
            }
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
        return updateResult;
    }


    //user delete
    public int deleteUser(String userId) {
        EntityManager entityManager = null;
        int deleteResult;
        try {
            entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
            entityManager.getTransaction().begin();

            // Find the existing user by ID
            User existingUser = entityManager.find(User.class, userId);

            if (existingUser != null) {
                // Remove the existing user from the database
                entityManager.remove(existingUser);

                // Changes will be persisted when the transaction is committed
                entityManager.getTransaction().commit();

                // Indicate that the delete was successful
                deleteResult = 1;
            } else {
                // Handle the case where the user with the specified ID is not found
                // You might want to throw an exception or handle this case differently
                entityManager.getTransaction().rollback();
                throw new EntityNotFoundException("User not found with ID: " + userId);
            }
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
        return deleteResult;
    }

    //get user Id
    public String getLatestUserId() {
        String latestUserId = "USR001";

        EntityManager entityManager = null;
        try {
            entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
            Query query = entityManager.createQuery("SELECT MAX(CAST(SUBSTRING(u.id, 4) AS UNSIGNED)) FROM User u");
            BigInteger maxUserNumber = (BigInteger) query.getSingleResult();

            if (maxUserNumber != null) {
                int newUserNumber = maxUserNumber.intValue() + 1;

                if (maxUserNumber.intValue() < 10) {
                    latestUserId = "USR00" + newUserNumber;
                } else if (maxUserNumber.intValue() < 100) {
                    latestUserId = "USR0" + newUserNumber;
                } else {
                    latestUserId = "USR" + newUserNumber;
                }
            }

        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
            return latestUserId;
    }


    public User getUserByEmailAndPassword(String email, String password) {
        EntityManager entityManager = null;
        User user = null;

        try {
            entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
            entityManager.getTransaction().begin();

            Query query = entityManager.createQuery("SELECT u FROM User u WHERE u.email = :email AND u.password = :password", User.class);
            query.setParameter("email", email);
            query.setParameter("password", password);

            List<User> users = query.getResultList();
            if (!users.isEmpty()) {
                user = users.get(0);
            }

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            // Handle exceptions appropriately
            if (entityManager != null && entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
        return user;
    }

    public List<User> searchUserByName(String name) {
        List<User> users = new ArrayList<>();

        try (EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager()) {
            // Using JPA query to select users by name
            Query query = em.createQuery("SELECT u FROM User u WHERE u.name LIKE :name");
            query.setParameter("name", "%" + name + "%");

            // Execute the query and get the result list
            List<User> resultList = query.getResultList();

            // Add the result entities to the list
            users.addAll(resultList);
        } catch (Exception e) {
            System.out.println("Searching user by name failed: " + e.getMessage());
            e.printStackTrace();
        }

        return users;
    }

    public List<User> searchUserById(String userId) {
        List<User> users = new ArrayList<>();

        try (EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager()) {
            // Using JPA query to select a user by ID
            Query query = em.createQuery("SELECT u FROM User u WHERE u.id = :userId");
            query.setParameter("userId", userId);

            // Execute the query and get the result list
            List<User> resultList = query.getResultList();

            // Add the result entities to the list
            users.addAll(resultList);
        } catch (Exception e) {
            System.out.println("Searching user by ID failed: " + e.getMessage());
            e.printStackTrace();
        }

        return users;
    }


}
