import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';
import { GameListComponent } from "./components/game-list/game-list.component";
import { NavBarComponent } from "./components/nav-bar/nav-bar.component";

@Component({
    selector: 'app-root',
    standalone: true,
    templateUrl: './app.component.html',
    styleUrl: './app.component.css',
    imports: [CommonModule, RouterOutlet, GameListComponent, NavBarComponent]
})
export class AppComponent {
  title = 'ngTabletop';
}
