package nl.kattekoop.repository;

import nl.kattekoop.domain.Advertentie;
import nl.kattekoop.domain.Cattery;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext-unittest.xml")
public class CatteryRepositoryTest {
    @Inject
    private CatteryRepository catteryRepository;
    @Inject
    private AdvertentieRepository advertentieRepository;

    @Test
    public void testAll() {
        String advertentieTitel = "advertentieTitel";
        String naamCattery1 = "Cattery1";
        String naamCattery2 = "Cattery2";

        Cattery cattery = new Cattery();
        cattery.setNaam(naamCattery1);

        catteryRepository.opslaan(cattery);

        assertThat(catteryRepository.alles().size(), is(1));

        Cattery cattery2 = new Cattery();
        cattery2.setNaam(naamCattery2);

        catteryRepository.opslaan(cattery2);

        Advertentie advertentie = new Advertentie();
        advertentie.setTitel(advertentieTitel);
        advertentie.setCattery(cattery);
        cattery.getAdvertenties().add(advertentie);

        catteryRepository.opslaan(cattery);

        assertThat(catteryRepository.alles().size(), is(2));

        Cattery catteryOpgehaald = catteryRepository.lees(cattery.getId());
        assertThat(catteryOpgehaald.getAdvertenties().size(), is(1));
        assertThat(advertentieRepository.alles().size(), is(1));

        catteryRepository.verwijder(cattery);
        assertThat(catteryRepository.alles().size(), is(1));
        assertThat(advertentieRepository.alles().size(), is(0));

        catteryRepository.verwijder(cattery2);
        assertThat(catteryRepository.alles().size(), is(0));
        assertThat(advertentieRepository.alles().size(), is(0));
    }
}