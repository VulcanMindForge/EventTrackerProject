import { Game } from "./game.model";
import { Player } from "./player.model";

export class Campaign {
  id: number;
  name: string;
  storyteller: Player;
  game: Game;
  players: any[] | undefined = undefined;

  constructor(
    id: number = 0,
    name: string = '',
    storyteller: Player = new Player(),
    game: Game = new Game(),
    players: any[] | undefined = undefined,
  ) {
    this.id = id;
    this.name = name;
    this.storyteller = storyteller;
    this.game = game;
    this.players = players;
  }
}

