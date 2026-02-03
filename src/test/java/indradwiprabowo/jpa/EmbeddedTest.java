package indradwiprabowo.jpa;

import indradwiprabowo.jpa.entity.Members;
import indradwiprabowo.jpa.entity.Name;
import indradwiprabowo.jpa.util.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EmbeddedTest {

    private EntityManagerFactory entityManagerFactory;

    @BeforeEach
    void setUp() {
        entityManagerFactory = JpaUtil.getEntityManagerFactory();
    }

    @Test
    void embedded() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();


        Name name = new Name();
        name.setTitle("Mr");
        name.setFirstName("Indra");
        name.setMiddleName("Dwi");
        name.setLastName("Prabowo");

        Members members = new Members();
        members.setEmail("example.com");
        members.setName(name);

        entityManager.persist(members);

        entityTransaction.commit();
        entityManager.close();
    }
}
