package com.ladmakhi.projecttracker.features.board;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ladmakhi.projecttracker.core.entity.CoreEntity;
import com.ladmakhi.projecttracker.features.collection.Collection;
import com.ladmakhi.projecttracker.features.invitations.Invitation;
import com.ladmakhi.projecttracker.features.users.User;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "boards")
@Data
public class Board extends CoreEntity {
    private String name;

    @ManyToMany(mappedBy = "joinedBoards")
    @JsonIgnore
    @JsonManagedReference
    private List<User> team;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    @JsonIgnore
    @JsonBackReference
    private List<Collection> collections;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    @JsonIgnore
    @JsonBackReference
    private List<Invitation> invitations;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    @JsonManagedReference
    private User owner;
}
