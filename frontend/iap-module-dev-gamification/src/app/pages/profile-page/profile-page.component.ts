import { Component, OnInit } from '@angular/core';
import { CurrentUserService } from '@intershop/iap-core';
import { Observable } from 'rxjs';
import { User } from '../../models/user.model';
import { UsersService } from '../../services/users.service';
import { switchMap } from 'rxjs/operators';
@Component({
  selector: 'iap-profile-page',
  templateUrl: './profile-page.component.html',
  styleUrls: ['./profile-page.component.scss'],
})
export class ProfilePageComponent implements OnInit {
  user$: Observable<any>;
  constructor(
    private userService: UsersService,
    private currentUser: CurrentUserService
  ) {
    this.user$ = this.currentUser
      .getCurrentUser()
      .pipe(switchMap((u) => this.userService.getUser(u.profile.fullName)));
  }

  ngOnInit(): void {}
}
