package symulator;

public class Main {
    static void main(String[] args){
        Silnik silnik = new Silnik("BMW","N57",7000);
        Sprzeglo sprzeglo = new Sprzeglo("Sachs","SRE 240");
        SkrzyniaBiegow skrzynia = new SkrzyniaBiegow("BMW","ZF 8HP",8,sprzeglo);
        Pozycja pozycja = new Pozycja();

        Samochod autko = new Samochod(silnik,skrzynia,pozycja);
        System.out.println(autko);

        autko.wlacz();
        System.out.println("\nPo wlaczeniu:\n" + autko);

        try {
            autko.skrzynia.sprzeglo.wcisnij();
            autko.skrzynia.zwiekszBieg();
            autko.skrzynia.zwiekszBieg();
            autko.skrzynia.zmniejszBieg();
            System.out.println("\nPo zmianie biegow (sprzeglo wcisniete):\n" + autko);

            autko.skrzynia.sprzeglo.zwolnij();
            autko.skrzynia.zwiekszBieg(); // rzuci wyjatek
        } catch (SprzegloException e) {
            System.out.println("\nBlad: " + e.getMessage());
        }

        autko.wylacz();
        System.out.println("\nPo wylaczeniu:\n" + autko);
    }
}
