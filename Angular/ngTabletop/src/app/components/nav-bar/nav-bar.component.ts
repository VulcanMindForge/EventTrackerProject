import { Component } from '@angular/core';
import { Player } from '../../models/player.model';
import { RouterLink } from '@angular/router';
import { NgbCollapseModule } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-nav-bar',
  standalone: true,
  imports: [RouterLink, NgbCollapseModule],
  templateUrl: './nav-bar.component.html',
  styleUrl: './nav-bar.component.css'
})

export class NavBarComponent {
loggedInPlayer: Player | null = null;
}
