export enum genere{
    FPS="FPS",Sandbox="SandBox",MMORPG="MMORPG",MOBA="MOBA",ACTIONRPG="ActionRPG",Racing="Racing",Sport="Sport",PlatForm="Platform",Survival="Survival",Strategy="Strategy"
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
