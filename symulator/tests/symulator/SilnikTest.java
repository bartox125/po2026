package symulator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SilnikTest {

    private static Silnik silnik(int maxObroty) {
        return new Silnik("BMW", "N57", maxObroty);
    }

    @Test
    void uruchomUstawiaObrotyBieguJalowego() {
        Silnik s = silnik(7000);

        s.uruchom();

        assertEquals(1000, s.obroty);
    }

    @Test
    void uruchomPoZatrzymaniuPonownieUstawia1000() {
        Silnik s = silnik(7000);
        s.uruchom();
        s.zatrzymaj();

        s.uruchom();

        assertEquals(1000, s.obroty);
    }

    @Test
    void uruchomNaPracujacymSilnikuWracaDo1000() {
        Silnik s = silnik(7000);
        s.uruchom();
        s.zwiekszObroty();
        s.zwiekszObroty();

        s.uruchom();

        assertEquals(1000, s.obroty);
    }

    @Test
    void zatrzymajPoUruchomieniuZerujeObroty() {
        Silnik s = silnik(7000);
        s.uruchom();

        s.zatrzymaj();

        assertEquals(0, s.obroty);
    }

    @Test
    void zatrzymajPrzyWysokichObrotachZerujeObroty() {
        Silnik s = silnik(7000);
        s.uruchom();
        for (int i = 0; i < 10; i++) {
            s.zwiekszObroty();
        }

        s.zatrzymaj();

        assertEquals(0, s.obroty);
    }

    @Test
    void zatrzymajZatrzymanySilnikZostawiaZero() {
        Silnik s = silnik(7000);

        s.zatrzymaj();

        assertEquals(0, s.obroty);
    }

    @Test
    void zwiekszObrotyDodaje100() {
        Silnik s = silnik(7000);
        s.uruchom();

        s.zwiekszObroty();

        assertEquals(1100, s.obroty);
    }

    @Test
    void zwiekszObrotyKilkaRazySumujeSie() {
        Silnik s = silnik(7000);
        s.uruchom();

        for (int i = 0; i < 5; i++) {
            s.zwiekszObroty();
        }

        assertEquals(1500, s.obroty);
    }

    @Test
    void zwiekszObrotyNiePrzekraczaMaksimum() {
        Silnik s = silnik(1050);
        s.uruchom();

        s.zwiekszObroty();

        assertEquals(1000, s.obroty);
    }

    @Test
    void zmniejszObrotyOdejmuje100() {
        Silnik s = silnik(7000);
        s.uruchom();

        s.zmniejszObroty();

        assertEquals(900, s.obroty);
    }

    @Test
    void zmniejszObrotyNaZatrzymanymSilnikuZostawiaZero() {
        Silnik s = silnik(7000);

        s.zmniejszObroty();

        assertEquals(0, s.obroty);
    }

    @Test
    void zmniejszObrotyNieSchodziPonizej100() {
        Silnik s = silnik(7000);
        s.uruchom();

        for (int i = 0; i < 20; i++) {
            s.zmniejszObroty();
        }

        assertEquals(100, s.obroty);
    }
}
