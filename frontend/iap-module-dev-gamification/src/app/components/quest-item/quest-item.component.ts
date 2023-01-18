import { AfterContentInit, Component, Input, OnDestroy, OnInit } from '@angular/core';
import { TopicOverview } from '../../models/topic.model';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'iap-quest-item',
  templateUrl: './quest-item.component.html',
  styleUrls: ['./quest-item.component.scss'],
})
export class QuestItemComponent implements OnInit, AfterContentInit,OnDestroy {
  @Input()
  quests!: TopicOverview;
  @Input()
  team!: string;
  @Input()
  pos!: { x: number; y: number };
  @Input()
  size!: { w: number; h: number };

  private bgSizesInterval: any;

  currentBgWitdh!: number;
  currentBgHeight!: number;
  
  constructor(
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.bgSizesInterval = setInterval(() => {
      this.updateBgSizes();
    });
  }
  ngAfterContentInit(): void {
    this.updateBgSizes();
  }
  updateBgSizes() {
    const img = document.querySelector('.backgroundImage') as any;
    if (img) {
      this.currentBgWitdh = img.width ?? 100;
      this.currentBgHeight = img.height ?? 100;
    }
  }

  openQuestlist(team: string): void {
    console.log(team);
    let roomDiv = document.getElementById("room");
    roomDiv!.classList.add("hidden");
    setTimeout(() => {
      this.router.navigate(['../quests/', team], { relativeTo: this.route });
    }, 2000);
  }
  ngOnDestroy(): void {
    clearInterval(this.bgSizesInterval);
  }
}
