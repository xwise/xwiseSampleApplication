import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { XwiseSampleApplicationSharedModule } from '../../shared';
import {
    BaselineAncSuffixService,
    BaselineAncSuffixPopupService,
    BaselineAncSuffixComponent,
    BaselineAncSuffixDetailComponent,
    BaselineAncSuffixDialogComponent,
    BaselineAncSuffixPopupComponent,
    BaselineAncSuffixDeletePopupComponent,
    BaselineAncSuffixDeleteDialogComponent,
    baselineRoute,
    baselinePopupRoute,
    BaselineAncSuffixResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...baselineRoute,
    ...baselinePopupRoute,
];

@NgModule({
    imports: [
        XwiseSampleApplicationSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        BaselineAncSuffixComponent,
        BaselineAncSuffixDetailComponent,
        BaselineAncSuffixDialogComponent,
        BaselineAncSuffixDeleteDialogComponent,
        BaselineAncSuffixPopupComponent,
        BaselineAncSuffixDeletePopupComponent,
    ],
    entryComponents: [
        BaselineAncSuffixComponent,
        BaselineAncSuffixDialogComponent,
        BaselineAncSuffixPopupComponent,
        BaselineAncSuffixDeleteDialogComponent,
        BaselineAncSuffixDeletePopupComponent,
    ],
    providers: [
        BaselineAncSuffixService,
        BaselineAncSuffixPopupService,
        BaselineAncSuffixResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class XwiseSampleApplicationBaselineAncSuffixModule {}
