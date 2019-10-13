import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-createuser',
  templateUrl: './createuser.component.html',
  styleUrls: ['./createuser.component.scss']
})
export class CreateuserComponent implements OnInit {

  createUserForm: FormGroup;
  firstName:String;
  lastName:String;
  email:String;
  phoneNumber:String;
  userName:String;
  password:String;
  country:String="";
  constructor(public formBuilder: FormBuilder) {}

  ngOnInit() {
    this.createUserForm = this.formBuilder.group({
      firstName: [''], lastName: [''], email: [''], phoneNumber: [''],
      userName: [''], password: [''], country: ['']
    });
  }

}
