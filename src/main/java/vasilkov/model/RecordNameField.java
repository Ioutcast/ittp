package vasilkov.model;

import vasilkov.cache.InMemoryCache;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class RecordNameField extends Field<String> implements RecordField {
    private final InMemoryCache<Long, Record> cache;
    public RecordNameField() {
        this.cache = InMemoryCache.getInstance();
    }
    @Override
    public void addRecord(Record record) {
        //Here we can validate the field according
        //to the rules defined for it
        //if ... throw SpecificFieldError()
        add(record.name(),record.account());

    }
    @Override
    public void deleteRecord(Record record) {
        // -//-
        delete(record.name());
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
        // -//-
        Set<Long> identityField = getFieldCache().get(value);

        if (identityField == null || identityField.isEmpty()) {
            return Collections.emptyList();
        }

        List<Record> records = new ArrayList<>();

        for (Long field : identityField) {
            Record record = cache.getCache().get(field);
            if (record != null) {
                records.add(record);
            }
        }

        return records;
    }
    @Override
    public String getField() {
        return "name";
    }
}
