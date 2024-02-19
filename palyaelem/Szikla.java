package palyaelem;
import Palya.*;

/**
 * A szikla tereptárgy viselkedését leíró osztály
 */
public class Szikla extends Tereptargy {

    /**
     *
     * @param nev név
     * @param x x(i) koordináta
     * @param y y(j) koordináta
     * @param width szélesség
     * @param height magasság
     */
    public Szikla(String nev, int x, int y, int width, int height) {
        super(nev, x, y, width, height);
    }

    /**
     * Végigmegyünk a pálya elemein, és ellenőrizzük, hogy a
     * @param palya pálya, amin a Sziklas elhelyezkedését fogjuk vizsgálni. Ehhez a tereptárgy height és
     * witdth adattagjait használjuk. Megnézzük, hogy a tárgy height*width körzetében egyenlő-e a pálya a tereptárgy
     * szimbólumával
     */
    public static void add(Palya palya) {
        for (int i = 1; i < 15; i++) {
            for (int j = 1; j < 15; j++) {
                if (palya.tereptargyak[i][j].equals(" K ") && palya.tereptargyak[i + 2][j].equals(" K ") && palya.tereptargyak[i][j + 2].equals(" K ")) {
                    palya.elhelyezett.add(new Tereptargy("Szikla", i, j, 3, 3));
                }
            }
        }

    }
}
