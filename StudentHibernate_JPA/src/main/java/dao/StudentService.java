package dao;

import app.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.util.List;

public class StudentService {
    public void addStudent(Student student) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(student);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void updateStudent(Student student) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(student);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void deleteStudent(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            Student student = em.find(Student.class, id);
            if (student != null) {
                tx.begin();
                em.remove(student);
                tx.commit();
            }
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public Student getStudent(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        return em.find(Student.class, id);
    }

    public List<Student> getAllStudents() {
        EntityManager em = JPAUtil.getEntityManager();
        return em.createQuery("FROM Student", Student.class).getResultList();
    }
}
