# EpicEnergyServices
 progetto finale
Abbiamo realizzato il backend di un sistema CRM per un'azienda fornitrice di energia, denominata "EPIC ENERGY SERVICES", che vuole gestire i contatti con i propri clienti business.
Il sistema basato su Web Service REST Spring Boot e database PostgreSQL , permette di gestire un elenco dei clienti.
I comuni sono gestiti attraverso un'anagrafica centralizza e sono caratterizzati da un nome e da un riferimento ad una provincia, anch'essa gestita in anagrafica centralizzata e caratterizzata da un nome ed una sigla. 
Associato ad ogni cliente c'è un insieme di fatture ed ogni fattura ha uno stato. 
Il progetto prevede inoltre un sistema di autenticazione e autorizzazione basato su token JWT che permetta a diversi utenti di accedere alle funzioni del backend e di registrarsi al sistema.

-funzionalità del progetto-
autenticazione:
  accedere tramite admin, admin
          user, user (abilitato alle sole operazioni di lettura)
 o registrarsi con username, email, password e specificare il ruolo come admin o user.

-inserimento il cliente-
prima d'inserire un cliente bisogna inserire due indirizzi senza id:
{
  "via": "via uitui",
  "civico": 20,
  "localita": "yigy",
  "cap": 8795,
  "comune": {
    "id": 113,
    "provincia": {
      "id": 97
    }
  }
}
        










