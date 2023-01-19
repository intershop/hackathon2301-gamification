import { Component, OnInit, Input } from '@angular/core';
import { User } from 'src/app/models/user.model';
import { Quest } from 'src/app/models/quest.model';
import { QuestsService } from 'src/app/services/quests.service';
import { UsersService } from 'src/app/services/users.service';
import { TopicOverview } from 'src/app/models/topic.model';
import { map, delay } from 'rxjs/operators';
import { assetUrl } from 'src/single-spa/asset-url';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'iap-highscore-page',
  templateUrl: './highscore-page.component.html',
  styleUrls: ['./highscore-page.component.scss'],
})
export class HighscorePageComponent implements OnInit {
  team: string = '';
  quests?: Quest[];
  users: User[] = [];
  selectedQuest?: Quest;

  assetUrl = assetUrl;

  bugImgUrl = assetUrl('/achievements/bug_test.gif');

  constructor(
    private route: ActivatedRoute,
    private userService: UsersService
  ) {}

  ngOnInit(): void {
    this.getUsers();
  }

  getUsers(): void {
    this.userService.getUsers().subscribe((users) => {
      console.log(users);
      this.users = users.sort(
        (u1, u2) => u2.experience_points - u1.experience_points
      );
    });
  }

  getUrl(name: string) {
    const f = name.split(' ');
    f.pop();
    return assetUrl(`./avatars/${f.join(' ').toLowerCase()}_avatar.png`);
  }
}
