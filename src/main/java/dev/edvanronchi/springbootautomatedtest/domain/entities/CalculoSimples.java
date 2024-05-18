package dev.edvanronchi.springbootautomatedtest.domain.entities;

import dev.edvanronchi.springbootautomatedtest.domain.enums.Operacao;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@Table(name = "calculos_simples")
public class CalculoSimples {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "a")
    private double a;

    @Column(name = "b")
    private double b;

    @Column(name = "operacao")
    private Operacao operacao;

    @Column(name = "resultado")
    private double resultado;

    public CalculoSimples() {

    }
}
