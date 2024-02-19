package palyaelem;
import Palya.*;

/**
 * A ház tereptárgy viselkedését leíró osztály
 */
public class Haz extends Tereptargy {
    /**
     *
     * @param nev név
     * @param x x(i) koordináta
     * @param y y(j) koordináta
     * @param width szélesség
     * @param height magasság
     */
    public Haz(String nev, int x, int y, int width, int height) {
        super(nev, x, y, width, height);
    }

    /**
     * Végigmegyünk a pálya elemein, és ellenőrizzük, hogy a
     * @param palya pálya, amin a ház elhelyezkedését fogjuk vizsgálni. Mivel olyan tereptárgyról van szó
     * ami vízszintesen és függőlegesen is elhelyezhető, így kétszer fogunk végigmenni a pálya 'tereptárgyak' adattagján
     * és megnézzük, hogy vízszintesen vagy függőlegesen el van-e helyezve ilyen objekum. Ehhez a tereptárgy height és
     * witdth adattagjait használjuk.
     */
    public static void add(Palya palya) {
        for (int i = 1; i < 15; i++) {
            for (int j = 1; j < 14; j++) {
                if (palya.tereptargyak[i][j].equals(" . ") && palya.tereptargyak[i + 1][j].equals("   ") && palya.tereptargyak[i + 2][j].equals(" . ")) {
                    palya.elhelyezett.add(new Tereptargy("Haz", i+1, j+3, 2, 2));
                }
            }
        }
    }
}
