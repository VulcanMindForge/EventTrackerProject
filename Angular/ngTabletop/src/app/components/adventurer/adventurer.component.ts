import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Adventurer } from '../../models/adventurer.model';
import { ActivatedRoute, Router } from '@angular/router';
import { AdventurerService } from '../../services/adventurer.service';
import { CampaignService } from '../../services/campaign.service';
import { Campaign } from '../../models/campaign.model';
import { PlayerService } from '../../services/player.service';
import { Player } from '../../models/player.model';

@Component({
  selector: 'app-adventurer',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './adventurer.component.html',
  styleUrl: './adventurer.component.css'
})
export class AdventurerComponent {
  adventurers: Adventurer[] = [];
  selected: Adventurer | null = null;
  newAdventurer = new Adventurer();
  editAdventurer: Adventurer | null = null;
  addAdventurerDisplay = false;
  campaigns: Campaign[] = [];
  players: Player[] = [];

  constructor(private router: Router, private activatedRoute: ActivatedRoute, private adventServ: AdventurerService, private campServ: CampaignService, private playServ: PlayerService){}

  ngOnInit(): void {
    this.reload();
    this.activatedRoute.paramMap.subscribe(
      {
        next: (params) => {
          let adventIdStr = params.get("adventId");
          if (adventIdStr) {
            let adventId = parseInt(adventIdStr);
            if (!isNaN(adventId)) {
              this.adventServ.show(adventId).subscribe(
                {
                  next: (advent) => {
                    this.selected = advent;
                  },
                  error: (error) => {
                    console.error('Error in adventurer.component: id correct but no adventurer found')
                    this.router.navigateByUrl('notFound');
                  }
                }
              )
            } else {
              this.router.navigateByUrl("invalidTodoId");
            }
          }
        }
      }
    )
  }

  addAdventurerToggle() {
    this.getCampaigns();
    this.getPlayers();
    if (this.addAdventurerDisplay) {
      this.addAdventurerDisplay = false;
    } else {
      this.addAdventurerDisplay = true;
    }

    this.newAdventurer = new Adventurer();
  }

  displayAdventurer(advent: Adventurer): void {
    this.selected = advent;
  }

  displayTable(): void {
    this.selected = null;
  }

  setEditAdventurer(): void {
    this.getCampaigns();
    this.editAdventurer = Object.assign({}, this.selected);
  }

  cancelEdit() {
    this.editAdventurer = null;
  }

  reload() {
    this.adventServ.index().subscribe(
      {
        next: (adventurers) => {
          this.adventurers = adventurers;
        },
        error: (problem) => {
          console.error('AdventurerHttpComponent.reload(): error loading adventurers', problem);
        }
      }
      );
    }

  getCampaigns() {
    this.campServ.index().subscribe(
      {
        next: (campaigns) => {
          this.campaigns = campaigns;
        },
        error: (problem) => {
          console.error('AdventurerHttpComponent.getCampaigns(): error loading campaign', problem);
        }
      }
      );
    }

    getPlayers() {
      this.playServ.index().subscribe(
        {
          next: (players) => {
            this.players = players;
          },
          error: (problem) => {
            console.error('AdventurerHttpComponent.getPlayers(): error loading campaign', problem);
          }
        }
        );
      }

  addAdventurer() {
    this.adventServ.create(this.newAdventurer).subscribe(
      {
        next: (createdAdvent) => {
          this.adventurers.push(createdAdvent);
          this.newAdventurer = new Adventurer();
          this.addAdventurerDisplay = false;
          this.reload();
        },
        error: (error) => {
          console.error('AdventurerHttpComponent.addAdventurer(): error creating adventurer', error);
        }
      }
    );
  }

  updateAdventurer(adventurer: Adventurer) {
    this.adventServ.update(adventurer).subscribe(
      {
        next: (updatedAdventurer) => {
          if (this.selected) {
            this.selected = Object.assign({}, this.editAdventurer);
          }
          this.editAdventurer = null;
          this.reload();
        },
        error: (error) => {
          console.error('AdventurerHttpComponent.updateAdventurer(): error updating adventurer', error);
        }
      }
    );
  }

  deleteAdventurer(adventId: number) {
    this.adventServ.destroy(adventId).subscribe(
      {
        next: () => {
          this.adventurers = this.adventurers.filter(advent => advent.id !== adventId);
          if (this.selected && this.selected.id === adventId) {
            this.selected = null;
          }
        },
        error: (error) => {
          console.error('AdventurerHttpComponent.deleteAdventurer(): error removing adventurer', error);
        }
      }
    );
  }
}
