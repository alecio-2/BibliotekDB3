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
@Table(name = "anstalld")
@NamedQueries({
    @NamedQuery(name = "Anstalld.findAll", query = "SELECT a FROM Anstalld a"),
    @NamedQuery(name = "Anstalld.findByAnvandareNr", query = "SELECT a FROM Anstalld a WHERE a.anvandareNr = :anvandareNr"),
    @NamedQuery(name = "Anstalld.findByAnstalldTyp", query = "SELECT a FROM Anstalld a WHERE a.anstalldTyp = :anstalldTyp")})
public class Anstalld implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "anvandareNr")
    private Integer anvandareNr;
    @Basic(optional = false)
    @Column(name = "anstalldTyp")
    private String anstalldTyp;
    @JoinColumn(name = "anvandareNr", referencedColumnName = "anvandareNr", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Anvandare anvandare;

    public Anstalld() {
    }

    public Anstalld(Integer anvandareNr) {
        this.anvandareNr = anvandareNr;
    }

    public Anstalld(Integer anvandareNr, String anstalldTyp) {
        this.anvandareNr = anvandareNr;
        this.anstalldTyp = anstalldTyp;
    }

    public Integer getAnvandareNr() {
        return anvandareNr;
    }

    public void setAnvandareNr(Integer anvandareNr) {
        this.anvandareNr = anvandareNr;
    }

    public String getAnstalldTyp() {
        return anstalldTyp;
    }

    public void setAnstalldTyp(String anstalldTyp) {
        this.anstalldTyp = anstalldTyp;
    }

    public Anvandare getAnvandare() {
        return anvandare;
    }

    public void setAnvandare(Anvandare anvandare) {
        this.anvandare = anvandare;
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
        if (!(object instanceof Anstalld)) {
            return false;
        }
        Anstalld other = (Anstalld) object;
        if ((this.anvandareNr == null && other.anvandareNr != null) || (this.anvandareNr != null && !this.anvandareNr.equals(other.anvandareNr))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.biblitekdb1.Anstalld[ anvandareNr=" + anvandareNr + " ]";
    }
    
}
