package com.osv;

import com.osv.converter.BirthdayConverter;
import com.osv.entity.Birthday;
import com.osv.entity.Role;
import com.osv.entity.User;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.hibernate.cfg.Configuration;

import java.sql.SQLException;
import java.time.LocalDate;

public class HibernateRunner {

    public static void main(String[] args) throws SQLException {
//        Connection connection = DriverManager
//               .getConnection("db.url", "db.username", "db.password");

        Configuration configuration = new Configuration();
        configuration.setPhysicalNamingStrategy(new CamelCaseToUnderscoresNamingStrategy());
        configuration.addAnnotatedClass(User.class);
        configuration.addAttributeConverter(new BirthdayConverter());
        configuration.registerTypeOverride(new JsonBinaryType());
        configuration.configure();

        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();

 //           User user = User.builder()
 //                   .username("ivan9@gmail.com")
 //                   .firstname("Ivan")
  //                  .lastname("Ivanov")
  //                  .info("""
  //                          {
  //                              "name": "Ivan",
 //                              "id": 25
  //                          }
  //                          """)
   //                 .birthDate(new Birthday(LocalDate.of(2000, 1, 19)))
  //                  .role(Role.ADMIN)
 //                   .build();
//            session.save(user);


            User user1 = session.get(User.class, "ivan@gmail.com");
            user1.setLastname("Petrov2");
            session.flush();

            System.out.println(session.isDirty());
//            session.evict(user1);
//            session.clear();
//            session.close();

            session.getTransaction().commit();

        }
    }

}
