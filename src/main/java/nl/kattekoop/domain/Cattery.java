package nl.kattekoop.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "CATTERY")
public class Cattery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "NAAM")
    private String naam;

    @OneToMany(mappedBy = "cattery", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Advertentie> advertenties;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public List<Advertentie> getAdvertenties() {
        if (advertenties == null) {
            advertenties = new ArrayList<>();
        }
        return advertenties;
    }

    public void setAdvertenties(List<Advertentie> advertenties) {
        this.advertenties = advertenties;
    }
}
