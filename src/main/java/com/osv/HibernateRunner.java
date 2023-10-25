package com.osv;

import com.osv.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.SQLException;
import java.time.LocalDate;

public class HibernateRunner {

    public static void main(String[] args) throws SQLException {
//        Connection connection = DriverManager
//               .getConnection("db.url", "db.username", "db.password");

        Configuration configuration = new Configuration();
//      configuration.setPhysicalNamingStrategy(new CamelCaseToUnderscoresNamingStrategy());
//      configuration.addAnnotatedClass(User.class);
        configuration.configure();

        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            User user = User.builder()
                    .username("ivan@gmail.com")
                    .firstname("Ivan")
                    .lastname("Ivanov")
                    .birthDate(LocalDate.of(2002, 6, 23))
                    .age(21)
                    .build();
            session.save(user);

            session.getTransaction().commit();

        }
    }

}
