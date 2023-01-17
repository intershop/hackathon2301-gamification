import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user.model';
import { Quest } from 'src/app/models/quest.model';
import { QuestsService } from 'src/app/services/quests.service';
import { UsersService } from 'src/app/services/users.service';
import { TopicOverview } from 'src/app/models/topic.model';

@Component({
  selector: 'iap-overworld-page',
  templateUrl: './overworld-page.component.html',
  styleUrls: ['./overworld-page.component.scss']
})
export class OverworldPageComponent implements OnInit {
  quests: TopicOverview = {"": []}
  users: User[] = []

  constructor(private questService: QuestsService, private userService: UsersService) { }

  ngOnInit(): void {
    this.getQuests();
    this.getUsers();
  }

  getQuests(): void {
    this.questService.getQuests()
        .subscribe(quests => this.quests = quests);
  }

  getUsers(): void {
    this.userService.getUsers()
        .subscribe(users => this.users = users);
  }

}
