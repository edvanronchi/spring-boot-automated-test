package dev.edvanronchi.springbootautomatedtest.domain.repositories;

import dev.edvanronchi.springbootautomatedtest.domain.entities.CalculoSimples;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalculoSimplesRepository extends JpaRepository<CalculoSimples, Long> {
}
