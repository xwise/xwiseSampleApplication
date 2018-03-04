import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { BaselineEntryVersionAncSuffixComponent } from './baseline-entry-version-anc-suffix.component';
import { BaselineEntryVersionAncSuffixDetailComponent } from './baseline-entry-version-anc-suffix-detail.component';
import { BaselineEntryVersionAncSuffixPopupComponent } from './baseline-entry-version-anc-suffix-dialog.component';
import {
    BaselineEntryVersionAncSuffixDeletePopupComponent
} from './baseline-entry-version-anc-suffix-delete-dialog.component';

export const baselineEntryVersionRoute: Routes = [
    {
        path: 'baseline-entry-version-anc-suffix',
        component: BaselineEntryVersionAncSuffixComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'BaselineEntryVersions'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'baseline-entry-version-anc-suffix/:id',
        component: BaselineEntryVersionAncSuffixDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'BaselineEntryVersions'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const baselineEntryVersionPopupRoute: Routes = [
    {
        path: 'baseline-entry-version-anc-suffix-new',
        component: BaselineEntryVersionAncSuffixPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'BaselineEntryVersions'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'baseline-entry-version-anc-suffix/:id/edit',
        component: BaselineEntryVersionAncSuffixPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'BaselineEntryVersions'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'baseline-entry-version-anc-suffix/:id/delete',
        component: BaselineEntryVersionAncSuffixDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'BaselineEntryVersions'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
