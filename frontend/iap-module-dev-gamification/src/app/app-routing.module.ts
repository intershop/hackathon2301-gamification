import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { EmptyRouteComponent } from './empty-route/empty-route.component';
import { OverworldPageComponent } from './pages/overworld-page/overworld-page.component';

const routes: Routes = [
  { path: 'dev-cockpit/gamification', component: OverworldPageComponent },
  { path: '**', component: EmptyRouteComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
