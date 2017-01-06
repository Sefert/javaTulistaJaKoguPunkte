# Projekt by Marko Mõznikov

##<b>Licence</b><br />
<img src="https://licensebuttons.net/l/by-nd/3.0/88x31.png" alt="LLicence"><br />
<b>This work is licensed under a <a href="https://creativecommons.org/licenses/by-nd/4.0/legalcode">Attribution-NoDerivatives 4.0 International</a> licence (CC BY-ND 4.0)</b><br />

##<b>Juhend mängule</b><br />

Andmebaasi kasutamiseks fili sqlite-jdbc-3.14.2.1.jar parema kliki alt Add as Library
Laev liigub hiirt liigutades<br />
Tulistada saab vasaku hiirenupu alt<br />
Punktisumma on vasakul üleval ääres<br />
Iga 5000 punkti tagant muutub laev lillaks ja saab kasutada kaitsekilpi, mis kestab 10 sekundit-parema kliki all<br />
Iga 30s tagant muutub mäng kiiremaks. Max kiirusaste 8x.<br />
Vastase pihta ei tohi minna-ainult 1 elu.<br />

Music (and/or Sound Effects) by Eric Matyas

www.soundimage.org

##Kasutatud abimaterialid<br />

<a href="http://stackoverflow.com/questions/13455326/javafx-tableview-text-alignment">CSS abimaterial</a><br />
<a href="http://www.w3schools.com/">CSS , andmebaasi abimaterial</a><br />
<a href="http://i200.itcollege.ee/">Java abimaterialid</a><br />
<a href="https://www.tutorialspoint.com/sqlite/sqlite_java.htm">Andmebaasi abimaterialid I</a><br />
<a href="http://docs.oracle.com/javafx/2/ui_controls/table-view.htm">Andmebaasi abimaterialid II</a><br />
<a href="http://sqlitebrowser.org/">Andmebaasi abimaterialid III</a><br />
<a href="https://docs.oracle.com/javase/tutorial/jdbc/basics/processingsqlstatements.html">Andmebaasi abimaterialid IV</a><br />
<a href="https://blog.idrsolutions.com/2014/04/use-external-css-files-javafx/">Mediaplayeri abimaterialid</a><br />
<a href="http://grinder.sourceforge.net/g3/script-javadoc/HTTPClient/URI.html">URI seletus</a><br />
<a href="https://dzone.com/articles/javafx-collections-observablelist-and-observablema">Observable list seletus</a><br />

## Niisiis idee:

Lihtne mugandatud vanaaegne mäng, kus lendad klotisga ringi ja tulistasid teisi vastu lendavaid klotse.<br />
Kasutaja saab sisestada oma nime ning nimi salvestatakse sqlite anmebaasi.<br />
Graafika poolele ei pööra üldse mingit tähelepanu. Ainult lendavad kolmnurgad, nelinurgad, ringid jne.<br />
Pilt liigub ülevalt alla ja laev on suunatud pidevalt ainult ülesse.<br />
Laev ei näe korraga kogu pilti, vaid ainult teatud raadiusega ümber oma tsentri.<br />
Kasutaja saab liigutada laeva kogu screeni ulatuses.<br />
Laev järgib hiire liikumist ekraanil.<br />
Vasak hiire click paneb laeva tulistama.<br />
Parem hiire click tekitab effekti, kus kogu pilt ekraanil muutub mingiks hetkeks nähtavaks ja lava ümber tekib klotse hävitav barjäär.<br />
Kasutada saab effekti vaid teatud aja möödudes.<br />
Ülevalt ekraani servast tulistatakse laeva suunas kujundeid, kujundimustreid.<br />
Näiteks diagonaalis rivi üle ekraani või keerlev ajas suurenev kujundikombinatsioon jne.<br />
Laev peab nende eest põgenema ja hävitama neid võimalikult palju ning hoiduma kokkupõrkest.<br />
Erilise tulistamismustri jätab esialgu välja ning lendavad klotsid ei tulista vastu, vaid omavad mingit liikumismustrit.<br />
Elusid on ainult 1.<br />
Mäng käib parimale puntisummale - "score" <br />
Eesmärgiks on jõuda edetabelis parimale kohale.<br />
Punkte saab vastavalt hävitatud klotsidele.<br />
Klotside poolt antud punktid suurenevad vastavalt kiirusele.<br />
Liikumine läheb pidevalt kiiremaks, kuni "game over"<br />
Tulemus salvestatakse andmebaasi nime juurde.<br />
Tulemusi saab vaadata edetabelist.<br />

## Tehtud toimingud:

/02.10.2016/<br />
Raamistik ja raami sisu algus.<br />
/25.10.2016/<br />
Joonista midagi fxis.<br />
/28.10.2016/<br />
Login aken + laev + laeva liikumine + tulistamise algus + andmebaasi algus<br />
/29.10.2016/<br />
Kuuli animatsioon, 1ksiku vastase genereerimine, score counter, kokkupõrke algus<br />
/30.10.2016/<br />
Üleminek Animationtimer süsteemile<br />
/31.10.2016/<br />
Üleminek objektorjenteeritusele<br />
/13.11.2016/<br />
Parandused objektorjenteerituses/<b>toimiv versioon 1 vastasega</b><br />
/04.12.2016/<br />
Array vastasega<br />
/05.12.2016/<br />
Minifix erroritele/toimiv laeva collision vastasega/bugine multi tulistamise võimalus<br />
/06.12.2016/<br />
Toimiv tulistamine mitu korda klikkides<br />
Bug - aegajalt on game over nähtamatule arraylistile pihta minnes<br />
Bug - mängu veider aeglustumine ja fps drop<br />
/07.12.2016/<br />
Kiiruse muut ajas/vastase hajusus arrays/random vastase suurus antud vahemikus<br />
Bug - aegajalt on game over nähtamatule arraylistile pihta minnes<br />
Bug - mängu veider aeglustumine ja fps drop<br />
/08.12.2016/<br />
Koodi lihtsustamine/vastasse arraylist jookseb pidevalt ilma katkestuseta/lisatud vastase resistsus<br />
/lisatud paremkliki alla shield mis tekib ümber laeva 10-ks sekundiks. Shieldi saab tekitada, kui laev on muutunud lillaks.<br />
 Lilla värvus tekib hetkel iga 3000 punkti järel. Shieldiga saab vaid pooled punktid<br />
/lisatud collision shieldiga.
Bug - tuvastatud läbi tulistamise visuaalselt, et aegajalt genereeritakse või jääb listi alles nähtamatu vaenlane. Põhjus - unknown<br />
Bug - mängu FPS drop rate, ilmselt vaja kuidagi arvutada systeemne FPS<br />
/09.12.2016/<br />
FPS optimiseerimine/shield punktide muudatus 100-le/max speed vähendatud 2x<br />
/21.12.2016/<br />
Toimiv andmebaas/visuaalsus/optimiseeritud vastane<br />
/28.12.2016/<br />
Vähendatud tekkivat spagetti/lisatud 2 vastane/lisatud liikumismuut<br />
/29.12.2016/<br />
Lisatud sound/pildid<br />
/01.01.2017/<br />
Väike failsafe<br />
/03.01.2017/<br />
Mute helile<br />
