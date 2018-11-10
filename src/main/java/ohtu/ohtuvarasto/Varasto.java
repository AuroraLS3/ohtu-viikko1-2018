package ohtu.ohtuvarasto;

public class Varasto {

    // --- piilotettu tietorakenteen toteutus: ---
    private double tilavuus; // paljonko varastoon mahtuu, > 0
    private double saldo; // paljonko varastossa on nyt, >= 0

    // --- konstruktorit: ---
    public Varasto(double tilavuus) { // tilavuus on annettava
        asetaTilavuus(tilavuus);
        this.saldo = 0; // oletus: varasto on tyhjä
    }

    public Varasto(double tilavuus, double alkuSaldo) { // kuormitetaan
        asetaTilavuus(tilavuus);
        asetaSaldo(alkuSaldo);
    }

    private void asetaTilavuus(double tilavuus) {
        if (tilavuus > 0.0) {
            this.tilavuus = tilavuus;
        } else {
            // virheellinen, nollataan
            this.tilavuus = 0.0; // => käyttökelvoton varasto
        }
    }

    private void asetaSaldo(double alkuSaldo) {
        if (alkuSaldo < 0.0) {
            this.saldo = 0.0;
        } else if (alkuSaldo <= tilavuus) {
            // mahtuu
            this.saldo = alkuSaldo;
        } else {
            this.saldo = tilavuus; // täyteen ja ylimäärä hukkaan!
        }
    }

    // --- ottavat aksessorit eli getterit: ---
    public double getSaldo() {
        return saldo;
    }

    public double getTilavuus() {
        return tilavuus;
    }

    public double paljonkoMahtuu() { // huom: ominaisuus voidaan myös laskea
        return tilavuus - saldo; // ei tarvita erillistä kenttää vielaTilaa tms.
    }

    // --- asettavat aksessorit eli setterit: ---
    public void lisaaVarastoon(double maara) {
        if (maara < 0) {
            // virhetilanteessa voidaan tehdä
            // tällainen pikapoistuminenkin!
            return;
        }
        if (maara <= paljonkoMahtuu()) {
            // omia aksessoreita voi kutsua
            saldo = saldo + maara; // ihan suoraan sellaisinaan
        } else {
            saldo = tilavuus; // täyteen ja ylimäärä hukkaan!
        }
    }

    public double otaVarastosta(double maara) {
        if (maara < 0) {
            // virhetilanteessa voidaan tehdä
            // tällainen pikapoistuminenkin!
            return 0.0;
        }
        if (maara > saldo) { // annetaan mitä voidaan
            double kaikkiMitaVoidaan = saldo;
            saldo = 0.0; // ja tyhjäksi menee
            return kaikkiMitaVoidaan; // poistutaan saman tien
        }
        // jos tänne päästään, kaikki pyydetty voidaan antaa
        saldo = saldo - maara; // vähennetään annettava saldosta
        return maara;
    }

    // --- Merkkijonoesitys Varasto-oliolle: ----
    public String toString() {
        return ("saldo = " + saldo + ", vielä tilaa " + paljonkoMahtuu());
    }
}