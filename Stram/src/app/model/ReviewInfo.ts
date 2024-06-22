
export enum TypeInfo{
    like="/GetLikes/",report="/GetReports/"
}

export enum GiudizioSegnalazione{
  Corretta,Errata
}

export enum StatoSegnalazione{
  NuovaSegnalazione="NUOVA SEGNALAZIONE",
  InLavorazioneSegnalazione="IN LAVORAZIONE",
  CompletataSegnalazione="COMPLETETA"
}

export interface reviewInfo{
    mittente: string | null,
    destinatario: string,
    idVideogioco : number,
    stato?: string | null
}