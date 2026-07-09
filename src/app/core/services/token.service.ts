import { Injectable } from '@angular/core';

@Injectable({
    providedIn: 'root'
})
export class TokenService {

    private readonly CHAVE = 'jwt';

    salvar(token: string): void {
        localStorage.setItem(this.CHAVE, token);
    }

    obter(): string | null {
        return localStorage.getItem(this.CHAVE);
    }

    remover(): void {
        localStorage.removeItem(this.CHAVE);
    }

    autenticado(): boolean {
        return this.obter() !== null;
    }

}