package indradwiprabowo.jpa;

import indradwiprabowo.jpa.entity.*;
import indradwiprabowo.jpa.util.JpaUtil;
import jakarta.persistence.*;
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

    @Test
    void joinClause() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        TypedQuery<Product> query = entityManager.createQuery("select p from Product p " +
                "join p.brand b where b.name = :brand", Product.class);
        query.setParameter("brand", "Samsung");

        List<Product> products = query.getResultList();
        for (Product product : products) {
            System.out.println(product.getId() + " : " + product.getName() + " : " + product.getBrand().getName());
        }

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void joinFetchClause() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        TypedQuery<User> query = entityManager.createQuery("select u from User u " +
                "join  u.likes p where p.name = :product", User.class);
        query.setParameter("product", "Samsung Galaxy 25 Ultra");

        List<User> users = query.getResultList();
        for (User user : users) {
            System.out.println("User: " + user.getName());
            for (Product product : user.getLikes()) {
                System.out.println("Product: " + product.getName());
            }
        }

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void orderClause() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        TypedQuery<Brand> query = entityManager.createQuery("select b from Brand b order by b.name desc ", Brand.class);
        List<Brand> list = query.getResultList();
        for (Brand brand : list) {
            System.out.println(brand.getName());
        }

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void insertRandomBrand() {

        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        for (int i = 0; i < 100; i++) {
            Brand brand = new Brand();
            brand.setId(String.valueOf(i));
            brand.setName("Brand " + i);
            entityManager.persist(brand);
        }

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void limitOffset() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        TypedQuery<Brand> query = entityManager.createQuery("select b from Brand b order by b.id", Brand.class);
        query.setFirstResult(10);
        query.setMaxResults(10);

        List<Brand> list = query.getResultList();
        for (Brand brand : list) {
            System.out.println(brand.getId());
        }

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void namedQuery() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        TypedQuery<Brand> query = entityManager.createNamedQuery("Brand.findAllByName", Brand.class);
        query.setParameter("name", "Xiaomi");

        List<Brand> list = query.getResultList();
        for (Brand brand : list) {
            System.out.println(brand.getId());
        }

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void selectSomeFields() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        TypedQuery<Object[]> query = entityManager.createQuery("select b.id, b.name from Brand b where b.name = :name", Object[].class);
        query.setParameter("name", "Xiaomi");

        List<Object[]> list = query.getResultList();
        for (Object[] objects : list) {
            System.out.println(objects[0] + " : " + objects[1]);
        }

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void selectNewConstructor() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        TypedQuery<SimpleBrand> query = entityManager.createQuery("select new indradwiprabowo.jpa.entity.SimpleBrand(b.id, b.name)" +
                "from Brand b where b.name = :name", SimpleBrand.class);
        query.setParameter("name", "Xiaomi");

        List<SimpleBrand> resultList = query.getResultList();
        for (SimpleBrand simpleBrand : resultList) {
            System.out.println(simpleBrand.getId() + " : " + simpleBrand.getName());
        }

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void aggregateQuery() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        TypedQuery<Object[]> query = entityManager.createQuery("select min(p.price), max(p.price), avg(p.price) from Product p ", Object[].class);
        Object[] result = query.getSingleResult();

        System.out.println("Min : " + result[0]);
        System.out.println("Max : " + result[1]);
        System.out.println("Avg : " + result[2]);

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void aggregateQueryGrubBy() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        TypedQuery<Object[]> query = entityManager.createQuery(
                "select b.id, min(p.price), max(p.price), avg(p.price) from Product p join p.brand b " +
                        "group by b.id having min(p.price) > :min", Object[].class);

        query.setParameter("min", 500_000L);

        List<Object[]> objects = query.getResultList();
        for (Object[] object : objects) {
            System.out.println("Brand " + object[0]);
            System.out.println("Min " + object[1]);
            System.out.println("Max " + object[2]);
            System.out.println("Avg " + object[3]);
        }

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void nativeQuery() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        // walaupun tidak disarankan, karena bisa membuat kita kesulitan ketika akan mengubah database
        // yang sedang digunakan, namun fitur ini juga bisa dilakukan di JPA
        // dan sangat disarankan menggunakan jpa query languages dibandingkan native query

        Query query = entityManager.createNativeQuery("select * from brands where brands.created_at is not null", Brand.class);
        List<Brand> brands = query.getResultList();

        for (Brand brand : brands) {
            System.out.println(brand.getId() + " : " + brand.getName());
        }

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void namedNativeQuery() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Query query = entityManager.createNamedQuery("Brand.native.findALl", Brand.class);
        List<Brand> brands = query.getResultList();

        for (Brand brand : brands) {
            System.out.println(brand.getId() + " : " + brand.getName());
        }

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void nonQuery() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        // sangat disarankan menggunakan entityManager, update atau delete
        // karena ketika di update fitur optimistic locking tidak mungkin bisa bekerja

        Query query = entityManager.createQuery("update Brand b set b.name = :name where b.id = :id");
        query.setParameter("name", "Samsung Updated");
        query.setParameter("id", "samsung");
        int impactedRecord = query.executeUpdate();
        System.out.println("Success update " + impactedRecord + " records");

        entityTransaction.commit();
        entityManager.close();
    }

}
