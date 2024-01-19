import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class GameService {

  constructor(private http: HttpClient) { }

  private url = environment.baseUrl + 'api/games';

  index(): Observable<Game[]> {
    return this.http.get<Game[]>(this.url + '?sorted=true').pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('GameService.index(): error retrieving games: ' + err)
        );
      })
    );
  }
}
