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
    switch (this.achievement) {
      case 'Debugger':
        return assetUrl('achievements/bug_test.gif');
      case 'Self Sustained':
        return assetUrl('achievements/bug_test.gif');
      case 'Peace Was No Decision':
        return assetUrl('achievements/bug_test.gif');
      case 'Lucky Luke':
        return assetUrl('achievements/bug_test.gif');
      case 'No Hurry':
        return assetUrl('achievements/bug_test.gif');
      case 'Firefighter':
        return assetUrl('achievements/bug_test.gif');
      case 'Street':
        return assetUrl('achievements/bug_test.gif');
      default:
        return '';
    }
  }
}
