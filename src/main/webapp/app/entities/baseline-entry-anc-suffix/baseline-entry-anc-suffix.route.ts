import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';

import { UserRouteAccessService } from '../../shared';
import { BaselineEntryAncSuffixComponent } from './baseline-entry-anc-suffix.component';
import { BaselineEntryAncSuffixDetailComponent } from './baseline-entry-anc-suffix-detail.component';
import { BaselineEntryAncSuffixPopupComponent } from './baseline-entry-anc-suffix-dialog.component';
import { BaselineEntryAncSuffixDeletePopupComponent } from './baseline-entry-anc-suffix-delete-dialog.component';

@Injectable()
export class BaselineEntryAncSuffixResolvePagingParams implements Resolve<any> {

    constructor(private paginationUtil: JhiPaginationUtil) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const page = route.queryParams['page'] ? route.queryParams['page'] : '1';
        const sort = route.queryParams['sort'] ? route.queryParams['sort'] : 'id,asc';
        return {
            page: this.paginationUtil.parsePage(page),
            predicate: this.paginationUtil.parsePredicate(sort),
            ascending: this.paginationUtil.parseAscending(sort)
      };
    }
}

export const baselineEntryRoute: Routes = [
    {
        path: 'baseline-entry-anc-suffix',
        component: BaselineEntryAncSuffixComponent,
        resolve: {
            'pagingParams': BaselineEntryAncSuffixResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'BaselineEntries'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'baseline-entry-anc-suffix/:id',
        component: BaselineEntryAncSuffixDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'BaselineEntries'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const baselineEntryPopupRoute: Routes = [
    {
        path: 'baseline-entry-anc-suffix-new',
        component: BaselineEntryAncSuffixPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'BaselineEntries'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'baseline-entry-anc-suffix/:id/edit',
        component: BaselineEntryAncSuffixPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'BaselineEntries'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'baseline-entry-anc-suffix/:id/delete',
        component: BaselineEntryAncSuffixDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'BaselineEntries'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
