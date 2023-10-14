package com.example.salefinder;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.salefinder.dao.FlyerDao;
import com.example.salefinder.database.AppDatabase;
import com.example.salefinder.entity.Flyer;
import com.example.salefinder.util.TestUtil;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

@RunWith(AndroidJUnit4.class)
public class FlyerInstrumentedTest {
    private FlyerDao flyerDao;
    private AppDatabase db;

    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
        flyerDao = db.flyerDao();
    }

    @After
    public void closeDb() throws IOException {
        db.close();
    }

    @Test
    public void writeFlyerAndReadInList() throws Exception {
        Flyer flyer = TestUtil.createFlyer(1234, "Freshco", "url");
        flyerDao.insert(flyer);
        List<Flyer> byMerchant = flyerDao.findByMerchant("Freshco");
        assertEquals(1, byMerchant.size());
        assertTrue(byMerchant.get(0).equals(flyer));
    }

    @Test
    public void testFindFlyerIdByMerchant() {
        Flyer flyer1 = TestUtil.createFlyer(1234, "Freshco", "url");
        Flyer flyer2 = TestUtil.createFlyer(1235, "Freshco", "url");
        Flyer flyer3 = TestUtil.createFlyer(1236, "No Frills", "url");
        flyer2.categories_csv = "All Flyers";
        flyerDao.insert(flyer1);
        flyerDao.insert(flyer2);
        List<Integer> ids = flyerDao.findFlyerIdByMerchant("Freshco");
        assertEquals(1, ids.size());
        assertEquals((Integer) 1234, ids.get(0));
    }

    @Test
    public void testFindLogoByMerchant() {
        Flyer flyer = TestUtil.createFlyer(1234, "Freshco", "url");
        flyerDao.insert(flyer);
        List<String> logos = flyerDao.findLogoByMerchant("Freshco");
        assertEquals(1, logos.size());
        assertEquals("url", logos.get(0));
    }

    @Test
    public void testFindAllMerchants() {
        Flyer flyer1 = TestUtil.createFlyer(1234, "Freshco", "url");
        Flyer flyer2 = TestUtil.createFlyer(1235, "Food Basics", "url");
        Flyer flyer3 = TestUtil.createFlyer(1236, "No Frills", "url");
        Flyer flyer4 = TestUtil.createFlyer(1237, "Walmart", "url");
        Flyer flyer5 = TestUtil.createFlyer(1238, "Walmart", "url");

        flyerDao.insert(flyer1);
        flyerDao.insert(flyer2);
        flyerDao.insert(flyer3);
        flyerDao.insert(flyer4);
        flyerDao.insert(flyer5);

        List<String> merchants = flyerDao.findAllMerchants();

        assertEquals(4, merchants.size());
        assertEquals("Food Basics", merchants.get(0));
        assertEquals("Freshco", merchants.get(1));
        assertEquals("No Frills", merchants.get(2));
        assertEquals("Walmart", merchants.get(3));
    }

}
