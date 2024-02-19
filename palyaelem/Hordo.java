package palyaelem;
import Palya.*;

/**
 * A hordó tereptárgy viselkedését leíró osztály
 */
public class Hordo extends Tereptargy {

    /**
     *
     * @param nev név
     * @param x x(i) koordináta
     * @param y y(j) koordináta
     * @param width szélesség
     * @param height magasság
     */
    public Hordo(String nev, int x, int y, int width, int height) {
        super(nev, x, y, width, height);
    }

    /**
     * Végigmegyünk a pálya elemein, és ellenőrizzük, hogy a
     * @param palya pálya, amin a hordó elhelyezkedését fogjuk vizsgálni. Mivel olyan tereptárgyról van szó
     * ami vízszintesen és függőlegesen is elhelyezhető, így kétszer fogunk végigmenni a pálya 'tereptárgyak' adattagján
     * és megnézzük, hogy vízszintesen vagy függőlegesen el van-e helyezve ilyen objekum. Ehhez a tereptárgy height és
     * witdth adattagjait használjuk.
     */
    public static void add(Palya palya) {
        for (int i = 1; i < 16; i++) {
            for (int j = 1; j < 14; j++) {
                if (palya.tereptargyak[i][j].equals(" H ") && palya.tereptargyak[i + 1][j].equals(" H ") && palya.tereptargyak[i][j + 3].equals(" H ")) {
                    palya.elhelyezett.add(new Tereptargy("Hordo", i, j, 2, 4));
                }
            }
        }
        for (int i = 1; i < 14; i++) {
            for (int j = 1; j < 16; j++) {
                if (palya.tereptargyak[i][j].equals(" H ") && palya.tereptargyak[i + 3][j].equals(" H ") && palya.tereptargyak[i][j + 1].equals(" H ")) {
                    palya.elhelyezett.add(new Tereptargy("Hordo", i, j, 4, 2));
                }
            }
        }
    }

}
