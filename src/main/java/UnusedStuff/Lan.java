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
import javax.persistence.Table;

/**
 *
 * @author alexc
 */
@Entity
@Table(name = "lan")
@NamedQueries({
    @NamedQuery(name = "Lan.findAll", query = "SELECT l FROM Lan l"),
    @NamedQuery(name = "Lan.findByLanNr", query = "SELECT l FROM Lan l WHERE l.lanNr = :lanNr")})
public class Lan implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "lanNr")
    private Integer lanNr;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lan")
    private Collection<Lanartikel> lanartikelCollection;
    @JoinColumn(name = "anvandareNr", referencedColumnName = "anvandareNr")
    @ManyToOne
    private Anvandare anvandareNr;

    public Lan() {
    }

    public Lan(Integer lanNr) {
        this.lanNr = lanNr;
    }

    public Integer getLanNr() {
        return lanNr;
    }

    public void setLanNr(Integer lanNr) {
        this.lanNr = lanNr;
    }

    public Collection<Lanartikel> getLanartikelCollection() {
        return lanartikelCollection;
    }

    public void setLanartikelCollection(Collection<Lanartikel> lanartikelCollection) {
        this.lanartikelCollection = lanartikelCollection;
    }

    public Anvandare getAnvandareNr() {
        return anvandareNr;
    }

    public void setAnvandareNr(Anvandare anvandareNr) {
        this.anvandareNr = anvandareNr;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (lanNr != null ? lanNr.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Lan)) {
            return false;
        }
        Lan other = (Lan) object;
        if ((this.lanNr == null && other.lanNr != null) || (this.lanNr != null && !this.lanNr.equals(other.lanNr))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.biblitekdb1.Lan[ lanNr=" + lanNr + " ]";
    }
    
}
