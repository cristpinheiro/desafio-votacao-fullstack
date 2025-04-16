import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AssociadoDTO } from '../models/associado.dto';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AssociadoService {

  private readonly API = 'http://localhost:8080/api/associados';

  constructor(private http: HttpClient) { }

  salvar(associado: AssociadoDTO): Observable<string> {
    return this.http.post(this.API, associado, { responseType: 'text' });
  }
}
