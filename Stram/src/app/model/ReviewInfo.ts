
export enum TypeInfo{
    like="/GetLikes/",report="/GetReports/"
}

export interface StatoSegnalazione {
    type: string;
  }
  
  export class NuovaSegnalazione implements StatoSegnalazione {
    type = 'Nuova';
  }
  
  export class InCorsoSegnalazione implements StatoSegnalazione {
    type = 'In corso';
  }
  
  export class CompletataSegnalazione implements StatoSegnalazione {
    type = 'Completata';
  }

export interface reviewInfo{
    mittente: string | null,
    destinatario: string,
    idVideogioco : number,
    state?: StatoSegnalazione | null
}