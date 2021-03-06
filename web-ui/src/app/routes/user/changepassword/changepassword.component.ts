import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-changepassword',
  templateUrl: './changepassword.component.html',
  styleUrls: ['./changepassword.component.scss']
})
export class ChangepasswordComponent implements OnInit {

  changePassForm: FormGroup;
  newPassword:String;
  confirmNewPassword:String;
  constructor(public formBuilder: FormBuilder) { }

  ngOnInit() {
    this.changePassForm = this.formBuilder.group({
      newPassword: [''], confirmNewPassword: ['']
    });
  }

}
