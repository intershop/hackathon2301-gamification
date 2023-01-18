import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { EmptyRouteComponent } from './empty-route/empty-route.component';
import { OverworldPageComponent } from './pages/overworld-page/overworld-page.component';
import { StartPageComponent } from './pages/start-page/start-page.component';
import { QuestListComponent } from './pages/quest-list/quest-list.component';
import { ProfilePageComponent } from './pages/profile-page/profile-page.component';

const routes: Routes = [
  { path: 'dev-cockpit/gamification', component: StartPageComponent },
  { path: 'dev-cockpit/gamification/map', component: OverworldPageComponent },
  { path: 'dev-cockpit/gamification/profile', component: ProfilePageComponent },
  {
    path: 'dev-cockpit/gamification/quests/:topic',
    component: QuestListComponent,
  },
  { path: '**', component: EmptyRouteComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
