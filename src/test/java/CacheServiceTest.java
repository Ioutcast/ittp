import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import vasilkov.model.Record;
import vasilkov.service.CacheService;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

public class CacheServiceTest {
    private CacheService cacheService;

    @BeforeEach
    void setUp() {
        cacheService = new CacheService();
    }

    @Test
    void addRecordTest() {

        Record record = new Record(123L, "Test Record", 50.0);
        cacheService.add(record);

        assertEquals(1, cacheService.getByField("account", 123L).size());
        assertEquals(1, cacheService.getByField("name", "Test Record").size());
        assertEquals(1, cacheService.getByField("value", 50.0).size());
    }

    @Test
    void removeRecordTest() {

        Record record = new Record(123L, "Test Record", 50.0);

        cacheService.add(record);

        cacheService.remove(record);

        assertTrue(cacheService.getByField("account", 123L).isEmpty());

    }

    @Test
    void updateRecordTest() {

        Record record = new Record(123L, "Test Record", 50.0);
        cacheService.add(record);

        Record updatedRecord = new Record(123L, "Updated Record", 100.0);
        cacheService.update(updatedRecord);

        Record result = cacheService
                .getByField("account",123L)
                .get(0);

        assertEquals(record.account(), result.account());
        assertNotEquals(record.name(), result.name());
        assertNotEquals(record.value(), result.value());
    }

    @Test
    void getByFieldTest() {
        Record record1 = new Record(123L, "Test Record", 50.0);
        Record record2 = new Record(456L, "Another Record", 100.0);

        cacheService.add(record1);
        cacheService.add(record2);

        List<Record> recordsWithAccount123 = cacheService.getByField("account", 123L);
        assertEquals(record1, recordsWithAccount123.get(0));

        List<Record> recordsWithNameTestRecord = cacheService.getByField("name", "Test Record");
        assertNotEquals("Another Record", recordsWithNameTestRecord.get(0).name());

        List<Record> recordsWithValue100 = cacheService.getByField("value", 100.0);
        assertEquals(record2, recordsWithValue100.get(0));
    }
}
