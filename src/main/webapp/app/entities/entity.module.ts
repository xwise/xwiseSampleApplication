import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { XwiseSampleApplicationBaselineAncSuffixModule } from './baseline-anc-suffix/baseline-anc-suffix.module';
import { XwiseSampleApplicationFileEntryAncSuffixModule } from './file-entry-anc-suffix/file-entry-anc-suffix.module';
import { XwiseSampleApplicationMyFirstComponentAncSuffixModule } from './my-first-component-anc-suffix/my-first-component-anc-suffix.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    imports: [
        XwiseSampleApplicationBaselineAncSuffixModule,
        XwiseSampleApplicationFileEntryAncSuffixModule,
        XwiseSampleApplicationMyFirstComponentAncSuffixModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class XwiseSampleApplicationEntityModule {}
