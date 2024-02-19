package karakter;

/**
 * a játékban előforduló karaktereket leíró osztály
 */
public class Karakter {

private int jpoz;
private int ipoz;

    /**
     * a Slenderman és a Jatekos osztály származnak ebből. Közös metódusuk a manhattan, ami a két karakter közti
     * manhattan távolságot számítja ki
     * @param x1 az első objektum x koordinátája
     * @param y1 az első objektum y koordinátája
     * @param x2 a második objektum x koordinátája
     * @param y2 a második objektum y koordinátája
     * @return a 2 obektum közti Manhattan-távolság
     */
    public static int manhattan(int x1, int y1, int x2, int y2) {
        return (Math.abs(x1-x2) + Math.abs(y1-y2));
    }

}
