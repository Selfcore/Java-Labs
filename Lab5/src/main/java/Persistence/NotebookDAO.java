package Persistence;

import Models.Notebook;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class NotebookDAO {
    public NotebookDAO() {}

    public void save(Notebook notebook) {
        Transaction tx = null;

        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.persist(notebook);
            tx.commit();
        }
        catch(Exception ex) {
            if(tx != null)
                tx.rollback();
        }
    }

    public List<Notebook> findAll() {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Notebook", Notebook.class).list();
        }
    }

    public Notebook findById(int id) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.find(Notebook.class, id);
        }
    }

    public void update(Notebook notebook) {
        Transaction tx = null;

        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.merge(notebook);
            tx.commit();
        }
        catch(Exception ex) {
            if(tx != null)
                tx.rollback();
        }
    }

    public void delete(int id) {
        Transaction tx = null;

        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Notebook nb = findById(id);

            if(nb != null)
                session.remove(nb);

            tx.commit();
        }
        catch(Exception ex) {
            if(tx != null)
                tx.rollback();
        }
    }

    public Object[] findCountryWithMaxNotebooks() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                            "select n.country, count(n) as cnt from Notebook n group by n.country order by cnt desc",
                            Object[].class
                    )
                    .setMaxResults(1)
                    .uniqueResult();
        }
    }

    public Object[] findCountryWithMinNotebooks() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                            "SELECT n.country, count(n) AS cnt FROM Notebook n GROUP BY n.country ORDER BY cnt ASC",
                            Object[].class
                    )
                    .setMaxResults(1)
                    .uniqueResult();
        }
    }

    public Object[] findCompanyWithMaxNotebooks() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                            "SELECT n.manufacturer, count(n) AS cnt FROM Notebook n GROUP BY n.manufacturer ORDER BY cnt DESC",
                            Object[].class
                    )
                    .setMaxResults(1)
                    .uniqueResult();
        }
    }

    public Object[] findCompanyWithMinNotebooks() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                            "SELECT n.manufacturer, count(n) AS cnt FROM Notebook n GROUP BY n.manufacturer ORDER BY cnt ASC",
                            Object[].class
                    )
                    .setMaxResults(1)
                    .uniqueResult();
        }
    }

    public List<Notebook> findByPages(int pages) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Notebook n WHERE n.pages = :pages", Notebook.class)
                    .setParameter("pages", pages)
                    .list();
        }
    }

    public List<Notebook> findByCirculation(int circulation) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Notebook n where n.circulation = :circ", Notebook.class)
                    .setParameter("circ", circulation)
                    .list();
        }
    }

    public List<Object[]> getCountryStatistics() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                    "SELECT n.country, count(n) FROM Notebook n GROUP BY n.country ORDER BY count(n) DESC",
                    Object[].class
            ).list();
        }
    }
}
