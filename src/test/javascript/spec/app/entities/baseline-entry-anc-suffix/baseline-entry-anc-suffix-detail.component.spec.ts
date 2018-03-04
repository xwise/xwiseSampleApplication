/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { XwiseSampleApplicationTestModule } from '../../../test.module';
import { BaselineEntryAncSuffixDetailComponent } from '../../../../../../main/webapp/app/entities/baseline-entry-anc-suffix/baseline-entry-anc-suffix-detail.component';
import { BaselineEntryAncSuffixService } from '../../../../../../main/webapp/app/entities/baseline-entry-anc-suffix/baseline-entry-anc-suffix.service';
import { BaselineEntryAncSuffix } from '../../../../../../main/webapp/app/entities/baseline-entry-anc-suffix/baseline-entry-anc-suffix.model';

describe('Component Tests', () => {

    describe('BaselineEntryAncSuffix Management Detail Component', () => {
        let comp: BaselineEntryAncSuffixDetailComponent;
        let fixture: ComponentFixture<BaselineEntryAncSuffixDetailComponent>;
        let service: BaselineEntryAncSuffixService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [XwiseSampleApplicationTestModule],
                declarations: [BaselineEntryAncSuffixDetailComponent],
                providers: [
                    BaselineEntryAncSuffixService
                ]
            })
            .overrideTemplate(BaselineEntryAncSuffixDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(BaselineEntryAncSuffixDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(BaselineEntryAncSuffixService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new BaselineEntryAncSuffix(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.baselineEntry).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
