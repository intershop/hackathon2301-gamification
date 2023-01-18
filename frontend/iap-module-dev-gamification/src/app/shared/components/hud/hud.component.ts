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
  name: string = '';
  url: string = '';

  constructor(private currentUserService: CurrentUserService) {
    this.user$ = currentUserService.getCurrentUser();
    this.user$.subscribe((currentUser) => {
      this.name = currentUser.profile.firstName.toLowerCase();
    });
    this.url = `./avatars/${this.name}_avatar.png`;
  }

  ngOnInit(): void {}

  getUrl() {
    return assetUrl(this.url);
  }
}
