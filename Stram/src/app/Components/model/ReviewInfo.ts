import { StateSegnalazioni } from "./ReportStatus"

export interface reviewLikeInfo{
    usernameMittente: string,
    idVideogioco : number,
    usernameDestinatario: string
}

export interface reviewReportInfo{
    stato : StateSegnalazioni,
    mittente: string,
    destinatario: string,
    idVideogioco : number,
}