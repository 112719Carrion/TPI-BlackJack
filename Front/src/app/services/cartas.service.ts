import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Carta } from 'src/models/Carta';

@Injectable({
  providedIn: 'root'
})
export class CartasService {

  APIUrl = "http://localhost:8080/";
  carta: Carta;

  constructor(private http: HttpClient) { }

  //obtener de la api el objeto carta y asignarlo a la variable carta
  getCarta(tipoJugador: number): Observable<any>{
    const url = this.APIUrl + "cartas/" + tipoJugador;
        const headers = { 'Content-Type': 'application/json' };
        return this.http.get(url, { headers })
  }

  calcPuntos(tipoJugador: number): Observable<any>{
    const url = this.APIUrl + "cartas/" + tipoJugador + "/puntos";
        const headers = { 'Content-Type': 'application/json' };
        return this.http.get(url, { headers })
  }

  reset(): Observable<any>{
    const url = this.APIUrl + "reset";
        const headers = { 'Content-Type': 'application/json' };
        return this.http.get(url, { headers })
  }

  logicaJugador(): Observable<any>{
    const url = this.APIUrl + "logicaJugador";
        const headers = { 'Content-Type': 'application/json' };
        return this.http.get(url, { headers })
  }

  logicaCrupier(): Observable<any>{
    const url = this.APIUrl + "logicaCrupier";
        const headers = { 'Content-Type': 'application/json' };
        return this.http.get(url, { headers })
  }

  getGanador(): Observable<any>{
    const url = this.APIUrl + "ganador";
        const headers = { 'Content-Type': 'application/json' };
        return this.http.get(url, { headers })
  }

  guardarPartida(idUsuario: number): Observable<any>{
    const url = this.APIUrl + "guardar/" + idUsuario;
        const headers = { 'Content-Type': 'application/json' };
        return this.http.get(url, { headers })
  }

  cargarPartida(): Observable<any>{
    const url = this.APIUrl + "guardar";
        const headers = { 'Content-Type': 'application/json' };
        return this.http.get(url, { headers })
  }
}