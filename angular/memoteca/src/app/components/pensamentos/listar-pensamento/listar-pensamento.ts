import { Component, OnInit } from '@angular/core';
import { RouterLink } from "@angular/router";
import { Pensamento } from "../pensamento/pensamento";
import { NgFor, NgIf } from '@angular/common';
import { PensamentoInterface } from '../pensamento/pensamentoInterface';
import { PensamentoService } from '../pensamento.service';

@Component({
  selector: 'app-listar-pensamento',
  standalone: true,
  imports: [RouterLink, Pensamento, NgFor, NgIf],
  templateUrl: './listar-pensamento.html',
  styleUrl: './listar-pensamento.css',
})
export class ListarPensamento implements OnInit {

  constructor(private service: PensamentoService) {}

  listaPensamentos: PensamentoInterface[] = [];
  carregando = true;
  erro = false; // Adicione esta linha

  ngOnInit(): void {
    this.service.listar().subscribe({
      next: (listaPensamentos) => {
        console.log('Pensamentos recebidos:', listaPensamentos);
        this.listaPensamentos = listaPensamentos;
        this.carregando = false;
        this.erro = false;
      },
      error: (error) => {
        console.error('Erro ao carregar pensamentos:', error);
        this.carregando = false;
        this.erro = true;
      }
    });
  }
}
