package nl.hu.dp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @Column(name = "product_nummer", nullable = false)
    private Long id;

    private String naam;
    private String beschrijving;
    private Double prijs;
    @ManyToMany
    @JoinTable(name = "ov_chipkaart_product", joinColumns = {@JoinColumn(name = "product_nummer")},
            inverseJoinColumns = @JoinColumn(name = "kaart_nummer"))
    private List<OVChipkaart> ovChipkaarten = new ArrayList<>();

    public Product() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product(Long productNummer, String naam, String beschrijving, Double prijs) {
        this.id = productNummer;
        this.naam = naam;
        this.beschrijving = beschrijving;
        this.prijs = prijs;
    }

    public Long getProductNummer() {
        return id;
    }

    public void setProductNummer(Long productNummer) {
        this.id = productNummer;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public void setBeschrijving(String beschrijving) {
        this.beschrijving = beschrijving;
    }

    public Double getPrijs() {
        return prijs;
    }

    public void setPrijs(Double prijs) {
        this.prijs = prijs;
    }

    public void voegOVChipkaartToe(OVChipkaart ovChipkaart) {
        if (ovChipkaart != null) {
            ovChipkaarten.add(ovChipkaart);
        }
    }

    public void verwijderOVchipkaart(OVChipkaart ovChipkaart) {
        ovChipkaarten.remove(ovChipkaart);
    }

    public List<OVChipkaart> getOvChipkaarten() {
        return ovChipkaarten;
    }

    @Override
    public String toString() {
        String ovchipkaart = "";
        for (OVChipkaart o : ovChipkaarten) {
            ovchipkaart += "kaartnummer: #" + o.getKaartId() +
                    " geldigTot: " + o.getGeldigTot() + " klasse: " + o.getKlasse() + " saldo: "
                    + o.getSaldo();

        }
        String s = "Product{nummer: #" + id + " naam: " + naam + " beschrijving: " + beschrijving + "";

        if (ovChipkaarten != null) {
            s += "\nOVChipkaarten{" + ovchipkaart + "}";
        }

        return s;
    }
}
