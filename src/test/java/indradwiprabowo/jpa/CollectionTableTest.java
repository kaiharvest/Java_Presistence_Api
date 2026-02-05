package indradwiprabowo.jpa;

import indradwiprabowo.jpa.entity.Members;
import indradwiprabowo.jpa.entity.Name;
import indradwiprabowo.jpa.util.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class CollectionTableTest {

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

        Name name = new Name();
        name.setTitle("Mr");
        name.setFirstName("Joko");
        name.setMiddleName("Kurniawan");
        name.setLastName("Budi");

        Members members = new Members();
        members.setEmail("example.com");
        members.setName(name);
        members.setHobbies(new ArrayList<>());
        members.getHobbies().add("Coding");
        members.getHobbies().add("Basket");

        entityManager.persist(members);

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void update() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Members members = entityManager.find(Members.class, 4);
        members.getHobbies().add("Traveling");

        entityManager.persist(members);

        entityTransaction.commit();
        entityManager.close();
    }
}
