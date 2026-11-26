package symulator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SkrzyniaBiegowTest {

    private static SkrzyniaBiegow skrzynia(int iloscBiegow) {
        return new SkrzyniaBiegow("BMW", "ZF 8HP", iloscBiegow, new Sprzeglo("Sachs", "SRE 240"));
    }

    // ===== zwiekszBieg =====

    @Test
    void zwiekszBiegZWcisnietymSprzeglemZwiekszaBiegOJeden() throws SprzegloException {
        SkrzyniaBiegow s = skrzynia(8);
        s.sprzeglo.wcisnij();

        s.zwiekszBieg();

        assertEquals(1, s.aktualnyBieg);
    }

    @Test
    void zwiekszBiegNaNajwyzszymBieguNicNieZmienia() throws SprzegloException {
        SkrzyniaBiegow s = skrzynia(2);
        s.sprzeglo.wcisnij();
        s.zwiekszBieg();
        s.zwiekszBieg();

        s.zwiekszBieg();

        assertEquals(2, s.aktualnyBieg);
    }

    @Test
    void zwiekszBiegBezSprzeglaRzucaWyjatek() {
        SkrzyniaBiegow s = skrzynia(8);

        SprzegloException e = assertThrows(SprzegloException.class, s::zwiekszBieg);

        assertEquals("Nie mozna zmienic biegu bez wcisnietego sprzegla", e.getMessage());
        assertEquals(0, s.aktualnyBieg);
    }

    // ===== zmniejszBieg =====

    @Test
    void zmniejszBiegZWcisnietymSprzeglemZmniejszaBiegOJeden() throws SprzegloException {
        SkrzyniaBiegow s = skrzynia(8);
        s.sprzeglo.wcisnij();
        s.zwiekszBieg();
        s.zwiekszBieg();

        s.zmniejszBieg();

        assertEquals(1, s.aktualnyBieg);
    }

    @Test
    void zmniejszBiegNaLuzieNicNieZmienia() throws SprzegloException {
        SkrzyniaBiegow s = skrzynia(8);
        s.sprzeglo.wcisnij();

        s.zmniejszBieg();

        assertEquals(0, s.aktualnyBieg);
    }

    @Test
    void zmniejszBiegBezSprzeglaRzucaWyjatekIZostawiaBieg() throws SprzegloException {
        SkrzyniaBiegow s = skrzynia(8);
        s.sprzeglo.wcisnij();
        s.zwiekszBieg();
        s.sprzeglo.zwolnij();

        assertThrows(SprzegloException.class, s::zmniejszBieg);

        assertEquals(1, s.aktualnyBieg);
    }

    // ===== zerujBieg =====

    @Test
    void zerujBiegUstawiaLuz() throws SprzegloException {
        SkrzyniaBiegow s = skrzynia(8);
        s.sprzeglo.wcisnij();
        s.zwiekszBieg();
        s.zwiekszBieg();
        s.zwiekszBieg();

        s.zerujBieg();

        assertEquals(0, s.aktualnyBieg);
    }

    @Test
    void zerujBiegNaLuzieZostawiaLuz() {
        SkrzyniaBiegow s = skrzynia(8);

        s.zerujBieg();

        assertEquals(0, s.aktualnyBieg);
    }

    @Test
    void zerujBiegDzialaBezWcisnietegoSprzegla() throws SprzegloException {
        SkrzyniaBiegow s = skrzynia(8);
        s.sprzeglo.wcisnij();
        s.zwiekszBieg();
        s.zwiekszBieg();
        s.sprzeglo.zwolnij();

        assertDoesNotThrow(s::zerujBieg);

        assertEquals(0, s.aktualnyBieg);
    }
}
