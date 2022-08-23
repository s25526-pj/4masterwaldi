import java.time.LocalDate;
import java.time.Year;
import java.util.Date;

public class Wizyta {

    private final Lekarz lekarz;
    private final Pacjent pacjent;
    private final LocalDate data;

    private Wizyta(Lekarz lekarz, Pacjent pacjent, LocalDate data) {
        this.lekarz = lekarz;
        this.pacjent = pacjent;
        this.data = data;
    }

    public static Wizyta utworzWizyte(Lekarz lekarz, Pacjent pacjent, LocalDate data) {
        if (lekarz == null){
            throw new IllegalArgumentException("Nie można utworzyć wizyty bez lekarza");
        }
        if (pacjent == null){
            throw new IllegalArgumentException("Nie można utworzyć wizyty bez pacjenta");
        }
        if (data == null){
            throw new IllegalArgumentException("Nie można utworzyć wizyty bez daty");
        }
        Wizyta wizyta = new Wizyta(lekarz,pacjent,data);
        lekarz.dodajWizyte(wizyta);
        pacjent.dodajWizyte(wizyta);
        return wizyta;
    }

    public Lekarz getLekarz() {
        return lekarz;
    }

    public String getSpecjalizacja() {
        return lekarz.getSpecjalizacja();
    }

    public Pacjent getPacjent() {
        return pacjent;
    }

    public Year getYear() {
        return Year.of(data.getYear());
    }

    public LocalDate getData() {
        return data;
    }

    @Override
    public String toString() {
        return "Lekarz: " + lekarz.getImie() + " " + lekarz.getNazwisko() + " "
                + lekarz.getSpecjalizacja() + ", Pacjent: " + pacjent.getImie() + " "
                + pacjent.getNazwisko() + ", Data wizyty: " + data;

    }
}
