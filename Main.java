import java.io.*;
import palyaelem.*;
import Palya.*;
import karakter.*;

public class Main{

    /**
     * ha terminálból futtatod a programot, akkor folyamosan törli a képernyőt, hogy átláthatóbb legyen
     */
    public static void clrscr(){
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } catch (IOException | InterruptedException ex) {}
    }

    /**
     * Magát a játékmenetet leíró metódus. Itt képződnek az alap objektumok, és hívódnak meg a hozzájuk tartozó metódusok
     * Ha a slenderman pozíciója megegyezik a játékoséval vagy a játékos papírjainak száma eléri a 8-at, akkor a játék véget ér.
     * @param args parancssori argumentumok
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        boolean futas = true;
        String hibauzenet = "";
        Palya palya = new Palya();
        Jatekos ember = new Jatekos();
        palya.tereptargyatListaz(palya);
        palya.papirtKioszt();
        Slenderman slenderman = new Slenderman();
        ember.kezdoPozicio(palya);
        System.out.println(palya);
        while (futas == true) {
            hibauzenet = ember.mozog(palya);
            if(hibauzenet.length() == 0) {
                slenderman.spawnol(palya, ember);
                slenderman.teleportal(palya, ember);
            }
            ember.papirtFeltar(palya);
            clrscr();
            System.out.println(palya);
            System.out.println(hibauzenet);
            System.out.println("Megtalalt papirok: " + ember.getPapirok() + " / 8");
           /* System.out.println("" +
                    "" +
                    "Manhattan: " + Karakter.Manhattan(ember.getIpoz(), ember.getJpoz(), slenderman.ipoz, slenderman.jpoz));
            System.out.println("szamlalo: " + slenderman.szamlalo);
            System.out.println("megtett lepesek: " + ember.getMegtettLepesek());
            System.out.println("A slenderman pozicioja: "  + slenderman.ipoz + " " + slenderman.jpoz);
            System.out.println("A jatekos pozicioja: " + ember.getIpoz() + " " + ember.getJpoz());
            palya.kiiratas(); */
        if (ember.getPapirok() == 8) {
            System.out.println("Gratulalok! Megtalaltad az osszes papirt!");
            futas = false;
        }
         else if(ember.getIpoz() == slenderman.getIpoz() && ember.getJpoz() == slenderman.getJpoz()) {
            System.err.println("A Slenderman elkapott. A jatek veget ert");
            futas = false;
            break;
        }
    }
    }

}