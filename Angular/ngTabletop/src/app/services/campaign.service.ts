import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { Campaign } from '../models/campaign.model';
import { Observable, catchError, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CampaignService {

  constructor(private http: HttpClient) { }

  private url = environment.baseUrl + 'api/campaigns';

  index(): Observable<Campaign[]> {
    return this.http.get<Campaign[]>(this.url + '?sorted=true').pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('CampaignService.index(): error retrieving campaigns: ' + err)
        );
      })
    );
  }

  show(campId: number): Observable<Campaign> {
    return this.http.get<Campaign>(this.url + '/' + campId).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('CampaignService.show(): error retrieving campaign with id ' + campId + ': ' + err)
        );
      })
    );
  }

  create(campaign: Campaign): Observable<Campaign> {
    return this.http.post<Campaign>(this.url, campaign).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('CampaignService.create(): error creating campaign: ' + err)
        );
      })
    );
  }

  update(campaign: Campaign): Observable<Campaign> {
    return this.http.put<Campaign>(this.url + "/" + campaign.id, campaign).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('CampaignService.update(): error updating todo with id ' + campaign.id + ': ' + err)
        );
      })
    );
  }

  destroy(campId: number): Observable<void> {
    return this.http.delete<void>(this.url + '/' + campId).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('CampaignService.update(): error deleting campaign with id ' + campId + ': ' + err)
        );
      })
    );
  }
}
