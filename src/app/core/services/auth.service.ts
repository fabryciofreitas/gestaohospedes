import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Observable, tap } from 'rxjs';

import { TokenService } from './token.service';

@Injectable({
    providedIn: 'root'
})
export class AuthService {

    private readonly api = 'http://localhost:8080/api/auth';

    private http = inject(HttpClient);

    private token = inject(TokenService);

    login(usuario: string, senha: string): Observable<any> {

        return this.http.post<any>(
            `${this.api}/login`,
            {
                usuario,
                senha
            }
        ).pipe(

            tap(resposta => {

                this.token.salvar(resposta.token);

            })

        );

    }

    logout() {

        this.token.remover();

    }

}