package ru.sapteh.dao;

import java.util.List;

public interface Dao<Entity,Key> {
    void create(Entity entity);
    Entity read(Key id);
    List<Entity> readByAll();
}
