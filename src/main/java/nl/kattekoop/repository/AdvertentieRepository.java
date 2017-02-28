package nl.kattekoop.repository;

import nl.kattekoop.domain.Advertentie;
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
public class AdvertentieRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(AdvertentieRepository.class);

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

    public void opslaan(Advertentie advertentie) {
        LOGGER.debug("Opslaan Advertentie");
        getTransaction();

        if (advertentie.getId() == null) {
            getSession().save(advertentie);
        } else {
            getSession().merge(advertentie);
        }

        getTransaction().commit();
    }

    public List<Advertentie> alles() {
        getTransaction();

        Query query = getSession().createQuery("select c from Advertentie c");

        List<Advertentie> catteries = query.list();

        getTransaction().commit();

        return catteries;
    }

    public void verwijder(Advertentie advertentie) {
        verwijder(advertentie, true);
    }

    public void verwijder(Advertentie advertentie, boolean transactieStarten) {
        if (transactieStarten) {
            getTransaction();
        }

        getSession().delete(advertentie);

        if (transactieStarten) {
            getTransaction().commit();
        }
    }

    public Advertentie lees(Long id) {
        getTransaction();

        Advertentie advertentie = getSession().get(Advertentie.class, id);

        getTransaction().commit();

        return advertentie;
    }
}
