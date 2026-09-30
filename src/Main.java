import java.math.BigDecimal; import java.time.LocalDateTime;
public class Main {
 public static void main(String[] args){
  var sf=HibernateUtil.getSessionFactory();
  try{
   Category c=new Category("Electronics","Electronic products");
   Product p1=new Product("Laptop",new BigDecimal("65000.00"),10);
   Product p2=new Product("Mouse",new BigDecimal("1200.00"),20); c.addProduct(p1);c.addProduct(p2);
   Users u=new Users("testuser",PasswordUtil.hash("password123"),"test@example.com",Role.CUSTOMER);
   Orders o=new Orders(LocalDateTime.now(),new BigDecimal("66200.00"));
   o.addDetail(new OrderDetails(1,p1.getPrice(),p1)); o.addDetail(new OrderDetails(1,p2.getPrice(),p2)); u.addOrder(o);
   sf.inTransaction(s->{s.persist(c);s.persist(u);});
   try(var s=sf.openSession()){
    Orders found=s.createQuery("select distinct o from Orders o join fetch o.user left join fetch o.details d left join fetch d.product where o.id=:id",Orders.class).setParameter("id",o.getId()).uniqueResult();
    System.out.println("Order user: "+found.getUser().getUsername());
    for(OrderDetails d:found.getDetails()) System.out.println("Product: "+d.getProduct().getName());
   }
   sf.inTransaction(s->{Product p=s.find(Product.class,p1.getId());p.setStockQuantity(9);});
   System.out.println("CRUD operations completed successfully.");
  } finally {HibernateUtil.shutdown();}
 }
}