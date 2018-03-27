import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { XwiseSampleApplicationSharedModule } from '../../shared';
import {
    UploadVersionAncSuffixService,
    UploadVersionAncSuffixPopupService,
    UploadVersionAncSuffixComponent,
    UploadVersionAncSuffixDetailComponent,
    UploadVersionAncSuffixDialogComponent,
    UploadVersionAncSuffixPopupComponent,
    UploadVersionAncSuffixDeletePopupComponent,
    UploadVersionAncSuffixDeleteDialogComponent,
    uploadVersionRoute,
    uploadVersionPopupRoute,
} from './';

const ENTITY_STATES = [
    ...uploadVersionRoute,
    ...uploadVersionPopupRoute,
];

@NgModule({
    imports: [
        XwiseSampleApplicationSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        UploadVersionAncSuffixComponent,
        UploadVersionAncSuffixDetailComponent,
        UploadVersionAncSuffixDialogComponent,
        UploadVersionAncSuffixDeleteDialogComponent,
        UploadVersionAncSuffixPopupComponent,
        UploadVersionAncSuffixDeletePopupComponent,
    ],
    entryComponents: [
        UploadVersionAncSuffixComponent,
        UploadVersionAncSuffixDialogComponent,
        UploadVersionAncSuffixPopupComponent,
        UploadVersionAncSuffixDeleteDialogComponent,
        UploadVersionAncSuffixDeletePopupComponent,
    ],
    providers: [
        UploadVersionAncSuffixService,
        UploadVersionAncSuffixPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class XwiseSampleApplicationUploadVersionAncSuffixModule {}
