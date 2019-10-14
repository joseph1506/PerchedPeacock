import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {Router} from "@angular/router";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  homeForm:FormGroup;
  loginResponse;
  role:String;

  constructor(public formBuilder:FormBuilder, public router:Router) { }

  ngOnInit() {
  }

}
