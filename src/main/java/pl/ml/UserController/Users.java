package pl.ml.UserController;

import lombok.Data;
import lombok.NoArgsConstructor;
import pl.ml.TaskController.Tasks;

import javax.persistence.*;
import java.util.List;

/**
 * @author Dawid Kamasz
 */

@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
public class Users {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_id;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "USER_NAME")
    private String userName;

    @Column(name = "PASSWORD")
    private String password;

    @Transient
    private List<Tasks> listOfTasks;

}
