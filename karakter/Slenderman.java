package karakter;
import Palya.*;

/**
 * A Slenderman működésért felelős osztály. Adattagjai ipoz és jpoz - amik a slenderman i- és j pozícióját jelölik,
 * jelenVan, ami azt jelöli, hogy megjelent-e már a pályán, és egy szamlalo, ami azt jelzi, hányszor volt egymás után 1
 * a játékos és slenderman közti távolság (ha ez 3, akkor adott valószínűséggel elkapja).
 */
public class Slenderman extends Karakter {

    protected int ipoz;
    protected int jpoz;
    protected boolean jelenVan;
    protected int szamlalo = 0;

    public Slenderman() {
        this.ipoz  = 0;
        this.jpoz = 0;
        this.jelenVan = false;
    }

    public int getIpoz() {
        return ipoz;
    }

    public int getJpoz() {
        return jpoz;
    }

    public boolean isJelenVan() {
        return jelenVan;
    }

    public int getSzamlalo() {
        return szamlalo;
    }

    /**
     * Ha a slenderman és a játékos közti távolság <= 3, akkor a pályán láthatóvá válik a slenderman
     * @param jatekos a játékos
     * @param slenderman a slenderman akinek a játékostól való távolságát számítjuk
     * @return megjelenhet-e a pályán a slendermant jelző szimbólum vagy nem
     */
    public boolean Lathato(Jatekos jatekos, Slenderman slenderman) {
        if (manhattan(jatekos.getIpoz(), jatekos.getJpoz(), slenderman.ipoz, slenderman.jpoz) <= 3) {
            return true;
        } else {
            return false;

        }
    }

    /**
     * A slenderman akkor spawnol, amikor a játékos felveszi az első papírját. Mivel csak egyszer spawnolhat, ezért
     * ellenőrizzük folyamatosan, hogy a jelenVan adattag igaz-e. Ha igaz, akkor már lespawnolt, nem kell újra meghívni
     * a metódust
     * @param palya pálya, amire spawnolni akarjuk
     * @param jatekos játékos, akit ellenőrzünk, hogy mikor veszi fel az első papírját
     */
    public void spawnol(Palya palya, Jatekos jatekos) {
        if (jatekos.getPapirok()  == 1 && !jelenVan) {
            this.ipoz = (int) (Math.random() * 15) + 1;
            this.jpoz = (int) (Math.random() * 15) + 1;

            if(Lathato(jatekos, this)) {
                palya.tereptargyak[ipoz][jpoz] = " X ";
            }
            this.jelenVan = true;
        }
    }

    /**
     * A következő 4 metódus a slenderman mozgását írja le, attól függően hogy hány papírja van a játékosnak.
     * (a metódusok neve jelzi, hogy mikor melyik kerül meghívásra). A játékmenet része, hogyha egymás után 3-szor 1
     *  a távolság játékos és slenderman közt, akkor a slenderman egy adott valószínűséggel elkapja. Ezek a metódusok
     *  fogják növelni vagy nullázni a számláló értékét, ami azt jelzi hányszor volt egymás után a távolságok mérete 1
     *  Ha ez eléri a 3-at, akkor a slenderman pozíciója az adott valószínűséggel meg fog egyezni a játékoséval. Egyébként
     *  a szabályok szerint véletlenül választ magának olyan pozíciót, ami a játékos és slenderman közti távolságnak
     *  megfelel.
     * @param jatekos a játékos akitől a távolságot mérjük
     * @param szamlalo a számláló, ami jelzi, hogy hányszor volt 1 a távolság. Folyamatosan frissítjuk
     * @param palya a pálya amin módosítani akarjuk a slenderman pozícióját
     * @return az új számláló érték
     */
    public int nullaKetto(Jatekos jatekos, int szamlalo, Palya palya) {
        palya.tereptargyak[ipoz][jpoz] = palya.papirok[ipoz][jpoz];
        this.ipoz = (int) (Math.random() * 15) + 1;
        this.jpoz = (int) (Math.random() * 15) + 1;
        while (manhattan(ipoz, jpoz, jatekos.getIpoz(), jatekos.getJpoz()) <= 5) {
            this.ipoz = (int) (Math.random() * 15) + 1;
            this.jpoz = (int) (Math.random() * 15) + 1;
        }
        if(Lathato(jatekos, this)) {
            palya.tereptargyak[ipoz][jpoz] = " X ";
        }
        return szamlalo;
    }

