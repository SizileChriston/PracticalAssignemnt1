/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.practicalassignment1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SeriesManagerTest {

    private SeriesManager manager;

    @BeforeEach
    public void setUp() {
        manager = new SeriesManager();
    }

    @Test
    public void testAddSeries() {
        manager.AddSeries("101", "Stranger Things", 16, 25);
        assertEquals(1, manager.getSeriesList().size());
        assertEquals("Stranger Things", manager.getSeriesList().get(0).seriesName);
    }

    @Test
    public void testSearchSeriesById() {
        manager.AddSeries("102", "Game of Thrones", 18, 80);
        Series.SeriesModel found = manager.SearchSeriesById("102");
        assertNotNull(found);
        assertEquals("Game of Thrones", found.seriesName);
    }

    @Test
    public void testUpdateSeriesAge() {
        manager.AddSeries("103", "Breaking Bad", 16, 62);
        boolean updated = manager.UpdateSeriesAge("103", 18);
        assertTrue(updated);
        assertEquals(18, manager.SearchSeriesById("103").seriesAge);
    }

    @Test
    public void testDeleteSeries() {
        manager.AddSeries("104", "Peaky Blinders", 18, 36);
        boolean deleted = manager.DeleteSeries("104");
        assertTrue(deleted);
        assertNull(manager.SearchSeriesById("104"));
    }

    @Test
    public void testPrintSeriesReport_NoSeries() {
        assertTrue(manager.getSeriesList().isEmpty());
        manager.PrintSeriesReport(); // Just prints, no assertion needed
    }
}
