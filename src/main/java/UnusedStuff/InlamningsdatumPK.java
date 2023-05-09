/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UnusedStuff;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author alexc
 */
@Embeddable
public class InlamningsdatumPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "lanNr")
    private int lanNr;
    @Basic(optional = false)
    @Column(name = "artikelNr")
    private int artikelNr;

    public InlamningsdatumPK() {
    }

    public InlamningsdatumPK(int lanNr, int artikelNr) {
        this.lanNr = lanNr;
        this.artikelNr = artikelNr;
    }

    public int getLanNr() {
        return lanNr;
    }

    public void setLanNr(int lanNr) {
        this.lanNr = lanNr;
    }

    public int getArtikelNr() {
        return artikelNr;
    }

    public void setArtikelNr(int artikelNr) {
        this.artikelNr = artikelNr;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) lanNr;
        hash += (int) artikelNr;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InlamningsdatumPK)) {
            return false;
        }
        InlamningsdatumPK other = (InlamningsdatumPK) object;
        if (this.lanNr != other.lanNr) {
            return false;
        }
        if (this.artikelNr != other.artikelNr) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.biblitekdb1.InlamningsdatumPK[ lanNr=" + lanNr + ", artikelNr=" + artikelNr + " ]";
    }
    
}
