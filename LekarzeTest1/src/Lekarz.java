import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Lekarz {

    private static final List<Lekarz> ekstensjaLekarz = new ArrayList<>();

    private final int idNumber;
    private String nazwisko;
    private String imie;
    private String specjalizacja;
    private final LocalDate dataUrodzenia;
    private final String nip;
    private final BigInteger pesel;
    private List<Wizyta> wizyty = new ArrayList<>();

    public Lekarz(int idNumber, String nazwisko, String imie, String specjalizacja, LocalDate dataUrodzenia, String nip, BigInteger pesel) {
        this.idNumber = idNumber;
        this.nazwisko = nazwisko;
        this.imie = imie;
        this.specjalizacja = specjalizacja;
        this.dataUrodzenia = dataUrodzenia;
        this.nip = nip;
        this.pesel = pesel;
        ekstensjaLekarz.add(this);
    }

    public void dodajWizyte(Wizyta wizyta) {
        if (!wizyty.contains(wizyta)){
            wizyty.add(wizyta);
        }
    }

    public boolean isExclusive() {
        return wizyty.size() == 1;
    }

    public int getLiczbaWizyt() {
        return wizyty.size();
    }

    public boolean mialWizyte() {
        return wizyty.size() > 0;
    }

    public int getWiek() {
        return LocalDate.now().getYear() - dataUrodzenia.getYear();
    }

    public static Lekarz getLekarzById(int id) {
        for (Lekarz lekarz : ekstensjaLekarz) {
            if (lekarz.idNumber == id) {
                return lekarz;
            }
        }
        throw new RuntimeException("Nie ma lekarza o takim ID");
    }

    public int getIdNumber() {
        return idNumber;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getSpecjalizacja() {
        return specjalizacja;
    }

    public void setSpecjalizacja(String specjalizacja) {
        this.specjalizacja = specjalizacja;
    }

    public LocalDate getDataUrodzenia() {
        return dataUrodzenia;
    }

    public String getNip() {
        return nip;
    }

    public BigInteger getPesel() {
        return pesel;
    }

    public List<Wizyta> getWizyty() {
        return wizyty;
    }

    @Override
    public String toString() {
        return "Lekarz: " + imie + " " + " " + nazwisko + " " + specjalizacja + " " +nip;
    }
}
