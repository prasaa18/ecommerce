import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import {MatIconModule} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';
import {MatMenuModule} from '@angular/material/menu';
import { FeatureModule } from './Module/feature/feature.module';
import { SharedModule } from './Module/shared/shared.module';
import { AdminModule } from './Module/admin/admin.module';


@NgModule({
  declarations: [
    AppComponent,
   
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    FeatureModule,
    SharedModule,
    AdminModule
  
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
