export class TokenManager {
 private token : string | null | undefined
  getToken()
  {
    if (this.token== null)
    {
      this.token= localStorage.getItem("user-token");
    }
    return this.token
  }

  setToken(token: string)
  {
    this.token = token.toString();
    localStorage.setItem("user-token",this.token.toString());
  }
  removeToken()
  {
    localStorage.removeItem("user");
    localStorage.removeItem("user-token");
    this.token= null;
  }
}
