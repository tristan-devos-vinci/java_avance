package domaine;

import exceptions.DateDejaPresenteException;
import exceptions.PrixNonDisponibleException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ProduitTest {

    Prix prixAucune;
    Prix prixPub;
    Prix prixSolde;
    Produit produit1;
    Produit produit2;

    @BeforeEach
    void setUp() {
        prixAucune = new Prix();
        prixAucune.definirPrix(1, 20);
        prixAucune.definirPrix(10, 10);

        prixPub = new Prix(TypePromo.PUB, 30);
        prixPub.definirPrix(3, 15);

        prixSolde = new Prix(TypePromo.SOLDE, 50);

        produit1 = new Produit("Jeans Vert", "One Green Elephant", "F4");
        produit2 = new Produit("Jeans Rose", "One Green Elephant", "Y8");
        produit2.ajouterPrix(LocalDate.of(2019, 1, 3), prixPub);
        produit2.ajouterPrix(LocalDate.of(2019, 2, 3), prixSolde);
        produit2.ajouterPrix(LocalDate.of(2019, 3, 2), prixAucune);
    }

    @Test
    void testConstructor() {
        assertThrows(IllegalArgumentException.class, () -> new Produit(null, "One Green Elephant", "F4"));
        assertThrows(IllegalArgumentException.class, () -> new Produit("aaa", null, "F4"));

    }

    @Test
    void testGettersProduit() {
        assertAll(
                () -> assertEquals("Jeans Rose", produit2.getNom()),
                () -> assertEquals("One Green Elephant", produit2.getMarque()),
                () -> assertEquals("Y8", produit2.getRayon())
        );
    }

    @Test
    void testajouterPrix1(){
        assertThrows(IllegalArgumentException.class, () -> produit1.ajouterPrix(LocalDate.of(2019, 1, 3), null));
        assertThrows(IllegalArgumentException.class, () -> produit2.ajouterPrix(null, prixPub));
    }
    @Test
    void testajouterPrix3(){
        LocalDate date = LocalDate.of(2023, 10, 1);
        Prix prix = new Prix(TypePromo.SOLDE, 25);
        produit1.ajouterPrix(date, prix);
        assertEquals(prix, produit1.getPrix(date));
    }

    @Test
    void testEarlierDate(){
        LocalDate earlierDate = LocalDate.of(2018, 12, 31);
        assertThrows(PrixNonDisponibleException.class, () -> produit2.getPrix(earlierDate));
    }

    @Test
    void testPasDePrix(){
        assertThrows(PrixNonDisponibleException.class, () -> produit1.getPrix(LocalDate.of(2019, 1, 3)));
    }

    @Test
    void testPrixEntreDates(){
        Produit produit = new Produit("Jeans Vert", "One Green Elephant", "F4");
        produit.ajouterPrix(LocalDate.of(2019, 1, 3), prixPub);
        produit.ajouterPrix(LocalDate.of(2019, 1, 5), prixSolde);
        assertEquals(prixPub, produit.getPrix(LocalDate.of(2019, 1, 4)));
    }

    @Test
    void testequalsProduit1(){
        Produit produit1 = new Produit("Jeans Vert", "One Green Elephant", "F4");
        Produit produit2 = new Produit("Jeans Vert", "One Green Elephant", "F4");
        assertEquals(produit1, produit2);
    }

    @Test
    void testequalsProduit2(){
        Produit produit1 = new Produit("Jeans Rouge", "One Green Elephant", "F4");
        Produit produit2 = new Produit("Jeans Vert", "One Green Elephant", "F4");
        assertNotEquals(produit1, produit2);
    }

    @Test
    void testequalsProduit3(){
        Produit produit1 = new Produit("Jeans Rouge", "One Green", "F4");
        Produit produit2 = new Produit("Jeans Rouge", "One Green Elephant", "F4");
        assertNotEquals(produit1, produit2);
    }

    @Test
    void testequalsProduit4(){
        Produit produit1 = new Produit("Jeans Rouge", "One Green Elephant", "F2");
        Produit produit2 = new Produit("Jeans Rouge", "One Green Elephant", "F4");
        assertNotEquals(produit1, produit2);
    }

    @Test
    void testhashCode(){
        Produit produit1 = new Produit("Jeans Vert", "One Green Elephant", "F4");
        Produit produit2 = new Produit("Jeans Vert", "One Green Elephant", "F4");
        assertEquals(produit1.hashCode(), produit2.hashCode());
    }







    @Test
    void getMarque() {
    }

    @Test
    void getNom() {
    }

    @Test
    void getRayon() {
    }

    @Test
    void ajouterPrix() {
    }

    @Test
    void getPrix() {
    }

    @Test
    void testEquals() {
    }

    @Test
    void testHashCode() {
    }
}