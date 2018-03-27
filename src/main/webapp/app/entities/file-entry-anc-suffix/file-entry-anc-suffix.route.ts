import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';

import { UserRouteAccessService } from '../../shared';
import { FileEntryAncSuffixComponent } from './file-entry-anc-suffix.component';
import { FileEntryAncSuffixDetailComponent } from './file-entry-anc-suffix-detail.component';
import { FileEntryAncSuffixPopupComponent } from './file-entry-anc-suffix-dialog.component';
import { FileEntryAncSuffixDeletePopupComponent } from './file-entry-anc-suffix-delete-dialog.component';

@Injectable()
export class FileEntryAncSuffixResolvePagingParams implements Resolve<any> {

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

export const fileEntryRoute: Routes = [
    {
        path: 'file-entry-anc-suffix',
        component: FileEntryAncSuffixComponent,
        resolve: {
            'pagingParams': FileEntryAncSuffixResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'FileEntries'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'file-entry-anc-suffix/:id',
        component: FileEntryAncSuffixDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'FileEntries'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const fileEntryPopupRoute: Routes = [
    {
        path: 'file-entry-anc-suffix-new',
        component: FileEntryAncSuffixPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'FileEntries'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'file-entry-anc-suffix/:id/edit',
        component: FileEntryAncSuffixPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'FileEntries'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'file-entry-anc-suffix/:id/delete',
        component: FileEntryAncSuffixDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'FileEntries'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
