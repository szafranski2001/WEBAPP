
export enum TypeInfo{
    like="/GetLikes/",report="/GetReports/"
}

export interface reviewInfo{
    mittente: string | null,
    destinatario: string,
    idVideogioco : number
}