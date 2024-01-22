import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable, catchError, throwError } from 'rxjs';
import { Meeting } from '../models/meeting.model';

@Injectable({
  providedIn: 'root'
})
export class MeetingService {

  constructor(private http: HttpClient) { }

  private url = environment.baseUrl + 'api/meetings';

  index(): Observable<Meeting[]> {
    return this.http.get<Meeting[]>(this.url + '?sorted=true').pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('MeetingService.index(): error retrieving meetings: ' + err)
        );
      })
    );
  }

  show(meetingId: number): Observable<Meeting> {
    return this.http.get<Meeting>(this.url + '/' + meetingId).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('MeetingService.show(): error retrieving meeting with id ' + meetingId + ': ' + err)
        );
      })
    );
  }

  create(meeting: Meeting): Observable<Meeting> {
    return this.http.post<Meeting>(this.url, meeting).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('MeetingService.create(): error creating meeting: ' + err)
        );
      })
    );
  }

  update(meeting: Meeting): Observable<Meeting> {
    return this.http.put<Meeting>(this.url + "/" + meeting.id, meeting).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('MeetingService.update(): error updating todo with id ' + meeting.id + ': ' + err)
        );
      })
    );
  }

  destroy(meetingId: number): Observable<void> {
    return this.http.delete<void>(this.url + '/' + meetingId).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('MeetingService.update(): error deleting meeting with id ' + meetingId + ': ' + err)
        );
      })
    );
  }
}
