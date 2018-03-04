import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { MyFirstComponentAncSuffixComponent } from './my-first-component-anc-suffix.component';
import { MyFirstComponentAncSuffixDetailComponent } from './my-first-component-anc-suffix-detail.component';
import { MyFirstComponentAncSuffixPopupComponent } from './my-first-component-anc-suffix-dialog.component';
import { MyFirstComponentAncSuffixDeletePopupComponent } from './my-first-component-anc-suffix-delete-dialog.component';

export const myFirstComponentRoute: Routes = [
    {
        path: 'my-first-component-anc-suffix',
        component: MyFirstComponentAncSuffixComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'MyFirstComponents'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'my-first-component-anc-suffix/:id',
        component: MyFirstComponentAncSuffixDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'MyFirstComponents'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const myFirstComponentPopupRoute: Routes = [
    {
        path: 'my-first-component-anc-suffix-new',
        component: MyFirstComponentAncSuffixPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'MyFirstComponents'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'my-first-component-anc-suffix/:id/edit',
        component: MyFirstComponentAncSuffixPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'MyFirstComponents'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'my-first-component-anc-suffix/:id/delete',
        component: MyFirstComponentAncSuffixDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'MyFirstComponents'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
