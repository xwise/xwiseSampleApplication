/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { XwiseSampleApplicationTestModule } from '../../../test.module';
import { BaselineEntryVersionAncSuffixDetailComponent } from '../../../../../../main/webapp/app/entities/baseline-entry-version-anc-suffix/baseline-entry-version-anc-suffix-detail.component';
import { BaselineEntryVersionAncSuffixService } from '../../../../../../main/webapp/app/entities/baseline-entry-version-anc-suffix/baseline-entry-version-anc-suffix.service';
import { BaselineEntryVersionAncSuffix } from '../../../../../../main/webapp/app/entities/baseline-entry-version-anc-suffix/baseline-entry-version-anc-suffix.model';

describe('Component Tests', () => {

    describe('BaselineEntryVersionAncSuffix Management Detail Component', () => {
        let comp: BaselineEntryVersionAncSuffixDetailComponent;
        let fixture: ComponentFixture<BaselineEntryVersionAncSuffixDetailComponent>;
        let service: BaselineEntryVersionAncSuffixService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [XwiseSampleApplicationTestModule],
                declarations: [BaselineEntryVersionAncSuffixDetailComponent],
                providers: [
                    BaselineEntryVersionAncSuffixService
                ]
            })
            .overrideTemplate(BaselineEntryVersionAncSuffixDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(BaselineEntryVersionAncSuffixDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(BaselineEntryVersionAncSuffixService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new BaselineEntryVersionAncSuffix(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.baselineEntryVersion).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
