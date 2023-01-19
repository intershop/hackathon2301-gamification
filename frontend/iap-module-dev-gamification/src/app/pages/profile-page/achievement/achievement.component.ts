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
  getImg() {
    switch (this.achievement.title) {
      case 'Debugger':
        return assetUrl('achievements/bug_test.gif');
      case 'Self Sustained':
        return assetUrl('achievements/self.gif');
      case 'Peace Was No Decision':
        return assetUrl('achievements/goose.gif');
      case 'Lucky Luke':
        return assetUrl('achievements/hedgehog.gif');
      case 'No Hurry':
        return assetUrl('achievements/rabbit.gif');
      case 'Firefighter':
        return assetUrl('achievements/fire_extinguisher.gif');
      case 'Street':
        return assetUrl('achievements/poker_card.gif');
      default:
        console.log(this.achievement);
        return '';
    }
  }

  getTier() {
    if (this.achievement.tier == '1')
      return assetUrl('achievements/bronze.gif');
    if (this.achievement.tier == '2')
      return assetUrl('achievements/silver.gif');
    return assetUrl('achievements/gold.gif');
  }
}
