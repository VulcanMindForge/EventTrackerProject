import { Routes } from '@angular/router';
import { GameListComponent } from './components/game-list/game-list.component';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { MeetingComponent } from './components/meeting/meeting.component';
import { CampaignComponent } from './components/campaign/campaign.component';
import { AdventurerComponent } from './components/adventurer/adventurer.component';

export const routes: Routes = [
  {path: '', pathMatch: 'full', redirectTo: 'home'},
  {path: 'home', component: GameListComponent},
  {path: 'campaigns', component: CampaignComponent},
  {path: 'adventurers', component: AdventurerComponent},
  {path: 'meetings', component: MeetingComponent},
  { path: '**', component: NotFoundComponent }
];
