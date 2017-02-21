package nl.kattekoop.domain;

import javax.persistence.*;

@Entity
@Table(name = "CATTERY")
public class Cattery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "NAAM")
    private String naam;
}
