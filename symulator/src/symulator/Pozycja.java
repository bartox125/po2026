package symulator;

public class Pozycja {
    double x=0;
    double y=0;

    void przemiesc(double dt,double V,Pozycja cel){
        double dx=cel.x-this.x;
        double dy=cel.y-this.y;
        double odleglosc = Math.sqrt(Math.pow(dx,2) + Math.pow(dy,2));
        double krok=V*dt;

        if (odleglosc <= krok) {
            this.x = cel.x;
            this.y = cel.y;
        } else {
            this.x += dx / odleglosc * krok;
            this.y += dy / odleglosc * krok;
        }
    }
    String getPozycja(){
        return "(" + x + ", " + y + ")";
    }
}
