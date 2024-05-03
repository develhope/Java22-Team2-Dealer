package com.develhope.spring.Amministratore;

import com.develhope.spring.Amministratore.Amministratore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AministratoreRepository extends JpaRepository<Amministratore, Long> {
}
