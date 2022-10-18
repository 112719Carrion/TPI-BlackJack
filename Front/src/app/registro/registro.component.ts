import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Usuario } from 'src/models/Usuario';

@Component({
  selector: 'app-registro',
  templateUrl: './registro.component.html',
  styleUrls: ['./registro.component.css']
})
export class RegistroComponent implements OnInit {

  usuario = {} as Usuario;
  constructor(private router: Router) { 
    this.usuario = new Usuario(0, '', '');
  }

  ngOnInit(): void {
  }

  register() {
    if([this.usuario.email, this.usuario.password, this.usuario.passwordConfirmation].includes('') || !this.validarMail() || !this.validarPassword()) {
      return alert('Completar los campos correctamente');
    }

    if(!this.registrarUser) return alert('Error al registrar usuario');
    alert('Usuario registrado correctamente');
    this.router.navigate(['/game']);
  }

  validarMail(): boolean {
    const regex = /^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/g;
    return regex.test(this.usuario.email);
  }

  validarPassword(): boolean {
    return this.usuario.password.length >= 6 && this.usuario.password === this.usuario.passwordConfirmation;
  }

  registrarUser(): boolean {
    return true;
  }
}
