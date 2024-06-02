package com.develhope.spring.Features.Repository;

import com.develhope.spring.Features.Entity.Amministratore.Amministratore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AmministratoreRepository extends JpaRepository<Amministratore, Long> {

}
