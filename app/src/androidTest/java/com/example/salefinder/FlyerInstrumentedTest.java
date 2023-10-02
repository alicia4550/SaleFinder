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
        Flyer flyer = TestUtil.createFlyer(1234, "Freshco");
        flyerDao.insert(flyer);
        List<Flyer> byMerchant = flyerDao.findByMerchant("Freshco");
        assertEquals(1, byMerchant.size());
        assertTrue(byMerchant.get(0).equals(flyer));
    }

}
