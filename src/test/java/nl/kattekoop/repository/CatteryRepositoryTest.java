package nl.kattekoop.repository;

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

    @Test
    public void testAll() {
        Cattery cattery = new Cattery();
        cattery.setNaam("Cattery1");

        catteryRepository.opslaan(cattery);

        assertThat(catteryRepository.alles().size(), is(1));

        Cattery cattery2 = new Cattery();
        cattery2.setNaam("Cattery2");

        catteryRepository.opslaan(cattery2);

        assertThat(catteryRepository.alles().size(), is(2));

        catteryRepository.verwijder(cattery);
        assertThat(catteryRepository.alles().size(), is(1));

        catteryRepository.verwijder(cattery2);
        assertThat(catteryRepository.alles().size(), is(0));
    }
}