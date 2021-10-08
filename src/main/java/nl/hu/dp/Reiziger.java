package nl.hu.dp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "reiziger")
public class Reiziger {
    @Id
    @Column(name = "reiziger_id", nullable = false)
    private Long id;
    private String voorletters;
    private String tussenvoegsel;
    private String achternaam;
    private String geboortedatum;

    //voor assosiatie met klasse Adres en ovChipkaarten
    @OneToOne
    @JoinColumn(name = "reiziger_id")
    private Adres adres;
    @OneToMany
    @JoinColumn(name = "reiziger_id")
    private List<OVChipkaart> ovChipkaarten = new ArrayList<>();

    public Reiziger() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Reiziger(Long id, String voorletters, String tussenvoegsel, String achternaam, String geboortedatum) {
        this.id = id;
        this.voorletters = voorletters;
        this.tussenvoegsel = tussenvoegsel;
        this.achternaam = achternaam;
        this.geboortedatum = geboortedatum;
    }

    public void setAdres(Adres adres) {
        this.adres = adres;
    }

    public Adres getAdres() {
        return adres;
    }

    public String getVoorletters() {
        return voorletters;
    }

    public void setVoorletters(String vl) {
        voorletters = vl;
    }

    public String getTussenvoegsel() {
        return tussenvoegsel;
    }

    public void setTussenvoegsel(String tv) {
        tussenvoegsel = tv;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public void setAchternaam(String an) {
        achternaam = an;
    }

    public void setGeboortedatum(String gd) {
        geboortedatum = gd;
    }

    public String getGeboortedatum() {
        return geboortedatum;
    }

    public String getNaam() {
        if (tussenvoegsel == null) {
            tussenvoegsel = "";
            return voorletters + " " + tussenvoegsel + " " + achternaam;
        }
        return voorletters + " " + tussenvoegsel + " " + achternaam;
    }

    public void voegOvChipkaartToe(OVChipkaart ovChipkaart) {
        this.ovChipkaarten.add(ovChipkaart);
    }

    public void verwijderOvChipkaart(OVChipkaart ovChipkaart) {
        this.ovChipkaarten.remove(ovChipkaart);
    }


    public List<OVChipkaart> getOvChipkaarten() {
        return ovChipkaarten;
    }


    public String toString() {

        String chipKaarten = "";
        for (OVChipkaart ovChipkaart : getOvChipkaarten()) {
            chipKaarten += ovChipkaart + "\n";
        }

        if (adres == null) {
            return "Reiziger {#" + id + " " + getNaam() + " (" + geboortedatum + ")} Ovchipkaarten " + chipKaarten + "";
        }

        return "Reiziger {#" + id + " " + getNaam() + " " + geboortedatum + ", Adres {" + adres + "} Ovchipkaarten " + chipKaarten + "";
    }
}

