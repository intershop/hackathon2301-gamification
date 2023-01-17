import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user.model';
import { Quest } from 'src/app/models/quest.model';
import { QuestsService } from 'src/app/services/quests.service';
import { UsersService } from 'src/app/services/users.service';
import { TopicOverview } from 'src/app/models/topic.model';
import { assetUrl } from 'src/single-spa/asset-url';
@Component({
  selector: 'iap-overworld-page',
  templateUrl: './overworld-page.component.html',
  styleUrls: ['./overworld-page.component.scss'],
})
export class OverworldPageComponent implements OnInit {
  quests: TopicOverview = { '': [] };
  users: User[] = [];

  objects = [
    {
      team: 'wifi',
      pos: { x: 855, y: 157 },
      size: { w: 65, h: 112 },
    },
    {
      team: 'aquarium',
      pos: { x: 1051, y: 449 },
      size: { w: 299, h: 269 },
    },
    {
      team: 'printer',
      pos: { x: 882, y: 567 },
      size: { w: 115, h: 162 },
    },
    {
      team: 'coffee',
      pos: { x: 611, y: 535 },
      size: { w: 77, h: 143 },
    },
    {
      team: 'energy',
      pos: { x: 921, y: 815 },
      size: { w: 72, h: 293 },
    },
    {
      team: 'mail',
      pos: { x: 497, y: 733 },
      size: { w: 102, h: 111 },
    },
    {
      team: 'radio',
      pos: { x: 495, y: 623 },
      size: { w: 94, h: 54 },
    },
    {
      team: 'clock',
      pos: { x: 1010, y: 81 },
      size: { w: 160, h: 131 },
    },
    {
      team: 'cat',
      pos: { x: 5, y: 738 },
      size: { w: 128, h: 74 },
    },
    {
      team: 'qr',
      pos: { x: 181, y: 601 },
      size: { w: 62, h: 79 },
    },
    {
      team: 'plant',
      pos: { x: 588, y: 119 },
      size: { w: 82, h: 147 },
    },
  ];
  constructor(
    private questService: QuestsService,
    private userService: UsersService
  ) {
    console.log(this.objects.length);
  }

  ngOnInit(): void {
    this.getQuests();
    this.getUsers();
    let last = { x: 0, y: 0 };
    (
      document.querySelector('.backgroundImage') as HTMLImageElement
    ).addEventListener('click', (e: MouseEvent) => {
      const { x, y } = {
        x: (e as any).layerX - last.x,
        y: (e as any).layerY - last.y,
      };
      console.log({ x, y });
      last = { x, y };
    });
  }

  getQuests(): void {
    this.questService.getQuests().subscribe((quests) => (this.quests = quests));
  }

  getUsers(): void {
    this.userService.getUsers().subscribe((users) => (this.users = users));
  }

  getUrl() {
    return assetUrl('./background.gif');
  }
}
