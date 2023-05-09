/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UnusedStuff;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author alexc
 */
@Entity
@Table(name = "lanartikel")
@NamedQueries({
    @NamedQuery(name = "Lanartikel.findAll", query = "SELECT l FROM Lanartikel l"),
    @NamedQuery(name = "Lanartikel.findByLanNr", query = "SELECT l FROM Lanartikel l WHERE l.lanartikelPK.lanNr = :lanNr"),
    @NamedQuery(name = "Lanartikel.findByArtikelNr", query = "SELECT l FROM Lanartikel l WHERE l.lanartikelPK.artikelNr = :artikelNr"),
    @NamedQuery(name = "Lanartikel.findByLaneDatum", query = "SELECT l FROM Lanartikel l WHERE l.laneDatum = :laneDatum"),
    @NamedQuery(name = "Lanartikel.findByForfalloDatum", query = "SELECT l FROM Lanartikel l WHERE l.forfalloDatum = :forfalloDatum")})
public class Lanartikel implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected LanartikelPK lanartikelPK;
    @Basic(optional = false)
    @Column(name = "laneDatum")
    @Temporal(TemporalType.DATE)
    private Date laneDatum;
    @Basic(optional = false)
    @Column(name = "forfalloDatum")
    @Temporal(TemporalType.DATE)
    private Date forfalloDatum;
    @JoinColumn(name = "artikelNr", referencedColumnName = "artikelNr", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Artikel artikel;
    @JoinColumn(name = "lanNr", referencedColumnName = "lanNr", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Lan lan;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "lanartikel")
    private Inlamningsdatum inlamningsdatum;

    public Lanartikel() {
    }

    public Lanartikel(LanartikelPK lanartikelPK) {
        this.lanartikelPK = lanartikelPK;
    }

    public Lanartikel(LanartikelPK lanartikelPK, Date laneDatum, Date forfalloDatum) {
        this.lanartikelPK = lanartikelPK;
        this.laneDatum = laneDatum;
        this.forfalloDatum = forfalloDatum;
    }

    public Lanartikel(int lanNr, int artikelNr) {
        this.lanartikelPK = new LanartikelPK(lanNr, artikelNr);
    }

    public LanartikelPK getLanartikelPK() {
        return lanartikelPK;
    }

    public void setLanartikelPK(LanartikelPK lanartikelPK) {
        this.lanartikelPK = lanartikelPK;
    }

    public Date getLaneDatum() {
        return laneDatum;
    }

    public void setLaneDatum(Date laneDatum) {
        this.laneDatum = laneDatum;
    }

    public Date getForfalloDatum() {
        return forfalloDatum;
    }

    public void setForfalloDatum(Date forfalloDatum) {
        this.forfalloDatum = forfalloDatum;
    }

    public Artikel getArtikel() {
        return artikel;
    }

    public void setArtikel(Artikel artikel) {
        this.artikel = artikel;
    }

    public Lan getLan() {
        return lan;
    }

    public void setLan(Lan lan) {
        this.lan = lan;
    }

    public Inlamningsdatum getInlamningsdatum() {
        return inlamningsdatum;
    }

    public void setInlamningsdatum(Inlamningsdatum inlamningsdatum) {
        this.inlamningsdatum = inlamningsdatum;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (lanartikelPK != null ? lanartikelPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Lanartikel)) {
            return false;
        }
        Lanartikel other = (Lanartikel) object;
        if ((this.lanartikelPK == null && other.lanartikelPK != null) || (this.lanartikelPK != null && !this.lanartikelPK.equals(other.lanartikelPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.biblitekdb1.Lanartikel[ lanartikelPK=" + lanartikelPK + " ]";
    }
    
}
