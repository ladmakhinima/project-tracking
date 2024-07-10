package com.ladmakhi.projecttracker.features.invitations;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ladmakhi.projecttracker.core.entity.CoreEntity;
import com.ladmakhi.projecttracker.features.board.Board;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "invitations")
public class Invitation extends CoreEntity {
    private String email;

    private String token;

    private LocalDate expireTime;

    @ManyToOne
    @JoinColumn(name = "board_id")
    @JsonManagedReference
    private Board board;
}
