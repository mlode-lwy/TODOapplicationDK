package pl.ml.TaskController;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import pl.ml.HibernateUtil;
import pl.ml.UserController.UserController;

/**
 * @author Remigiusz Zudzin
 */
public class TaskController {

    private static Session session;

    private final static Logger logger = Logger.getLogger(TaskController.class);

    private UserController userController;

    public void setUserController(UserController userController) {
        this.userController = userController;
    }

    public void addTask(String taskName, String taskDesc, String dueDate) {

        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Tasks task = new Tasks();
        task.setStatus(TaskStatus.TODO);
        task.setTaskName(taskName);
        task.setTaskDesc(taskDesc);
        task.setDueDate(dueDate);

    }



}
