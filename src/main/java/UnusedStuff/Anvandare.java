/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UnusedStuff;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author alexc
 */
@Entity
@Table(name = "anvandare")
@NamedQueries({
    @NamedQuery(name = "Anvandare.findAll", query = "SELECT a FROM Anvandare a"),
    @NamedQuery(name = "Anvandare.findByAnvandareNr", query = "SELECT a FROM Anvandare a WHERE a.anvandareNr = :anvandareNr"),
    @NamedQuery(name = "Anvandare.findByFNamn", query = "SELECT a FROM Anvandare a WHERE a.fNamn = :fNamn"),
    @NamedQuery(name = "Anvandare.findByENamn", query = "SELECT a FROM Anvandare a WHERE a.eNamn = :eNamn"),
    @NamedQuery(name = "Anvandare.findByGatuNamn", query = "SELECT a FROM Anvandare a WHERE a.gatuNamn = :gatuNamn"),
    @NamedQuery(name = "Anvandare.findByGatuNr", query = "SELECT a FROM Anvandare a WHERE a.gatuNr = :gatuNr"),
    @NamedQuery(name = "Anvandare.findByPostNr", query = "SELECT a FROM Anvandare a WHERE a.postNr = :postNr"),
    @NamedQuery(name = "Anvandare.findByOrt", query = "SELECT a FROM Anvandare a WHERE a.ort = :ort"),
    @NamedQuery(name = "Anvandare.findByEmail", query = "SELECT a FROM Anvandare a WHERE a.email = :email"),
    @NamedQuery(name = "Anvandare.findByTelefonNr", query = "SELECT a FROM Anvandare a WHERE a.telefonNr = :telefonNr")})
public class Anvandare implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "anvandareNr")
    private Integer anvandareNr;
    @Basic(optional = false)
    @Column(name = "fNamn")
    private String fNamn;
    @Basic(optional = false)
    @Column(name = "eNamn")
    private String eNamn;
    @Column(name = "gatuNamn")
    private String gatuNamn;
    @Column(name = "gatuNr")
    private Short gatuNr;
    @Column(name = "postNr")
    private Integer postNr;
    @Column(name = "ort")
    private String ort;
    @Basic(optional = false)
    @Column(name = "email")
    private String email;
    @Column(name = "telefonNr")
    private String telefonNr;
    @JoinColumn(name = "anvandareTyp", referencedColumnName = "anvandareTyp")
    @ManyToOne(optional = false)
    private Anvandaretyp anvandareTyp;
    @OneToMany(mappedBy = "anvandareNr")
    private Collection<Lan> lanCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "anvandareNr")
    private Collection<Reservation> reservationCollection;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "anvandare")
    private Anstalld anstalld;

    public Anvandare() {
    }

    public Anvandare(Integer anvandareNr) {
        this.anvandareNr = anvandareNr;
    }

    public Anvandare(Integer anvandareNr, String fNamn, String eNamn, String email) {
        this.anvandareNr = anvandareNr;
        this.fNamn = fNamn;
        this.eNamn = eNamn;
        this.email = email;
    }

    public Integer getAnvandareNr() {
        return anvandareNr;
    }

    public void setAnvandareNr(Integer anvandareNr) {
        this.anvandareNr = anvandareNr;
    }

    public String getFNamn() {
        return fNamn;
    }

    public void setFNamn(String fNamn) {
        this.fNamn = fNamn;
    }

    public String getENamn() {
        return eNamn;
    }

    public void setENamn(String eNamn) {
        this.eNamn = eNamn;
    }

    public String getGatuNamn() {
        return gatuNamn;
    }

    public void setGatuNamn(String gatuNamn) {
        this.gatuNamn = gatuNamn;
    }

    public Short getGatuNr() {
        return gatuNr;
    }

    public void setGatuNr(Short gatuNr) {
        this.gatuNr = gatuNr;
    }

    public Integer getPostNr() {
        return postNr;
    }

    public void setPostNr(Integer postNr) {
        this.postNr = postNr;
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefonNr() {
        return telefonNr;
    }

    public void setTelefonNr(String telefonNr) {
        this.telefonNr = telefonNr;
    }

    public Anvandaretyp getAnvandareTyp() {
        return anvandareTyp;
    }

    public void setAnvandareTyp(Anvandaretyp anvandareTyp) {
        this.anvandareTyp = anvandareTyp;
    }

    public Collection<Lan> getLanCollection() {
        return lanCollection;
    }

    public void setLanCollection(Collection<Lan> lanCollection) {
        this.lanCollection = lanCollection;
    }

    public Collection<Reservation> getReservationCollection() {
        return reservationCollection;
    }

    public void setReservationCollection(Collection<Reservation> reservationCollection) {
        this.reservationCollection = reservationCollection;
    }

    public Anstalld getAnstalld() {
        return anstalld;
    }

    public void setAnstalld(Anstalld anstalld) {
        this.anstalld = anstalld;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (anvandareNr != null ? anvandareNr.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Anvandare)) {
            return false;
        }
        Anvandare other = (Anvandare) object;
        if ((this.anvandareNr == null && other.anvandareNr != null) || (this.anvandareNr != null && !this.anvandareNr.equals(other.anvandareNr))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.biblitekdb1.Anvandare[ anvandareNr=" + anvandareNr + " ]";
    }
    
}
