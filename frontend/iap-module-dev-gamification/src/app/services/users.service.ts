import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { tap } from 'rxjs/operators';
import { User } from '../models/user.model';

@Injectable({
  providedIn: 'root',
})
export class UsersService {
  USERS = [
    { id: 12, name: 'Dr. Nice', email: 'nice@intershop.com' },
    { id: 13, name: 'Bombasto', email: 'bombasto@intershop.com' },
    { id: 14, name: 'Celeritas', email: 'celeritas@intershop.com' },
    { id: 15, name: 'Magneta', email: 'Magneta@intershop.com' },
    { id: 16, name: 'RubberMan', email: 'RubberMan@intershop.com' },
    { id: 17, name: 'Dynama', email: 'Dynama@intershop.com' },
    { id: 18, name: 'Dr. IQ', email: 'iq@intershop.com' },
    { id: 19, name: 'Magma', email: 'Magma@intershop.com' },
    { id: 20, name: 'Tornado', email: 'Tornado@intershop.com' },
  ];

  user: User = { id: 12, name: 'Dr. Nice', email: 'nice@intershop.com' };

  constructor(private httpClient: HttpClient) {}

  getUsers(): Observable<User[]> {
    const quests = of(this.USERS);
    return quests;
  }

  getUser(name: string): Observable<User> {
    //TO DO: search for the user by email
    //return the user
    return this.httpClient
      .get<any>(
        'https://10.0.29.131:9443/users/' +
          name.toLocaleLowerCase().replace(' ', '_').replace('ß', 'ss')
      )
      .pipe(tap((a: any) => (a.display_name = name)));
  }
}
