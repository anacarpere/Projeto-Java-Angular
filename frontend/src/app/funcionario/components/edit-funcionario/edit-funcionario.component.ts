import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Funcionario } from '../../models/funcionario';
import { FuncionarioHttpService } from '../../services/funcionario-http.service';

@Component({
  selector: 'app-edit-funcionario',
  templateUrl: './edit-funcionario.component.html',
  styleUrls: ['./edit-funcionario.component.css']
})
export class EditFuncionarioComponent implements OnInit {

  fun!: Funcionario 
  funcionario: FormGroup = this.fb.group({
    nome: ['', [Validators.required]],
    email: ['', [Validators.required, Validators.email]],
    foto: [null]
  })
  foto!: File

  constructor(
    private route: ActivatedRoute,
    private funHttpService: FuncionarioHttpService,
    private fb: FormBuilder
  ) { }

  ngOnInit(): void {
    const id: number = parseInt(this.route.snapshot.paramMap.get('idFuncionario') || '0') 

    this.funHttpService.getFuncionarioById(id)
    .subscribe(
      (f) =>{
        this.fun = f

        this.funcionario.patchValue({
          nome: this.fun.nome,
          email: this.fun.email
        })
      }
    )
  }

  submit(): void {
    this.fun.nome = this.funcionario.value.nome
    this.fun.email = this.funcionario.value.email

  }

  selectImage(): void {

  }

  fileChange(event: any): void {
    this.foto = event.target.files[0]

  }

}
