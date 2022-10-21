import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Usuario } from 'src/models/Usuario';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  APIUrl = "http://localhost:8080/";
  usuario: Usuario;

  constructor(private http: HttpClient) { }

  //comparar el usuario y la contraseña con la api  
  login(usuario: string, password: string): Observable<any>{
    const url = this.APIUrl + "usuario/login/" + usuario + "/" + password;
        const headers = { 'Content-Type': 'application/json' };
        return this.http.get(url, { headers })
  }

  
}
