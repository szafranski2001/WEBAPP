export enum genere{
    FPS,Sandbox,MMORPG,MOBA
}

export interface videogame{
    id:number,
    titolo:string,
    genere: genere,
    durata:number,
    anno:number,
    casaP:string,
    descrizione:string,
    valutazione:number
}
