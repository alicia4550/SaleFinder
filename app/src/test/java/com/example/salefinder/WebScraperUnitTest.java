package com.example.salefinder;

import org.junit.Test;

import static org.junit.Assert.*;

import com.example.salefinder.entity.Flyer;
import com.example.salefinder.entity.Item;
import com.example.salefinder.service.WebScraperService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class WebScraperUnitTest {
    @Test
    public void testGetAllFlyers() throws ParseException {
        List<Flyer> flyerList = WebScraperService.getAllFlyers();
        Date today = new Date();
        for (Flyer flyer : flyerList) {
            assertEquals("en-ca", flyer.locale);
            Date available_from = new SimpleDateFormat("yyyy-MM-dd").parse(flyer.available_from.substring(0, 10));
            Date available_to = new SimpleDateFormat("yyyy-MM-dd").parse(flyer.available_to.substring(0, 10));
            assertTrue(available_from.before(today) || isSameDay(today, available_from));
            assertTrue(available_to.after(today) || isSameDay(today, available_to));
            assertEquals("M6H4B9", flyer.postal_code);
        }
    }

    @Test
    public void testGetAllItemsByFlyer() throws ParseException {
        Flyer flyer = WebScraperService.getAllFlyers().get(0);
        List<Item> itemList = WebScraperService.getAllItemsByFlyer(flyer.id);
        Date today = new Date();
        for (Item item : itemList) {
            assertEquals(flyer.id, item.flyer_id);
            Date valid_from = new SimpleDateFormat("yyyy-MM-dd").parse(item.valid_from.substring(0, 10));
            Date valid_to = new SimpleDateFormat("yyyy-MM-dd").parse(item.valid_to.substring(0, 10));
            assertTrue(valid_from.before(today) || isSameDay(today, valid_from));
            assertTrue(valid_to.after(today) || isSameDay(today, valid_to));
        }
    }

    private boolean isSameDay(Date date1, Date date2) {
        return date1.getDate() == date2.getDate() &&
                date1.getMonth() == date2.getMonth() &&
                date1.getYear() == date2.getYear();
    }
}