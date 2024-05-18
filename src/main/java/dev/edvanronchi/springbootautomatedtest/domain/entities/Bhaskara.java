package dev.edvanronchi.springbootautomatedtest.domain.entities;

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
@Table(name = "bhaskara")
public class Bhaskara {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "a")
    private double a;

    @Column(name = "b")
    private double b;

    @Column(name = "c")
    private double c;

    @Column(name = "x1")
    private double x1;

    @Column(name = "x2")
    private double x2;

    public Bhaskara() {

    }
}
