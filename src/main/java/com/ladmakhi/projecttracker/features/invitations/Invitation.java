package com.ladmakhi.projecttracker.features.invitations;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Builder
@AllArgsConstructor
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

    @Transient
    @JsonIgnore
    public boolean checkIsExpired() {
        return this.isExpired() || this.getExpireTime().before(new Date());
    }
}
