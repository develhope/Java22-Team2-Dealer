package com.develhope.spring.Features.Autentication.Entity;

import com.develhope.spring.Features.User.Entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long refreshTokenId;

    private String token;
    private Instant expiryDate;

    @OneToOne
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private User userInfo;

}
