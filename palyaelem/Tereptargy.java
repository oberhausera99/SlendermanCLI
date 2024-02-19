package palyaelem;
import Palya.*;

/**
 * A tereptárgyak kezelésére létrehozott osztály. A tereptárgyakról eltároljuk azok nevét (nev), x és y koordinátáját, amik
 * a tömbindexelés során j-nek és i-nek felelnek meg, valamint a magasságát és szélességét (height és width). Ebből az osztályból
 * származik a pályán elhelyezkedő tereptárgyak összes típusa. A kis fának viszont nem csináltam külön gyerekosztályt,
 * mivel nem tartalmaz papírt és nem képez akadályt, így lényegében annyi a gyakorlati szerepe, mint egy alap fű mezőnek.
 */
public class Tereptargy {
    public String nev;
    public int x;
    public int height;
    public int width;
    public int y;

    /**
     *
     * @param nev a tereptárgy neve
     * @param x   x koordináta
     * @param y   y koordináta
     * @param width szélesség
     * @param height magasság
     */
    public Tereptargy(String nev, int x, int y, int width, int height) {
        this.nev = nev;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /**
     * A tereptárgy kiiratására szolgáló metódus. A main metódusban egyfajta segítő metódusként van feltüntetve
     * @return a tereptárgy tulajdonságaival való kiiratása
     */
        public String toString () {
            String eredmeny = "";
            eredmeny = eredmeny + this.nev + " " + x + " " + y;
            return eredmeny;
        }




    }

