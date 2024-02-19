package palyaelem;
import Palya.*;

/**
 * A fa tereptárgy viselkedését leíró osztály
 */
public class Fa extends Tereptargy {
    /**
     * @param nev név
     * @param x x(i) koordináta
     * @param y y(j) koordináta
     * @param width szélesség
     * @param height magasság
     */
    public Fa(String nev, int x, int y, int width, int height) {
        super(nev, x, y, width, height);
    }

    /**
     * Végigmegyünk a pálya elemein, és ellenőrizzük, hogy a
     * @param palya pálya, amin a Fa elhelyezkedését fogjuk vizsgálni. Ehhez a tereptárgy height és
     * witdth adattagjait használjuk. Megnézzük, hogy a tárgy height*width körzetében egyenlő-e a pálya a tereptárgy
     * szimbólumával
     */
    public static void add(Palya palya) {
        for (int i = 1; i < 16; i++) {
            for (int j = 1; j < 16; j++) {
                if (palya.tereptargyak[i][j].equals(" F ") && palya.tereptargyak[i + 1][j].equals(" F ") && palya.tereptargyak[i][j + 1].equals(" F ")) {
                    palya.elhelyezett.add(new Fa("Fa", i, j, 2, 2));
                }
            }
        }
    }
}
