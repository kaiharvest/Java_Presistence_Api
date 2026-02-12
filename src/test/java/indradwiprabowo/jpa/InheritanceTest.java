package indradwiprabowo.jpa;

import indradwiprabowo.jpa.entity.*;
import indradwiprabowo.jpa.util.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class InheritanceTest {

    private EntityManagerFactory entityManagerFactory;

    @BeforeEach
    void setUp() {
        entityManagerFactory = JpaUtil.getEntityManagerFactory();
    }

    @Test
    void singleTableInsert() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Employee employee = new Employee();
        employee.setId("rina");
        employee.setName("Rina Wati");
        entityManager.persist(employee);

        Manager manager = new Manager();
        manager.setId("joko");
        manager.setName("Joko Hugroho");
        manager.setTotalEmployee(10);
        entityManager.persist(manager);

        VicePresident vp = new VicePresident();
        vp.setId("budi");
        vp.setName("Budi Nugraha");
        vp.setTotalManager(5);
        entityManager.persist(vp);

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void singleTableFind() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Manager manager = entityManager.find(Manager.class, "joko");
        Assertions.assertEquals("Joko Hugroho", manager.getName());

        Employee employee = entityManager.find(Employee.class, "budi");
        VicePresident vp = (VicePresident) employee;
        Assertions.assertEquals("Budi Nugraha", vp.getName());

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void joinedTableInsert() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        PaymentGopay paymentGopay = new PaymentGopay();
        paymentGopay.setId("gopay1");
        paymentGopay.setAmount(100_000L);
        paymentGopay.setGopayId("089999999999");
        entityManager.persist(paymentGopay);

        PaymentCreditCard paymentCreditCard = new PaymentCreditCard();
        paymentCreditCard.setId("cc1");
        paymentCreditCard.setAmount(500_000L);
        paymentCreditCard.setMasterCard("4555-5555");
        paymentCreditCard.setBank("BCA");
        entityManager.persist(paymentCreditCard);

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void joinedTableFind() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        PaymentGopay paymentGopay = entityManager.find(PaymentGopay.class, "gopay1");

        PaymentCreditCard paymentCreditCard = entityManager.find(PaymentCreditCard.class, "cc1");

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void joinedTableFindParent() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        // jika ingin query terus ke parent class nya, lebih baik pakai SINGLE TABLE inheritance karena tidak perlu join ke child class nya
        // joined table ini cocok jika query nya langsung ke table child class nya
        Payment paymentGopay = entityManager.find(Payment.class, "gopay1");

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void tablePerClassInsert() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Transaction transaction = new Transaction();
        transaction.setId("t1");
        transaction.setCreatedAt(LocalDateTime.now());
        transaction.setBalance(1_000_000L);
        entityManager.persist(transaction);

        TransactionDebit transactionDebit = new TransactionDebit();
        transactionDebit.setId("t2");
        transactionDebit.setCreatedAt(LocalDateTime.now());
        transactionDebit.setBalance(2_000_000L);
        transactionDebit.setDebitAmount(1_000_000L);
        entityManager.persist(transactionDebit);

        TransactionCredit transactionCredit = new TransactionCredit();
        transactionCredit.setId("t3");
        transactionCredit.setCreatedAt(LocalDateTime.now());
        transactionCredit.setBalance(1_000_000L);
        transactionCredit.setCreditAmount(1_000_000L);
        entityManager.persist(transactionCredit);

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void tablePerClassFindChild() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        TransactionDebit transactionDebit = entityManager.find(TransactionDebit.class, "t2");

        TransactionCredit transactionCredit = entityManager.find(TransactionCredit.class, "t3");

        entityTransaction.commit();
        entityManager.close();
    }


    @Test
    void tablePerClassFindParent() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        // jika ingin find ke table parent dan jika child nya tidak begitu banyak lebih baik menggunakan bisa menggunakan join table strategy
        // jika child nya banyak di sarankan pakai SINGLE TABLE
        Transaction transaction = entityManager.find(Transaction.class, "t1");

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void mappedSuperclass() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Brand brand = new Brand();
        brand.setId("xiaomi");
        brand.setName("Xiaomi");
        brand.setDescription("Xiaomi Global");
        brand.setCreatedAt(LocalDateTime.now());
        brand.setUpdateAd(LocalDateTime.now());
        entityManager.persist(brand);

        entityTransaction.commit();
        entityManager.close();
    }

}
