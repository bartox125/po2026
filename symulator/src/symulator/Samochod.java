package symulator;

public class Samochod {
    Silnik silnik;
    SkrzyniaBiegow skrzynia;
    Pozycja pozycja;

    public Samochod(Silnik silnik, SkrzyniaBiegow skrzynia, Pozycja pozycja) {
        this.silnik = silnik;
        this.skrzynia = skrzynia;
        this.pozycja = pozycja;
    }

    void wlacz(){
        silnik.uruchom();
    }
    void wylacz(){
        silnik.zatrzymaj();
        skrzynia.zerujBieg();
    }

    @Override
    public String toString() {
        return "Samochod:\n"
                + "  " + silnik + "\n"
                + "  " + skrzynia + "\n"
                + "  " + skrzynia.sprzeglo + "\n"
                + "  Pozycja: " + pozycja.getPozycja();
    }
}
