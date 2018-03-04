import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { XwiseSampleApplicationSharedModule } from '../../shared';
import {
    FileGroupAncSuffixService,
    FileGroupAncSuffixPopupService,
    FileGroupAncSuffixComponent,
    FileGroupAncSuffixDetailComponent,
    FileGroupAncSuffixDialogComponent,
    FileGroupAncSuffixPopupComponent,
    FileGroupAncSuffixDeletePopupComponent,
    FileGroupAncSuffixDeleteDialogComponent,
    fileGroupRoute,
    fileGroupPopupRoute,
} from './';

const ENTITY_STATES = [
    ...fileGroupRoute,
    ...fileGroupPopupRoute,
];

@NgModule({
    imports: [
        XwiseSampleApplicationSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        FileGroupAncSuffixComponent,
        FileGroupAncSuffixDetailComponent,
        FileGroupAncSuffixDialogComponent,
        FileGroupAncSuffixDeleteDialogComponent,
        FileGroupAncSuffixPopupComponent,
        FileGroupAncSuffixDeletePopupComponent,
    ],
    entryComponents: [
        FileGroupAncSuffixComponent,
        FileGroupAncSuffixDialogComponent,
        FileGroupAncSuffixPopupComponent,
        FileGroupAncSuffixDeleteDialogComponent,
        FileGroupAncSuffixDeletePopupComponent,
    ],
    providers: [
        FileGroupAncSuffixService,
        FileGroupAncSuffixPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class XwiseSampleApplicationFileGroupAncSuffixModule {}
