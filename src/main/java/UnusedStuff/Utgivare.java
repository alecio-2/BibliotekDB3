/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UnusedStuff;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author alexc
 */
@Entity
@Table(name = "utgivare")
@NamedQueries({
    @NamedQuery(name = "Utgivare.findAll", query = "SELECT u FROM Utgivare u"),
    @NamedQuery(name = "Utgivare.findByArtikelNr", query = "SELECT u FROM Utgivare u WHERE u.artikelNr = :artikelNr"),
    @NamedQuery(name = "Utgivare.findByUtgivare", query = "SELECT u FROM Utgivare u WHERE u.utgivare = :utgivare")})
public class Utgivare implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "artikelNr")
    private Integer artikelNr;
    @Basic(optional = false)
    @Column(name = "utgivare")
    private String utgivare;
    @JoinColumn(name = "artikelNr", referencedColumnName = "artikelNr", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Artikel artikel;

    public Utgivare() {
    }

    public Utgivare(Integer artikelNr) {
        this.artikelNr = artikelNr;
    }

    public Utgivare(Integer artikelNr, String utgivare) {
        this.artikelNr = artikelNr;
        this.utgivare = utgivare;
    }

    public Integer getArtikelNr() {
        return artikelNr;
    }

    public void setArtikelNr(Integer artikelNr) {
        this.artikelNr = artikelNr;
    }

    public String getUtgivare() {
        return utgivare;
    }

    public void setUtgivare(String utgivare) {
        this.utgivare = utgivare;
    }

    public Artikel getArtikel() {
        return artikel;
    }

    public void setArtikel(Artikel artikel) {
        this.artikel = artikel;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (artikelNr != null ? artikelNr.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Utgivare)) {
            return false;
        }
        Utgivare other = (Utgivare) object;
        if ((this.artikelNr == null && other.artikelNr != null) || (this.artikelNr != null && !this.artikelNr.equals(other.artikelNr))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.biblitekdb1.Utgivare[ artikelNr=" + artikelNr + " ]";
    }
    
}
