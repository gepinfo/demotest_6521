import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { I18NextModule } from 'angular-i18next';
import { VaultadminComponent } from './vaultadmin.component';
import { NzModalModule } from 'ng-zorro-antd/modal';
import { NzListModule } from 'ng-zorro-antd/list';
import { NzIconModule } from 'ng-zorro-antd/icon';
import { NzDrawerModule } from 'ng-zorro-antd/drawer';
import { AgGridModule } from 'ag-grid-angular';

@NgModule({
  imports: [
NzListModule,
NzIconModule,
NzDrawerModule,
AgGridModule,
NzModalModule,
CommonModule,
FormsModule,
ReactiveFormsModule,
RouterModule,
I18NextModule.forRoot()
],
  declarations: [
VaultadminComponent
]
  
  
})
export class VaultadminModule { }