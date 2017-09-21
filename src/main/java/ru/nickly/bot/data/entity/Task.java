package ru.nickly.bot.data.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "tasks")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "task_id")
    @Getter @Setter private Integer id;

    @Column(name = "group_id", insertable = false, updatable = false)
    @Getter @Setter private Integer group_id;

    @Column(name = "date")
    @Getter @Setter private Date date;

    @Column(name = "done")
    @Getter @Setter private Boolean done;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;


    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", group_id=" + group_id +
                ", date=" + date +
                ", done=" + done +
                '}';
    }
}
