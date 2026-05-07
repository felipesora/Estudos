import { Routes } from '@angular/router';
import { CriarPensamento } from './components/pensamentos/criar-pensamento/criar-pensamento';
import { ListarPensamento } from './components/pensamentos/listar-pensamento/listar-pensamento';

export const routes: Routes = [
    {
        path: '',
        pathMatch: 'full',
        redirectTo: 'listarPensamento'
    },
    {
        path: 'criarPensamento',
        component: CriarPensamento
    },
    {
        path: 'listarPensamento',
        component: ListarPensamento
    }
];
