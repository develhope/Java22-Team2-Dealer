package com.develhope.spring.Features.Entity.OrdineAcquisto;

import com.develhope.spring.Features.Entity.User.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name = "ordine_acquisto_link")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class OrdineAcquistoLink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long linkId;

    @OneToOne
    @JoinColumn(name = "acquirente_id")
    private User acquirente;

    @OneToOne
    @JoinColumn(name = "ordine_acquisto_id")
    private OrdineAcquisto ordineAcquisto;

    @OneToOne
    @JoinColumn(name = "venditore_id")
    private User venditore;

    public OrdineAcquistoLink(User acquirente, OrdineAcquisto ordineAcquisto, User venditore) {
        this.acquirente = acquirente;
        this.ordineAcquisto = ordineAcquisto;
        this.venditore = venditore;
    }
}
