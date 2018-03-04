import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { XwiseSampleApplicationSharedModule } from '../../shared';
import {
    BaselineEntryAncSuffixService,
    BaselineEntryAncSuffixPopupService,
    BaselineEntryAncSuffixComponent,
    BaselineEntryAncSuffixDetailComponent,
    BaselineEntryAncSuffixDialogComponent,
    BaselineEntryAncSuffixPopupComponent,
    BaselineEntryAncSuffixDeletePopupComponent,
    BaselineEntryAncSuffixDeleteDialogComponent,
    baselineEntryRoute,
    baselineEntryPopupRoute,
    BaselineEntryAncSuffixResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...baselineEntryRoute,
    ...baselineEntryPopupRoute,
];

@NgModule({
    imports: [
        XwiseSampleApplicationSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        BaselineEntryAncSuffixComponent,
        BaselineEntryAncSuffixDetailComponent,
        BaselineEntryAncSuffixDialogComponent,
        BaselineEntryAncSuffixDeleteDialogComponent,
        BaselineEntryAncSuffixPopupComponent,
        BaselineEntryAncSuffixDeletePopupComponent,
    ],
    entryComponents: [
        BaselineEntryAncSuffixComponent,
        BaselineEntryAncSuffixDialogComponent,
        BaselineEntryAncSuffixPopupComponent,
        BaselineEntryAncSuffixDeleteDialogComponent,
        BaselineEntryAncSuffixDeletePopupComponent,
    ],
    providers: [
        BaselineEntryAncSuffixService,
        BaselineEntryAncSuffixPopupService,
        BaselineEntryAncSuffixResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class XwiseSampleApplicationBaselineEntryAncSuffixModule {}
