package pl.ml.UserController;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import pl.ml.HibernateUtil;

import javax.persistence.NoResultException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Dawid Kamasz
 */
public class UserController {

    private static Session session;

    private final static Logger logger = Logger.getLogger(UserController.class);

    public static void registerUser(String firstName, String lastName, String userName,String password) {

        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Users user = new Users();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUserName(userName);
        user.setPassword(password);

        logger.log(Level.WARN, "Dodano: "
                + " Imię: "
                + user.getFirstName()
                + ", Nazwisko: "
                + user.getLastName()
                + ", Nick: "
                + user.getUserName());

        session.save(user);
        session.getTransaction().commit();
        session.close();

    }

    public static void logIn() {
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

    }

    /**
     * try catch subsides if statement, where user is not null this method will always return true because
     * user exists, otherwise this part of code goes straight to catch block.
     */
    public static boolean checkIfLoginExists(String userName) {
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Users user;

        try {
            user = (Users) session.createQuery(
                    "FROM Users "
                            + "WHERE USER_NAME = '"
                            + userName
                            + "'")
                    .getSingleResult();
            return true;
        } catch (NoResultException e) {
            logger.log(Level.WARN,
                    "Sprawdzono: "
                            + " OBIEKT NIE ISTNIEJE");
            return false;
        } finally {
            session.close();
        }
    }

    public static boolean checkIfLoginMatchesPassword(String userName, String password) {
        if (!checkIfLoginExists(userName)) {
            logger.log(Level.INFO, "Wrong login, or password!");
        } else {
            try {
                session = HibernateUtil.getSessionFactory().openSession();
                session.beginTransaction();
                session.createQuery(
                        "FROM Users "
                                + "WHERE USER_NAME = '"
                                + userName
                                + "'"
                                + " AND PASSWORD = '"
                                + password
                                + "'")
                        .getSingleResult();
                return true;
            } catch (NoResultException e) {
                logger.log(Level.INFO,
                        "Sprawdzono: "
                                + "HASŁO BŁĘDNE");
                return false;
            } finally {
                session.close();
            }
        }
        return false;
    }

    public static void removeUser(String userName, String password) {
        if (checkIfLoginExists(userName)) {
            if (checkIfLoginMatchesPassword(userName, password)) {
                session = HibernateUtil
                        .getSessionFactory()
                        .openSession();
                session.beginTransaction();

                Users user;
                user = (Users) session.createQuery(
                        "FROM Users "
                                + "WHERE USER_NAME = '"
                                + userName
                                + "'"
                                + " AND PASSWORD = '"
                                + password
                                + "'")
                        .getSingleResult();

                logger.log(Level.INFO, "Your account has been removed!");
                session.delete(user);
                session.getTransaction().commit();
                session.close();
                //TODO move to loginScene
            } else {
                logger.log(Level.INFO, "Wrong password!");
            }
        } else {
            logger.log(Level.INFO, "Wrong login!");
        }
    }

    public static void editUser(String userName, String password, String firstName, String lastName) {
        if (checkIfLoginExists(userName)) {
            if (checkIfLoginMatchesPassword(userName, password)) {
                session = HibernateUtil
                        .getSessionFactory()
                        .openSession();
                session.beginTransaction();

                Users user;
                user = (Users) session.createQuery(
                        "FROM Users "
                                + "WHERE USER_NAME = '"
                                + userName
                                + "'"
                                + " AND PASSWORD = '"
                                + password
                                + "'")
                        .getSingleResult();

                logger.log(Level.INFO, "Your account has been edited!");

                user.setUserName(userName);
                user.setPassword(password);
                user.setFirstName(firstName);
                user.setLastName(lastName);
                session.update(user);
                session.getTransaction().commit();
                session.close();
                //TODO close scene
            } else {
                logger.log(Level.INFO, "Wrong login!");
            }
        }
    }

    public static Users LogIn(String userName, String password) {
        if (!checkIfLoginExists(userName)) {
            logger.log(Level.INFO, "Wrong login, or password!");
        } else {
            try {
                session = HibernateUtil.getSessionFactory().openSession();
                session.beginTransaction();
              Users user = (Users)  session.createQuery(
                        "FROM Users "
                                + "WHERE USER_NAME = '"
                                + userName
                                + "'"
                                + " AND PASSWORD = '"
                                + password
                                + "'")
                        .getSingleResult();
                return user;
            } catch (NoResultException e) {
                logger.log(Level.INFO,
                        "Sprawdzono: "
                                + "HASŁO BŁĘDNE");
                return null;
            } finally {
                session.close();
            }
        }
        return null;
    }

    public static boolean checkPasswordPattern(String password){
        String passwordPattern = "((?=.*[a-z])(?=.*\\\\d)(?=.*[A-Z])(?=.*[@#$%!]).{8,40})";
        Matcher matcher = Pattern.compile(passwordPattern).matcher(password);
        return matcher.matches();
    }
}
