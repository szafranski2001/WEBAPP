import { ReportStatus } from "./ReportStatus"

export interface reviewLikeInfo{
    mittente: string,
    destinatario: string,
    videogameId : number
}

export interface reviewReportInfo{
    mittente: string,
    destinatario: string,
    videogameId : number,
    stato : ReportStatus
}