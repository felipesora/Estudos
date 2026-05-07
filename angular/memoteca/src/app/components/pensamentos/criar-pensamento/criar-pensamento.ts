import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { PensamentoInterface } from '../pensamento/pensamentoInterface';
import { PensamentoService } from '../pensamento.service';

@Component({
  selector: 'app-criar-pensamento',
  standalone: true,
  imports: [FormsModule, RouterLink],
  templateUrl: './criar-pensamento.html',
  styleUrl: './criar-pensamento.css',
})
export class CriarPensamento {

  constructor(private service: PensamentoService, private router: Router) {}

  pensamento: PensamentoInterface = {
    id: '',
    conteudo: '',
    autoria: '',
    modelo: 'modelo1'
  }

  criarPensamento() {
    this.service.criar(this.pensamento).subscribe(() => {
      this.router.navigate(['/listarPensamento'])
    })
  }

  cancelar() {
    alert("Pensamento cancelado")
  }
}
