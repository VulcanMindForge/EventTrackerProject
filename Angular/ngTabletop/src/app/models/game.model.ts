export class Game {
  id: number;
  name: string;
  description: string;
  imageURL: string;
  campaigns: any[] | undefined = undefined;

    constructor(
      id: number = 0,
      name: string = '',
      description: string = '',
      imageURL: string = '',
      campaigns: any[] | undefined = undefined,
    ) {
      this.id = id;
      this.name = name;
      this.description = description;
      this.imageURL = imageURL;
      this.campaigns = campaigns;
    }
  }
