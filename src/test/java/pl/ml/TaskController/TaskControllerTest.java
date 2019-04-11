package pl.ml.TaskController;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.ml.UserController.UserController;

/**
 * @author Remigiusz Zudzin
 */
class TaskControllerTest {

    private static Session session;
    private final static Logger logger = Logger.getLogger(UserController.class);
    private static Tasks tasks;

    @BeforeEach
    void setUp() {

        BasicConfigurator.configure();
        tasks = new Tasks();
        tasks.setDueDate("25.05.2019");
        tasks.setStatus(TaskStatus.TODO);
        tasks.setTaskDesc("Opis zadania");
        tasks.setTaskName("Nazwa zadania");

    }

    @Test
    void addTaskTest(){

    }

}