import { genere } from "./genere";

export interface spettacolo{
    id: number,
    genere: genere,
    titolo: string,
    attore: string,
    durata: number,
    atti: number,
    anno: number,
    descrizione: string;
}