import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { OverworldPageComponent } from './pages/overworld-page/overworld-page.component';
import { StartPageComponent } from './pages/start-page/start-page.component';
import { HudComponent } from './shared/components/hud/hud.component';

@NgModule({
  declarations: [
    AppComponent,
    OverworldPageComponent,
    StartPageComponent,
    HudComponent,
  ],
  imports: [BrowserModule, AppRoutingModule],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
