# Exchange-app

### Üdvözöllek Kedves Látogató!

I'm assuming te kaptad javításra ezt a projektemet, ezt kicsit szeretném megkönnyíteni ezzel a dokumentummal.

#### App főbb funkciói: 
- Regisztráció
- Bejelentkezés anonymousként, Google fiókkal, regiszrált email címmel
- Összes user számára elérhető funkciók:
  - Valuták böngészése, árfolyam követése
  - Valuták közötti keresés (***KERESÉS*** ikon)
  - GUI elrendezés változtatása (***ELRENDEZÉS*** ikon)
  - Értesítések ki/be kapcsolása (óránként kijelzi az Euro áfolyamot ha az app meg van nyitva/háttérben fut, árfolyamos oldalon ***ÉRTESÍTÉSEK LETILTÁSA/ENGEDÉLYEZÉSE*** fül)
  - Kijelentkezés (***KIJELENTKEZÉS*** fül)
- Csak regisztrált + Google fiókos usernek érhető el:
  - Adatbázis manipulálása: törlés, módosítás, hozzáadás (Árfolyamos oldalon ***ADATOK*** fül)

#### Osztályok, feladatuk:
- AlarmReceiver, NotificationHandler: Értesítések kezelése
- RegistActivity: a Regisztráció activityje, regisztráció feladatának ellátása
- MainActivity: a Bejelentkezés activityje, bejelentkezés feladatának ellátása
- MyCurrency: Model osztály, a valuta modeljét írja le
- MyCurrencyAdapter: Adapter osztály, a valuta megjelenítésében, keresésben van feladata
- CurrenciesActivity: a valuták listázásának activityje, feladata a valuták megjelenítése
- DataMGMTActivity: az adatbázis módosításának activityje

### Resourceok, layoutok:
- activity_main: bejelentkezés activityje
- activity_regist: regisztráció activityje
- activity_data_mgmt: az adatbázis kezelésének activityje
- activity_currencies: a valuták listázásának activityje
- in_row, in_row_2: valuták kártyáinak animációi
- currency_menu: valuták megjelenítésekor a menüszalag resource-a


#### Jó javítást!
