package pl.ml.TaskController;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author Remigiusz Zudzin
 */

@Data
@NoArgsConstructor
@Entity
@Table(name = "tasks")
public class Tasks {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int task_id;

    @Column(name = "TASK_NAME")
    private String taskName;

    @Column(name = "TASK_DESC")
    private String taskDesc;

    @Column(name = "DUE_DATE")
    private String dueDate;

    @Column(name = "STATUS")
    private TaskStatus status;

    @Column(name = "USER_ID")
    private int userId;

}
