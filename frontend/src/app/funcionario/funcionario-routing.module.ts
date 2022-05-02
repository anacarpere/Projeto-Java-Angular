import { Component, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { EditFuncionarioComponent } from './components/edit-funcionario/edit-funcionario.component';
import { ConfirmExitGuard } from './guards/confirm-exit.guard';
import { IsNumberGuard } from './guards/is-number.guard';
import { FuncionarioComponent } from './pages/funcionario/funcionario.component';
import { ListarFuncionarioComponent } from './pages/listar-funcionario/listar-funcionario.component';
import { NovoFuncionarioComponent } from './pages/novo-funcionario/novo-funcionario.component';

const routes: Routes = [{
  path: 'novo-funcionario',
  component: NovoFuncionarioComponent,
  canDeactivate: [ConfirmExitGuard]
},
{
  path: '',
  pathMatch: 'full',
  component: ListarFuncionarioComponent
}, 
{
  path: ':idFuncionario',
  component: FuncionarioComponent,
  canActivate: [IsNumberGuard]
},
{
  path: 'edit/:idFuncionario', 
  component: EditFuncionarioComponent,
  
}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class FuncionarioRoutingModule { }
