export enum genere{
    FPS="FPS",Sandbox="SandBox",MMORPG="MMORPG",MOBA="MOBA"
}

export interface videogame{
    id:number,
    titolo:string,
    genere: genere,
    durata:number,
    anno:number,
    casaP:string,
    descrizione:string,
    valutazione:number,
    trailer: string
}
