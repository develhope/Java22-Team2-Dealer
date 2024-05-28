package com.develhope.spring.Features.Repository;

import com.develhope.spring.Features.Entity.Noleggio.Noleggio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoleggioRepository extends JpaRepository<Noleggio, Long> {


}
