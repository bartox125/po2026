package symulator;

public class SkrzyniaBiegow extends Komponent{
    int aktualnyBieg;
    int iloscBiegow;
    int przelozenie;
    Sprzeglo sprzeglo;

    public SkrzyniaBiegow(String producent, String model, int iloscBiegow,Sprzeglo sprzeglo) {
        super(producent, model);
        this.iloscBiegow = Math.max(1, iloscBiegow);
        this.przelozenie=0;
        this.aktualnyBieg = 0;
        this.sprzeglo = sprzeglo;
    }

    void zwiekszBieg() throws SprzegloException {
        sprawdzSprzeglo();
        if(aktualnyBieg<iloscBiegow){
            aktualnyBieg++;
        }
    }
    void zmniejszBieg() throws SprzegloException {
        sprawdzSprzeglo();
        if(aktualnyBieg>0){
            aktualnyBieg--;
        }
    }
    private void sprawdzSprzeglo() throws SprzegloException {
        if(!sprzeglo.stanSprzegla){
            throw new SprzegloException("Nie mozna zmienic biegu bez wcisnietego sprzegla");
        }
    }
    void zerujBieg(){
        aktualnyBieg=0;
    }

    @Override
    public String toString() {
        return "Skrzynia " + producent + " " + model + ": bieg " + aktualnyBieg + "/" + iloscBiegow;
    }
}
