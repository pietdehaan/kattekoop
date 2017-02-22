package nl.kattekoop.repository;

import nl.kattekoop.domain.Cattery;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.resource.transaction.spi.TransactionStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CatteryRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(CatteryRepository.class);

    @Autowired
    private SessionFactory sessionFactory;

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    protected Transaction getTransaction() {
        Transaction transaction = getSession().getTransaction();
        if (transaction.getStatus() != TransactionStatus.ACTIVE) {
            transaction.begin();
        }

        return transaction;
    }

    public void opslaan(Cattery cattery) {
        LOGGER.debug("Opslaan Cattery");
        getTransaction();

        if (cattery.getId() == null) {
            getSession().save(cattery);
        } else {
            getSession().merge(cattery);
        }

        getTransaction().commit();
    }

    public List<Cattery> alles() {
        getTransaction();

        Query query = getSession().createQuery("select c from Cattery c");

        List<Cattery> catteries = query.list();

        getTransaction().commit();

        return catteries;
    }

    public void verwijder(Cattery cattery) {
        getTransaction();

        getSession().delete(cattery);

        getTransaction().commit();
    }
}
