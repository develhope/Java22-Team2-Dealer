package com.develhope.spring.Features.Entity.Noleggio;

import com.develhope.spring.Features.Entity.User.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
public class NoleggioLink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long linkId;

    @ManyToOne
    @JoinColumn(name = "acquirente_id", nullable = false)
    private User acquirente;

    @OneToOne
    @JoinColumn(name = "noleggio_id", nullable = false)
    private Noleggio noleggio;

    @ManyToOne
    @JoinColumn(name = "venditore_id")
    private User venditore;

    public NoleggioLink(User acquirente, Noleggio noleggio) {
        this.acquirente = acquirente;
        this.noleggio = noleggio;
    }

    public NoleggioLink(User acquirente, Noleggio noleggio, User venditore) {
        this.acquirente = acquirente;
        this.noleggio = noleggio;
        this.venditore = venditore;
    }

}
