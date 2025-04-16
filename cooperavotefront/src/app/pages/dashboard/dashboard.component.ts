import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { PautaDTO } from '../../core/models/pauta.dto';
import { PautaService } from '../../core/services/pauta.service';
import { CriarPautaDialogComponent } from '../../shared/criar-pauta-dialog/criar-pauta-dialog.component';
import { MatListModule } from '@angular/material/list';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-dashboard',
  imports: [MatListModule, CommonModule],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.scss'
})
export class DashboardComponent implements OnInit {
  pautas: PautaDTO[] = [];

  constructor(private pautaService: PautaService, private dialog: MatDialog) { }

  ngOnInit(): void {
    this.carregarPautas();
  }

  carregarPautas() {
    this.pautaService.getPautas().subscribe((data) => {
      this.pautas = data;
    });
  }

  abrirDialogCriarPauta() {
    const dialogRef = this.dialog.open(CriarPautaDialogComponent, {
      width: '400px',
    });

    dialogRef.afterClosed().subscribe((resultado) => {
      if (resultado === 'atualizar') {
        this.carregarPautas();
      }
    });
  }
}
