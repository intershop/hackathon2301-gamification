import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { Quest } from '../models/quest.model';
import { TopicOverview } from '../models/topic.model';

@Injectable({
  providedIn: 'root',
})
export class QuestsService {
  firstQuest: Quest = {
    id: 0,
    title: 'product zoom',
    createdBy: 'steven',
    assignedTo: 'dilara',
    state: 'in progress',
  };

  secondQuest: Quest = {
    id: 1,
    title: 'gamification',
    createdBy: 'sebastian',
    assignedTo: 'paul',
    state: 'in progress',
  };

  QUESTS: Quest[] = [this.firstQuest, this.secondQuest];

  constructor(private httpClient: HttpClient) {}

  getQuests(): Observable<TopicOverview> {
    const quests = of({ core: this.QUESTS });
    return this.httpClient.get<any>('https://10.0.29.131:9443/quests');
  }

  addQuest(quest: Quest): void {
    this.QUESTS.push(quest);
  }
}
