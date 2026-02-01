package indradwiprabowo.jpa;

import indradwiprabowo.jpa.entity.Categories;
import indradwiprabowo.jpa.util.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GenerateValueTest {

    private EntityManagerFactory entityManagerFactory;

    @BeforeEach
    void setUp() {
        entityManagerFactory = JpaUtil.getEntityManagerFactory();
    }

    @Test
    void create() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Categories categories = new Categories();
        categories.setName("Indra Dwi");
        categories.setDescription("Ini adalah belajar JPA di udemy PZN");
        entityManager.persist(categories);
        Assertions.assertNotNull(categories.getId());

        entityTransaction.commit();
        entityManager.close();
    }
}
