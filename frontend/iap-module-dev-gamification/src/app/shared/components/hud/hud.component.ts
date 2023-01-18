import { LowerCasePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { CurrentUserService, User } from '@intershop/iap-core';
import { Observable } from 'rxjs';
import { assetUrl } from 'src/single-spa/asset-url';

@Component({
  selector: 'iap-hud',
  templateUrl: './hud.component.html',
  styleUrls: ['./hud.component.scss'],
})
export class HudComponent implements OnInit {
  user$!: Observable<User>;
  constructor(private currentUserService: CurrentUserService) {
    this.user$ = currentUserService.getCurrentUser();
  }

  ngOnInit(): void {}

  findAvatar() {
    var name = '';
    this.user$.subscribe((currentUser) => {
      name = currentUser.profile.firstName.toLowerCase();
    });
    return name;
  }

  getUrl() {
    var firstName = this.findAvatar();
    var url = `./avatars/${firstName}_avatar.png`;
    return assetUrl(url);
  }
}
