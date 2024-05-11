package vasilkov.model;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

@Getter
public abstract class Field<T> {

    public Map<T, TreeSet<Long>> fieldCache;
    public Field() {
        fieldCache = new HashMap<>();
    }

    public void add(T field, Long value){

        TreeSet<Long> identityField = fieldCache.getOrDefault(field, new TreeSet<>());

        identityField.add(value);

        fieldCache.put(field, identityField);
    }

    public void delete(T field){
        fieldCache.remove(field);
    }
}