import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {ToastrManager} from "ng6-toastr-notifications";
import {catchError, map} from "rxjs/operators";
import {throwError} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(public http:HttpClient,public toastr:ToastrManager) { }

  login(userName,password){
    let body={userName:userName,password:password};
    return this.http.post('http://usunispdweb002:8000/login',body)
      .pipe( map(this.responseData),
        catchError((error:Response) => {
            this.toastr.errorToastr("Login Error",'Login',
              {toastTimeout:3000,position:"bottom-right",showCloseButton:true}
            );
          return throwError('Something went wrong');
          }
        )
      );
  }

  responseData(res:Response){
    let body=res;
    return body||{};
  }
}
