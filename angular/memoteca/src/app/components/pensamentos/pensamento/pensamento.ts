import { NgClass } from '@angular/common';
import { Component, Input } from '@angular/core';
import { PensamentoInterface } from './pensamentoInterface';

@Component({
  selector: 'app-pensamento',
  standalone: true,
  imports: [NgClass],
  templateUrl: './pensamento.html',
  styleUrl: './pensamento.css',
})
export class Pensamento {

  @Input() pensamento: PensamentoInterface = {
    id: '',
    conteudo: '',
    autoria: '',
    modelo: ''
  }

  larguraPensamento(): string {
    if(this.pensamento.conteudo.length >= 256) {
      return 'pensamento-g'
    }
    return 'pensamento-p'
  }
}
