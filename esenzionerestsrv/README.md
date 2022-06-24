# Componente di Prodotto

ESENZIONERESTSRV

## Descrizione del prodotto

Sono i servizi utilizzati da APISAN per la scelta delle autocertificazioni da reddito e per la scelta delle esenzioni da patologia.

## Configurazioni iniziali

E' necessario che il file di properties sia configurato affinchè punti a dei servizi mock generati secondo i descrittori.

## Prerequisiti di sistema

Java: Jdk 1.8.0_73

ANT: Ant version 1.8.0

Server Web: Apache 2.4.53

Application Server: JBoss eap 6.4.5 CP07

Tipo di database: postgres 9.6.10 

## Installazione

lanciare il comando ant -Dtarget prod per generare l'ear

## Deployment

Inserire il file ear generato durante l'installazione sotto la cartella deployments del Jboss

## Versioning

Per il versionamento del software si usa la tecnica Semantic Versioning (http://semver.org).
esenzionerestsrv-2.1.0

## Authors

* [Maurizio Chiappo](https://github.com/maurizio-chiappo)

## Copyrights

“© Copyright Regione Piemonte – 2022”