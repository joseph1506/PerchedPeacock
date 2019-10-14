import {AfterViewInit, Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {Observable} from "rxjs";
import {VERSION} from "@angular/material/core";
import {TranslateService} from "@ngx-translate/core";
import {AuthService} from "../../auth/auth.service";
import {NavService} from "./nav.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements AfterViewInit {
  isLoggedIn$ : Observable<boolean>;

  @ViewChild('appDrawer',{static:false}) appDrawer: ElementRef;
  version= VERSION;

  constructor(public translate:TranslateService,private authService:AuthService,
              public navService:NavService,private router:Router) {
    translate.setDefaultLang('en');
    translate.use('en');
  }

  ngOnInit(){
    this.isLoggedIn$= this.authService.isLoggedIn;
  }

  createUser(){
    this.router.navigateByUrl('/createUser');
  }

  viewUser(){
    this.router.navigateByUrl('/viewUser');
  }

  addCenter(){
    this.router.navigateByUrl('/addCenter');
  }

  viewCenter(){
    this.router.navigateByUrl('/viewCenter');
  }

  viewBooking(){
    this.router.navigateByUrl('/viewBooking');
  }

  createBooking(){
    this.router.navigateByUrl('/createBooking');
  }


  logout(){
    let details= {userName:'',password:''};
    this.authService.login(details);
    this.router.navigateByUrl('/login');
  }

  ngAfterViewInit(){
    this.navService.appDrawer=this.appDrawer;
  }

}
