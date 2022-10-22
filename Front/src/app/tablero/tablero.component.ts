import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Carta } from 'src/models/Carta';
import Swal from 'sweetalert2';
import { CartasService } from '../services/cartas.service';
import { UsuarioService } from '../services/usuario.service';

@Component({
  selector: 'app-tablero',
  templateUrl: './tablero.component.html',
  styleUrls: ['./tablero.component.css'],
})
export class TableroComponent implements OnInit {
  cartasJugador: Carta[] = [];
  cartasCrupier: Carta[] = [];
  puntajeJugador: number = 0;
  puntajeCrupier: number = 0;
  activo: boolean = false;
  idUsuario: number;
  
  constructor(public cartasService: CartasService, 
    private router: Router,
    private usuarioService : UsuarioService) {}

  ngOnInit(): void {
    this.usuarioService.getUsuarioID().subscribe((data) => {
      this.idUsuario = data;
      alert(this.idUsuario);
    });
    this.comenzarJuego();
    
  }

  comenzarJuego(): void {
    this.cartasService.hayPartidaGuardada(this.idUsuario).subscribe((data) => {
      console.log(data);
      console.log(this.idUsuario);
      if(data){
        Swal.fire({
          title: 'Comenzando juego',
          text: 'Espere un momento',
          allowOutsideClick: false,
          timer: 1000,
          timerProgressBar: true,
          didOpen: () => {
            Swal.showLoading();
          },
        }).then((result) => {
          this.cargarPartida();
          setTimeout(() => {
          }, 1000);
        });
      }else{
        Swal.fire({
          title: 'Comenzando juego',
          text: 'Espere un momento',
          allowOutsideClick: false,
          timer: 1000,
          timerProgressBar: true,
          didOpen: () => {
            Swal.showLoading();
          },
        }).then((result) => {
          this.pedirCartaJugador();
          this.pedirCartaCrupier();
          setTimeout(() => {
            this.pedirCartaJugador();
          }, 1000);
        });
      }
    });
  }

  pedirCartaJugador(): void {
    this.cartasService.getCarta(1).subscribe((carta) => {
      this.cartasJugador.push(carta);
      this.calcularPuntos();
      this.logicaJugador();
    });
  }

  pedirCartaCrupier(): void {
    this.cartasService.getCarta(2).subscribe((carta) => {
      this.cartasCrupier.push(carta);
      this.logicaAsesCrupier();
      this.calcularPuntos();
    });
  }

  logicaJugador(): void {
    this.cartasService.logicaJugador().subscribe((resp) => {
      if (resp) {
        this.activo = true;
        this.logicaCrupier();
      }
    });
  }

  logicaCrupier(): void {
    this.cartasService.logicaCrupier().subscribe((resp) => {
      if (resp) {
        this.pedirCartaCrupier();
        setTimeout(() => {
          this.logicaCrupier();
        }, 1000);
      } else {
        this.calcularGanador();
      }
    });
  }

  logicaAses(des: boolean): void {
    this.cartasJugador.forEach((carta) => {
      if (carta.valor == 1 || carta.valor == 11) {
        if (des) {
          carta.valor = 11;
        } else {
          carta.valor = 1;
        }
      }
    });
    this.calcularPuntos();
  }

  logicaAsesCrupier(): void {
    this.cartasCrupier.forEach((carta) => {
      if (carta.valor == 1 || carta.valor == 11) {
        if (this.puntajeCrupier < 11) {
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
    this.cartasService.calcPuntos(1).subscribe((puntos) => {
      this.puntajeJugador = puntos;
    });
    this.puntajeCrupier = 0;
    this.cartasService.calcPuntos(2).subscribe((puntos) => {
      this.puntajeCrupier = puntos;
    });
  }

  calcularGanador(): void {
    this.cartasService.getGanador().subscribe((ganador) => {
      if (ganador == 1) {
        Swal.fire({
          icon: 'error',
          title: 'Perdiste',
          text: 'Mala suerte, vuelve a intentarlo',
        });
      } else if (ganador == 2) {
        Swal.fire({
          icon: 'success',
          title: 'Ganaste',
          text: 'Felicidades, has ganado',
        });
      } else if (ganador == 0) {
        Swal.fire({
          icon: 'warning',
          title: 'Empate',
          text: 'No hay ganador',
        });
      }
    });
  }

  reiniciar(): void {
    this.cartasService.reset().subscribe((data) => {
      console.log(data);
      if (data) {
        this.cartasJugador = [];
        this.cartasCrupier = [];
        this.puntajeJugador = 0;
        this.puntajeCrupier = 0;
        this.activo = false;
      }
    });
  }

  salir(): void {
    this.cartasService.reset().subscribe((data) => {
      console.log(data);
      if (data) {
        this.cartasJugador = [];
        this.cartasCrupier = [];
        this.puntajeJugador = 0;
        this.puntajeCrupier = 0;
        this.activo = false;
      }
    });
  }

  logout(): void {
    Swal.fire({
      title: 'Aviso',
      text: '¿Desea guardar la partida?',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Sí, guardar',
      cancelButtonText: 'No, salir',
    }).then((result) => {
      if (result.isConfirmed) {
        this.guardarPartida();
        this.salir();
        this.router.navigate(['']);
      }else{
        this.salir();
        this.router.navigate(['']);
      }
    });
  }

  guardarPartida(): void {
    this.cartasService.guardarPartida(this.idUsuario).subscribe((data) => {
      console.log(data);
      if (data) {
        Swal.fire({
          icon: 'success',
          title: 'Partida guardada',
          text: 'La partida ha sido guardada',
        });
      }
    });
  }

  cargarPartida(): void {
    Swal.fire({
      title: 'Aviso',
      text: '¿Desea cargar la partida guardada?',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Sí, cargar',
      cancelButtonText: 'No, iniciar nueva partida',
    }).then((result) => {
      if (result.isConfirmed) {
        this.cartasService.cargarPartidaJugador(this.idUsuario).subscribe((data) => {
          if (data) {
            this.cartasJugador = data.cartasJugador;
          }
        });
        this.cartasService.cargarPartidaCrupier(this.idUsuario).subscribe((data) => {
          if (data) {
            this.cartasCrupier = data.cartasJugador;
          }
        });
      }
    });
  }

}
