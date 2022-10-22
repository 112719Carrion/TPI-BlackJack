import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import {  Observable, Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  APIUrl = "http://localhost:8080/";
  private usuarioID: Subject<number>;

  constructor(private http: HttpClient) { 
    this.usuarioID = new Subject<number>();
  }

  getUsuarioID(): Observable<number> {
    return this.usuarioID.asObservable();
  }

  setUsuarioID(id: number) {
    this.usuarioID.next(id);
  }

  //comparar el usuario y la contraseña con la api  
  login(usuario: string, password: string): Observable<any>{
    const url = this.APIUrl + "usuario/login/" + usuario + "/" + password;
        const headers = { 'Content-Type': 'application/json' };
        return this.http.get(url, { headers })
  }

  registrar(usuario: string, password: string): Observable<any>{
    const url = this.APIUrl + "usuario/registro/" + usuario + "/" + password;
        const headers = { 'Content-Type': 'application/json' };
        return this.http.get(url, { headers })
  }



  

  
  
}
