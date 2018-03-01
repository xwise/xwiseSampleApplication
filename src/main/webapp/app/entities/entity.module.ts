import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { XwiseSampleApplicationRegionMySuffixModule } from './region-my-suffix/region-my-suffix.module';
import { XwiseSampleApplicationCountryMySuffixModule } from './country-my-suffix/country-my-suffix.module';
import { XwiseSampleApplicationLocationMySuffixModule } from './location-my-suffix/location-my-suffix.module';
import { XwiseSampleApplicationDepartmentMySuffixModule } from './department-my-suffix/department-my-suffix.module';
import { XwiseSampleApplicationTaskMySuffixModule } from './task-my-suffix/task-my-suffix.module';
import { XwiseSampleApplicationEmployeeMySuffixModule } from './employee-my-suffix/employee-my-suffix.module';
import { XwiseSampleApplicationJobMySuffixModule } from './job-my-suffix/job-my-suffix.module';
import { XwiseSampleApplicationJobHistoryMySuffixModule } from './job-history-my-suffix/job-history-my-suffix.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    imports: [
        XwiseSampleApplicationRegionMySuffixModule,
        XwiseSampleApplicationCountryMySuffixModule,
        XwiseSampleApplicationLocationMySuffixModule,
        XwiseSampleApplicationDepartmentMySuffixModule,
        XwiseSampleApplicationTaskMySuffixModule,
        XwiseSampleApplicationEmployeeMySuffixModule,
        XwiseSampleApplicationJobMySuffixModule,
        XwiseSampleApplicationJobHistoryMySuffixModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class XwiseSampleApplicationEntityModule {}
