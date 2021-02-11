package ru.sapteh.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.sapteh.dao.Dao;
import ru.sapteh.model.UserRole;

import java.util.List;

public class UserRoleService implements Dao<UserRole,Integer> {

    private final SessionFactory factory;
    public UserRoleService(SessionFactory factory){
        this.factory=factory;
    }

    @Override
    public void create(UserRole userRole){
        try(Session session=factory.openSession()) {
            session.beginTransaction();
            session.save(userRole);
            session.getTransaction().commit();
        }
    }
    @Override
    public UserRole read(Integer id) {
        try(Session session=factory.openSession()) {
          UserRole userRole=session.get(UserRole.class,id);
          return userRole;
        }
    }

    @Override
    public List<UserRole> readByAll() {
        try(Session session=factory.openSession()) {
            String sql="FROM UserRole ";
            Query<UserRole> query=session.createQuery(sql);
            return query.list();
        }
    }
}
