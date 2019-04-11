package pl.ml.UserController;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.ml.HibernateUtil;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Remigiusz Zudzin
 */
class UserControllerTest {

    private static Session session;
    private final static Logger logger = Logger.getLogger(UserController.class);
    private static Users user;

    @BeforeEach
    void setup() {
        BasicConfigurator.configure();
        user = new Users();
        user.setFirstName("Jan");
        user.setLastName("Kowalski");
        user.setUserName("Janusz");
        user.setPassword("dupa");
    }

    @Test
    void registerUser() {
        //TODO from java to sql
        UserController uC = new UserController();
//        assertAll("User assertion",
//                () -> assertEquals(user.equals()));

    }

    @Test
    void checkIfLoginExists() {
        assertAll("First should return true, Second should return false",
                () -> assertTrue(UserController.checkIfLoginExists("Janusz")),
                () -> assertFalse(UserController.checkIfLoginExists("anythingElse")));
    }

    @Test
    void checkIfLoginMatchesPassword() {
        assertAll("First should return true, Second should return false",
                () -> assertTrue(UserController.checkIfLoginMatchesPassword("Janusz", "dupa")),
                () -> assertFalse(UserController.checkIfLoginMatchesPassword("Janusz", "wrongPassword")),
                () -> assertFalse(UserController.checkIfLoginMatchesPassword("wrongLogin", "dupa")),
                () -> assertFalse(UserController.checkIfLoginMatchesPassword("wrongLogin", "wrongPassword")));
    }


}