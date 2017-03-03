package nl.kattekoop.repository;

import nl.kattekoop.domain.Advertentie;
import nl.kattekoop.domain.Cattery;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

@Repository
public class CatteryRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(CatteryRepository.class);

    @Autowired
    private SessionFactory sessionFactory;
    @Inject
    private AdvertentieRepository advertentieRepository;

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Transactional
    public void opslaan(Cattery cattery) {
        LOGGER.debug("Opslaan Cattery");

        if (cattery.getId() == null) {
            getSession().save(cattery);
        } else {
            getSession().merge(cattery);
        }
    }

    @Transactional(readOnly = true)
    public List<Cattery> alles() {

        Query query = getSession().createQuery("select c from Cattery c");

        List<Cattery> catteries = query.list();

        return catteries;
    }

    @Transactional
    public void verwijder(Cattery cat) {
        Cattery cattery = getSession().get(Cattery.class, cat.getId());
        for (Advertentie advertentie : cattery.getAdvertenties()) {
            advertentieRepository.verwijder(advertentie);
        }
        getSession().delete(cattery);
    }

    @Transactional(readOnly = true)
    public Cattery lees(Long id) {
        return getSession().get(Cattery.class, id);
    }
}
