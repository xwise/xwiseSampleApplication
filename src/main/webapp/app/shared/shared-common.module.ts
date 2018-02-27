import { NgModule, LOCALE_ID } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { registerLocaleData } from '@angular/common';
import locale from '@angular/common/locales/en';

import {
    XwiseSampleApplicationSharedLibsModule,
    JhiAlertComponent,
    JhiAlertErrorComponent
} from './';

@NgModule({
    imports: [
        XwiseSampleApplicationSharedLibsModule
    ],
    declarations: [
        JhiAlertComponent,
        JhiAlertErrorComponent
    ],
    providers: [
        Title,
        {
            provide: LOCALE_ID,
            useValue: 'en'
        },
    ],
    exports: [
        XwiseSampleApplicationSharedLibsModule,
        JhiAlertComponent,
        JhiAlertErrorComponent
    ]
})
export class XwiseSampleApplicationSharedCommonModule {
    constructor() {
        registerLocaleData(locale);
    }
}
