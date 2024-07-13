package com.ladmakhi.projecttracker.features.collection;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ladmakhi.projecttracker.core.entity.CoreEntity;
import com.ladmakhi.projecttracker.features.board.Board;
import com.ladmakhi.projecttracker.features.tasks.Task;
import com.ladmakhi.projecttracker.features.users.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "collections")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Collection extends CoreEntity {
    private String name;

    @ManyToOne
    @JoinColumn(name = "board_id")
    @JsonManagedReference
    private Board board;

    @OneToMany(mappedBy = "collection")
    @JsonIgnore
    @JsonManagedReference
    private List<Task> tasks;

    @ManyToOne
    @JoinColumn(name = "created_by_id")
    @JsonManagedReference
    private User createdBy;
}
