package nl.kattekoop.repository;

import nl.kattekoop.domain.Advertentie;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class AdvertentieRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(AdvertentieRepository.class);

    @Autowired
    private SessionFactory sessionFactory;

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Transactional
    public void opslaan(Advertentie advertentie) {
        LOGGER.debug("Opslaan Advertentie");

        if (advertentie.getId() == null) {
            getSession().save(advertentie);
        } else {
            getSession().merge(advertentie);
        }
    }

    @Transactional(readOnly = true)
    public List<Advertentie> alles() {
        Query query = getSession().createQuery("select c from Advertentie c");

        List<Advertentie> catteries = query.list();

        return catteries;
    }

    @Transactional
    public void verwijder(Advertentie advertentie) {
        getSession().delete(advertentie);
    }

    @Transactional(readOnly = true)
    public Advertentie lees(Long id) {
        Advertentie advertentie = getSession().get(Advertentie.class, id);

        return advertentie;
    }
}
