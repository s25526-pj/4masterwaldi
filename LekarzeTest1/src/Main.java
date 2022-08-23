import java.io.*;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    /*
    Dane są trzy pliki tekstowe o nazwach: lekarze.txt, pacjenci.txt, wizyty.txt.
Zawierają one informacje na temat lekarzy, pacjentów i odbytych wizyt domowych.
W każdym z plików dane w wierszu oddzielone są znakami tabulacji.
Plik o nazwie lekarze.txt zawiera informacje na temat lekarzy: numer identyfikacyjny
lekarza, jego nazwisko, imię, specjalność, datę urodzenia, numer NIP i numer PESEL.
Przykład:
23 Kadaj Monika pediatra 1965-03-16 879-122-69-94 65031687654
34 Nowak Anna nefrolog 1965-03-16 879-122-69-94 65031687654
Plik o nazwie pacjenci.txt zawiera dane na temat pacjentów: numer identyfikacyjny
pacjenta, jego nazwisko, imię, numer PESEL i datę urodzenia.
Przykład:
122 Nowakowska Joanna 73050512356 1973-05-05
124 Witkowski Hubert 88030422345 1988-03-04
Plik o nazwie wizyty.txt zawiera informacje na temat domowych wizyt lekarskich
przeprowadzonych przez lekarzy u pacjentów: numer identyfikacyjny lekarza, numer
identyfikacyjny pacjenta oraz datę wizyty lekarskiej przeprowadzonej przez lekarza
u pacjenta.
Przykład:
23 124 2006-12-13
34 122 2007-02-20
Wykorzystując informacje zawarte w plikach wykonaj następujące polecenia:
- znajdź lekarza ktory miał najwięcej wizyt
- znajdź pacjenta który miał najwięcej wizyt
- która specalizacja cieszy się największym ppowodzeniem?
- którego roku było najwięcej wizyt?
- wypisz top 5 najstarszych lekarzy
- zwroc pacientow ktorzy byli u minumum 5ciu roznych lekarzy
- zwroc lekarzy exclusive - czyli takich ktorzy przyjmowali tylko jednego pacjenta
rozwiązania zrób w formie metod i klas, oceniane będzie podejście do problemu, poprawność rozwiażania, wytestowanie swojej aplikacji itp.
powodzenia :)
     */

    public static void main(String[] args) {

        File plikZLekarzami = new File("lekarze.txt");
        List<Lekarz> lekarze = wczytajLekarzy(wczytajDaneZPliku(plikZLekarzami));

        File plikZPacjentami = new File("pacjenci.txt");
        List<Pacjent> pacjenci = wczytajPacjentow(wczytajDaneZPliku(plikZPacjentami));

        File plikZWizytami = new File("wizyty.txt");
        List<Wizyta> wizyty = wczytajWizyty(wczytajDaneZPliku(plikZWizytami));

        System.out.println(getLekarzWithMostVisits(lekarze));
        System.out.println(getPacjentWithMostVisits(pacjenci));
        System.out.println(getMostPopularSpecialization(wizyty));
        System.out.println(getYearWithMostVisits(wizyty));
        /*
        Dwie metody poniżej wyrzucą błąd, ponieważ nie ma pacjentów, którzy odwiedzili 5 lub więcej lekarzy, oraz nie
        ma lekarzy którzy przyjeli tylko 1 pacjenta
        */
//        System.out.println(getPacjenciWhoVisitedMoreThanFiveDoctors(pacjenci));
//        System.out.println(getLekarzeExclusive(lekarze));
        System.out.println(getTopFiveOldestLekarz(lekarze));

    }

    private static List<String> wczytajDaneZPliku(File plik) {
        List<String> daneZPliku = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(plik));
            Stream<String> wierszePliku = reader.lines().skip(1);
            wierszePliku.forEachOrdered(czytanaLinia -> daneZPliku.add(czytanaLinia));
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return daneZPliku;
    }

    private static List<Lekarz> wczytajLekarzy(List<String> daneZPliku) {
        List<Lekarz> lekarze = new ArrayList<>();
        for (String string : daneZPliku) {
            String[] daneLekarza = string.split("\t");
            lekarze.add(new Lekarz(Integer.parseInt(daneLekarza[0]),
                    daneLekarza[1],
                    daneLekarza[2],
                    daneLekarza[3],
                    LocalDate.parse(daneLekarza[4]),
                    daneLekarza[5],
                    new BigInteger(daneLekarza[6])));
        }
        return lekarze;
    }

    private static List<Pacjent> wczytajPacjentow(List<String> daneZPliku) {
        List<Pacjent> pacjenci = new ArrayList<>();
        for (String string : daneZPliku){
            String[] danePacjenta = string.split("\t");
            pacjenci.add(new Pacjent(Integer.parseInt(danePacjenta[0]),
                    danePacjenta[1],
                    danePacjenta[2],
                    danePacjenta[3],
                    LocalDate.parse(danePacjenta[4],DateTimeFormatter.ofPattern("yyyy-M-d"))));
        }
        return pacjenci;
    }

    private static List<Wizyta> wczytajWizyty(List<String> daneZPliku){
        List<Wizyta> wizyty = new ArrayList<>();
        for (String string : daneZPliku){
            String[] daneWizyty = string.split("\t");
            int idLekarza = Integer.parseInt(daneWizyty[0]);
            int idPacjenta = Integer.parseInt(daneWizyty[1]);
            LocalDate dataWizyty = LocalDate.parse(daneWizyty[2],DateTimeFormatter.ofPattern("yyyy-M-d"));
            wizyty.add(Wizyta.utworzWizyte(Lekarz.getLekarzById(idLekarza),Pacjent.getPacjentByID(idPacjenta),dataWizyty));
        }
        return wizyty;
    }

    private static Lekarz getLekarzWithAnyVisit(List<Lekarz> lekarze) {
        for (Lekarz lekarz : lekarze){
            if (lekarz.mialWizyte()){
                return lekarz;
            }
        }
        throw new RuntimeException("Żaden lekarz z listy nie miał wizyty");
    }

    private static Lekarz getLekarzWithMostVisits(List<Lekarz> lekarze) {
        Lekarz lekarzWithMostVisits = getLekarzWithAnyVisit(lekarze);
        for (Lekarz lekarz : lekarze){
            if (lekarzWithMostVisits.getLiczbaWizyt() < lekarz.getLiczbaWizyt()){
                lekarzWithMostVisits = lekarz;
            }
        }
        return lekarzWithMostVisits;
    }

    private static Pacjent getPacjentWithAnyVisit(List<Pacjent> pacjenci){
        for (Pacjent pacjent : pacjenci){
            if (pacjent.bylNaWizycie()){
                return pacjent;
            }
        }
        throw new RuntimeException("Żaden pacjent z listy nie był na wizycie");
    }

    private static Pacjent getPacjentWithMostVisits(List<Pacjent> pacjenci) {
        Pacjent pacjentWithMostVisits = getPacjentWithAnyVisit(pacjenci);
        for (Pacjent pacjent : pacjenci){
            if (pacjentWithMostVisits.getLiczbaWizyt() < pacjent.getLiczbaWizyt()){
                pacjentWithMostVisits = pacjent;
            }
        }
        return pacjentWithMostVisits;
    }

    private static String getMostPopularSpecialization(List<Wizyta> wizyty) {
        Map<String,Integer> numberOfVisitsForSpecialization = new HashMap<>();
        for (Wizyta wizyta : wizyty){
            if (!numberOfVisitsForSpecialization.containsKey(wizyta.getSpecjalizacja())){
                numberOfVisitsForSpecialization.put(wizyta.getSpecjalizacja(),1);
            }
            else {
                numberOfVisitsForSpecialization.put(wizyta.getSpecjalizacja(),
                        numberOfVisitsForSpecialization.get(wizyta.getSpecjalizacja())+1);
            }
        }
        return Collections.max(numberOfVisitsForSpecialization.entrySet(),Map.Entry.comparingByValue()).getKey();
    }

    private static Year getYearWithMostVisits(List<Wizyta> wizyty) {
        Map<Year,Integer> numberOfVisitsForEachYear = new HashMap<>();
        for (Wizyta wizyta : wizyty){
            if (!numberOfVisitsForEachYear.containsKey(wizyta.getYear())){
                numberOfVisitsForEachYear.put(wizyta.getYear(),1);
            }
            else {
                numberOfVisitsForEachYear.put(wizyta.getYear(),numberOfVisitsForEachYear.get(wizyta.getYear()) + 1);
            }
        }
        return Collections.max(numberOfVisitsForEachYear.entrySet(),Map.Entry.comparingByValue()).getKey();
    }

    //Do tej metody można dodać drugi parametr (int), który będzie mówił Top ilu lekarzy zwrócić

    private static List<Lekarz> getTopFiveOldestLekarz(List<Lekarz> lekarze) {
        List<Lekarz> topFiveOldestLekarz;
        topFiveOldestLekarz = lekarze.stream()
                .sorted(Comparator.comparing(Lekarz::getWiek).reversed())
                .limit(5)
                .collect(Collectors.toList());
        return topFiveOldestLekarz;
    }

    //Do tej metody można dodać drugi parametr (int), który będzie mówił u ile lekarzy miał być pacjent

    private static List<Pacjent> getPacjenciWhoVisitedFiveOrMoreLekarz(List<Pacjent> pacjenci) {
        List<Pacjent> pacjenciKtorzyByliUMinimumPiecuLekarzy = new ArrayList<>();
        for (Pacjent pacjent : pacjenci) {
            if (pacjent.getUIluBylLekarzy() >= 5) {
                pacjenciKtorzyByliUMinimumPiecuLekarzy.add(pacjent);
            }
        }
        if (pacjenciKtorzyByliUMinimumPiecuLekarzy.isEmpty()) {
            throw new RuntimeException("Żaden z pacjentów nie był u minimum 5 lekarzy");
        }
        return pacjenciKtorzyByliUMinimumPiecuLekarzy;
    }

    private static List<Lekarz> getLekarzeExclusive(List<Lekarz> lekarze) {
        List<Lekarz> lekarzeExclusive = new ArrayList<>();
        for (Lekarz lekarz : lekarze) {
            if (lekarz.isExclusive()) {
                lekarzeExclusive.add(lekarz);
            }
        }
        if (lekarzeExclusive.isEmpty()) {
            throw new RuntimeException("Żaden z lekarzy nie przyjął tylko jednego pacjenta");
        }
        return lekarzeExclusive;
    }

}
