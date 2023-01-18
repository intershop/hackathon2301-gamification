import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user.model';
import { Quest } from 'src/app/models/quest.model';
import { QuestsService } from 'src/app/services/quests.service';
import { UsersService } from 'src/app/services/users.service';
import { TopicOverview } from 'src/app/models/topic.model';
import { map, delay } from 'rxjs/operators';
import { assetUrl } from 'src/single-spa/asset-url';

@Component({
  selector: 'iap-quest-list',
  templateUrl: './quest-list.component.html',
  styleUrls: ['./quest-list.component.scss']
})
export class QuestListComponent implements OnInit {

  team: string = "topic1";
  quests?: Quest[];
  users: User[] = [];
  selectedQuest?: Quest;

  assetUrl = assetUrl;

  bugImgUrl = assetUrl('/achievements/bug_test.gif');

  constructor(
    private questService: QuestsService,
    private userService: UsersService
  ) { }

  ngOnInit(): void {
    this.getQuestsByTeamName(this.team);
    this.getUsers();
  }

  getQuestsByTeamName(team: string): void {
    document.getElementById("loadingBug")?.classList.remove("d-none");
    this.questService.getQuests().pipe(
        map(questsMap => questsMap[team]),
        delay(1700)
      ).subscribe((questsArray) => { 
      (this.quests = questsArray);
      document.getElementById("loadingBug")?.classList.add("d-none");
      console.log(this.quests);
    });
  }

  showQuestDetail(questId: number) {
    this.selectedQuest = this.quests?.find((quest) => quest.id == questId);
    console.log(this.selectedQuest);

  }

  getUsers(): void {
    this.userService.getUsers().subscribe((users) => (this.users = users));
  }


}
