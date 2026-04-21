# DrugDose

DrugDose è un'applicazione Android sviluppata nell'ambito del corso di **Programmazione di Dispositivi Mobili** presso l'**Università dell'Insubria**, dallo studente Gobessi Emanuele, matricola: 757599.

L'obiettivo del progetto è realizzare un'app in grado di supportare il **calcolo automatico del dosaggio farmacologico** a partire dai parametri del paziente e da regole terapeutiche predefinite. L'applicazione è progettata per offrire un'interfaccia semplice e comprensibile anche a utenti con conoscenze farmaceutiche limitate, mantenendo al tempo stesso ordine nella gestione dei dati e correttezza nei calcoli.

## Obiettivo del progetto

DrugDose nasce con lo scopo di aiutare l'utente nella stima della dose suggerita di un farmaco sulla base di informazioni come:

- peso corporeo
- altezza
- età
- regola di dosaggio associata al farmaco selezionato

L'app supporta diverse tipologie di calcolo, tra cui:

- dosaggio basato sul peso corporeo
- dosaggio basato sulla superficie corporea
- dose fissa
- dosaggio per fasce di peso

## Organizzazione dei contenuti

A livello strutturale, l'app organizza i farmaci a partire dal **principio attivo**, così da mantenere una base dati più ordinata e coerente rispetto a una semplice lista di nomi commerciali.

Per migliorare l'esperienza utente, ogni principio attivo può contenere più **farmaci commerciali associati**, in modo che l'utente possa selezionare più facilmente il prodotto che possiede o conosce già.

## Ricerca del farmaco

Poiché l'utente potrebbe non conoscere il principio attivo del farmaco che sta cercando, la schermata di selezione include una **barra di ricerca** che consente di cercare direttamente un farmaco per nome.

In questo modo l'utente può:

- navigare normalmente tramite principio attivo
- cercare direttamente il farmaco commerciale che conosce

## Funzionalità principali

- organizzazione dei dati per principio attivo
- visualizzazione dei farmaci associati a ciascun principio attivo
- ricerca diretta di un farmaco tramite nome
- inserimento dei parametri corporei del paziente
- calcolo automatico del dosaggio
- supporto a più formule di calcolo
- visualizzazione chiara del risultato finale
- gestione di vincoli, limiti e possibili alert

## Funzionalità secondarie

- possibilità di salvare i parametri corporei dell'utente, così da evitare reinserimenti ripetuti
- controlli di validazione sugli input inseriti
- visualizzazione di eventuali avvisi, limiti o controindicazioni
- associazione del dosaggio a una fonte clinica o regolatoria verificabile

## Struttura dei dati

L'applicazione è pensata per utilizzare una base dati locale, in formato JSON, contenente informazioni come:

- principio attivo
- nome del farmaco
- descrizione o utilizzo principale
- formula di dosaggio
- unità di misura
- limiti minimi e massimi
- eventuali alert
- fonte del dosaggio

## Nota importante

DrugDose è un progetto didattico. I dosaggi mostrati per quanto associati a fonti cliniche o regole verificabili, sono comunque frutto di formule matematiche. L'app ha finalità educative e dimostrative e non sostituisce il parere di un medico o di un professionista sanitario.
