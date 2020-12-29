import { Injectable } from '@angular/core';
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {Observable} from "rxjs/index";
import {ProgressService} from "../progress/progress.service";
import {finalize} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class InterceptorService implements HttpInterceptor {

  constructor( public progressService: ProgressService ) { }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    this.progressService.isLoading.next(true);

    return next.handle(req).pipe(
      finalize(() => { this.progressService.isLoading.next(false); }
      )
    );
  }
}
