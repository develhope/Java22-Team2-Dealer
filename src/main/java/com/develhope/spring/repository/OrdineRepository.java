package com.develhope.spring.repository;
import com.develhope.spring.entity.Ordine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdineRepository extends JpaRepository<Ordine, Long> {
}
