package indradwiprabowo.jpa;

import indradwiprabowo.jpa.entity.Categories;
import indradwiprabowo.jpa.util.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.Test;


public class EntityListenerTest {

    @Test
    void listener() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Categories categories = new Categories();
        categories.setName("Contoh");

        entityManager.persist(categories);

        entityTransaction.commit();
        entityManager.close();
    }
}
