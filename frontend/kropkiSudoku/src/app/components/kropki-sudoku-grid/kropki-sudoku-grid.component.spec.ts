import { ComponentFixture, TestBed } from '@angular/core/testing';

import { KropkiSudokuGridComponent } from './kropki-sudoku-grid.component';

describe('KropkiSudokuGridComponent', () => {
  let component: KropkiSudokuGridComponent;
  let fixture: ComponentFixture<KropkiSudokuGridComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ KropkiSudokuGridComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(KropkiSudokuGridComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
