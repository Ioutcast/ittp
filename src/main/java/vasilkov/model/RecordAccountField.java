package vasilkov.model;

import vasilkov.cache.InMemoryCache;

import java.util.Collections;
import java.util.List;

public class RecordAccountField implements RecordField {
    private final InMemoryCache<Long, Record> cache;
    public RecordAccountField() {
        this.cache = InMemoryCache.getInstance();
    }
    @Override
    public void addRecord(Record record) {
        //Here we can validate the field according
        //to the rules defined for it
        //if ... throw SpecificFieldError()
        cache.getCache()
                .put(record.account(),record);
    }

    @Override
    public void deleteRecord(Record record) {
        // -//-
        cache.getCache()
                .remove(record.account());
    }

    @Override
    public Record updateRecord(Record record) {
        // -//-
        addRecord(record);
        return cache
                .getCache()
                .get(record.account());
    }

    @Override
    public List<Record> findRecordByFieldValue(Object value) {

        Record record = cache.getCache().get(value);

        if (record == null) {
            return Collections.emptyList();
        }

        return List.of(record);
    }

    @Override
    public String getField() {
        return "account";
    }
}
