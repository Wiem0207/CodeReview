package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import java.io.InputStream;

public class AwesomePasswordCheckerTest {

    private AwesomePasswordChecker passwordChecker;

    @BeforeEach
    public void setUp() throws IOException {
        // Initialisation de l'instance avec un fichier de test
        InputStream is = getClass().getClassLoader().getResourceAsStream("cluster_centers_HAC_aff.csv");
        if (is == null) {
            throw new IOException("Fichier de test introuvable");
        }
        passwordChecker = new AwesomePasswordChecker(is);
    }

    @Test
    public void testGetInstance() throws IOException {
        // Test de la méthode getInstance() avec un fichier existant
        InputStream is = getClass().getClassLoader().getResourceAsStream("cluster_centers_HAC_aff.csv");
        assertNotNull(is, "Le fichier de données n'a pas été trouvé.");
        AwesomePasswordChecker instance = AwesomePasswordChecker.getInstance(is);
        assertNotNull(instance, "L'instance de AwesomePasswordChecker ne doit pas être nulle.");
    }

    @Test
    public void testMaskAff() {
        // Test de la méthode maskAff pour une chaîne de mot de passe
        String password = "password123!";
        int[] expectedMask = new int[]{2, 2, 2, 2, 2, 2, 5, 5, 5, 5, 6, 7, 2, 2, 5, 5, 6, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7};
        int[] mask = passwordChecker.maskAff(password);
        assertArrayEquals(expectedMask, mask, "Le masque généré ne correspond pas aux attentes.");
    }

    @Test
    public void testGetDistance() {
        // Test de la méthode getDistance qui calcule la distance euclidienne minimale
        String password = "password123!";
        double distance = passwordChecker.getDIstance(password);
        assertTrue(distance >= 0, "La distance calculée ne doit pas être négative.");
    }

    @Test
    public void testComputeMD5() {
        // Test de la méthode ComputeMD5 pour vérifier la validité du hash MD5
        String input = "password123!";
        String expectedMD5 = "bcb3b8b6fc3c5cc31abf3cba7ff5ef04";  // Le hash attendu (à ajuster selon le calcul MD5 réel)
        String actualMD5 = AwesomePasswordChecker.ComputeMD5(input);
        assertEquals(expectedMD5, actualMD5, "Le hash MD5 calculé ne correspond pas à l'attendu.");
    }
}

