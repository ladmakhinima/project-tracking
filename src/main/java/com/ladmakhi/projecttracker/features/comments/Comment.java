package com.ladmakhi.projecttracker.features.comments;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ladmakhi.projecttracker.core.entity.CoreEntity;
import com.ladmakhi.projecttracker.features.tasks.Task;
import com.ladmakhi.projecttracker.features.users.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
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
    @JsonManagedReference
    private Task task;

    private String content;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    @JsonManagedReference
    private Comment parent;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parent")
    @JsonIgnore
    @JsonBackReference
    private List<Comment> children;

    public Comment(Task task, String content, Comment parent) {
        this.task = task;
        this.content = content;
        this.parent = parent;
    }
}
