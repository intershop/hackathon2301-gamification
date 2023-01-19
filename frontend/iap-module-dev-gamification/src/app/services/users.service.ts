import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { map, tap } from 'rxjs/operators';
import { User } from '../models/user.model';

@Injectable({
  providedIn: 'root',
})
export class UsersService {
  constructor(private httpClient: HttpClient) {}

  getUsers(): Observable<User[]> {
    return this.httpClient.get<any>('https://10.0.29.131:9443/users/');
  }

  getUser(name: string): Observable<User> {
    //TO DO: search for the user by email
    //return the user
    return this.httpClient.get<any>(
      'https://10.0.29.131:9443/users/' +
        name.toLocaleLowerCase().replace(' ', '_').replace('ß', 'ss')
    );
  }
}
