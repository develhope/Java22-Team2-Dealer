package com.develhope.spring.repository;

import com.develhope.spring.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    @Query("SELECT v FROM Vehicle v WHERE LOWER(v.marca) LIKE LOWER(CONCAT('%', :partialMarca, '%'))")
    List<Vehicle> searchByMarca(@Param("partialMarca") String partialMarca);


}
