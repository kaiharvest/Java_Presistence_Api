package indradwiprabowo.jpa;

import indradwiprabowo.jpa.entity.Customer;
import indradwiprabowo.jpa.util.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.Test;

public class ColumTest {

    @Test
    void colum() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Customer customer = new Customer();
        customer.setId("1");
        customer.setName("Indra");
        customer.setPrimary_email("indra@gmail.com");

        entityManager.persist(customer);

        entityTransaction.commit();
        entityManager.close();
    }
}
