package indradwiprabowo.jpa;

import indradwiprabowo.jpa.entity.Brand;
import indradwiprabowo.jpa.entity.Members;
import indradwiprabowo.jpa.util.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.Test;

import java.util.List;

public class JpaQueryLanguagesTest {

    @Test
    void select() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        TypedQuery<Brand> query = entityManager.createQuery("select b from Brand b", Brand.class);
        List<Brand> brands = query.getResultList();

        for (Brand brand : brands) {
            System.out.println(brand.getId() + " : " + brand.getName());
        }

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void whereClose() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        TypedQuery<Members> query = entityManager.createQuery("select m from Members m where " +
                "m.name.firstName = :firstName and m.name.lastName= :lastName", Members.class);
        query.setParameter("firstName", "Indra");
        query.setParameter("lastName", "Prabowo");

        List<Members> members = query.getResultList();
        for (Members member : members) {
            System.out.println(member.getId() +" : " + member.getFullName());
        }

        entityTransaction.commit();
        entityManager.close();
    }
}
