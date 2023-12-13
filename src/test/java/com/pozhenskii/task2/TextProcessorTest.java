package com.pozhenskii.task2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TextProcessorTest {

    @Test
    void testProcessText() {
        // Тестовые данные
        String[] inputTexts = {
                "",
                "Да; и; но ((4))",
                "Добрый день!",
                "сине-зеленый",
                "Чашка кофе с молоком без сахара.",
                "Эх раз, да еще раз, да еще много-много раз!",
                "Lorem ipsum dolor sit amet, consectetur\n" +
                        "adipiscing elit, sed do eiusmod tempor\n" +
                        "incididunt ut labore et dolore magna aliqua. Ut\n" +
                        "enim ad minim veniam, quis nostrud exercitation \n" +
                        "ullamco laboris nisi ut aliquip ex ea commodo\n" +
                        "consequat. Duis aute irure dolor in reprehenderit\n" +
                        "in voluptate velit esse cillum dolore eu fugiat\n" +
                        "nulla pariatur. Excepteur sint occaecat cupidatat\n" +
                        "non proident, sunt in culpa qui officia deserunt\n" +
                        "mollit anim id est laborum."
        };

        String[] expectedResults = {
                "",
                "",
                "ДЕНЬ ДОБРЫЙ",
                "ЗЕЛЕНЫЙ СИНЕ",
                "БЕЗ КОФЕ МОЛОКОМ САХАРА ЧАШКА",
                "ЕЩЕ МНОГО РАЗ",
                "ADIPISCING ALIQUA ALIQUIP AMET ANIM AUTE CILLUM COMMODO CONSECTETUR CONSEQUAT CULPA CUPIDATAT DESERUNT DOLOR DOLORE DUIS EIUSMOD ELIT ENIM ESSE EST EXCEPTEUR EXERCITATION FUGIAT INCIDIDUNT IPSUM IRURE LABORE LABORIS LABORUM LOREM MAGNA MINIM MOLLIT NISI NON NOSTRUD NULLA OCCAECAT OFFICIA PARIATUR PROIDENT QUI QUIS REPREHENDERIT SED SINT SIT SUNT TEMPOR ULLAMCO VELIT VENIAM VOLUPTATE"
        };

        for (int i = 0; i < inputTexts.length; i++) {
            String result = TextProcessor.processText(inputTexts[i]);
            assertEquals(expectedResults[i], result);
        }
    }
}