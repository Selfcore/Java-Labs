package Persistence.Repositories;

import Models.Client;
import Persistence.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ClientRepository implements RepositoryImp<Client> {
    @Override
    public List<Client> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Client C LEFT JOIN FETCH C.orders", Client.class).list();
        }
    }

    @Override
    public Client getById(int id) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.find(Client.class, id);
        }
    }

    @Override
    public void save(Client client) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(client);
            tx.commit();
        }
    }

    @Override
    public void update(Client client) {

    }

    @Override
    public void delete(Client client) {

    }
}
