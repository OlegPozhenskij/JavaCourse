package teamscore.pozhenskij2;

import org.json.JSONArray;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class MOEXSecuritiesSearchTest {
    @Test
    public void testSearchMOEXSecurities() {
        // Тестирование поиска ценных бумаг на MOEX
        String query = "Yandex"; // Ваш запрос для поиска

        JSONArray securities = MOEXSecuritiesSearch.searchMOEXSecurities(query);

        assertNotNull(securities);
        assertTrue(securities.length() > 0);
    }

    @Test
    public void testSaveSecuritiesToCSV() {
        // Тестирование сохранения ценных бумаг в CSV файл
        String query = "Yandex"; // Ваш запрос для поиска

        JSONArray securities = MOEXSecuritiesSearch.searchMOEXSecurities(query);

        assertNotNull(securities);
        assertTrue(securities.length() > 0);

        String fileName = query + ".csv";

        MOEXSecuritiesSearch.saveSecuritiesToCSV(query, securities);
        assertTrue(new java.io.File(fileName).exists());
    }
}