import java.text.DateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Pacjent {

    private static final List<Pacjent> ekstensjaPacjent = new ArrayList<>();

    private final int idNumber;
    private String nazwisko;
    private String imie;
    private final String pesel;
    private final LocalDate dataUrodzenia;
    private List<Wizyta> wizyty = new ArrayList<>();

    public Pacjent(int idNumber, String nazwisko, String imie, String pesel, LocalDate dataUrodzenia) {
        this.idNumber = idNumber;
        this.nazwisko = nazwisko;
        this.imie = imie;
        this.pesel = pesel;
        this.dataUrodzenia = dataUrodzenia;
        ekstensjaPacjent.add(this);
    }

    public void dodajWizyte(Wizyta wizyta) {
        if (!wizyty.contains(wizyta)){
            wizyty.add(wizyta);
        }
    }

    public int getLiczbaWizyt() {
        return wizyty.size();
    }

    public boolean bylNaWizycie() {
        return wizyty.size() > 0;
    }

    public int getUIluBylLekarzy() {
        List<Lekarz> lekarzeUKtorychByl = new ArrayList<>();
        for (Wizyta wizyta : wizyty){
            if (!lekarzeUKtorychByl.contains(wizyta.getLekarz())) {
                lekarzeUKtorychByl.add(wizyta.getLekarz());
            }
        }
        return lekarzeUKtorychByl.size();
    }

    public static Pacjent getPacjentByID(int id) {
        for (Pacjent pacjent : ekstensjaPacjent) {
            if (pacjent.idNumber == id) {
                return pacjent;
            }
        }
        throw new RuntimeException("Nie istnieje pacjent o takim ID");
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

    public String getPesel() {
        return pesel;
    }

    public LocalDate getDataUrodzenia() {
        return dataUrodzenia;
    }

    public List<Wizyta> getWizyty() {
        return wizyty;
    }

    @Override
    public String toString() {
        return "Pacjent: " + imie + " " + nazwisko + " " + dataUrodzenia + " " + pesel;
    }
}
