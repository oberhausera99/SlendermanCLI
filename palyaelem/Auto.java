package palyaelem;
import Palya.*;

/**
 * Az autó tereptárgy viselkedését leíró osztály
 */
public class Auto extends Tereptargy {

    /**
     *
     * @param nev név
     * @param x x(i) koordináta
     * @param y y(j) koordináta
     * @param width szélesség
     * @param height magasság
     */
    public Auto(String nev, int x, int y, int width, int height) {
        super(nev, x, y, width, height);
    }



    /**
     * Végigmegyünk a pálya elemein, és ellenőrizzük, hogy a
     * @param palya pálya, amin az autó elhelyezkedését fogjuk vizsgálni. Mivel olyan tereptárgyról van szó
     * ami vízszintesen és függőlegesen is elhelyezhető, így kétszer fogunk végigmenni a pálya 'tereptárgyak' adattagján
     * és megnézzük, hogy vízszintesen vagy függőlegesen el van-e helyezve ilyen objekum. Ehhez a tereptárgy height és
     * witdth adattagjait használjuk.
     */
    public static void add(Palya palya) {
        for (int i = 1; i < 16; i++) {
            for (int j = 1; j < 15; j++) {
                if (palya.tereptargyak[i][j].equals(" A ") && palya.tereptargyak[i + 1][j].equals(" A ") && palya.tereptargyak[i][j + 2].equals(" A ")) {
                    palya.elhelyezett.add(new Tereptargy("Auto", i, j, 2, 3));
                }
            }
        }
        for (int i = 1; i < 15; i++) {
            for (int j = 1; j < 16; j++) {
                if (palya.tereptargyak[i][j].equals(" A ") && palya.tereptargyak[i + 2][j].equals(" A ") && palya.tereptargyak[i][j + 1].equals(" A ")) {
                    palya.elhelyezett.add(new Tereptargy("Auto", i, j, 3, 2));
                }
            }
        }
    }
    
    
    
    
}
