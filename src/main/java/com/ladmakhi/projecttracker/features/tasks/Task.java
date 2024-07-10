package com.ladmakhi.projecttracker.features.tasks;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ladmakhi.projecttracker.core.entity.CoreEntity;
import com.ladmakhi.projecttracker.features.collection.Collection;
import com.ladmakhi.projecttracker.features.comments.Comment;
import com.ladmakhi.projecttracker.features.users.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "tasks")
public class Task extends CoreEntity {
    private String title;

    private String description;

    @Column(nullable = true)
    private LocalDate reminderDate;

    @ManyToMany
    @JoinTable(
            name = "tasks_users_mapping",
            joinColumns = @JoinColumn(name = "tasks_id"),
            inverseJoinColumns = @JoinColumn(name = "users_id")
    )
    @JsonManagedReference
    private List<User> functors;

    @Column(columnDefinition = "json")
    private List<String> attachments;

    @ManyToOne
    @JoinColumn(name = "collection_id")
    @JsonManagedReference
    private Collection collection;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "task")
    @JsonManagedReference
    @JsonIgnore
    private List<Comment> comments;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    @JsonManagedReference
    private User creator;
}
