package dev.edvanronchi.springbootautomatedtest.domain.repositories;

import dev.edvanronchi.springbootautomatedtest.domain.entities.Bhaskara;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BhaskaraRepository extends JpaRepository<Bhaskara, Long> {
}
