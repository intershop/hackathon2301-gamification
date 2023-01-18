import { AfterViewInit, Component } from '@angular/core';
import { LoaderService } from '@intershop/iap-core';
import { assetUrl } from '../single-spa/asset-url';
@Component({
  selector: 'iap-module-dev-cockpit',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
})
export class AppComponent implements AfterViewInit {
  constructor(loaderService: LoaderService) {
    loaderService.toggleLoader(false);
  }

  ngAfterViewInit(): void {
    (document.getElementById('a') as HTMLAudioElement).play();
  }
  assetUrl = assetUrl;
}
