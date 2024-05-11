package vasilkov.service;

import java.util.List;

public interface Service<T> {
    void add(T record);
    void remove(T record);
    void update(T record);
    List<T> getByField(String field, Object value);
}
