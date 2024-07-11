package com.ladmakhi.projecttracker.features.users;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ladmakhi.projecttracker.core.entity.CoreEntity;
import com.ladmakhi.projecttracker.features.board.Board;
import com.ladmakhi.projecttracker.features.collection.Collection;
import com.ladmakhi.projecttracker.features.comments.Comment;
import com.ladmakhi.projecttracker.features.tasks.Task;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Builder
@Setter
public class User extends CoreEntity {
    private String username;

    private String email;

    private String password;

    private String profileUrl;

    private UserStatus status;

    private LocalDate lastStatusUpdateDate;

    @ManyToMany
    @JoinTable(
            name = "boards_users_mapping",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "board_id")
    )
    @JsonIgnore
    @JsonBackReference
    private List<Board> joinedBoards;

    @ManyToMany(mappedBy = "functors")
    @JsonIgnore
    @JsonBackReference
    private List<Task> tasks;

    @OneToMany(mappedBy = "creator", cascade = CascadeType.ALL)
    @JsonIgnore
    @JsonBackReference
    private List<Comment> comments;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    @JsonIgnore
    @JsonBackReference
    private List<Board> boards;

    @OneToMany(mappedBy = "createdBy", cascade = CascadeType.ALL)
    @JsonIgnore
    @JsonBackReference
    private List<Collection> collections;

    @OneToMany(mappedBy = "creator", cascade = CascadeType.ALL)
    @JsonIgnore
    @JsonBackReference
    private List<Task> createdTasks;
}
