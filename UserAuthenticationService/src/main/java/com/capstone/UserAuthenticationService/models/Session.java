package com.capstone.UserAuthenticationService.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Session extends BaseModel{
    private String token;

    @Enumerated(EnumType.ORDINAL)
    private SessionState sessionState;

    @ManyToOne
    private User user;
}