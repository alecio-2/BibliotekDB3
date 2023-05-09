/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UnusedStuff;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
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
@Table(name = "inlamningsdatum")
@NamedQueries({
    @NamedQuery(name = "Inlamningsdatum.findAll", query = "SELECT i FROM Inlamningsdatum i"),
    @NamedQuery(name = "Inlamningsdatum.findByLanNr", query = "SELECT i FROM Inlamningsdatum i WHERE i.inlamningsdatumPK.lanNr = :lanNr"),
    @NamedQuery(name = "Inlamningsdatum.findByArtikelNr", query = "SELECT i FROM Inlamningsdatum i WHERE i.inlamningsdatumPK.artikelNr = :artikelNr"),
    @NamedQuery(name = "Inlamningsdatum.findByInlamningsDatum", query = "SELECT i FROM Inlamningsdatum i WHERE i.inlamningsDatum = :inlamningsDatum")})
public class Inlamningsdatum implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected InlamningsdatumPK inlamningsdatumPK;
    @Column(name = "inlamningsDatum")
    @Temporal(TemporalType.DATE)
    private Date inlamningsDatum;
    @JoinColumns({
        @JoinColumn(name = "lanNr", referencedColumnName = "lanNr", insertable = false, updatable = false),
        @JoinColumn(name = "artikelNr", referencedColumnName = "artikelNr", insertable = false, updatable = false)})
    @OneToOne(optional = false)
    private Lanartikel lanartikel;

    public Inlamningsdatum() {
    }

    public Inlamningsdatum(InlamningsdatumPK inlamningsdatumPK) {
        this.inlamningsdatumPK = inlamningsdatumPK;
    }

    public Inlamningsdatum(int lanNr, int artikelNr) {
        this.inlamningsdatumPK = new InlamningsdatumPK(lanNr, artikelNr);
    }

    public InlamningsdatumPK getInlamningsdatumPK() {
        return inlamningsdatumPK;
    }

    public void setInlamningsdatumPK(InlamningsdatumPK inlamningsdatumPK) {
        this.inlamningsdatumPK = inlamningsdatumPK;
    }

    public Date getInlamningsDatum() {
        return inlamningsDatum;
    }

    public void setInlamningsDatum(Date inlamningsDatum) {
        this.inlamningsDatum = inlamningsDatum;
    }

    public Lanartikel getLanartikel() {
        return lanartikel;
    }

    public void setLanartikel(Lanartikel lanartikel) {
        this.lanartikel = lanartikel;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (inlamningsdatumPK != null ? inlamningsdatumPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Inlamningsdatum)) {
            return false;
        }
        Inlamningsdatum other = (Inlamningsdatum) object;
        if ((this.inlamningsdatumPK == null && other.inlamningsdatumPK != null) || (this.inlamningsdatumPK != null && !this.inlamningsdatumPK.equals(other.inlamningsdatumPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.biblitekdb1.Inlamningsdatum[ inlamningsdatumPK=" + inlamningsdatumPK + " ]";
    }
    
}
