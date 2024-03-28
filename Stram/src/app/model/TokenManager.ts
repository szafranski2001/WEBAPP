export class TokenManager {
 private token : String | null
  getToken()
  {
    if (this.token== null)
    {
      this.token= localStorage.getItem("user-token")
    }
    return this.token
  }

  setToken(token: String)
  {
    this.token = token
    localStorage.setItem("user-token",this.token.toString())
  }
  removeToken()
  {
    localStorage.removeItem("user")
    localStorage.removeItem("user-token")
    this.token= null
  }
}
