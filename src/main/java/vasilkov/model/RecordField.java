package vasilkov.model;

import java.util.List;

public interface RecordField {
    void addRecord(Record record);
    void deleteRecord(Record record);
    Record updateRecord(Record record);
    List<Record> findRecordByFieldValue(Object value);
    String getField();
}
