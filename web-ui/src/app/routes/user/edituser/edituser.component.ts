import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {Router} from "@angular/router";

@Component({
  selector: 'app-edituser',
  templateUrl: './edituser.component.html',
  styleUrls: ['./edituser.component.scss']
})
export class EdituserComponent implements OnInit {

  editUserForm: FormGroup;
  firstName: String;
  lastName: String;
  email: String;
  phoneNumber: String;
  userName: String;
  password: String;
  country: String;

  constructor(private router: Router,public formBuilder: FormBuilder) {
  }

  ngOnInit(){
    this.editUserForm = this.formBuilder.group({
      firstName: [''], lastName: [''], email: [''], phoneNumber: [''],
      userName: [''], password: [''], country: ['']
    })
  }

}
