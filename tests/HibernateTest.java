import org.junit.jupiter.api.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

public class HibernateTest {
    private static final var sf = HibernateUtil.getSessionFactory();

    @Test
    void passwordHash() {
        String hash = PasswordUtil.hash("abc");
        assertNotEquals("abc", hash);
        assertTrue(PasswordUtil.check("abc", hash));
    }

    @Test
    void saveAndReadProduct() {
        Category category = new Category("Books", "Study");
        Product product = new Product("Java Book", new BigDecimal("500"), 5);
        category.addProduct(product);

        sf.inTransaction(session -> session.persist(category));

        try (var session = sf.openSession()) {
            Product saved = session.find(Product.class, product.getId());
            assertNotNull(saved);
            assertEquals("Java Book", saved.getName());
        }
    }

    @Test
    void createAndFetchOrderWithDetails() {
        Category category = new Category("Stationery", "College items");
        Product product = new Product("Notebook", new BigDecimal("100"), 10);
        category.addProduct(product);

        Users user = new Users(
            "orderuser",
            PasswordUtil.hash("pass123"),
            "order@example.com",
            Role.CUSTOMER
        );

        Orders order = new Orders(
            LocalDateTime.now(),
            new BigDecimal("200.00")
        );
        order.addDetail(new OrderDetails(2, product.getPrice(), product));
        user.addOrder(order);

        sf.inTransaction(session -> {
            session.persist(category);
            session.persist(user);
        });

        try (var session = sf.openSession()) {
            Orders saved = session.createQuery(
                "select distinct o from Orders o " +
                "join fetch o.user " +
                "left join fetch o.details d " +
                "left join fetch d.product " +
                "where o.id = :id", Orders.class)
                .setParameter("id", order.getId())
                .uniqueResult();

            assertNotNull(saved);
            assertEquals("orderuser", saved.getUser().getUsername());
            assertEquals(1, saved.getDetails().size());
            assertEquals("Notebook", saved.getDetails().get(0).getProduct().getName());
        }
    }

    @Test
    void updateAndDeleteProduct() {
        Category category = new Category("Test", "CRUD test");
        Product product = new Product("Test Product", new BigDecimal("50"), 5);
        category.addProduct(product);

        sf.inTransaction(session -> session.persist(category));

        sf.inTransaction(session -> {
            Product saved = session.find(Product.class, product.getId());
            saved.setStockQuantity(10);
        });

        try (var session = sf.openSession()) {
            assertEquals(10, session.find(Product.class, product.getId()).getStockQuantity());
        }

        sf.inTransaction(session -> {
            Product saved = session.find(Product.class, product.getId());
            session.remove(saved);
        });

        try (var session = sf.openSession()) {
            assertNull(session.find(Product.class, product.getId()));
        }
    }

    @AfterAll
    static void close() {
        HibernateUtil.shutdown();
    }
}
