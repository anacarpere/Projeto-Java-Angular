import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { DeleteDialogComponent } from '../../components/delete-dialog/delete-dialog.component';
import { Funcionario } from '../../models/funcionario';
import { FuncionarioHttpService } from '../../services/funcionario-http.service';

@Component({
  selector: 'app-listar-funcionario',
  templateUrl: './listar-funcionario.component.html',
  styleUrls: ['./listar-funcionario.component.css']
})
export class ListarFuncionarioComponent implements OnInit {

  funcionarios: Funcionario[] = []

  columns: string[] = ['id', 'nome', 'email', 'actions']

  constructor(
    private funHttpService: FuncionarioHttpService,
    private dialog: MatDialog,
    private snackbar: MatSnackBar

  ) { }

  ngOnInit(): void {
    this.recoverFuncionarios()
  }

  confirmationDelete(id: number){
    const DialogRef = this.dialog.open(DeleteDialogComponent)

    DialogRef.afterClosed().subscribe(
      canDelete =>{
       if (canDelete){
         this.funHttpService.deleteFuncionario(id).subscribe(
           () =>{ 
             this.snackbar.open('Funcionário deletado', 'ok',{
               duration:3000
             })
             this.recoverFuncionarios()  
           }
         )
       }
      }
    )
  }

  recoverFuncionarios(){
    this.funHttpService.getFuncionarios().subscribe((funcionarios) =>{
      this.funcionarios = funcionarios
    }
    )
  }

}
