export class ResultStatusOnly {
  private ok: number;           //tutto ok a numero e vice-versa
  private motivation: string;   //spiegazione dell'errore

  constructor(ok: number, motivation: string) {
    this.ok = ok

  }
  public getok() {
    return this.ok ;
  }
}
