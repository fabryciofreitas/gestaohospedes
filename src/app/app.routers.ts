import { Routes } from '@angular/router';

import { LoginComponent } from './features/auth/login/login.component';
import { DashboardComponent } from './features/dashboard/dashboard.component';

import { HospedeListComponent } from './features/hospedes/list/hospede-list.component';
import { HospedeFormComponent } from './features/hospedes/form/hospede-form.component';

import { CheckinComponent } from './features/checkin/checkin.component';
import { CheckoutComponent } from './features/checkout/checkout.component';

import { PresentesComponent } from './features/presentes/presentes.component';
import { HistoricoComponent } from './features/historico/historico.component';

import { AuthGuard } from './core/guards/auth.guard';

export const routes: Routes = [

    {
        path: '',
        redirectTo: 'login',
        pathMatch: 'full'
    },

    {
        path: 'login',
        component: LoginComponent
    },

    {
        path: 'dashboard',
        component: DashboardComponent,
        canActivate: [AuthGuard]
    },

    {
        path: 'hospedes',
        component: HospedeListComponent,
        canActivate: [AuthGuard]
    },

    {
        path: 'hospedes/novo',
        component: HospedeFormComponent,
        canActivate: [AuthGuard]
    },

    {
        path: 'hospedes/:id',
        component: HospedeFormComponent,
        canActivate: [AuthGuard]
    },

    {
        path: 'checkin',
        component: CheckinComponent,
        canActivate: [AuthGuard]
    },

    {
        path: 'checkout',
        component: CheckoutComponent,
        canActivate: [AuthGuard]
    },

    {
        path: 'presentes',
        component: PresentesComponent,
        canActivate: [AuthGuard]
    },

    {
        path: 'historico',
        component: HistoricoComponent,
        canActivate: [AuthGuard]
    },

    {
        path: '**',
        redirectTo: 'dashboard'
    }

];