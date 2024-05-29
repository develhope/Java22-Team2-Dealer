package com.develhope.spring.Features.Entity.Noleggio;

import com.develhope.spring.Features.Entity.User.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "noleggio_link")
@NoArgsConstructor
@AllArgsConstructor
public class NoleggioLink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long linkId;

    @ManyToOne
    @JoinColumn(name = "acquirente_id")
    private User acquirente;

    @OneToOne
    @JoinColumn(name = "noleggio_id")
    private Noleggio noleggio;

    @ManyToOne
    @JoinColumn(name = "venditore_id")
    private User venditore;

    public NoleggioLink(Noleggio noleggio, User acquirente, User venditore) {
        this.noleggio = noleggio;
        this.acquirente = acquirente;
        this.venditore = venditore;
    }
}
