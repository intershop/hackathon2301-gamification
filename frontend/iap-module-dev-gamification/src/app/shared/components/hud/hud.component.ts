import { Component, OnInit } from '@angular/core';
import { CurrentUserService } from '@intershop/iap-core';
import { Observable } from 'rxjs';
import { switchMap } from 'rxjs/operators';
import { assetUrl } from 'src/single-spa/asset-url';
import { UsersService } from '../../../services/users.service';

@Component({
  selector: 'iap-hud',
  templateUrl: './hud.component.html',
  styleUrls: ['./hud.component.scss'],
})
export class HudComponent implements OnInit {
  user$!: Observable<any>;
  name: string = '';
  url: string = '';
  constructor(
    private userService: UsersService,
    private currentUser: CurrentUserService
  ) {
    this.user$ = this.currentUser
      .getCurrentUser()
      .pipe(switchMap((u) => this.userService.getUser(u.profile.fullName)));
  }

  ngOnInit(): void {}

  getUrl(name: string) {
    const f = name.split(' ');
    f.pop();
    return assetUrl(`./avatars/${f.join(' ').toLowerCase()}_avatar.png`);
  }
}
