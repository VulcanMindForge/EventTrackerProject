import { Campaign } from "./campaign.model";
import { Player } from "./player.model";

export class Adventurer {
  id: number;
  name: string;
  characterClass: string;
  level: number;
  player: any | undefined;
  campaign: any | undefined;

  constructor (
    id: number = 0,
    name: string = '',
    characterClass: string = '',
    level: number = 0,
    player: any | undefined = undefined,
    campaign: any | undefined = undefined,
  ) {
    this.id = id;
    this.name = name;
    this.characterClass = characterClass;
    this.level = level;
    this.player = player;
    this.campaign = campaign;
  }
}
