package br.ce.wcaquino.taskbackend.utils;

import org.junit.Test;

import java.time.LocalDate;

import static br.ce.wcaquino.taskbackend.utils.DateUtils.isEqualOrFutureDate;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DateUtilsTest {


    @Test
    public void testIsEqualOrFutureDate_WithEqualDate() {
        LocalDate today = LocalDate.now();
        assertTrue(isEqualOrFutureDate(today)); // Espera verdadeiro para a data de hoje
    }

    @Test
    public void testIsEqualOrFutureDate_WithFutureDate() {
        LocalDate futureDate = LocalDate.now().plusDays(1);
        assertTrue(isEqualOrFutureDate(futureDate)); // Espera verdadeiro para uma data no futuro
    }

    @Test
    public void testIsEqualOrFutureDate_WithPastDate() {
        LocalDate pastDate = LocalDate.now().minusDays(1);
        assertFalse(isEqualOrFutureDate(pastDate)); // Espera falso para uma data no passado
    }
}
