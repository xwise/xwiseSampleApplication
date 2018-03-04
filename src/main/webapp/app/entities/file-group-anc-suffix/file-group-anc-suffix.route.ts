import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { FileGroupAncSuffixComponent } from './file-group-anc-suffix.component';
import { FileGroupAncSuffixDetailComponent } from './file-group-anc-suffix-detail.component';
import { FileGroupAncSuffixPopupComponent } from './file-group-anc-suffix-dialog.component';
import { FileGroupAncSuffixDeletePopupComponent } from './file-group-anc-suffix-delete-dialog.component';

export const fileGroupRoute: Routes = [
    {
        path: 'file-group-anc-suffix',
        component: FileGroupAncSuffixComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'FileGroups'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'file-group-anc-suffix/:id',
        component: FileGroupAncSuffixDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'FileGroups'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const fileGroupPopupRoute: Routes = [
    {
        path: 'file-group-anc-suffix-new',
        component: FileGroupAncSuffixPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'FileGroups'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'file-group-anc-suffix/:id/edit',
        component: FileGroupAncSuffixPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'FileGroups'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'file-group-anc-suffix/:id/delete',
        component: FileGroupAncSuffixDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'FileGroups'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
