import { Component, Input, OnInit } from '@angular/core';
import { TopicOverview } from '../../models/topic.model';

@Component({
  selector: 'iap-quest-item',
  templateUrl: './quest-item.component.html',
  styleUrls: ['./quest-item.component.scss'],
})
export class QuestItemComponent implements OnInit {
  @Input()
  quests!: TopicOverview;
  @Input()
  team!: string;
  @Input()
  pos!: { x: number; y: number };
  @Input()
  size!: { w: number; h: number };

  constructor() {}

  ngOnInit(): void {}
}
