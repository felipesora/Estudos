import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { PensamentoInterface } from './pensamento/pensamentoInterface';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class PensamentoService {

  private readonly API = 'http://localhost:3000/pensamentos';

  constructor(private http: HttpClient) {}

  listar(): Observable<PensamentoInterface[]> {
    return this.http.get<PensamentoInterface[]>(this.API);
  }

  criar(pensamento: PensamentoInterface): Observable<PensamentoInterface> {
    return this.http.post<PensamentoInterface>(this.API, pensamento);
  }
}
