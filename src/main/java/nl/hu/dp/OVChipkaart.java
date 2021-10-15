package nl.hu.dp;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.sql.Date;
import java.util.*;

@Entity
@Table(name = "ov_chipkaart")
public class OVChipkaart {
    @Id
    @Column(name = "kaart_nummer", nullable = false)
    private int id;

    @Column(name = "geldig_tot")
    private Date geldigTot;
    private Integer klasse;
    private Integer saldo;
    @ManyToOne
    @JoinColumn(name = "reiziger_id")
    private Reiziger reiziger_id;

    //https://javaee.github.io/javaee-spec/javadocs/javax/persistence/ManyToMany.html
    //ovchipkaart is de owning side hier zitten joins om product en ovchipkaart samen te voegen
    //deze joins zorgen dus voor de relationship die we nodig hebben
    //zie @ManyToMany van klasse Product
    @ManyToMany
    @JoinTable(name = "ov_chipkaart_product", joinColumns = @JoinColumn(name = "kaart_nummer")
            ,inverseJoinColumns = @JoinColumn(name = "product_nummer"))
    private List<Product> producten = new ArrayList<>();

    public OVChipkaart() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public OVChipkaart(int id, Date geldigTot, Integer klasse, Integer saldo) {
        this.id = id;
        this.geldigTot = geldigTot;
        this.klasse = klasse;
        this.saldo = saldo;
    }

    public int getKaartId() {
        return id;
    }

    public void setKaartId(int id) {
        this.id=id;
    }

    public Reiziger getReiziger() {
        return reiziger_id;
    }

    public void setReiziger(Reiziger reiziger) {
        this.reiziger_id = reiziger;
    }

    public Date getGeldigTot() {
        return geldigTot;
    }

    public void setGeldigTot(Date geldigTot) {
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
        if (reiziger_id != null && producten == null) {
            return "{kaartnummer: #" + id + " geldigTot: " + geldigTot + " klasse: " + klasse + " saldo: " + saldo +
                    " {ReizigerID: " + "#" + reiziger_id.getId() + "}";
        } else if (producten != null && reiziger_id != null) {
            return "{kaartnummer: #" + id + " geldigTot: " + geldigTot + " klasse: " + klasse + " saldo: " + saldo +
                    "\n Producten{" + product + "}" + " Reiziger{"+reiziger_id.getNaam()+"}";
        }

        return "{kaartnummer: #" + id + " geldigTot: " + geldigTot + " klasse: " + klasse + " saldo: " + saldo + "}";
    }
}
