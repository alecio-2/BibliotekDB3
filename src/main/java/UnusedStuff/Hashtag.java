/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UnusedStuff;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author alexc
 */
@Entity
@Table(name = "hashtag")
@NamedQueries({
    @NamedQuery(name = "Hashtag.findAll", query = "SELECT h FROM Hashtag h"),
    @NamedQuery(name = "Hashtag.findByHashtagNr", query = "SELECT h FROM Hashtag h WHERE h.hashtagNr = :hashtagNr"),
    @NamedQuery(name = "Hashtag.findByHashtagNamn", query = "SELECT h FROM Hashtag h WHERE h.hashtagNamn = :hashtagNamn")})
public class Hashtag implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "hashtagNr")
    private Integer hashtagNr;
    @Basic(optional = false)
    @Column(name = "hashtagNamn")
    private String hashtagNamn;
    @ManyToMany(mappedBy = "hashtagCollection")
    private Collection<Artikel> artikelCollection;

    public Hashtag() {
    }

    public Hashtag(Integer hashtagNr) {
        this.hashtagNr = hashtagNr;
    }

    public Hashtag(Integer hashtagNr, String hashtagNamn) {
        this.hashtagNr = hashtagNr;
        this.hashtagNamn = hashtagNamn;
    }

    public Integer getHashtagNr() {
        return hashtagNr;
    }

    public void setHashtagNr(Integer hashtagNr) {
        this.hashtagNr = hashtagNr;
    }

    public String getHashtagNamn() {
        return hashtagNamn;
    }

    public void setHashtagNamn(String hashtagNamn) {
        this.hashtagNamn = hashtagNamn;
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
        hash += (hashtagNr != null ? hashtagNr.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Hashtag)) {
            return false;
        }
        Hashtag other = (Hashtag) object;
        if ((this.hashtagNr == null && other.hashtagNr != null) || (this.hashtagNr != null && !this.hashtagNr.equals(other.hashtagNr))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.biblitekdb1.Hashtag[ hashtagNr=" + hashtagNr + " ]";
    }
    
}
