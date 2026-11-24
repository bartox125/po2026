package systemplikow.model;

import org.junit.jupiter.api.Test;

import java.util.EnumSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static systemplikow.model.Uprawnienie.*;

class PrawaDostepuTest {

    @Test
    void prawaDomyslneWlascicielMaWszystko() {
        PrawaDostepu prawa = new PrawaDostepu();

        assertTrue(prawa.czyPozwala(ODCZYT, true));
        assertTrue(prawa.czyPozwala(ZAPIS, true));
        assertTrue(prawa.czyPozwala(WYKONANIE, true));
    }

    @Test
    void prawaDomyslnePozostaliMajaOdczytIWykonanie() {
        PrawaDostepu prawa = new PrawaDostepu();

        assertTrue(prawa.czyPozwala(ODCZYT, false));
        assertFalse(prawa.czyPozwala(ZAPIS, false));
        assertTrue(prawa.czyPozwala(WYKONANIE, false));
    }

    @Test
    void odbierzUsuwaUprawnienieTylkoZWybranegoZbioru() {
        PrawaDostepu prawa = new PrawaDostepu();

        prawa.odbierz(ODCZYT, true); // ODCZYT mają domyślnie OBA zbiory

        assertFalse(prawa.czyPozwala(ODCZYT, true));
        assertTrue(prawa.czyPozwala(ODCZYT, false)); // pozostali nietknięci
        assertTrue(prawa.czyPozwala(ZAPIS, true));
    }

    @Test
    void nadajDodajeUprawnienieTylkoDoWybranegoZbioru() {
        PrawaDostepu prawa = new PrawaDostepu();

        prawa.odbierz(ZAPIS, true); // teraz żaden zbiór nie ma ZAPIS
        prawa.nadaj(ZAPIS, false);

        assertTrue(prawa.czyPozwala(ZAPIS, false));
        assertEquals(EnumSet.allOf(Uprawnienie.class), prawa.getPrawaPozostalych());
        assertFalse(prawa.czyPozwala(ZAPIS, true)); // właściciel nietknięty
    }

    @Test
    void nadajIOdbierzSaIdempotentne() {
        PrawaDostepu prawa = new PrawaDostepu();

        prawa.nadaj(ODCZYT, false);
        prawa.nadaj(ODCZYT, false);
        assertTrue(prawa.czyPozwala(ODCZYT, false));

        prawa.odbierz(ZAPIS, false); // pozostali i tak nie mieli ZAPIS
        prawa.odbierz(ODCZYT, false);
        prawa.odbierz(ODCZYT, false);
        assertFalse(prawa.czyPozwala(ODCZYT, false));
    }

    @Test
    void moznaOdebracWszystkoIPrzywrocic() {
        PrawaDostepu prawa = new PrawaDostepu();

        for (Uprawnienie u : Uprawnienie.values()) {
            prawa.odbierz(u, true);
            prawa.odbierz(u, false);
        }
        for (Uprawnienie u : Uprawnienie.values()) {
            assertFalse(prawa.czyPozwala(u, true));
            assertFalse(prawa.czyPozwala(u, false));
        }
        assertTrue(prawa.getPrawaWlasciciela().isEmpty());
        assertTrue(prawa.getPrawaPozostalych().isEmpty());

        prawa.nadaj(WYKONANIE, true);
        assertTrue(prawa.czyPozwala(WYKONANIE, true));
        assertFalse(prawa.czyPozwala(WYKONANIE, false)); // pozostali nadal bez praw
    }

    @Test
    void getteryZwracajaKopieNiePozwalajaPsucStanu() {
        PrawaDostepu prawa = new PrawaDostepu();

        Set<Uprawnienie> wlasciciel = prawa.getPrawaWlasciciela();
        wlasciciel.clear();
        Set<Uprawnienie> pozostali = prawa.getPrawaPozostalych();
        pozostali.add(ZAPIS);

        assertTrue(prawa.czyPozwala(ZAPIS, true));
        assertFalse(prawa.czyPozwala(ZAPIS, false));
    }

    @Test
    void getteryOdzwierciedlajaZmianyPoNadajIOdbierz() {
        PrawaDostepu prawa = new PrawaDostepu();

        prawa.odbierz(WYKONANIE, true);
        prawa.nadaj(ZAPIS, false);

        assertEquals(EnumSet.of(ODCZYT, ZAPIS), prawa.getPrawaWlasciciela());
        assertEquals(EnumSet.allOf(Uprawnienie.class), prawa.getPrawaPozostalych());
    }

    @Test
    void toStringPokazujePrawaWFormacieUniksowym() {
        PrawaDostepu prawa = new PrawaDostepu();
        assertEquals("rwxr-x", prawa.toString());

        prawa.odbierz(WYKONANIE, true);
        prawa.nadaj(ZAPIS, false);
        assertEquals("rw-rwx", prawa.toString());

        prawa.odbierz(ODCZYT, false);
        prawa.odbierz(ZAPIS, false);
        prawa.odbierz(WYKONANIE, false);
        assertEquals("rw----", prawa.toString());
    }

    @Test
    void konstruktorKopiujacyKopiujeObaZbiory() {
        PrawaDostepu oryginal = new PrawaDostepu();
        oryginal.odbierz(WYKONANIE, true);
        oryginal.nadaj(ZAPIS, false);

        PrawaDostepu kopia = new PrawaDostepu(oryginal);

        assertEquals(oryginal.getPrawaWlasciciela(), kopia.getPrawaWlasciciela());
        assertEquals(oryginal.getPrawaPozostalych(), kopia.getPrawaPozostalych());
        assertEquals("rw-rwx", kopia.toString());
    }

    @Test
    void kopiaJestNiezaleznaOdOryginalu() {
        PrawaDostepu oryginal = new PrawaDostepu();
        PrawaDostepu kopia = new PrawaDostepu(oryginal);

        oryginal.odbierz(ZAPIS, true);
        oryginal.nadaj(ZAPIS, false);
        assertTrue(kopia.czyPozwala(ZAPIS, true));
        assertFalse(kopia.czyPozwala(ZAPIS, false));

        kopia.odbierz(ODCZYT, true);
        kopia.odbierz(ODCZYT, false);
        assertTrue(oryginal.czyPozwala(ODCZYT, true));
        assertTrue(oryginal.czyPozwala(ODCZYT, false));
    }

    @Test
    void konstruktorKopiujacyDlaPustychPrawDziala() {
        PrawaDostepu oryginal = new PrawaDostepu();
        for (Uprawnienie u : Uprawnienie.values()) {
            oryginal.odbierz(u, true);
            oryginal.odbierz(u, false);
        }

        PrawaDostepu kopia = new PrawaDostepu(oryginal);

        assertEquals("------", kopia.toString());
    }

    @Test
    void nullJakoPrawaWKonstruktorzeKopiujacymToNullPointerException() {
        assertThrows(NullPointerException.class, () -> new PrawaDostepu((PrawaDostepu) null));
    }

    @Test
    void nullJakoUprawnienieToNullPointerException() {
        PrawaDostepu prawa = new PrawaDostepu();

        assertThrows(NullPointerException.class, () -> prawa.czyPozwala(null, true));
        assertThrows(NullPointerException.class, () -> prawa.nadaj(null, true));
        assertThrows(NullPointerException.class, () -> prawa.odbierz(null, false));
    }
}
