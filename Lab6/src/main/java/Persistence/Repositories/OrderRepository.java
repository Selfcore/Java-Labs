package Persistence.Repositories;

import Models.Order;
import Persistence.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.util.List;

public class OrderRepository implements RepositoryImp<Order> {

    @Override
    public List<Order> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                    "SELECT DISTINCT o FROM Order o " +
                            "LEFT JOIN FETCH o.client " +
                            "LEFT JOIN FETCH o.worker " +
                            "LEFT JOIN FETCH o.orderLines",
                    Order.class
            ).list();
        }
    }

    @Override
    public Order getById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                            "SELECT DISTINCT o FROM Order o " +
                                    "LEFT JOIN FETCH o.client " +
                                    "LEFT JOIN FETCH o.worker " +
                                    "LEFT JOIN FETCH o.orderLines " +
                                    "WHERE o.id = :id",
                            Order.class
                    )
                    .setParameter("id", id)
                    .uniqueResult();
        }
    }

    @Override
    public void save(Order order) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.merge(order);
            tx.commit();
        }
    }

    @Override
    public void update(Order order) {

    }

    @Override
    public void delete(Order order) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.remove(session.merge(order));
            tx.commit();
        }
    }

    public List<Order> getByDateRange(LocalDate from, LocalDate to) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                            "SELECT DISTINCT o FROM Order o " +
                                    "LEFT JOIN FETCH o.client " +
                                    "LEFT JOIN FETCH o.worker " +
                                    "LEFT JOIN FETCH o.orderLines " +
                                    "WHERE o.date >= :from AND o.date <= :to " +
                                    "ORDER BY o.date ASC",
                            Order.class
                    )
                    .setParameter("from", from)
                    .setParameter("to", to)
                    .list();
        }
    }
}
