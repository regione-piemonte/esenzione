# Componente di Prodotto

ESENREDSRV

## Descrizione del prodotto

E' un'api rest che espone ad Aura i servizi per l'aggiornamento delle esenzioni da reddito.

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
esenredsrv-1.0.0

## Authors

* [Maurizio Chiappo](https://github.com/maurizio-chiappo)

## Copyrights

“© Copyright Regione Piemonte – 2022”