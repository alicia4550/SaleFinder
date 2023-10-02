package com.example.salefinder;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.salefinder.dao.ItemDao;
import com.example.salefinder.database.AppDatabase;
import com.example.salefinder.entity.Item;
import com.example.salefinder.util.TestUtil;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

@RunWith(AndroidJUnit4.class)
public class ItemInstrumentedTest {
    private ItemDao itemDao;
    private AppDatabase db;

    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
        itemDao = db.itemDao();
    }

    @After
    public void closeDb() throws IOException {
        db.close();
    }

    @Test
    public void writeItemAndReadInList() throws Exception {
        Item item = TestUtil.createItem(1234, "Apple");
        itemDao.insert(item);
        List<Item> byFlyerId = itemDao.findByFlyerId(1234);
        assertEquals(1, byFlyerId.size());
        assertTrue(byFlyerId.get(0).equals(item));
    }

}
