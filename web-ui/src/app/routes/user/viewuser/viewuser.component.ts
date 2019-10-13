import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'app-viewuser',
  templateUrl: './viewuser.component.html',
  styleUrls: ['./viewuser.component.scss']
})
export class ViewuserComponent implements OnInit {
  private gridApi;
  private gridColumnApi; userDetails;

  constructor(private router: Router) {
    //Ag-grid View Users Grid
    this.userDetails={};
    this.userDetails.columnDefs = [
    {headerName: 'First Name', field: 'firstName', resizable: true},
    {headerName: 'Last Name', field: 'lastName', resizable: true},
    {headerName: 'Email', field: 'email', resizable: true},
      {headerName: 'Phone Number', field: 'phoneNumber', resizable: true},
      {headerName: 'Country', field:'country', resizable: true},
      {headerName: 'Action', resizable: true, width: 90, editable: false,
        template: '<button value="edit" data-action-type="edit" id="edit" title="Edit" style="background: red;">Edit</button> ' +
          '<button title="Delete" id="delete" style="margin-left: 6px;background: red" (click)="deleteEvent($event);"' +
          'data-action-type="delete">Delete</button>'
      }
    ];
    this.userDetails.rowData = [
      {firstName:"Ravi", lastName:"K",email:"ravi.k@gmail.com", phoneNumber:"7656787889",country:"India"},
      {firstName:"Raju", lastName:"K", email:"raju.k@gmail.com", phoneNumber:"7656787889",country:"India"},
      {firstName:"Rakesh", lastName:"K", email:"rajesh.k@gmail.com", phoneNumber:"7656787889",country:"India"},
      {firstName:"Bhaskar", lastName:"K", email:"bhaskar.k@gmail.com", phoneNumber:"7656787889",country:"India"}
      ];
    this.userDetails.rowSelection='multiple';
    this.userDetails.domLayout='autoHeight';
  }

  ngOnInit() {
  }

  userAction(event){
    let actionType=event.event.target.getAttribute("data-action-type");
    if(actionType==="edit"){
      this.router.navigateByUrl('./editUser');
    }
    if(actionType==="delete"){
        console.log("Delete");
    }
  }

  onGridReady(params){
    this.gridApi = params.api;
    this.gridColumnApi = params.columnApi;
    params.api.sizeColumnsToFit();
    window.addEventListener("resize", function() {
      setTimeout(function(){
        params.api.sizeColumnsToFit();
      });
    });
  }

}
