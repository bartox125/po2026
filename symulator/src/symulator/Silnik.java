package symulator;

public class Silnik extends Komponent{
    int maxObroty;
    int obroty;

    public Silnik(String producent, String model, int maxObroty) {
        super(producent, model);
        this.maxObroty = Math.max(0, maxObroty);
        this.obroty = 0;
    }

    void uruchom(){
        this.obroty=1000;
    }
    void zatrzymaj(){
        this.obroty=0;
    }

    void zwiekszObroty(){
        if(obroty+100<this.maxObroty){
            this.obroty+=100;
        }
    }
    void zmniejszObroty(){
        if(obroty>100){
            this.obroty-=100;
        }
    }

    @Override
    public String toString() {
        return "Silnik " + producent + " " + model + ": " + obroty + "/" + maxObroty + " obr/min";
    }
}
