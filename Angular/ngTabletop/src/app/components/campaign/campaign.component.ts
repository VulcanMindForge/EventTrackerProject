import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Campaign } from '../../models/campaign.model';
import { CampaignService } from '../../services/campaign.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { PlayerService } from '../../services/player.service';
import { Player } from '../../models/player.model';
import { Game } from '../../models/game.model';
import { GameService } from '../../services/game.service';

@Component({
  selector: 'app-campaign',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './campaign.component.html',
  styleUrl: './campaign.component.css'
})
export class CampaignComponent {

  campaigns: Campaign[] = [];
  selected: Campaign | null = null;
  newCampaign: Campaign = new Campaign();
  editCampaign: Campaign | null = null;
  players: Player[] = [];
  games: Game[] = [];
  addedPlayers: Player[] = [];
  addCampaignDisplay = false;

  constructor(private router: Router, private activatedRoute: ActivatedRoute, private campServ: CampaignService, private playServ: PlayerService, private gameServ: GameService){}

  ngOnInit(): void {
    this.reload();
    this.getGames();
    this.getPlayers();
    this.activatedRoute.paramMap.subscribe(
      {
        next: (params) => {
          let campIdStr = params.get("campId");
          if (campIdStr) {
            let campId = parseInt(campIdStr);
            if (!isNaN(campId)) {
              this.campServ.show(campId).subscribe(
                {
                  next: (camp) => {
                    this.selected = camp;
                  },
                  error: (error) => {
                    console.error('Error in Campaign.component: id correct but no campaign found')
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

  addCampaignToggle() {
    this.getGames();
    this.getPlayers();
    if (this.addCampaignDisplay) {
      this.addCampaignDisplay = false;
    } else {
      this.addCampaignDisplay = true;
    }

    this.newCampaign = new Campaign();
  }

  addPlayer(playerAdd: Player) {
    if (playerAdd && this.editCampaign) {
      // Check if the player is not already added
      if (!this.editCampaign?.players?.find(player => player.id === playerAdd.id)) {
        this.editCampaign?.players?.push(playerAdd);
      }
    }
  }

  removePlayer(player: Player) {
    if (this.editCampaign) {
      this.editCampaign.players = this.editCampaign?.players?.filter(p => p.id !== player.id);
    }
  }

  addPlayerNew(playerAdd: Player) {
    if (!this.newCampaign.players) {
      this.newCampaign.players = [];
    }

    if (!this.newCampaign.players.find(p => p.id === playerAdd.id)) {
      this.newCampaign.players.push(playerAdd);
    }
  }

  removePlayerNew(player: Player) {
    if (this.newCampaign) {
      this.newCampaign.players = this.newCampaign?.players?.filter(p => p.id !== player.id);
    }
  }

  getPlayers() {
    this.playServ.index().subscribe(
      {
        next: (players) => {
          this.players = players;
        },
        error: (problem) => {
          console.error('CampaignHttpComponent.getPlayers(): error loading campaign', problem);
        }
      }
    );
  }

  getGames() {
    this.gameServ.index().subscribe(
      {
        next: (games) => {
          this.games = games;
        },
        error: (problem) => {
          console.error('CampaignHttpComponent.getPlayers(): error loading campaign', problem);
        }
      }
    );
  }

  displayCampaign(camp: Campaign): void {
    this.selected = camp;
  }

  displayTable(): void {
    this.selected = null;
  }

  setEditCampaign(): void {
    this.editCampaign = Object.assign({}, this.selected);
  }

  cancelEdit() {
    this.editCampaign = null;
  }

  reload() {
    this.campServ.index().subscribe(
      {
        next: (todos) => {
          this.campaigns = todos;
        },
        error: (problem) => {
          console.error('CampaignHttpComponent.reload(): error loading campaigns', problem);
        }
      }
      );
    }

  addCampaign(camp: Campaign) {
    this.campServ.create(this.newCampaign).subscribe(
      {
        next: (createdTodo) => {
          this.campaigns.push(createdTodo);
          this.newCampaign = new Campaign();
          this.addCampaignToggle();
          this.reload();
        },
        error: (error) => {
          console.error('CampaignHttpComponent.addCampaign(): error creating campaign', error);
        }
      }
    );
  }

  updateCampaign(campaign: Campaign) {
    this.campServ.update(campaign).subscribe(
      {
        next: (updatedCampaign) => {
          if (this.selected) {
            this.selected = Object.assign({}, this.editCampaign);
          }
          this.editCampaign = null;
          this.reload();
        },
        error: (error) => {
          console.error('CampaignHttpComponent.updateTodo(): error updating campaign', error);
        }
      }
    );
  }

  deleteCampaign(campId: number) {
    this.campServ.destroy(campId).subscribe(
      {
        next: () => {
          this.campaigns = this.campaigns.filter(camp => camp.id !== campId);
          if (this.selected && this.selected.id === campId) {
            this.selected = null;
          }
        },
        error: (error) => {
          console.error('TodoListHttpComponent.deleteTodo(): error removing todo', error);
        }
      }
    );
  }

}
