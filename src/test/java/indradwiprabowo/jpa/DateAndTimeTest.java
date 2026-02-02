package indradwiprabowo.jpa;

import indradwiprabowo.jpa.entity.Categories;
import indradwiprabowo.jpa.util.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Calendar;

public class DateAndTimeTest {

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
        categories.setName("Foot");
        categories.setDescription("Kali ini saya makan mie ayam");
        categories.setCreatedAt(Calendar.getInstance());
        categories.setUpdatedAt(LocalDateTime.now());

        entityManager.persist(categories);

        entityTransaction.commit();
        entityManager.close();
    }

}
