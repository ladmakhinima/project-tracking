package com.ladmakhi.projecttracker.features.comments;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ladmakhi.projecttracker.core.entity.CoreEntity;
import com.ladmakhi.projecttracker.features.tasks.Task;
import com.ladmakhi.projecttracker.features.users.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "comments")
@AllArgsConstructor
@NoArgsConstructor
public class Comment extends CoreEntity {
    @ManyToOne
    @JoinColumn(name = "creator_id")
    @JsonManagedReference
    private User creator;

    @ManyToOne
    @JoinColumn(name = "task_id")
    @JsonBackReference
    private Task task;

    private String content;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    @JsonBackReference
    @JsonIgnore
    private Comment parent;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parent")
    @JsonManagedReference
    private List<Comment> children;

    public Comment(Task task, String content, User creator, Comment parent) {
        this.task = task;
        this.content = content;
        this.parent = parent;
        this.creator = creator;
    }
}
