package palyaelem;
import Palya.*;

/**
 * A teherautó tereptárgy viselkedését leíró osztály
 */
public class Teherauto extends Tereptargy {
    /**
     *
     * @param nev név
     * @param x x(i) koordináta
     * @param y y(j) koordináta
     * @param width szélesség
     * @param height magasság
     */
    public Teherauto(String nev, int x, int y, int width, int height) {
        super(nev, x, y, width, height);
    }

    /**
     * Végigmegyünk a pálya elemein, és ellenőrizzük, hogy a
     * @param palya pálya, amin a teherautó elhelyezkedését fogjuk vizsgálni. Mivel olyan tereptárgyról van szó
     * ami vízszintesen és függőlegesen is elhelyezhető, így kétszer fogunk végigmenni a pálya 'tereptárgyak' adattagján
     * és megnézzük, hogy vízszintesen vagy függőlegesen el van-e helyezve ilyen objekum. Ehhez a tereptárgy height és
     * witdth adattagjait használjuk.
     */
    public static void add(Palya palya) {
        for (int i = 1; i < 15; i++) {
            for (int j = 1; j < 13; j++) {
                if (palya.tereptargyak[i][j].equals(" T ") && palya.tereptargyak[i + 2][j].equals(" T ") && palya.tereptargyak[i][j + 4].equals(" T ")) {
                    palya.elhelyezett.add(new Tereptargy("Teherauto", i, j, 3, 5));
                }
            }
        }
        for (int i = 1; i < 13; i++) {
            for (int j = 1; j < 15; j++) {
                if (palya.tereptargyak[i][j].equals(" T ") && palya.tereptargyak[i + 4][j].equals(" T ") && palya.tereptargyak[i][j + 2].equals(" T ")) {
                    palya.elhelyezett.add(new Tereptargy("Teherauto", i, j, 5, 3));
                }
            }
        }
    }
    
}
