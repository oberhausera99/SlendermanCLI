A pályán lévő tereptárgyak a következő szimbólumokkal vannak jelölve a programban:
"   " - fű (szóköz)
" F " - nagy méretű fa
" f " - kis méretű fa
" A " - autó
" T " - teherautó
" * " - pályaszegély. Nem része a pályának, nem lehet rálépni. Csak azért van, hogy lehessen látni, hol a pálya széle
" . " - házfal. Magát a ház tereptárgyat veszi körül. Ezen belül található papír
" K " - szikla(kő)
" H " - hordó
" O " - a játékos pozícióját jelző szimbólum
" X " - a slenderman pozícióját jelző szimbólum
" P " - ha megtaláltál egy papírt az adott tereptárgyon, akkor a pozíció, ahol a papír volt átvált "P"-re. Ezzel jelzi, hogy azon a tereptárgyon már
	megtaláltad a papírt. Ha a "P" szimbólum egy járható tereptárgyon jelenik meg, akkor rá lehet lépni, egyébként nem


A main metódusban ki van kommentezve pár sor. Ezek a program tesztelésében nyújthatnak segítséget, ha benne hagyod. Kiírják azokat a tereptárgyakat a pozíciójukkal,
ahol még nem találtad meg a papírt, a játékos és a slenderman pozícióját, a játékos által megtett lépések számát, és ezt hogy egymás után hányszor volt 1 a slenderman és a játékos közti távolság

Terminálból futtatva (java Main) meg van oldva a képernyő folyamatos törlése, így ott jobban átlátható

A saját pálya betöltését sajnos nem tudtam hibátlanul megoldani. A Palya osztály konstruktora egy előre megírt map.txt fájlt vár, ez a default pálya. Ha saját pályát akarsz létrehozni, akkor ezt módosíthatod.
A pályán található tereptárgyak szimbólumai elrendezés szerint vesszővel elválasztva vannak leírva.
Viszont nem fog minden inputra helyesen működni. 