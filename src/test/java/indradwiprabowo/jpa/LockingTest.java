package indradwiprabowo.jpa;

import indradwiprabowo.jpa.entity.Brand;
import indradwiprabowo.jpa.util.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.LockModeType;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class LockingTest {

    @Test
    void optimisticLocking() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Brand brand = new Brand();
        brand.setId("nokia");
        brand.setName("Nokia");
        brand.setCreatedAt(LocalDateTime.now());
        brand.setUpdateAd(LocalDateTime.now());
        entityManager.persist(brand);

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void optimisticLockingDemo1() throws InterruptedException {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Brand brand = entityManager.find(Brand.class, "nokia");
        brand.setName("Nokia Demo 1");
        brand.setCreatedAt(LocalDateTime.now());
        brand.setUpdateAd(LocalDateTime.now());

        Thread.sleep(10 * 1000L);
        entityManager.persist(brand);

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void optimisticLockingDemo2() throws InterruptedException {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Brand brand = entityManager.find(Brand.class, "nokia");
        brand.setName("Nokia Demo 2");
        brand.setCreatedAt(LocalDateTime.now());
        brand.setUpdateAd(LocalDateTime.now());
        entityManager.persist(brand);

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void pessimisticLockingDemo1() throws InterruptedException {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Brand brand = entityManager.find(Brand.class, "nokia", LockModeType.PESSIMISTIC_WRITE);
        brand.setName("Nokia Demo 1");
        brand.setCreatedAt(LocalDateTime.now());
        brand.setUpdateAd(LocalDateTime.now());

        Thread.sleep(10 * 1000L);
        entityManager.persist(brand);

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void pessimisticLockingDemo2() throws InterruptedException {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Brand brand = entityManager.find(Brand.class, "nokia", LockModeType.PESSIMISTIC_WRITE);
        brand.setName("Nokia Demo 2");
        brand.setCreatedAt(LocalDateTime.now());
        brand.setUpdateAd(LocalDateTime.now());
        entityManager.persist(brand);

        entityTransaction.commit();
        entityManager.close();
    }
}
