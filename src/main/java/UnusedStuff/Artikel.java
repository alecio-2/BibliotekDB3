package UnusedStuff;/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author alexc
 */
@Entity
@Table(name = "artikel")
@NamedQueries({
    @NamedQuery(name = "Artikel.findAll", query = "SELECT a FROM Artikel a"),
    @NamedQuery(name = "Artikel.findByArtikelNr", query = "SELECT a FROM Artikel a WHERE a.artikelNr = :artikelNr"),
    @NamedQuery(name = "Artikel.findBySab", query = "SELECT a FROM Artikel a WHERE a.sab = :sab"),
    @NamedQuery(name = "Artikel.findByTitel", query = "SELECT a FROM Artikel a WHERE a.titel = :titel"),
    @NamedQuery(name = "Artikel.findByArtist", query = "SELECT a FROM Artikel a WHERE a.artist = :artist"),
    @NamedQuery(name = "Artikel.findByUtgava", query = "SELECT a FROM Artikel a WHERE a.utgava = :utgava"),
    @NamedQuery(name = "Artikel.findByArtikelGenre", query = "SELECT a FROM Artikel a WHERE a.artikelGenre = :artikelGenre"),
    @NamedQuery(name = "Artikel.findByIsbn", query = "SELECT a FROM Artikel a WHERE a.isbn = :isbn"),
    @NamedQuery(name = "Artikel.findByStatusTyp", query = "SELECT a FROM Artikel a WHERE a.statusTyp = :statusTyp"),
    @NamedQuery(name = "Artikel.findAllCustom", query = "SELECT a FROM Artikel a WHERE a.titel LIKE :searchTerm OR a.artist LIKE :searchTerm OR a.utgava LIKE :searchTerm OR a.Genre LIKE :searchTerm OR a.isbn LIKE :searchTerm OR a.statusTyp Like :searchTerm")})

public class Artikel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "artikelNr")
    private Integer artikelNr;
    @Basic(optional = false)
    @Column(name = "sab")
    private String sab;
    @Basic(optional = false)
    @Column(name = "titel")
    private String titel;
    @Basic(optional = false)
    @Column(name = "artist")
    private String artist;
    @Basic(optional = false)
    @Column(name = "utgava")
    private short utgava;
    @Basic(optional = false)
    @Column(name = "artikelGenre")
    private String artikelGenre;
    @Basic(optional = false)
    @Column(name = "isbn")
    private String isbn;
    @Basic(optional = false)
    @Column(name = "statusTyp")
    private String statusTyp;
    @JoinTable(name = "hashtagartikel", joinColumns = {
        @JoinColumn(name = "artikelNr", referencedColumnName = "artikelNr")}, inverseJoinColumns = {
        @JoinColumn(name = "hashtagNr", referencedColumnName = "hashtagNr")})
    @ManyToMany
    private Collection<Hashtag> hashtagCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "artikel")
    private Collection<Lanartikel> lanartikelCollection;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "artikel")
    private Utgivare utgivare;
    @JoinColumn(name = "artikelKategori", referencedColumnName = "artikelKategori")
    @ManyToOne(optional = false)
    private Artkategori artikelKategori;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "artikelNr")
    private Collection<Reservation> reservationCollection;

    public Artikel() {
    }

    public Artikel(Integer artikelNr) {
        this.artikelNr = artikelNr;
    }

    public Artikel(Integer artikelNr, String sab, String titel, String artist, short utgava, String artikelGenre, String isbn, String statusTyp) {
        this.artikelNr = artikelNr;
        this.sab = sab;
        this.titel = titel;
        this.artist = artist;
        this.utgava = utgava;
        this.artikelGenre = artikelGenre;
        this.isbn = isbn;
        this.statusTyp = statusTyp;
    }

    public Integer getArtikelNr() {
        return artikelNr;
    }

    public void setArtikelNr(Integer artikelNr) {
        this.artikelNr = artikelNr;
    }

    public String getSab() {
        return sab;
    }

    public void setSab(String sab) {
        this.sab = sab;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public short getUtgava() {
        return utgava;
    }

    public void setUtgava(short utgava) {
        this.utgava = utgava;
    }

    public String getArtikelGenre() {
        return artikelGenre;
    }

    public void setArtikelGenre(String artikelGenre) {
        this.artikelGenre = artikelGenre;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getStatusTyp() {
        return statusTyp;
    }

    public void setStatusTyp(String statusTyp) {
        this.statusTyp = statusTyp;
    }

    public Collection<Hashtag> getHashtagCollection() {
        return hashtagCollection;
    }

    public void setHashtagCollection(Collection<Hashtag> hashtagCollection) {
        this.hashtagCollection = hashtagCollection;
    }

    public Collection<Lanartikel> getLanartikelCollection() {
        return lanartikelCollection;
    }

    public void setLanartikelCollection(Collection<Lanartikel> lanartikelCollection) {
        this.lanartikelCollection = lanartikelCollection;
    }

    public Utgivare getUtgivare() {
        return utgivare;
    }

    public void setUtgivare(Utgivare utgivare) {
        this.utgivare = utgivare;
    }

    public Artkategori getArtikelKategori() {
        return artikelKategori;
    }

    public void setArtikelKategori(Artkategori artikelKategori) {
        this.artikelKategori = artikelKategori;
    }

    public Collection<Reservation> getReservationCollection() {
        return reservationCollection;
    }

    public void setReservationCollection(Collection<Reservation> reservationCollection) {
        this.reservationCollection = reservationCollection;
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
        if (!(object instanceof Artikel)) {
            return false;
        }
        Artikel other = (Artikel) object;
        if ((this.artikelNr == null && other.artikelNr != null) || (this.artikelNr != null && !this.artikelNr.equals(other.artikelNr))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.biblitekdb1.Artikel[ artikelNr=" + artikelNr + " ]";
    }
    
}
