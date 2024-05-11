package vasilkov.service;

import vasilkov.model.*;
import vasilkov.model.Record;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import static java.util.stream.Collectors.toMap;


public class CacheService implements Service<Record> {
    private final Map<String, RecordField> fieldMap;
    public CacheService() {

        // Retrieving "fields" could be done better
        // but for the test-task i leave it like this
        List<RecordField> fields = new ArrayList<>();

        fields.add(new RecordAccountField());
        fields.add(new RecordNameField());
        fields.add(new RecordValueField());

        this.fieldMap = fields
                .stream()
                .collect(
                        toMap(RecordField::getField, Function.identity())
                );
    }

    @Override
    public void add(Record record) {
        //Here we can validate the entity according
        //to the rules defined for it
        //if ... throw SomeCommonError()
        fieldMap.forEach(
                (k,v)-> v.addRecord(record)
        );
    }

    @Override
    public void remove(Record record) {
        // -//-
        fieldMap.forEach(
                (k,v)-> v.deleteRecord(record)
        );
    }

    @Override
    public void update(Record record) {
        //  -//-
        fieldMap.forEach(
                (k,v)-> v.updateRecord(record)
        );
    }

    @Override
    public List<Record> getByField(String field, Object value) {
        //-/-
        RecordField recordField = fieldMap.get(field);

        if (recordField == null) {
            throw new UnsupportedOperationException(field);
        }

        return recordField.findRecordByFieldValue(value);
    }

}
