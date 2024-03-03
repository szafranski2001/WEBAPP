import { ReportStatus } from "./ReportStatus"

export interface reviewLikeInfo{
    usernameMittente: string,
    idVideogioco : number,
    usernameDestinatario: string
}

export interface reviewReportInfo{
    mittente: string,
    destinatario: string,
    videogioco : number,
    stato : ReportStatus
}