package com.pozhenskii.task3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TransliteratorTest {

    String ruString = "Юлия, съешь ещё этих мягких французских булок из Йошкар-Олы, да выпей алтайского чаю";
    String latString = "Yuliya, s``esh` eshhyo e`tix myagkix franczuzskix bulok iz Joshkar-Oly`, da vy`pej altajskogo chayu";

    @Test
    void rusToLat() {
        assertEquals(latString, Transliterator.rusToLat(ruString));
    }

    @Test
    void latinToRus() {
        assertEquals(ruString, Transliterator.latToRus(latString));
    }
}