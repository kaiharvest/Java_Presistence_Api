package indradwiprabowo.jpa;

import indradwiprabowo.jpa.entity.Categories;
import indradwiprabowo.jpa.entity.Customer;
import indradwiprabowo.jpa.util.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GenerateDataTest {

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

        Customer customer = new Customer();
        customer.setId("2");
        customer.setName("Joko");
        customer.setAge((byte) 22);
        customer.setMarried(false);
        customer.setPrimary_email("example.com");
        entityManager.persist(customer);

        entityTransaction.commit();
        entityManager.close();
    }
}
