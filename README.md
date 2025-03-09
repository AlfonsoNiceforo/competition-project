# Progetto Demo

## Descrizione
Questo è un progetto demo per dimostrare le mie competenze in Docker, Spring Boot e microservizi.
L’applicazione è un'API REST che restituisce dati sugli eventi di un ipotetica competizione in cui i giocatori partecipano in coppia, come statistiche sugli accoppiamenti dei giocatori e leaderboard e permette l'inserzione di nuove partite previa verifica dei giocatori e conseguente aggiornamento sia per leaderboard che statistiche tramite eventi con Kafka.

## Tecnologie utilizzate
- **Java + Spring Boot** (per l’API REST)
- **H2** (per il database in memoria)
- **Docker & Docker Compose** (per containerizzare l’app)
- **Kafka** (per la gestione di eventi tra microservizi)

## Come avviare il progetto
Requisiti: avere **Docker** installato
```sh
git clone https://github.com/mio-username/mio-progetto.git
cd mio-progetto
```

### Avviare i container
```sh
docker-compose up --build -d
```
L’app sarà disponibile su http://localhost:8080

## Endpoint API disponibili

| Metodo | Endpoint            | Descrizione                                                      | es. Payload                                                          |
| ------ | ------------------- | ---------------------------------------------------------------- | -------------------------------------------------------------------- |
| POST   | /game               | inserisce nuova partita                                          | {"playerName1" : "Maurizio", "playerName2" : "Pino", "time" : 120}   |
| GET    | /players            | restituisce lista di giocatori e percentuale di partecipazione   |                                                                      |
| GET    | /leaderboard        | restituisce la leaderboard dei migliori tempi della prestazione  |                                                                      |
| GET    | /statistiche        | restituisce le statistiche di accoppiamenti di tutti i giocatori |                                                                      |
| GET    | /statistiche/team   | restituisce statistiche relative ad una coppia di giocatori      | {"playerName1" : "Maurizio", "playerName2" : "Gianluca"}             |
| GET    | /statistiche/{Nome} | restituisce dati relativi agli accoppiamenti di un giocatore     |                                                                      |
