import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-createbooking',
  templateUrl: './createbooking.component.html',
  styleUrls: ['./createbooking.component.scss']
})
export class CreatebookingComponent implements OnInit {

  createUserForm: FormGroup;
  firstName:String;
  lastName:String;
  email:String;
  phoneNumber:String;
  userName:String;
  password:String;
  country:String="";
  private gridApi; private gridColumnApi;
  availableSlots;
  viewSlotsEnable:boolean=false;

  constructor(public formBuilder: FormBuilder) {
    //Ag-grid activeBookingDetails Grid
    this.availableSlots = {};
    this.availableSlots.columnDefs = [
      {headerName: 'Action', resizable: true, width: 90, editable: false,
        template: '<input type="radio"/>'
      },
      {headerName: 'Country', field:'country', resizable: true},
      {headerName: 'Center', field:'center', resizable: true},
      {headerName: 'Slot', field:'slot', resizable: true}
    ];

    this.availableSlots.rowData = [
      {country:"India", center:"Bangalore", slot:"SLOT-21",fromDate:"13-Oct-2019", toDate:"13-Oct-2019"},
      {country:"India", center:"Bangalore", slot:"SLOT-34",fromDate:"14-Oct-2019", toDate:"14-Oct-2019"},
      {country:"India", center:"Bangalore", slot:"SLOT-43",fromDate:"13-Oct-2019", toDate:"13-Oct-2019"},
      {country:"India", center:"Bangalore", slot:"SLOT-77",fromDate:"14-Oct-2019", toDate:"14-Oct-2019"}];
    this.availableSlots.rowSelection='multiple';
    this.availableSlots.domLayout='autoHeight';
  }

  ngOnInit() {
    this.createUserForm = this.formBuilder.group({
      firstName: [''], lastName: [''], email: [''], phoneNumber: [''],
      userName: [''], password: [''], country: ['']
    });
  }
  submit(){
    this.viewSlotsEnable=true;
  }

}
