import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { PautaService } from '../../core/services/pauta.service';
import { PautaDTO } from '../../core/models/pauta.dto';

@Component({
  selector: 'app-criar-pauta-dialog',
  imports: [MatFormFieldModule, MatDialogModule, MatDialogModule, ReactiveFormsModule],
  templateUrl: './criar-pauta-dialog.component.html',
  styleUrl: './criar-pauta-dialog.component.scss'
})
export class CriarPautaDialogComponent {
  form: FormGroup;

  constructor(
    private fb: FormBuilder,
    private pautaService: PautaService,
    private dialogRef: MatDialogRef<CriarPautaDialogComponent>
  ) {
    this.form = this.fb.group({
      titulo: ['', Validators.required],
      descricao: [''],
    });
  }

  salvar() {
    if (this.form.valid) {
      const pauta: PautaDTO = this.form.value;
      this.pautaService.criarPauta(pauta).subscribe({
        next: () => this.dialogRef.close(true),
        error: () => alert('Erro ao criar pauta'),
      });
    }
  }

  fechar() {
    this.dialogRef.close(false);
  }
}
