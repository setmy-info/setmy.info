| BPMN element / task                              | Muutujad / input / output                                                          | Kirjeldus / eesmärk                                        | Potentsiaalne probleem / kontroll                                           | Märkused                                                          |
|--------------------------------------------------|------------------------------------------------------------------------------------|------------------------------------------------------------|-----------------------------------------------------------------------------|-------------------------------------------------------------------|
| `fetchCSVFileNameList`                           | `directoryName` (input), `csvFileNames` (output)                                   | Loeb CSV failid kaustast, tagastab nimed listina           | Veendu, et input kausta nimi tuleb õigesti                                  | Java handler peab outputiks andma listi stringidest               |
| `FilesProcessing` (MI sequential)                | `csvFileNames` (input collection), `csvFileName` (elementVariable)                 | Itereerib failide kaupa                                    | MI peab olema sequential; kontrolli, et input collection on korrektne       | Iga faili puhul käivitatakse subproces                            |
| `csvToH2Db`                                      | `csvFileName` (input), taskHeader `splitSize`                                      | Loeb CSV, kirjutab H2 DB faili                             | Veendu, et DB nimi või tabeli nimi kaardub õigesti                          | Java handler saab splitSize, et portsioniteks jagada              |
| `generatePortionsList`                           | `dbName` (input), `portionSize`                                                    | Loob portsionite listi DB-st                               | PortionSize saab määrata BPMN IO mapping või jobHeader kaudu                | Output peaks olema list portsioni ID-sid (JSON array)             |
| `dbToUUIDPortions` (MI sequential)               | `portionsIds` (input collection), `portionsId` (elementVariable), `dbName` (input) | Igale portsionile määratakse UUID, valmistatakse JSON read | Kontrolli, et portionsIds on tegelikult olemas ja korrektne formaat         | Java handler peab looma JSON read portioni sees                   |
| `portionToJSON`                                  | `portionId` (input), `dbName` (input), output: `jsonRows`?                         | Valmistab portsioni read JSON formaati                     | Veendu, et output collection (`jsonRows`) on defineeritud MI parallel jaoks | outputCollection peab olema seatud                                |
| `JSON portion parallel processing` (MI parallel) | `jsonRows` (input collection), `jsonRow` (elementVariable)                         | Töötleb iga CSV rea paralleelselt                          | Veendu, et MI parallel on seatud, inputCollection olemas                    | Java handler saab ühe rea (`jsonRow`) korraga                     |
| `portionSize`                                    | input taskHeader või ioMapping                                                     | Portsioni suurus                                           | Veendu, et väärtus määratakse jooksu ajal, mitte ainult BPMN xml            | Võib määrata ka käivitamisel                                      |
| `dbName`                                         | input/output                                                                       | DB fail, kuhu read kirjutatakse                            | Kõik MI taskid peavad nägema sama dbName muutujat                           | Soovitatav kasutada process-variable, mitte ainult local-variable |

Märkused:

IO Mapping peab olema kooskõlas Java handleri parameetritega.

Output Collection MI parallel taskide jaoks peab olema seatud (jsonRows) — muidu MI ei käivitu paralleelselt.

Sequential MI on failide ja portsionite jaoks õige valik.

TaskHeaders nagu splitSize on alternatiiv ioMappingule, aga veendu, et Java handler neid oskab lugeda.

Kõik muutujad, mida mitu taski kasutab (nt dbName), peaksid olema process-level variables, et neid näeksid ka alam-subprocesid.