    public int kettoNegy(Jatekos jatekos, int szamlalo, Palya palya) {
        palya.tereptargyak[ipoz][jpoz] = palya.papirok[ipoz][jpoz];
        this.ipoz = (int) (Math.random() * 15) + 1;
        this.jpoz = (int) (Math.random() * 15) + 1;
        while (manhattan(ipoz, jpoz, jatekos.getIpoz(), jatekos.getJpoz()) >= 5) {
            this.ipoz = (int) (Math.random() * 15) + 1;
            this.jpoz = (int) (Math.random() * 15) + 1;
        }
        if(Lathato(jatekos, this)) {
            palya.tereptargyak[ipoz][jpoz] = " X ";
        }
        if(manhattan(ipoz,jpoz,jatekos.getIpoz(), jatekos.getJpoz()) == 1) {
            szamlalo++;
        } else {
            szamlalo = 0;
        }
        if(szamlalo >= 3) {
            int r = (int)(Math.random() * 3) + 1;
            if(r == 1) {
                this.ipoz = jatekos.getIpoz();
                this.jpoz = jatekos.getJpoz();
            }
        }
        return szamlalo;
    }

    public int negyHat(Jatekos jatekos, int szamlalo, Palya palya) {
        palya.tereptargyak[ipoz][jpoz] = palya.papirok[ipoz][jpoz];
        this.ipoz = (int) (Math.random() * 15) + 1;
        this.jpoz = (int) (Math.random() * 15) + 1;
        while (manhattan(ipoz, jpoz, jatekos.getIpoz(), jatekos.getJpoz()) >= 4) {
            this.ipoz = (int) (Math.random() * 15) + 1;
            this.jpoz = (int) (Math.random() * 15) + 1;
        }
        if(Lathato(jatekos, this)) {
            palya.tereptargyak[ipoz][jpoz] = " X ";
        }
        if(manhattan(ipoz,jpoz,jatekos.getIpoz(), jatekos.getJpoz()) == 1) {
            szamlalo++;
        } else {
            szamlalo = 0;
        }
        if(szamlalo >= 3) {
            int r = (int)(Math.random() * 2) + 1;
            if(r == 1) {
                this.ipoz = jatekos.getIpoz();
                this.jpoz = jatekos.getJpoz();
            }
        }
        return szamlalo;
    }

    public int hatNyolc(Jatekos jatekos, int szamlalo, Palya palya) {
        palya.tereptargyak[ipoz][jpoz] = palya.papirok[ipoz][jpoz];
        this.ipoz = (int) (Math.random() * 15) + 1;
        this.jpoz = (int) (Math.random() * 15) + 1;
        while (manhattan(ipoz, jpoz, jatekos.getIpoz(), jatekos.getJpoz()) >= 3) {
            this.ipoz = (int) (Math.random() * 15) + 1;
            this.jpoz = (int) (Math.random() * 15) + 1;
        }
        if(Lathato(jatekos, this)) {
            palya.tereptargyak[ipoz][jpoz] = " X ";
        }
        if(manhattan(ipoz,jpoz,jatekos.getIpoz(), jatekos.getJpoz()) == 1) {
            szamlalo++;
        } else {
            szamlalo = 0;
        }
        if(szamlalo >= 3) {
            int r = (int)(Math.random() * 3) + 1;
            if(r == 1 || r == 2) {
                this.ipoz = jatekos.getIpoz();
                this.jpoz = jatekos.getJpoz();
            }
        }
        return szamlalo;
    }

    /**
     * Attól függően, hogy hány papírja van a játékosnak meghívjuk a slenderman mozgásáért felelős metódusokat. Minden
     * 5.-ik körben a Slendermant a metódus véletlenszerű pozícióra teleportálja.
     * @param palya pálya amin mozog a slenderman
     * @param jatekos játékos, akinek az adataival dolgozunk
     */
     public void teleportal(Palya palya, Jatekos jatekos) {
         if (!jelenVan) {
             spawnol(palya, jatekos);
         } else {
             int ujipoz;
             int ujjpoz;

             if (jatekos.getMegtettLepesek() % 5 == 0) {
                 palya.tereptargyak[ipoz][jpoz] = palya.papirok[ipoz][jpoz];

                 ujipoz = (int) (Math.random() * 15) + 1;
                 ujjpoz = (int) (Math.random() * 15) + 1;
                 palya.tereptargyak[ipoz][jpoz] = palya.papirok[ipoz][jpoz];
                 this.ipoz = ujipoz;
                 this.jpoz = ujjpoz;
                 if(Lathato(jatekos, this)) {
                     palya.tereptargyak[ipoz][jpoz] = " X ";
                 }
             } else if (jatekos.getPapirok() < 2) {
                 szamlalo = nullaKetto(jatekos, szamlalo, palya);
             } else if (jatekos.getPapirok() >= 2 && jatekos.getPapirok() < 4) {
                 szamlalo = kettoNegy(jatekos, szamlalo, palya);
             } else if (jatekos.getPapirok() >= 4 && jatekos.getPapirok() < 6) {
                 szamlalo = negyHat(jatekos, szamlalo, palya);
             } else if (jatekos.getPapirok() >= 6) {
                 szamlalo = hatNyolc(jatekos, szamlalo, palya);
             }
         }
     }
    }




