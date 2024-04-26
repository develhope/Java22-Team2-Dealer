package com.develhope.spring.repository;

import com.develhope.spring.entity.Amministratore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AministratoreRepository extends JpaRepository<Amministratore, Long> {
}
