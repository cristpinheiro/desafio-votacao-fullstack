import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators, ReactiveFormsModule } from '@angular/forms';
import { AssociadoService } from '../../../core/services/associado.service';
import { AssociadoDTO } from '../../../core/models/associado.dto';
import { CommonModule } from '@angular/common';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatCardModule } from '@angular/material/card';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  imports: [CommonModule,
    MatFormFieldModule,
    MatInputModule,
    ReactiveFormsModule, MatCardModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {
  form: FormGroup;

  constructor(
    private fb: FormBuilder,
    private associadoService: AssociadoService,
    private router: Router
  ) {
    this.form = this.fb.group({
      nome: ['', Validators.required],
      cpf: ['', [Validators.required, Validators.pattern(/^\d{11}$/)]],
    });
  }

  onSubmit() {
    if (this.form.valid) {
      const associado: AssociadoDTO = this.form.value;
      this.associadoService.salvar(associado).subscribe({
        next: () => {
          this.router.navigateByUrl('/dashboard');
        },
        error: (err) => alert('Erro ao registrar associado'),
      });
    }
  }
}
