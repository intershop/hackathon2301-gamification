import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { OverworldPageComponent } from './pages/overworld-page/overworld-page.component';
import { QuestListComponent } from './pages/quest-list/quest-list.component';
import { StartPageComponent } from './pages/start-page/start-page.component';
import { HudComponent } from './shared/components/hud/hud.component';
import { QuestItemComponent } from './components/quest-item/quest-item.component';
import { HttpClientModule } from '@angular/common/http';

@NgModule({
  declarations: [
    AppComponent,
    OverworldPageComponent,
    StartPageComponent,
    HudComponent,
    QuestListComponent,
    QuestItemComponent,
  ],
  imports: [BrowserModule, AppRoutingModule, HttpClientModule],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
