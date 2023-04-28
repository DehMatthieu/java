package com.epf.rentmanager.util;

import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.service.ClientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ClientServiceTest {

    /**
     * Renvoie true si l’utilisateur passé en paramètre a un age >= 18 ans
     * @param "client" L'instance d’utilisateur à tester
     * @return Résultat du test (>= 18 ans)
     */
    @Test
    public void isLegal_should_return_true_when_age_is_over_18() {
        // Given
        Client client = new Client("John", "Doe", "john.doe@ensta.fr", LocalDate.parse("2001-12-12"));

        // Then
        assertTrue(Client.isLegal(client));
    }

    @Test
    public void isLegal_should_return_false_when_age_is_under_18() {
        // Given
        Client client = new Client("John", "Doe", "john.doe@ensta.fr", LocalDate.parse("2008-12-12"));

        // Then
        assertFalse(Client.isLegal(client));
    }
}


