package nl.kattekoop.domain;

import javax.persistence.*;

@Entity
@Table(name = "ADVERTENTIE")
public class Advertentie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "TITEL")
    private String titel;

    @ManyToOne
    @JoinColumn(name = "CATTERY")
    private Cattery cattery;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public Cattery getCattery() {
        return cattery;
    }

    public void setCattery(Cattery cattery) {
        this.cattery = cattery;
    }
}
