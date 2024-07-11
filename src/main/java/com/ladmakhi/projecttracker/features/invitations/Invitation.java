package com.ladmakhi.projecttracker.features.invitations;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ladmakhi.projecttracker.core.entity.CoreEntity;
import com.ladmakhi.projecttracker.features.board.Board;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "invitations")
@Setter
@Getter
@NoArgsConstructor
public class Invitation extends CoreEntity {
    private String email;

    private String token;

    private Date expireTime;
    private boolean isExpired = false;

    @ManyToOne
    @JoinColumn(name = "board_id")
    @JsonManagedReference
    private Board board;


    public Invitation(String email, String token, Date expireTime, Board board) {
        this.email = email;
        this.token = token;
        this.expireTime = expireTime;
        this.board = board;
    }
}
