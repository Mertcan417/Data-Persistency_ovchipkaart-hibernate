package nl.hu.dp;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "ov_chipkaart")
public class OVChipkaart {
    @Id
    @Column(name = "kaart_nummer", nullable = false)
    private Long id;

    @Column(name = "geldig_tot")
    private String geldigTot;
    private Integer klasse;
    private Integer saldo;
    @ManyToOne
    @JoinColumn(name = "reiziger_id")
    private Reiziger reiziger;

    @ManyToMany
    @JoinTable(name = "ov_chipkaart_product", joinColumns = @JoinColumn(name = "kaart_nummer")
            ,inverseJoinColumns = @JoinColumn(name = "product_nummer"))
    private List<Product> producten = new ArrayList<>();

    public OVChipkaart() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OVChipkaart(Long id, String geldigTot, Integer klasse, Integer saldo) {
        this.id = id;
        this.geldigTot = geldigTot;
        this.klasse = klasse;
        this.saldo = saldo;
    }

    public Long getKaartId() {
        return id;
    }

    public void setKaartId(Long id) {
        this.id=id;
    }

    public Reiziger getReiziger() {
        return reiziger;
    }

    public void setReiziger(Reiziger reiziger) {
        this.reiziger = reiziger;
    }

    public String getGeldigTot() {
        return geldigTot;
    }

    public void setGeldigTot(String geldigTot) {
        this.geldigTot = geldigTot;
    }

    public Integer getKlasse() {
        return klasse;
    }

    public void setKlasse(Integer klasse) {
        this.klasse = klasse;
    }

    public Integer getSaldo() {
        return saldo;
    }

    public void setSaldo(Integer saldo) {
        this.saldo = saldo;
    }

    public void voegProductToe(Product product) {
        producten.add(product);
    }

    public void verwijderProduct(Product product) {
        producten.remove(product);
    }

    public List<Product> getProducten() {
        return producten;
    }

    public String toString() {

        String product = "";

        for (Product p : producten) {
            product +=
                    "nummer #" + p.getProductNummer() +
                            " naam: " + p.getNaam() + " beschrijving: " + p.getBeschrijving() + " ";
        }
        if (reiziger != null && producten == null) {
            return "{kaartnummer: #" + id + " geldigTot: " + geldigTot + " klasse: " + klasse + " saldo: " + saldo +
                    " {ReizigerID: " + "#" + reiziger.getId() + "}";
        } else if (producten != null && reiziger != null) {
            return "{kaartnummer: #" + id + " geldigTot: " + geldigTot + " klasse: " + klasse + " saldo: " + saldo +
                    "\n Producten{" + product + "}" + " Reiziger{"+reiziger.getNaam()+"}";
        }

        return "{kaartnummer: #" + id + " geldigTot: " + geldigTot + " klasse: " + klasse + " saldo: " + saldo + "}";
    }
}
