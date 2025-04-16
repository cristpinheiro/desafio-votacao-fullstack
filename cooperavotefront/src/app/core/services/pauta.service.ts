import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { PautaDTO } from '../models/pauta.dto';

@Injectable({
  providedIn: 'root'
})
export class PautaService {
  private apiUrl = 'http://localhost:8080/api/pautas';

  constructor(private http: HttpClient) { }

  getPautas(): Observable<PautaDTO[]> {
    return this.http.get<PautaDTO[]>(this.apiUrl);
  }

  criarPauta(dto: PautaDTO): Observable<void> {
    return this.http.post<void>(this.apiUrl, dto);
  }
}
