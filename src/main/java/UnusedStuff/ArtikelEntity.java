package UnusedStuff;


import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "ARTIKEL", schema = "bibliotek", catalog = "")
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

public class ArtikelEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "artikelNr")
    private int artikelNr;
    @Basic
    @Column(name = "sab")
    private String sab;
    @Basic
    @Column(name = "titel")
    private String titel;
    @Basic
    @Column(name = "artist")
    private String artist;
    @Basic
    @Column(name = "utgava")
    private short utgava;
    @Basic
    @Column(name = "artikelGenre")
    private String artikelGenre;
    @Basic
    @Column(name = "artikelKategori")
    private String artikelKategori;
    @Basic
    @Column(name = "isbn")
    private String isbn;
    @Basic
    @Column(name = "statusTyp")
    private String statusTyp;

    public int getArtikelNr() {
        return artikelNr;
    }

    public void setArtikelNr(int artikelNr) {
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

    public String getArtikelKategori() {
        return artikelKategori;
    }

    public void setArtikelKategori(String artikelKategori) {
        this.artikelKategori = artikelKategori;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArtikelEntity that = (ArtikelEntity) o;
        return artikelNr == that.artikelNr && utgava == that.utgava && Objects.equals(sab, that.sab) && Objects.equals(titel, that.titel) && Objects.equals(artist, that.artist) && Objects.equals(artikelGenre, that.artikelGenre) && Objects.equals(artikelKategori, that.artikelKategori) && Objects.equals(isbn, that.isbn) && Objects.equals(statusTyp, that.statusTyp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(artikelNr, sab, titel, artist, utgava, artikelGenre, artikelKategori, isbn, statusTyp);
    }
}
