/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UnusedStuff;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author alexc
 */
@Entity
@Table(name = "reservation")
@NamedQueries({
    @NamedQuery(name = "Reservation.findAll", query = "SELECT r FROM Reservation r"),
    @NamedQuery(name = "Reservation.findByReservationNr", query = "SELECT r FROM Reservation r WHERE r.reservationNr = :reservationNr"),
    @NamedQuery(name = "Reservation.findByReservationDatum", query = "SELECT r FROM Reservation r WHERE r.reservationDatum = :reservationDatum")})
public class Reservation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "reservationNr")
    private Integer reservationNr;
    @Basic(optional = false)
    @Column(name = "reservationDatum")
    @Temporal(TemporalType.DATE)
    private Date reservationDatum;
    @JoinColumn(name = "anvandareNr", referencedColumnName = "anvandareNr")
    @ManyToOne(optional = false)
    private Anvandare anvandareNr;
    @JoinColumn(name = "artikelNr", referencedColumnName = "artikelNr")
    @ManyToOne(optional = false)
    private Artikel artikelNr;

    public Reservation() {
    }

    public Reservation(Integer reservationNr) {
        this.reservationNr = reservationNr;
    }

    public Reservation(Integer reservationNr, Date reservationDatum) {
        this.reservationNr = reservationNr;
        this.reservationDatum = reservationDatum;
    }

    public Integer getReservationNr() {
        return reservationNr;
    }

    public void setReservationNr(Integer reservationNr) {
        this.reservationNr = reservationNr;
    }

    public Date getReservationDatum() {
        return reservationDatum;
    }

    public void setReservationDatum(Date reservationDatum) {
        this.reservationDatum = reservationDatum;
    }

    public Anvandare getAnvandareNr() {
        return anvandareNr;
    }

    public void setAnvandareNr(Anvandare anvandareNr) {
        this.anvandareNr = anvandareNr;
    }

    public Artikel getArtikelNr() {
        return artikelNr;
    }

    public void setArtikelNr(Artikel artikelNr) {
        this.artikelNr = artikelNr;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (reservationNr != null ? reservationNr.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reservation)) {
            return false;
        }
        Reservation other = (Reservation) object;
        if ((this.reservationNr == null && other.reservationNr != null) || (this.reservationNr != null && !this.reservationNr.equals(other.reservationNr))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.biblitekdb1.Reservation[ reservationNr=" + reservationNr + " ]";
    }
    
}
