package Persistence.Repositories;

import Models.Order;
import Models.Worker;
import Persistence.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class WorkerRepository implements RepositoryImp<Worker> {
    @Override
    public List<Worker> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Worker W LEFT JOIN FETCH W.orders", Worker.class).list();
        }
    }

    @Override
    public Worker getById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                            "SELECT DISTINCT W FROM Worker W " +
                                    "LEFT JOIN FETCH W.orders " +
                                    "WHERE W.id = :id",
                            Worker.class
                    )
                    .setParameter("id", id)
                    .uniqueResult();
        }
    }

    @Override
    public void save(Worker worker) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(worker);
            tx.commit();
        }
    }

    @Override
    public void update(Worker worker) {

    }

    @Override
    public void delete(Worker worker) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.remove(worker);
            tx.commit();
        }
    }
}
