package nl.hu.dp;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "reiziger")
public class Reiziger {
    @Id
    @Column(name = "reiziger_id", nullable = false)
    private int reiziger_id;
    private String voorletters;
    private String tussenvoegsel;
    private String achternaam;
    private Date geboortedatum;

    //voor assosiatie met klasse Adres en ovChipkaarten
    @OneToOne
    @JoinColumn(name = "reiziger_id")
    private Adres adres;
    @OneToMany
    @JoinColumn(name = "reiziger_id")
    private List<OVChipkaart> ovChipkaarten = new ArrayList<>();

    public Reiziger() {

    }

    public int getId() {
        return this.reiziger_id;
    }

    public void setId(int id) {
        this.reiziger_id = id;
    }


    public Reiziger(int id, String voorletters, String tussenvoegsel, String achternaam, Date geboortedatum) {
        this.reiziger_id = id;
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

    public void setGeboortedatum(Date gd) {
        geboortedatum = gd;
    }

    public Date getGeboortedatum() {
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
            return "Reiziger {#" + reiziger_id + " " + getNaam() + " (" + geboortedatum + ")} Ovchipkaarten " + chipKaarten + "";
        }

        return "Reiziger {#" + reiziger_id + " " + getNaam() + " " + geboortedatum + ", Adres {" + adres + "} Ovchipkaarten " + chipKaarten + "";
    }
}

