package domaine;

import exceptions.QuantiteNonAutoriseeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class PrixTest {
    Prix prixAucune;
    Prix prixPub;
    Prix prixSolde;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        prixAucune = new Prix();
        prixAucune.definirPrix(1, 20);
        prixAucune.definirPrix(10, 10);

        prixPub = new Prix(TypePromo.PUB, 30);
        prixPub.definirPrix(3, 15);

        prixSolde = new Prix(TypePromo.SOLDE, 50);
    }

    @Test
    @DisplayName("Test du type de promotion pour prixPub")
    void getTypePromoPrixAucune() {
        assertNull(prixAucune.getTypePromo());
    }
    @Test
    @DisplayName("Test du type de promotion pour prixPub")
    void getTypePromoPrixPub() {
        assertEquals(TypePromo.PUB, prixPub.getTypePromo());
    }

    @Test
    @DisplayName("Test du type de promotion")
    void getTypePromoPrixSolde() {
        assertEquals(TypePromo.SOLDE, prixSolde.getTypePromo());
    }

    @Test
    @DisplayName("Test de la valeur de la promotion pour prixPub")
    void getValeurPromo() {
        assertEquals(30, prixPub.getValeurPromo());
    }

    @Test
    @DisplayName("Test de la définition du prix pour prixAucune")
    void definirPrix() {
        assertEquals(20, prixAucune.getPrix(1));
    }

    @Test
    @DisplayName("Test de la récupération du prix pour prixAucune")
    void getPrix() {
        assertEquals(20, prixAucune.getPrix(1));

    }



    @Test
    @DisplayName("Constructor")
    void TestConstructor() {
        assertThrows(IllegalArgumentException.class, () -> new Prix(null, 0));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1, -2, -4, -132})
    @DisplayName("Constructor")
    void PrixInvalidValues(int inva) {
        assertThrows(IllegalArgumentException.class, () -> new Prix(TypePromo.PUB, inva));
    }

    @Test
    @DisplayName("Test getters1")
    void TestGetters1() {
        Prix prixGetters = new Prix();
        assertEquals(0, prixGetters.getValeurPromo());
    }

    @Test
    @DisplayName("Test getters2")
    void TestGetters2() {
        Prix prixGetters = new Prix(TypePromo.PUB, 3);
        assertEquals(3, prixGetters.getValeurPromo());
    }
    @Test
    @DisplayName("Test getters3")
    void TestGetters3() {
        Prix prixGetters = new Prix();
        assertNull(prixGetters.getTypePromo());
    }
    @Test
    @DisplayName("Test getters4")
    void TestGetters4() {
        Prix prixGetters = new Prix(TypePromo.SOLDE, 7);
        assertEquals(TypePromo.SOLDE, prixGetters.getTypePromo());
    }

    @Test
    @DisplayName("Test definirPrix 1")
    void TestDefinirPrix1() {
        Prix prixDefinir = new Prix();
        assertThrows(IllegalArgumentException.class, () -> prixDefinir.definirPrix(-1, 1));
    }

    @Test
    @DisplayName("Test definirPrix2")
    void TestDefinirPrix2() {
        Prix prixDefinir = new Prix();
        assertThrows(IllegalArgumentException.class, () -> prixDefinir.definirPrix(1, -1));
    }


    // Définissez un prix de 6 euros à partir de 10 unités pour l’attribut prixAucune et vérifiez
    //que l’ancien prix a été remplacé.

    @Test
    @DisplayName("Test definirPrix 3")
    void TestDefinirPrix3() {
        prixAucune.definirPrix(10, 6);
        assertEquals(6, prixAucune.getPrix(10));
    }

    @Test
    @DisplayName("Test getPrix 1")
    void TestGetPrix1() {
        Prix prixGet = new Prix();
        assertThrows(IllegalArgumentException.class, () -> prixGet.getPrix(-1));
    }
    @ParameterizedTest
    @ValueSource(ints = {1, 5, 9})
    @DisplayName("Test getPrix 2")
    void TestGetPrix2(int val) {
        assertEquals(20, prixAucune.getPrix(val));
    }

    @Test
    @DisplayName("Test getPrix 3")
    void TestGetPrix3() {
        assertThrows(QuantiteNonAutoriseeException.class, () -> prixPub.getPrix(2));
    }

    @Test
    @DisplayName("Test getPrix 4")
    void TestGetPrix4() {
        assertThrows(QuantiteNonAutoriseeException.class, () -> prixSolde.getPrix(1));
    }







}