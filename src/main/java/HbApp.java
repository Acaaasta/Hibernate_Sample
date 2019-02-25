import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Scanner;

public class HbApp {

    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Product.class)
                .addAnnotatedClass(Client.class)
                .buildSessionFactory();

        int hhh=0;
        Session session = null;

        try {
            session = factory.getCurrentSession();
            session.beginTransaction();
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите ID клиента, чтобы узнать, какие товары он купил");
            String clientId = scanner.nextLine();
            Long idC = Long.parseLong(clientId);
            Client client = session.get(Client.class, idC);
            System.out.println(client.getProducts());
            System.out.println("Введите ID товара, чтобы узнать, кто его купил");
            String prodId = scanner.nextLine();
            Long idP = Long.parseLong(prodId);
            Product product = session.get(Product.class, idP);
            System.out.println(product.getClients());
            System.out.println("Введите ID клиента, которого надо удалить");
            String clientId2 = scanner.nextLine();
            Long idC2 = Long.parseLong(clientId2);
            session.createQuery("delete from Client where id = :id").setParameter("id", idC2).executeUpdate();
            System.out.println("Введите ID товара, который надо удалить");
            String prodId2 = scanner.nextLine();
            Long idP2 = Long.parseLong(prodId2);
            Product product2 = session.get(Product.class, idP2);
            session.createQuery("delete from Product where id = :id").setParameter("id", idP2).executeUpdate();
            session.getTransaction().commit();
        } finally {
            session.close();
            factory.close();
        }
    }
}
