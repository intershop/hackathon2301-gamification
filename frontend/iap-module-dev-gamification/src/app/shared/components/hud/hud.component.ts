import { Component, OnInit } from '@angular/core';
import { CurrentUserService, User } from '@intershop/iap-core';
import { Observable } from 'rxjs';

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
}
