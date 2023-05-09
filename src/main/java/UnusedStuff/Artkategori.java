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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author alexc
 */
@Entity
@Table(name = "artkategori")
@NamedQueries({
    @NamedQuery(name = "Artkategori.findAll", query = "SELECT a FROM Artkategori a"),
    @NamedQuery(name = "Artkategori.findByArtikelKategori", query = "SELECT a FROM Artkategori a WHERE a.artikelKategori = :artikelKategori"),
    @NamedQuery(name = "Artkategori.findByTidLaneGrans", query = "SELECT a FROM Artkategori a WHERE a.tidLaneGrans = :tidLaneGrans")})
public class Artkategori implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "artikelKategori")
    private String artikelKategori;
    @Basic(optional = false)
    @Column(name = "tidLaneGrans")
    private int tidLaneGrans;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "artikelKategori")
    private Collection<Artikel> artikelCollection;

    public Artkategori() {
    }

    public Artkategori(String artikelKategori) {
        this.artikelKategori = artikelKategori;
    }

    public Artkategori(String artikelKategori, int tidLaneGrans) {
        this.artikelKategori = artikelKategori;
        this.tidLaneGrans = tidLaneGrans;
    }

    public String getArtikelKategori() {
        return artikelKategori;
    }

    public void setArtikelKategori(String artikelKategori) {
        this.artikelKategori = artikelKategori;
    }

    public int getTidLaneGrans() {
        return tidLaneGrans;
    }

    public void setTidLaneGrans(int tidLaneGrans) {
        this.tidLaneGrans = tidLaneGrans;
    }

    public Collection<Artikel> getArtikelCollection() {
        return artikelCollection;
    }

    public void setArtikelCollection(Collection<Artikel> artikelCollection) {
        this.artikelCollection = artikelCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (artikelKategori != null ? artikelKategori.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Artkategori)) {
            return false;
        }
        Artkategori other = (Artkategori) object;
        if ((this.artikelKategori == null && other.artikelKategori != null) || (this.artikelKategori != null && !this.artikelKategori.equals(other.artikelKategori))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.biblitekdb1.Artkategori[ artikelKategori=" + artikelKategori + " ]";
    }
    
}
