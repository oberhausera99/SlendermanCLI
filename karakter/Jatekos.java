package karakter;

import java.util.Scanner;
import Palya.*;


/**
 * A játékos karakteréért felelős műveleteket kezelő osztály. Adattagjai egész számok: ipoz és jpoz - a játékos i- és j
 * pozíciója a pályán, papirok - a megtalált papírok száma, megtettLepesek - a játékos által megtett lépések (körök) száma
 */
public class Jatekos extends Karakter {

    protected int ipoz;
    protected int jpoz;
    protected int papirok;
    protected int megtettLepesek;

    /**
     * Default konstruktorral rendelkezik, először a 0,0 pozíción tároljuk el, de nem jelenik meg a pályán.
     */
    public Jatekos() {
        this.ipoz = 0;
        this.jpoz = 0;
        this.papirok = 0;
        this.megtettLepesek = 0;

    }

    public int getIpoz() {
        return ipoz;
    }

    public int getJpoz() {
        return jpoz;
    }

    public int getPapirok() {
        return papirok;
    }

    public int getMegtettLepesek() { return megtettLepesek; }

    /**
     * A játékos léptetésében van szerepe. Ha a játékossal az i,j pozícióra szeretnénk lépni, ellenőrzi hogy nem-e
     * foglalja azt el egy akadályt képező tereptárgy
     * @param palya a pálya amin a játékos el lett helyezve
     * @param i a játékos i-pozíciója pályán
     * @param j a játékos j- pozíciója a pályán
     * @return lehetséges-e a megadott pozícióra lépni
     */
    public boolean ellenoriz(Palya palya, int i, int j) {
        if((palya.eredeti[i][j].equals("   ") || palya.eredeti[i][j].equals(" f ") || palya.eredeti[i][j].equals(" F ")) && !palya.tereptargyak[i][j].equals(" X ")){
            return true;
        } else {
            return false;
        }
    }

    /**
     * A kezdeti 0,0 pozíciót véletlenszerűen a pálya egyik sarkára állítja. Ellenőrzi azt is, hogy elhelyezhető-e ott
     * a játékos, azaz nincs-e ott akadályt képező tereptárgy
     * @param palya a pálya ahová a játékost spawnolni szeretnénk.
     */
    public void kezdoPozicio(Palya palya) {

       while(ipoz < 1) {
           int sarok = (int) (Math.random() * 4) + 1;
           if (sarok == 1) {
               if (ellenoriz(palya, 1, 1)) {
                   ipoz = 1;
                   jpoz = 1;
                   palya.tereptargyak[ipoz][jpoz] = " O ";
               }
           } else if (sarok == 2) {
               if (ellenoriz(palya, 1, 15)) {
                   ipoz = 1;
                   jpoz = 15;
                   palya.tereptargyak[ipoz][jpoz] = " O ";
               }
           } else if (sarok == 3) {
               if (ellenoriz(palya, 15, 1)) {
                   ipoz = 15;
                   jpoz = 1;
                   palya.tereptargyak[ipoz][jpoz] = " O ";
               }
           } else if (sarok == 4) {
               if (ellenoriz(palya, 15, 15)) {
                   ipoz = 15;
                   jpoz = 15;
                   palya.tereptargyak[ipoz][jpoz] = " O ";
               }
           }
       }

    }

