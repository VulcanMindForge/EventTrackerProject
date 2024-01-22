import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable, catchError, throwError } from 'rxjs';
import { Adventurer } from '../models/adventurer.model';

@Injectable({
  providedIn: 'root'
})
export class AdventurerService {

  constructor(private http: HttpClient) { }

  private url = environment.baseUrl + 'api/players/1/adventurers';

  index(): Observable<Adventurer[]> {
    return this.http.get<Adventurer[]>(this.url + '?sorted=true').pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('AdventurerService.index(): error retrieving adventurers: ' + err)
        );
      })
    );
  }

  show(adventId: number): Observable<Adventurer> {
    return this.http.get<Adventurer>(this.url + '/' + adventId).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('AdventurerService.show(): error retrieving adventurer with id ' + adventId + ': ' + err)
        );
      })
    );
  }

  create(advent: Adventurer): Observable<Adventurer> {
    return this.http.post<Adventurer>(this.url, advent).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('AdventurerService.create(): error creating Adventurer: ' + err)
        );
      })
    );
  }

  update(advent: Adventurer): Observable<Adventurer> {
    return this.http.put<Adventurer>(this.url + "/" + advent.id, advent).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('AdventurerService.update(): error updating todo with id ' + advent.id + ': ' + err)
        );
      })
    );
  }

  destroy(adventId: number): Observable<void> {
    return this.http.delete<void>(this.url + '/' + adventId).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('AdventurerService.update(): error deleting adventurer with id ' + adventId + ': ' + err)
        );
      })
    );
  }
}
