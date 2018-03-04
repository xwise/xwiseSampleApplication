import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { XwiseSampleApplicationBaselineAncSuffixModule } from './baseline-anc-suffix/baseline-anc-suffix.module';
import { XwiseSampleApplicationBaselineEntryAncSuffixModule } from './baseline-entry-anc-suffix/baseline-entry-anc-suffix.module';
import { XwiseSampleApplicationBaselineEntryVersionAncSuffixModule } from './baseline-entry-version-anc-suffix/baseline-entry-version-anc-suffix.module';
import { XwiseSampleApplicationMyFirstComponentAncSuffixModule } from './my-first-component-anc-suffix/my-first-component-anc-suffix.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    imports: [
        XwiseSampleApplicationBaselineAncSuffixModule,
        XwiseSampleApplicationBaselineEntryAncSuffixModule,
        XwiseSampleApplicationBaselineEntryVersionAncSuffixModule,
        XwiseSampleApplicationMyFirstComponentAncSuffixModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class XwiseSampleApplicationEntityModule {}
