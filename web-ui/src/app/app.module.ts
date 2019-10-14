import { BrowserModule } from '@angular/platform-browser';
import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { NavbarComponent } from './shared/navbar/navbar.component';
import {AuthService} from "./auth/auth.service";
import {AuthGuard} from "./auth/auth.guard";
import { HomeComponent } from './routes/home/home.component';
import { ChangepasswordComponent } from './routes/user/changepassword/changepassword.component';
import { CreateuserComponent } from './routes/user/createuser/createuser.component';
import { EdituserComponent } from './routes/user/edituser/edituser.component';
import { ViewuserComponent } from './routes/user/viewuser/viewuser.component';
import { AddcenterComponent } from './routes/maintenance/center/addcenter/addcenter.component';
import {LoginService} from "./services/login.service";
import {NavService} from "./shared/navbar/nav.service";
import {LocationStrategy, PathLocationStrategy} from "@angular/common";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {MatToolbarModule} from "@angular/material/toolbar";
import {MatButtonModule} from "@angular/material/button";
import {MatSidenavModule} from "@angular/material/sidenav";
import {MatIconModule} from "@angular/material/icon";
import {MatListModule} from "@angular/material/list";
import {MatMenuModule} from "@angular/material/menu";
import {MatCardModule} from "@angular/material/card";
import {MatExpansionModule} from "@angular/material/expansion";
import {MatPaginatorModule} from "@angular/material/paginator";
import {MatCheckboxModule} from "@angular/material/checkbox";
import {MatSelectModule} from "@angular/material/select";
import {MatInputModule} from "@angular/material/input";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatDialogModule} from "@angular/material/dialog";
import {MatAutocompleteModule} from "@angular/material/autocomplete";
import {matTabsAnimations, MatTabsModule} from "@angular/material/tabs";
import {MatTableModule} from "@angular/material/table";
import {MatDatepickerModule} from "@angular/material/datepicker";
import {MatNativeDateModule} from "@angular/material/core";
import {AngularFontAwesomeModule} from "angular-font-awesome";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {NgbModule} from "@ng-bootstrap/ng-bootstrap";
import {AgGridModule} from "ag-grid-angular";
import {ToastrModule} from "ng6-toastr-notifications";
import {RouterModule} from "@angular/router";
import {HttpClient, HttpClientModule} from "@angular/common/http";
import {TranslateLoader, TranslateModule} from "@ngx-translate/core";
import {Angulartics2, Angulartics2Module} from "angulartics2";
import {NgMultiSelectDropDownModule} from "ng-multiselect-dropdown";
import {NgCircleProgressModule} from "ng-circle-progress";
import {TranslateHttpLoader} from "@ngx-translate/http-loader";
import { CreatebookingComponent } from './routes/bookings/createbooking/createbooking.component';
import { ViewbookingComponent } from './routes/bookings/viewbooking/viewbooking.component';
import { ViewcenterComponent } from './routes/maintenance/center/viewcenter/viewcenter.component';

export function HttpLoaderFactory(http:HttpClient){
  return new TranslateHttpLoader(http);
}

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    NavbarComponent,
    HomeComponent,
    ChangepasswordComponent,
    CreateuserComponent,
    EdituserComponent,
    ViewuserComponent,
    AddcenterComponent,
    CreatebookingComponent,
    ViewbookingComponent,
    ViewcenterComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    MatToolbarModule,
    MatButtonModule,
    MatSidenavModule,
    MatIconModule,
    MatListModule,
    MatMenuModule,
    MatCardModule,
    MatExpansionModule,
    MatPaginatorModule,
    MatCheckboxModule,
    MatSelectModule,
    MatInputModule,
    MatFormFieldModule,
    MatDialogModule,
    MatAutocompleteModule,
    MatTabsModule,
    MatTableModule,
    MatDatepickerModule,
    MatNativeDateModule,
    AngularFontAwesomeModule,
    FormsModule,
    ReactiveFormsModule,
    NgbModule,
    AgGridModule.withComponents([]),
    ToastrModule.forRoot(),
    HttpClientModule,
    TranslateModule.forRoot({
        loader:{
          provide:TranslateLoader,
          useFactory:HttpLoaderFactory,
          deps:[HttpClient]
        }
      }
    ),
    Angulartics2Module.forRoot(),
    NgMultiSelectDropDownModule.forRoot(),
    NgCircleProgressModule.forRoot(),
    AppRoutingModule
  ],
  providers: [AuthService,AuthGuard, LoginService,NavService,{provide: LocationStrategy, useClass: PathLocationStrategy}],
  bootstrap: [AppComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AppModule { }
