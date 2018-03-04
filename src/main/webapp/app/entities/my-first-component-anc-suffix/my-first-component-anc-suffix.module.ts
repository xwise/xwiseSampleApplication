import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { XwiseSampleApplicationSharedModule } from '../../shared';
import {
    MyFirstComponentAncSuffixService,
    MyFirstComponentAncSuffixPopupService,
    MyFirstComponentAncSuffixComponent,
    MyFirstComponentAncSuffixDetailComponent,
    MyFirstComponentAncSuffixDialogComponent,
    MyFirstComponentAncSuffixPopupComponent,
    MyFirstComponentAncSuffixDeletePopupComponent,
    MyFirstComponentAncSuffixDeleteDialogComponent,
    myFirstComponentRoute,
    myFirstComponentPopupRoute,
} from './';

const ENTITY_STATES = [
    ...myFirstComponentRoute,
    ...myFirstComponentPopupRoute,
];

@NgModule({
    imports: [
        XwiseSampleApplicationSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        MyFirstComponentAncSuffixComponent,
        MyFirstComponentAncSuffixDetailComponent,
        MyFirstComponentAncSuffixDialogComponent,
        MyFirstComponentAncSuffixDeleteDialogComponent,
        MyFirstComponentAncSuffixPopupComponent,
        MyFirstComponentAncSuffixDeletePopupComponent,
    ],
    entryComponents: [
        MyFirstComponentAncSuffixComponent,
        MyFirstComponentAncSuffixDialogComponent,
        MyFirstComponentAncSuffixPopupComponent,
        MyFirstComponentAncSuffixDeleteDialogComponent,
        MyFirstComponentAncSuffixDeletePopupComponent,
    ],
    providers: [
        MyFirstComponentAncSuffixService,
        MyFirstComponentAncSuffixPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class XwiseSampleApplicationMyFirstComponentAncSuffixModule {}
