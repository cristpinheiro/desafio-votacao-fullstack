import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CriarPautaDialogComponent } from './criar-pauta-dialog.component';

describe('CriarPautaDialogComponent', () => {
  let component: CriarPautaDialogComponent;
  let fixture: ComponentFixture<CriarPautaDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CriarPautaDialogComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CriarPautaDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
