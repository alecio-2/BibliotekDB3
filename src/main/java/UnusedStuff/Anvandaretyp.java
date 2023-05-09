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
@Table(name = "anvandaretyp")
@NamedQueries({
    @NamedQuery(name = "Anvandaretyp.findAll", query = "SELECT a FROM Anvandaretyp a"),
    @NamedQuery(name = "Anvandaretyp.findByAnvandareTyp", query = "SELECT a FROM Anvandaretyp a WHERE a.anvandareTyp = :anvandareTyp"),
    @NamedQuery(name = "Anvandaretyp.findByAntalLaneGrans", query = "SELECT a FROM Anvandaretyp a WHERE a.antalLaneGrans = :antalLaneGrans")})
public class Anvandaretyp implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "anvandareTyp")
    private String anvandareTyp;
    @Basic(optional = false)
    @Column(name = "antalLaneGrans")
    private short antalLaneGrans;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "anvandareTyp")
    private Collection<Anvandare> anvandareCollection;

    public Anvandaretyp() {
    }

    public Anvandaretyp(String anvandareTyp) {
        this.anvandareTyp = anvandareTyp;
    }

    public Anvandaretyp(String anvandareTyp, short antalLaneGrans) {
        this.anvandareTyp = anvandareTyp;
        this.antalLaneGrans = antalLaneGrans;
    }

    public String getAnvandareTyp() {
        return anvandareTyp;
    }

    public void setAnvandareTyp(String anvandareTyp) {
        this.anvandareTyp = anvandareTyp;
    }

    public short getAntalLaneGrans() {
        return antalLaneGrans;
    }

    public void setAntalLaneGrans(short antalLaneGrans) {
        this.antalLaneGrans = antalLaneGrans;
    }

    public Collection<Anvandare> getAnvandareCollection() {
        return anvandareCollection;
    }

    public void setAnvandareCollection(Collection<Anvandare> anvandareCollection) {
        this.anvandareCollection = anvandareCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (anvandareTyp != null ? anvandareTyp.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Anvandaretyp)) {
            return false;
        }
        Anvandaretyp other = (Anvandaretyp) object;
        if ((this.anvandareTyp == null && other.anvandareTyp != null) || (this.anvandareTyp != null && !this.anvandareTyp.equals(other.anvandareTyp))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.biblitekdb1.Anvandaretyp[ anvandareTyp=" + anvandareTyp + " ]";
    }
    
}