    /**
     * Ha a játékos egy papírt tartalmazó tereptárgy mellé ér, akkor megtalál egy papírt. Ekkor a tereptárgy játékos
     * melletti mezője "P"-szimbólumra vált. Ezzel jelezzük, hogy ott már megtaláltuk a papírt. Ekkor az aktuális
     * tereptárgyat eltávolítjuk a Pálya 'elhelyezett' listájából, hiszen az adott tárgyon már nem található papír, és
     * növeljük a 'papirok' adattagok 1-el.
     * @param palya a pálya ahol a papírokat meg akarjuk találni
     */
    public void papirtFeltar(Palya palya) {
        for (int i = 0; i < palya.elhelyezett.size(); i++) {
            int magassag = palya.elhelyezett.get(i).height;
            int szelesseg = palya.elhelyezett.get(i).width;

            for (int j = 0; j < szelesseg; j++) {
                for (int k = 0; k < magassag; k++) {
                    if (manhattan(palya.elhelyezett.get(i).x + j, palya.elhelyezett.get(i).y + k, this.ipoz, this.jpoz) == 1) {
                        palya.tereptargyak[palya.elhelyezett.get(i).x + j][palya.elhelyezett.get(i).y + k] = " P ";
                        palya.papirok[palya.elhelyezett.get(i).x + j][palya.elhelyezett.get(i).y + k] = " P ";
                        this.papirok++;
                        palya.elhelyezett.remove(i);
                    }
                }
            }
        }
    }

    /**
     * A játékos mozgásához használjuk ezt a metódust. A paraméterben megadott értéktől függően fog a játékos
     * fel/le/jobbra/balra irányba mozogni.
     * @param irany - w/s/d/a betű, attól függően hogy merre akarunk mozogni
     * @return egy intekből álló tömb, aminek az első eleme a beolvasott karakterből kialakított úk i-pozíció, a
     * második pedig a j.
     */
        public int[] pozicional(char irany) {
        int[] eredmeny = new int[2];
        int iirany = this.ipoz;
        int jirany = this.jpoz;
            if(irany == 'w') {
                iirany -= 1;
            } else if(irany == 's') {
                iirany += 1;
            } else if(irany == 'd') {
                jirany += 1;
            } else if(irany == 'a') {
                jirany -= 1;
            }
            eredmeny[0] = iirany;
            eredmeny[1] = jirany;
            return eredmeny;
        }

    /**
     * Bescannel egy irányjelző karaktert, majd ez alapján fogja a karakter pozícióját megváltoztatni. Fel van készülve
     * helytelen felhasználői inputokra is, ellenőrzi, hogy a megadni kívánt új pozícióra rá lehet-e lépni, illetve hogy
     * jó értéktartományból adott-e meg a játékos értéket a beolvasásnál. Mivel a metódus a Main osztályból lesz meghívva,
     * ezért futás közben egy hibaüzenettel tér vissza. Ha a felhasználó érvényes inputot adott meg, akkor ez egy üres string
     * ha nem, akkor egy hibaüzenet
     * @param palya pálya, amin a játékos mozogni akar
     * @return az inputtól függő hibaüzenet
     */
    public String mozog(Palya palya) {
                int ujipoz, ujjpoz;
                String hibauzenet = "";
            Scanner scan = new Scanner(System.in);
            System.out.println("Merre akarsz tovabblepni?(a/s/w/d)");
            char irany = scan.next().charAt(0);
            if(irany != 'a' && irany != 'w' && irany != 's' && irany != 'd') {

                hibauzenet = "Hiba! Ervenytelen irany";
            } else {
                if (ellenoriz(palya, pozicional(irany)[0], pozicional(irany)[1])) {
                    palya.tereptargyak[ipoz][jpoz] = palya.papirok[ipoz][jpoz];
                    ujipoz = pozicional(irany)[0];
                    ujjpoz = pozicional(irany)[1];
                    if (palya.tereptargyak[ujipoz][ujjpoz].equals(" P ")) {
                        if (ellenoriz(palya, ujipoz, ujjpoz)) {
                            this.ipoz = ujipoz;
                            this.jpoz = ujjpoz;
                        }
                    }

                    this.ipoz = ujipoz;
                    this.jpoz = ujjpoz;
                    palya.tereptargyak[this.ipoz][this.jpoz] = " O ";
                    this.megtettLepesek++;

                } else {

                    hibauzenet = "Hiba! Nem lephetsz ide";
                }
            }
                return hibauzenet;

        }




}
