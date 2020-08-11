import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { ProfileComponent } from './components/profile/profile.component';
import { DetailComponent } from './components/detail/detail.component';
import { StockOptionLoadComponent } from './components/stockOptionLoad/stockOptionLoad.component';
import { StockOptionReportComponent } from './components/stockOptionReport/stockOptionReport.component';

const routes: Routes = [
  {path: '', redirectTo: 'home', pathMatch:'full'},
  {path: 'home', component: LoginComponent},
  {path: 'login', component: HomeComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'profile', component: ProfileComponent},
  {path: 'detail/:id', component: DetailComponent},
  {path: 'stock-option-load', component: StockOptionLoadComponent},
  {path: 'stock-option-report/:filename', component: StockOptionReportComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
