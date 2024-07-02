
//interfaccia per la preview (card object) di un gioco
//informazioni necessarie da avere dal server
export interface SingleGameInfo {
  id: number;
  titolo: string;
  descrizione: string;
  genere: string;
  durata: number;
  anno: number;
  valutazione: number;
  trailer: string;
  casaP: string;
}
