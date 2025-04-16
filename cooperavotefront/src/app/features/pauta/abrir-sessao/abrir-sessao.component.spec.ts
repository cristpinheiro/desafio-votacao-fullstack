import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AbrirSessaoComponent } from './abrir-sessao.component';

describe('AbrirSessaoComponent', () => {
  let component: AbrirSessaoComponent;
  let fixture: ComponentFixture<AbrirSessaoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AbrirSessaoComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AbrirSessaoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
