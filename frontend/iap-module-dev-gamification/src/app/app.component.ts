import { Component } from '@angular/core';
import { LoaderService } from '@intershop/iap-core';

@Component({
  selector: 'iap-module-dev-cockpit',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
})
export class AppComponent {
  constructor(loaderService: LoaderService) {
    loaderService.toggleLoader(false);
  }
}
