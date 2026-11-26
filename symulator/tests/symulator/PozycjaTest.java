package symulator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PozycjaTest {

    private static Pozycja pozycja(double x, double y) {
        Pozycja p = new Pozycja();
        p.x = x;
        p.y = y;
        return p;
    }

    @Test
    void przemiescRobiJedenKrokWStroneCelu() {
        Pozycja p = pozycja(0, 0);

        p.przemiesc(1.0, 10.0, pozycja(30, 40));

        assertEquals(6.0, p.x);
        assertEquals(8.0, p.y);
    }

    @Test
    void przemiescNiePrzeskakujeCelu() {
        Pozycja p = pozycja(0, 0);

        p.przemiesc(1.0, 100.0, pozycja(3, 4));

        assertEquals(3.0, p.x);
        assertEquals(4.0, p.y);
    }

    @Test
    void przemiescPrzyZerowejPredkosciNieRuszaSamochodu() {
        Pozycja p = pozycja(1, 2);

        p.przemiesc(1.0, 0.0, pozycja(30, 40));

        assertEquals(1.0, p.x);
        assertEquals(2.0, p.y);
    }

    @Test
    void przemiescDzialaWKierunkuUjemnychWspolrzednych() {
        Pozycja p = pozycja(0, 0);

        p.przemiesc(1.0, 5.0, pozycja(-30, -40));

        assertEquals(-3.0, p.x);
        assertEquals(-4.0, p.y);
    }
}
