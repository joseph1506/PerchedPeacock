import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {LoginService} from "../services/login.service";
import {AuthService} from "../auth/auth.service";
import {ToastrManager} from "ng6-toastr-notifications";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  registerForm: FormGroup;
  firstName:String;
  lastName:String;
  email:String;
  phoneNumber:String;
  userName:String;
  password:String;
  country:String="";

  constructor(public formBuilder: FormBuilder,private router: Router,
              private activeRoute: ActivatedRoute,private loginService: LoginService,
              private authService:AuthService,private toastr:ToastrManager){}

  ngOnInit() {
    this.registerForm = this.formBuilder.group({
      firstName:[''], lastName:[''], email:[''], phoneNumber:[''],
      userName:[''], password:[''], country: ['']
    });
  }

  cancel() {
    this.router.navigateByUrl('/login');
  }

  submit() {
  }
}
