import { Component, OnInit } from '@angular/core';
import { GameService } from '../../services/game.service';
import { Game } from '../../models/game.model';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-game-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './game-list.component.html',
  styleUrl: './game-list.component.css'
})
export class GameListComponent implements OnInit {
  games: Game [] = [];

  constructor(
    private gameServ: GameService
  ) {}

  ngOnInit(): void {
    this.reload();
  }

  reload() {
    this.gameServ.index().subscribe(
      {
        next: (games) => {
          this.games = games;
        },
        error: (problem) => {
          console.error("Problem with httpgame-list.component reload(): problem loading games");
          console.error("problem");
        }
      }
    )
  }
}
