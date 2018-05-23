package jchat.db.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDAO <T, PK extends Serializable> {
    PK create(T obj);
    T read(PK id);
    void update(T obj);
    void delete(T obj);
    List<T> findAll();
}
