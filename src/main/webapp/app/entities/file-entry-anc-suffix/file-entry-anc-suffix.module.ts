import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { XwiseSampleApplicationSharedModule } from '../../shared';
import {
    FileEntryAncSuffixService,
    FileEntryAncSuffixPopupService,
    FileEntryAncSuffixComponent,
    FileEntryAncSuffixDetailComponent,
    FileEntryAncSuffixDialogComponent,
    FileEntryAncSuffixPopupComponent,
    FileEntryAncSuffixDeletePopupComponent,
    FileEntryAncSuffixDeleteDialogComponent,
    fileEntryRoute,
    fileEntryPopupRoute,
    FileEntryAncSuffixResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...fileEntryRoute,
    ...fileEntryPopupRoute,
];

@NgModule({
    imports: [
        XwiseSampleApplicationSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        FileEntryAncSuffixComponent,
        FileEntryAncSuffixDetailComponent,
        FileEntryAncSuffixDialogComponent,
        FileEntryAncSuffixDeleteDialogComponent,
        FileEntryAncSuffixPopupComponent,
        FileEntryAncSuffixDeletePopupComponent,
    ],
    entryComponents: [
        FileEntryAncSuffixComponent,
        FileEntryAncSuffixDialogComponent,
        FileEntryAncSuffixPopupComponent,
        FileEntryAncSuffixDeleteDialogComponent,
        FileEntryAncSuffixDeletePopupComponent,
    ],
    providers: [
        FileEntryAncSuffixService,
        FileEntryAncSuffixPopupService,
        FileEntryAncSuffixResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class XwiseSampleApplicationFileEntryAncSuffixModule {}
