import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {AuthService} from "../auth/auth.service";
import {LoginService} from "../services/login.service";
import {ToastrManager} from "ng6-toastr-notifications";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;
  defaultConfig: {};
  userName: String;
  password: String;

  constructor(public formBuilder: FormBuilder,private router: Router,
              private activeRoute: ActivatedRoute,private loginService: LoginService,
              private authService: AuthService,private toastr: ToastrManager){
        this.defaultConfig = {
          usernameParameter: 'j_username',
          passwordParameter: 'j_password',
          rememberMeParameter: 'j_remember_me',
          supportsRememberMe: false,
          loginUri: 'j_spring_security_check',
          logoutUri: 'j_spring_security_logout',
          currentUri: 'j_spring_security_current',
          currentUserUri: '/rest/security/j_spring_security_current'
        }
  }

  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      userName: [''], password: ['']
    });
  }

  register(){
    this.router.navigateByUrl('/register');
  }

  login(){
    if(this.userName!==undefined && this.password!==undefined){
      this.loginService.login(this.userName.trim(), this.password.trim())
        .subscribe( data => {
            let loginResponse=data;
            this.authService.login(this.loginForm.value);
            this.router.navigate(['/home'],{ state: { response: loginResponse} });
          },
          (error: any) => {
            console.log("Error");
          });
    }
    else{
      this.toastr.errorToastr('Please enter User Name and Password', 'Parking Login!',
        {toastTimeOut:3000, position:"bottom-rght",showCloseButton:true});
    }
  }

}
