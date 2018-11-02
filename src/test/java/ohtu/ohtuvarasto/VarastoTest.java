package ohtu.ohtuvarasto;

import org.junit.*;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void negatiivistaEiVahennetaLisatessa() {
        varasto.lisaaVarastoon(-1);
        assertEquals(10, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ylimaarainenEiMahdu() {
        varasto.lisaaVarastoon(11);
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void negatiivistaEiLisataOtettaessa() {
        varasto.lisaaVarastoon(5);
        varasto.otaVarastosta(-5);
        assertEquals(5, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void annetaanKaikki() {
        varasto.lisaaVarastoon(5);
        double result = varasto.otaVarastosta(8);
        assertEquals(5, result, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void virheellinenVarastoTilavuus() {
        Varasto v = new Varasto(-1);
        assertEquals(0, v.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void virheellinenVarastoTilavuus2() {
        Varasto v = new Varasto(-1, -1);
        assertEquals(0, v.paljonkoMahtuu(), vertailuTarkkuus);
    }

    // Tämä testi aiheuttaa varaston hajoamisen
    @Test
    public void varastoAntaaTyhmänArvon() {
        Varasto v = new Varasto(-1, 0);
        assertEquals(1, v.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void virheellinenAlkusaldo() {
        Varasto v = new Varasto(2, -1);
        assertEquals(2, v.paljonkoMahtuu(), vertailuTarkkuus);
        assertEquals(0, v.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void suurempiAlkusaldoKuinTilavuus() {
        Varasto v = new Varasto(2, 4);
        assertEquals(0, v.paljonkoMahtuu(), vertailuTarkkuus);
        assertEquals(2, v.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void pienempiAlkusaldoKuinTilavuus() {
        Varasto v = new Varasto(2, 1);
        assertEquals(1, v.paljonkoMahtuu(), vertailuTarkkuus);
        assertEquals(1, v.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void toStringSisaltaaAsioita() {
        String testing = varasto.toString();
        assertTrue(testing.contains("saldo"));
        assertTrue(testing.contains("vielä tilaa"));
    }
}