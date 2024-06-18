
//interfaccia per la preview (card object) di un gioco
//informazioni necessarie da avere dal server
export interface SingleGameInfo {
  rank: number;     //the svg-number related to show
  id: number;       //01
  name: string;     //Minecraft
  imgUrl: string;   //localhost:server/name.png
  rate: number;     //3 (su 5)
  isFavourite: boolean;
}
