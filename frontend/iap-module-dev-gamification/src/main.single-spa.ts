import { enableProdMode, NgZone } from '@angular/core';

import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';
import { Router } from '@angular/router';
import { SingleSpaHierarchy } from '@intershop/iap-core';

import {
  singleSpaAngular,
  getSingleSpaExtraProviders,
} from 'single-spa-angular';

import { AppModule } from './app/app.module';
import { environment } from './environments/environment';
import {
  SingleSpaProps,
  singleSpaPropsSubject,
} from './single-spa/single-spa-props';

if (environment.production) {
  enableProdMode();
}

const lifecycles = singleSpaAngular({
  domElementGetter: () => {
    const e = document.getElementById('iap-dev-cockpit') as HTMLElement;

    return e;
  },
  bootstrapFunction: (singleSpaProps: SingleSpaProps) => {
    singleSpaPropsSubject.next(singleSpaProps);
    console.log('boot now');
    return platformBrowserDynamic(getSingleSpaExtraProviders()).bootstrapModule(
      AppModule
    );
  },
  template: '<iap-module-dev-cockpit />',

  Router,
  NgZone,
});

export const bootstrap = lifecycles.bootstrap;
export const mount = SingleSpaHierarchy.doAfter(
  () => !!document.getElementById('iap-dev-cockpit'),
  lifecycles.mount
);
export const unmount = lifecycles.unmount;
