import { Injectable } from '@angular/core';
import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from "@angular/common/http";
import { Observable } from "rxjs";
import { ProgressService } from "../progress/progress.service";
import { finalize } from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})

// interceptor implements HttpInterceptor which intercepts all http request
// calls happening in the application.
export class InterceptorService implements HttpInterceptor {

  constructor( public progressService: ProgressService ) { }

  // Method which will be invoked every time a http request is happening
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    this.progressService.isLoading.next(true); // Make progress bar visible

    // Hide progress bar on completion of api call
    return next.handle(req).pipe(
      finalize(() => { this.progressService.isLoading.next(false); }
      )
    );
  }
}
