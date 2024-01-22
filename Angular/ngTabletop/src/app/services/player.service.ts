import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { Observable, catchError, throwError } from 'rxjs';
import { Player } from '../models/player.model';

@Injectable({
  providedIn: 'root'
})
export class PlayerService {

  constructor(private http: HttpClient) { }

  private url = environment.baseUrl + 'api/players';

  index(): Observable<Player[]> {
    return this.http.get<Player[]>(this.url + '?sorted=true').pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('PlayerService.index(): error retrieving meetings: ' + err)
        );
      })
    );
  }

  show(playerId: number): Observable<Player> {
    return this.http.get<Player>(this.url + '/' + playerId).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('PlayerService.show(): error retrieving player with id ' + playerId + ': ' + err)
        );
      })
    );
  }

}
