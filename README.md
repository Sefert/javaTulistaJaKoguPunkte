# Niisiis idee:
## Projekt by Marko Mõznikov
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
