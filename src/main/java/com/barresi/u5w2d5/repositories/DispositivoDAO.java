package com.barresi.u5w2d5.repositories;


import com.barresi.u5w2d5.entities.Dispositivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DispositivoDAO extends JpaRepository<Dispositivo, UUID> {
}
