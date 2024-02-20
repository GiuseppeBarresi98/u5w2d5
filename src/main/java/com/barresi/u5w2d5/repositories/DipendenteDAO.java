package com.barresi.u5w2d5.repositories;

import com.barresi.u5w2d5.entities.Dipendente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DipendenteDAO extends JpaRepository<Dipendente, UUID> {

    Optional<Dipendente> findByEmail(String email);
}
