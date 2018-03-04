import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';

import { UserRouteAccessService } from '../../shared';
import { BaselineAncSuffixComponent } from './baseline-anc-suffix.component';
import { BaselineAncSuffixDetailComponent } from './baseline-anc-suffix-detail.component';
import { BaselineAncSuffixPopupComponent } from './baseline-anc-suffix-dialog.component';
import { BaselineAncSuffixDeletePopupComponent } from './baseline-anc-suffix-delete-dialog.component';

@Injectable()
export class BaselineAncSuffixResolvePagingParams implements Resolve<any> {

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

export const baselineRoute: Routes = [
    {
        path: 'baseline-anc-suffix',
        component: BaselineAncSuffixComponent,
        resolve: {
            'pagingParams': BaselineAncSuffixResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Baselines'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'baseline-anc-suffix/:id',
        component: BaselineAncSuffixDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Baselines'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const baselinePopupRoute: Routes = [
    {
        path: 'baseline-anc-suffix-new',
        component: BaselineAncSuffixPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Baselines'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'baseline-anc-suffix/:id/edit',
        component: BaselineAncSuffixPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Baselines'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'baseline-anc-suffix/:id/delete',
        component: BaselineAncSuffixDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Baselines'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
