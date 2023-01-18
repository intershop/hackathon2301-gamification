import { Component, Input, OnInit } from '@angular/core';
import { assetUrl } from '../../../../single-spa/asset-url';

@Component({
  selector: 'iap-achievement',
  templateUrl: './achievement.component.html',
  styleUrls: ['./achievement.component.scss'],
})
export class AchievementComponent implements OnInit {
  @Input()
  achievement: any;
  constructor() {}

  assetUrl = assetUrl;
  ngOnInit(): void {}
}
