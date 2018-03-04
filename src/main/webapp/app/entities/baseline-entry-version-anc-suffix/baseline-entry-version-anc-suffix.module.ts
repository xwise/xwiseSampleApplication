import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { XwiseSampleApplicationSharedModule } from '../../shared';
import {
    BaselineEntryVersionAncSuffixService,
    BaselineEntryVersionAncSuffixPopupService,
    BaselineEntryVersionAncSuffixComponent,
    BaselineEntryVersionAncSuffixDetailComponent,
    BaselineEntryVersionAncSuffixDialogComponent,
    BaselineEntryVersionAncSuffixPopupComponent,
    BaselineEntryVersionAncSuffixDeletePopupComponent,
    BaselineEntryVersionAncSuffixDeleteDialogComponent,
    baselineEntryVersionRoute,
    baselineEntryVersionPopupRoute,
} from './';

const ENTITY_STATES = [
    ...baselineEntryVersionRoute,
    ...baselineEntryVersionPopupRoute,
];

@NgModule({
    imports: [
        XwiseSampleApplicationSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        BaselineEntryVersionAncSuffixComponent,
        BaselineEntryVersionAncSuffixDetailComponent,
        BaselineEntryVersionAncSuffixDialogComponent,
        BaselineEntryVersionAncSuffixDeleteDialogComponent,
        BaselineEntryVersionAncSuffixPopupComponent,
        BaselineEntryVersionAncSuffixDeletePopupComponent,
    ],
    entryComponents: [
        BaselineEntryVersionAncSuffixComponent,
        BaselineEntryVersionAncSuffixDialogComponent,
        BaselineEntryVersionAncSuffixPopupComponent,
        BaselineEntryVersionAncSuffixDeleteDialogComponent,
        BaselineEntryVersionAncSuffixDeletePopupComponent,
    ],
    providers: [
        BaselineEntryVersionAncSuffixService,
        BaselineEntryVersionAncSuffixPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class XwiseSampleApplicationBaselineEntryVersionAncSuffixModule {}
