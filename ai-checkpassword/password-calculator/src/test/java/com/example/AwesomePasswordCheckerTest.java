package com.example;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

public class AwesomePasswordCheckerTest {

    @Test
    public void testGetInstanceFromFile() throws IOException {
        File testFile = new File("test_clusters.csv");

        AwesomePasswordChecker checker = AwesomePasswordChecker.getInstance(testFile);

        assertNotNull(checker, "L'instance ne doit pas être nulle.");
    }

    @Test
    public void testGetDistance() throws IOException {
        AwesomePasswordChecker checker = AwesomePasswordChecker.getInstance();

        String password = "MotDePasse123!";
        double distance = checker.getDIstance(password);
        assertTrue(distance >= 0, "La distance doit être un nombre positif.");
    }
    @Test
    public void testMaskAff() throws IOException {
        AwesomePasswordChecker checker = AwesomePasswordChecker.getInstance();

        String password = "MotDePasse123!";
        int[] mask = checker.maskAff(password);
        assertEquals(28, mask.length, "Le masque doit contenir exactement 28 éléments.");
        assertEquals(4, mask[0], "Le premier caractère (M majuscule) devrait être assigné à 4.");
        assertEquals(5, mask[1], "Le deuxième caractère (o minuscule) devrait être assigné à 2.");
    }
    @Test
    public void testComputeMD5() {
        String input = "MotDePasse123!";
        String md5Hash = AwesomePasswordChecker.ComputeMD5(input);
        assertTrue(md5Hash.matches("[a-f0-9]{32}"), "Le hachage MD5 doit être une chaîne de 32 caractères hexadécimaux.");
    }
}
