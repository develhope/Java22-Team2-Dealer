package com.develhope.spring.repository;
import com.develhope.spring.entity.OrdineAcquisto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdineAcquistoRepository extends JpaRepository<OrdineAcquisto, Long> {
}