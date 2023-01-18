import { AfterContentInit, Component, Input, OnInit } from '@angular/core';
import { TopicOverview } from '../../models/topic.model';

@Component({
  selector: 'iap-quest-item',
  templateUrl: './quest-item.component.html',
  styleUrls: ['./quest-item.component.scss'],
})
export class QuestItemComponent implements OnInit, AfterContentInit {
  @Input()
  quests!: TopicOverview;
  @Input()
  team!: string;
  @Input()
  pos!: { x: number; y: number };
  @Input()
  size!: { w: number; h: number };

  currentBgWitdh!: number;
  currentBgHeight!: number;
  constructor() {}

  ngOnInit(): void {
    setInterval(() => {
      this.updateBgSizes();
    });
  }
  ngAfterContentInit(): void {
    this.updateBgSizes();
  }
  updateBgSizes() {
    const img = document.querySelector('.backgroundImage') as any;
    this.currentBgWitdh = img.width ?? 100;
    this.currentBgHeight = img.height ?? 100;
  }
}
