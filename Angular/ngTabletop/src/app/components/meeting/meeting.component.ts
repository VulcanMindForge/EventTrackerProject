import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Meeting } from '../../models/meeting.model';
import { ActivatedRoute, Router } from '@angular/router';
import { MeetingService } from '../../services/meeting.service';

@Component({
  selector: 'app-meeting',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './meeting.component.html',
  styleUrl: './meeting.component.css'
})
export class MeetingComponent {
  meetings: Meeting[] = [];
  selected: Meeting | null = null;
  newMeeting = new Meeting();
  editMeeting: Meeting | null = null;

  constructor(private router: Router, private activatedRoute: ActivatedRoute, private meetServ: MeetingService){}

  ngOnInit(): void {
    this.reload();
    this.activatedRoute.paramMap.subscribe(
      {
        next: (params) => {
          let meetIdStr = params.get("meetId");
          if (meetIdStr) {
            let meetId = parseInt(meetIdStr);
            if (!isNaN(meetId)) {
              this.meetServ.show(meetId).subscribe(
                {
                  next: (meeting) => {
                    this.selected = meeting;
                  },
                  error: (error) => {
                    console.error('Error in Meeting.component: id correct but no meeting found')
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

  displayMeeting(meeting: Meeting): void {
    this.selected = meeting;
  }

  displayTable(): void {
    this.selected = null;
  }

  setEditMeeting(): void {
    this.editMeeting = Object.assign({}, this.selected);
  }

  cancelEdit() {
    this.editMeeting = null;
  }

  reload() {
    this.meetServ.index().subscribe(
      {
        next: (meetings) => {
          this.meetings = meetings;
        },
        error: (problem) => {
          console.error('MeetingHttpComponent.reload(): error loading meetings', problem);
        }
      }
      );
    }

  addMeeting(meeting: Meeting) {
    this.meetServ.create(this.newMeeting).subscribe(
      {
        next: (createdMeeting) => {
          this.meetings.push(createdMeeting);
          this.newMeeting = new Meeting();
          this.reload();
        },
        error: (error) => {
          console.error('MeetingHttpComponent.addMeeting(): error creating meeting', error);
        }
      }
    );
  }

  updateMeeting(meeting: Meeting) {
    this.meetServ.update(meeting).subscribe(
      {
        next: (updatedMeeting) => {
          if (this.selected) {
            this.selected = Object.assign({}, this.editMeeting);
          }
          this.editMeeting = null;
          this.reload();
        },
        error: (error) => {
          console.error('MeetingHttpComponent.updateMeeting(): error updating meeting', error);
        }
      }
    );
  }

  deleteMeeting(meetingId: number) {
    this.meetServ.destroy(meetingId).subscribe(
      {
        next: () => {
          this.meetings = this.meetings.filter(meeting => meeting.id !== meetingId);
          if (this.selected && this.selected.id === meetingId) {
            this.selected = null;
          }
        },
        error: (error) => {
          console.error('MeetingHttpComponent.deleteMeeting(): error removing meeting', error);
        }
      }
    );
  }
}
