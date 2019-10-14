import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'app-viewbooking',
  templateUrl: './viewbooking.component.html',
  styleUrls: ['./viewbooking.component.scss']
})
export class ViewbookingComponent implements OnInit {

  private gridApi; private gridColumnApi;
  activeBookingDetails; expiredBookingDetails;
  viewSlotsEnable=true;

  constructor (private router: Router) {
    //Ag-grid activeBookingDetails Grid
    this.activeBookingDetails={};
    this.activeBookingDetails.columnDefs=[
      {headerName: 'Booking Id', field: 'bookingId', resizable: true},
      {headerName: 'Slot No', field: 'slotNo', resizable: true},
      {headerName: 'Vehicle', field: 'vehicle', resizable: true},
      {headerName: 'From Date', field: 'fromDate', resizable: true},
      {headerName: 'To Date', field: 'toDate', resizable: true},
      {headerName: 'Action', resizable: true, width: 90, editable: false,
        template: '<button title="Cancel" id="cancel" style="margin-left: 6px; background: red;" (click)="cancelEvent($event);"' +
          'data-action-type="cancel">Cancel</button>'
      }
    ];
    this.activeBookingDetails.rowData = [
      {bookingId:"21", slotNo:"111", vehicle: "KA23ED2244", fromDate: "22-Oct-2019:12:30:000", toDate: "22-Oct-2019:14:30:000"},
      {bookingId:"22", slotNo:"112", vehicle: "KA23ED2241", fromDate: "22-Oct-2019:12:30:000", toDate: "22-Oct-2019:14:30:000"},
      {bookingId:"23", slotNo:"113", vehicle: "KA23ED2242", fromDate: "22-Oct-2019:12:30:000", toDate: "22-Oct-2019:14:30:000"},
      {bookingId:"24", slotNo:"114", vehicle: "KA23ED2245", fromDate: "22-Oct-2019:12:30:000", toDate: "22-Oct-2019:14:30:000"}];
    this.activeBookingDetails.rowSelection='multiple';
    this.activeBookingDetails.domLayout='autoHeight';

    //Ag-grid expiredBookingDetails Grid
    this.expiredBookingDetails = {};
    this.expiredBookingDetails.columnDefs = [
      {headerName: 'Booking Id', field: 'bookingId', resizable: true},
      {headerName: 'Slot No', field: 'slotNo', resizable: true},
      {headerName: 'Vehicle', field: 'vehicle', resizable: true},
      {headerName: 'From Date', field: 'fromDate', resizable: true},
      {headerName: 'To Date', field: 'toDate', resizable: true}
    ];
    this.expiredBookingDetails.rowData = [
      {bookingId:"21", slotNo:"111", vehicle: "KA23ED2244", fromDate: "11-Oct-2019:12:30:000", toDate: "11-Oct-2019:14:30:000"},
      {bookingId:"22", slotNo:"112", vehicle: "KA23ED2241", fromDate: "11-Oct-2019:12:30:000", toDate: "11-Oct-2019:14:30:000"},
      {bookingId:"23", slotNo:"113", vehicle: "KA23ED2242", fromDate: "11-Oct-2019:12:30:000", toDate: "11-Oct-2019:14:30:000"},
      {bookingId:"24", slotNo:"114", vehicle: "KA23ED2245", fromDate: "11-Oct-2019:12:30:000", toDate: "11-Oct-2019:14:30:000"}];
    this.expiredBookingDetails.rowSelection='multiple';
    this.expiredBookingDetails.domLayout='autoHeight';
  }

  ngOnInit() {
  }

  userAction(event){
    let actionType=event.event.target.getAttribute ( "data-action-type");
    if(actionType==="edit"){
      this.router.navigateByUrl('/editUser');
    }
    if (actionType==="delete") {
      console.log("Delete");
    }
  }

  onGridReady(params) {
    this.gridApi=params.api;
    this.gridColumnApi=params.columnApi;
    params.api.sizeColumnsToFit();
    window.addEventListener( "resize", function() {
      setTimeout(function() {
        params.api.sizeColumnsToFit();
      });
    });
  }

}
