import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Funcionario } from '../models/funcionario';

@Injectable()
export class FuncionarioHttpService {

  private readonly BaseURL = 'http://localhost:8080/servicos/funcionario'

  constructor(
    private http: HttpClient
  ) { }

  getFuncionarios(): Observable<Funcionario[]>{
    return this.http.get<Funcionario[]>(this.BaseURL)

  }

  getFuncionarioById(id: number): Observable<Funcionario>{
    return this.http.get<Funcionario>(`${this.BaseURL}/${id}`)
  }

  deleteFuncionario(id: number): Observable<void>{
    return this.http.delete<void>(`${this.BaseURL}/${id}`)
  }
  createFuncionario(funcionario: Funcionario): Observable<Funcionario>{
    return this.http.post<Funcionario>(this.BaseURL,funcionario)
  }

  addFoto(id: number, data: FormData, filename: string): Observable<void>{
    return this.http.post<void>(`${this.BaseURL}/envioFoto/${id}?nome=${filename}`, data)
  }
}
