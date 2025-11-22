package Persistence.Repositories;

import Models.Product;
import Persistence.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ProductRepository implements RepositoryImp<Product> {

    @Override
    public List<Product> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Product", Product.class).list();
        }
    }

    @Override
    public Product getById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.find(Product.class, id);
        }
    }

    @Override
    public void save(Product product) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(product);
            transaction.commit();
        }
    }

    @Override
    public void update(Product product) {

    }

    @Override
    public void delete(Product product) {

    }
}
