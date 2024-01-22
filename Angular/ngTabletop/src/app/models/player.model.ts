export class Player {
    id: number;
    firstName: string;
    lastName: string;
    address: string;
    username: string;
    password: string;
    adventurers: any[] | [];
    campaigns: any[] | [];

  constructor(
  id: number = 0,
  firstName: string = '',
  lastName: string = '',
  address: string = '',
  username: string = '',
  password: string = '',
  adventurers: any[] | [] = [],
  campaigns: any[] | [] = [],
  ) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.address = address;
    this.username = username;
    this.password = password;
    this.adventurers = [];
    this.campaigns = [];
  }
}
