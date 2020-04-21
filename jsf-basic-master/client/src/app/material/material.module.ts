import { NgModule } from '@angular/core';
import { MatBadgeModule} from '@angular/material/badge';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatTableModule} from '@angular/material/table';
import {MatGridListModule} from '@angular/material/grid-list';
import { MatSortModule } from '@angular/material/sort';
import {MatCardModule} from '@angular/material/card';
import {MatTooltipModule} from '@angular/material/tooltip';
import {MatSelectModule} from '@angular/material/select';


const material = [
  MatBadgeModule,
  MatToolbarModule,
  MatTableModule,
  MatGridListModule,
  MatSortModule,
  MatCardModule,
  MatTooltipModule,
  MatSelectModule
]

@NgModule({
  imports: [ material
    
  ],
  exports: [ material  

  ]
})
export class MaterialModule { }
