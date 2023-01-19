import { Component, OnInit } from '@angular/core';
import { CurrentUserService } from '@intershop/iap-core';
import { Observable } from 'rxjs';
import { User } from '../../models/user.model';
import { UsersService } from '../../services/users.service';
import { switchMap } from 'rxjs/operators';
import { ActivatedRoute, Route } from '@angular/router';
import { assetUrl } from 'src/single-spa/asset-url';
@Component({
  selector: 'iap-profile-page',
  templateUrl: './profile-page.component.html',
  styleUrls: ['./profile-page.component.scss'],
})
export class ProfilePageComponent implements OnInit {
  user$: Observable<any>;
  name: string = '';
  url: string = '';

  constructor(
    private userService: UsersService,
    private currentUser: CurrentUserService,
    private route: ActivatedRoute
  ) {
    const user = this.route.snapshot.paramMap.get('user') || '';

    this.user$ = this.userService.getUser(user);
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
