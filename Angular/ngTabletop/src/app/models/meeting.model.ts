import { Campaign } from "./campaign.model";

export class Meeting {
  id: number;
  location: string;
  startTime: string | null;
  campaign: Campaign | null;

  constructor (
    id: number = 0,
    location: string = '',
    startTime: string = '',
    campaign: Campaign | null = null,
  ) {
    this.id = id;
    this.location = location;
    this.startTime = startTime;
    this.campaign = campaign;
  }
}
