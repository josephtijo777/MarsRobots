package com.example.marsrobots

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.marsrobots.repositories.home.HomeRepository
import com.example.marsrobots.room.ImageDao
import com.example.marsrobots.room.ImageEntity
import com.example.marsrobots.room.MarsRobotsDb
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class ImageDaoTest {
    private lateinit var imageDao: ImageDao
    private lateinit var db: MarsRobotsDb


    @Before
    fun createDb() {
        val context: Context = ApplicationProvider.getApplicationContext()
        // Using an in-memory database because the information stored here disappears when the
        // process is killed.
        db = Room.inMemoryDatabaseBuilder(context, MarsRobotsDb::class.java)
            // Allowing main thread queries, just for testing.
            .allowMainThreadQueries()
            .build()
        imageDao = db.imageDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetImage() = runBlocking {
        val imageEntity = ImageEntity(
            "https://images-assets.nasa.gov/image/NHQ201905310037/NHQ201905310037~thumb.jpg",
            "The Mars celebration Friday, May 31, 2019, in Mars, Pennsylvania. NASA is in the small town to celebrate Mars exploration and share the agency’s excitement about landing astronauts on the Moon in five years. The celebration includes a weekend of Science, Technology, Engineering, Arts and Mathematics (STEAM) activities. Photo Credit: (NASA/Bill Ingalls)",
            "2019-05-31T00:00:00Z"
        )
        imageDao.insert(imageEntity)
        val allImages = imageDao.getAllImages().first()
        assertEquals(allImages[0], imageEntity)
    }

    @Test
    @Throws(Exception::class)
    fun getAllImages() = runBlocking {
        val imageEntity1 = ImageEntity(
            "https://images-assets.nasa.gov/image/NHQ201905310037/NHQ201905310037~thumb.jpg",
            "The Mars celebration Friday, May 31, 2019, in Mars, Pennsylvania. NASA is in the small town to celebrate Mars exploration and share the agency’s excitement about landing astronauts on the Moon in five years. The celebration includes a weekend of Science, Technology, Engineering, Arts and Mathematics (STEAM) activities. Photo Credit: (NASA/Bill Ingalls)",
            "2019-05-31T00:00:00Z"
        )
        val imageEntity2 = ImageEntity(
            "https://images-assets.nasa.gov/image/NHQ201905310039/NHQ201905310039~thumb.jpg",
            "The Mars celebration Friday, May 31, 2019, in Mars, Pennsylvania. NASA is in the small town to celebrate Mars exploration and share the agency’s excitement about landing astronauts on the Moon in five years. The celebration includes a weekend of Science, Technology, Engineering, Arts and Mathematics (STEAM) activities. Photo Credit: (NASA/Bill Ingalls)",
            "2019-05-31T00:00:00Z"
        )
        imageDao.insert(imageEntity1)
        imageDao.insert(imageEntity2)

        val allImages = imageDao.getAllImages().first()
        assertEquals(allImages[0], imageEntity1)
        assertEquals(allImages[1], imageEntity2)
    }

    @Test
    @Throws(Exception::class)
    fun deleteAll() = runBlocking {
        val imageEntity1 = ImageEntity(
            "https://images-assets.nasa.gov/image/NHQ201905310037/NHQ201905310037~thumb.jpg",
            "The Mars celebration Friday, May 31, 2019, in Mars, Pennsylvania. NASA is in the small town to celebrate Mars exploration and share the agency’s excitement about landing astronauts on the Moon in five years. The celebration includes a weekend of Science, Technology, Engineering, Arts and Mathematics (STEAM) activities. Photo Credit: (NASA/Bill Ingalls)",
            "2019-05-31T00:00:00Z"
        )
        val imageEntity2 = ImageEntity(
            "https://images-assets.nasa.gov/image/NHQ201905310039/NHQ201905310039~thumb.jpg",
            "The Mars celebration Friday, May 31, 2019, in Mars, Pennsylvania. NASA is in the small town to celebrate Mars exploration and share the agency’s excitement about landing astronauts on the Moon in five years. The celebration includes a weekend of Science, Technology, Engineering, Arts and Mathematics (STEAM) activities. Photo Credit: (NASA/Bill Ingalls)",
            "2019-05-31T00:00:00Z"
        )
        imageDao.insert(imageEntity1)
        imageDao.insert(imageEntity2)
        imageDao.deleteAll()
        val allImages = imageDao.getAllImages().first()
        Assert.assertTrue(allImages.isEmpty())
    }
}