export enum genere{
    FPS="fps",Sandbox="sandbox",MMORPG="mmorpg",MOBA="moba"
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
