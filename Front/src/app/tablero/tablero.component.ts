import { Component, OnDestroy, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Carta } from 'src/models/Carta';
import Swal from 'sweetalert2';
import { CartasService } from '../services/cartas.service';

@Component({
  selector: 'app-tablero',
  templateUrl: './tablero.component.html',
  styleUrls: ['./tablero.component.css']
})
export class TableroComponent implements OnInit {

  
  cartasJugador: Carta[] = [];
  cartasCrupier: Carta[] = [];
  puntajeJugador: number = 0;
  puntajeCrupier: number = 0;
  activo: boolean = false;
  constructor(public cartasService: CartasService, private router:Router) { 
    
  }

  ngOnInit(): void {
    this.comenzarJuego();
  }


  comenzarJuego(): void {
    Swal.fire({
      title: 'Comenzando juego',
      text: 'Espere un momento',
      allowOutsideClick: false,
      timer: 1000,
      timerProgressBar: true,
      didOpen: () => {
        Swal.showLoading()
      },
    }).then((result) => {
      this.pedirCartaJugador();
      this.pedirCartaCrupier();
      setTimeout(() => {
        this.pedirCartaJugador();
      }
      , 1000);
    });
    

    
  }

  pedirCartaJugador(): void {
    console.log("jugador");
    console.log(this.cartasJugador);
    this.cartasService.getCarta(1).subscribe(carta => {
      this.cartasJugador.push(carta);
      this.calcularPuntos();
      this.logicaJugador();
    });
    
  }

  pedirCartaCrupier(): void {
    console.log("crupier");
    console.log(this.cartasCrupier);
    this.cartasService.getCarta(2).subscribe(carta => {
      this.cartasCrupier.push(carta);
      this.logicaAsesCrupier();
      this.calcularPuntos();
    });

  }

  logicaJugador(): void {
    this.cartasService.logicaJugador().subscribe(resp => {
      if(resp){
        this.activo = true;
        this.logicaCrupier();
      }
    });

  }

  logicaCrupier(): void {
    this.cartasService.logicaCrupier().subscribe(resp => {
    if(resp){
      this.pedirCartaCrupier();
      setTimeout(() => {
        this.logicaCrupier();
      }, 1000);
    } else {
      this.calcularGanador();
    }
    });
  }

  logicaAses(des:boolean): void {
    this.cartasJugador.forEach(carta => {
      if(carta.valor == 1 || carta.valor == 11){
        if(des){
          carta.valor = 11;
        } else {
          carta.valor = 1;
        }
      }
    });
    this.calcularPuntos();
  }

  logicaAsesCrupier(): void {
    this.cartasCrupier.forEach(carta => {
      if(carta.valor == 1 || carta.valor == 11){
        if(this.puntajeCrupier < 11){
          carta.valor = 11;
        } else {
          carta.valor = 1;
        }
      }
    });
    this.calcularPuntos();
  }

  plantarse(): void {
    this.activo = true;
    this.logicaCrupier();
  }

  calcularPuntos(): void {
    this.puntajeJugador = 0;
    this.cartasService.calcPuntos(1).subscribe(puntos => {
      this.puntajeJugador = puntos;
    });
    this.puntajeCrupier = 0;
    this.cartasService.calcPuntos(2).subscribe(puntos => {
      this.puntajeCrupier = puntos;
    });

  }

  calcularGanador(): void {
    this.cartasService.getGanador().subscribe(ganador => {
    if(ganador == 1){
      Swal.fire({
        icon: 'error',
        title: 'Perdiste',
        text: 'Mala suerte, vuelve a intentarlo',
      })
    } else if(ganador == 2){
      Swal.fire({
        icon: 'success',
        title: 'Ganaste',
        text: 'Felicidades, has ganado',
      })
    } else if(ganador == 0){
      Swal.fire({
        icon: 'warning',
        title: 'Empate',
        text: 'No hay ganador',
      })
    }
  });
}

  reiniciar(): void {
    this.cartasService.reset().subscribe((data) => {
      console.log(data);
      if(data){
        console.log("reset");
        this.cartasJugador = [];
        this.cartasCrupier = [];
        this.puntajeJugador = 0;
        this.puntajeCrupier = 0;
        this.activo = false;
        this.comenzarJuego();
      }
    });   
    
    }

  logout(): void {
    localStorage.removeItem('token');
    this.router.navigate(['']);
  }

}