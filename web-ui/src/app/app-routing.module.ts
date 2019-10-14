import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {LoginComponent} from "./login/login.component";
import {RegisterComponent} from "./register/register.component";
import {HomeComponent} from "./routes/home/home.component";
import {AuthGuard} from "./auth/auth.guard";
import {AddcenterComponent} from "./routes/maintenance/center/addcenter/addcenter.component";
import {ChangepasswordComponent} from "./routes/user/changepassword/changepassword.component";
import {EdituserComponent} from "./routes/user/edituser/edituser.component";
import {ViewuserComponent} from "./routes/user/viewuser/viewuser.component";
import {CreateuserComponent} from "./routes/user/createuser/createuser.component";
import {ViewcenterComponent} from "./routes/maintenance/center/viewcenter/viewcenter.component";
import {ViewbookingComponent} from "./routes/bookings/viewbooking/viewbooking.component";
import {CreatebookingComponent} from "./routes/bookings/createbooking/createbooking.component";


const routes: Routes = [
  {path:'', component:LoginComponent},
  {path:'register', component:RegisterComponent},
  {path:'home', component:HomeComponent, canActivate:[AuthGuard]},
  {path:'createUser', component:CreateuserComponent, canActivate:[AuthGuard]},
  {path:'viewUser', component:ViewuserComponent, canActivate:[AuthGuard]},
  {path:'editUser', component:EdituserComponent, canActivate:[AuthGuard]},
  {path:'changePassword', component:ChangepasswordComponent, canActivate:[AuthGuard]},
  {path:'addCenter', component:AddcenterComponent, canActivate:[AuthGuard]},
  {path:'viewCenter', component:ViewcenterComponent, canActivate:[AuthGuard]},
  {path:'viewBooking', component:ViewbookingComponent, canActivate:[AuthGuard]},
  {path:'createBooking', component:CreatebookingComponent, canActivate:[AuthGuard]},
  {path:'**', redirectTo:''},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
