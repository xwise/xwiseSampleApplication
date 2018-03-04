import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { UploadVersionAncSuffixComponent } from './upload-version-anc-suffix.component';
import { UploadVersionAncSuffixDetailComponent } from './upload-version-anc-suffix-detail.component';
import { UploadVersionAncSuffixPopupComponent } from './upload-version-anc-suffix-dialog.component';
import { UploadVersionAncSuffixDeletePopupComponent } from './upload-version-anc-suffix-delete-dialog.component';

export const uploadVersionRoute: Routes = [
    {
        path: 'upload-version-anc-suffix',
        component: UploadVersionAncSuffixComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'UploadVersions'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'upload-version-anc-suffix/:id',
        component: UploadVersionAncSuffixDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'UploadVersions'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const uploadVersionPopupRoute: Routes = [
    {
        path: 'upload-version-anc-suffix-new',
        component: UploadVersionAncSuffixPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'UploadVersions'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'upload-version-anc-suffix/:id/edit',
        component: UploadVersionAncSuffixPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'UploadVersions'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'upload-version-anc-suffix/:id/delete',
        component: UploadVersionAncSuffixDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'UploadVersions'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
