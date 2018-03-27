/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { XwiseSampleApplicationTestModule } from '../../../test.module';
import { BaselineAncSuffixDetailComponent } from '../../../../../../main/webapp/app/entities/baseline-anc-suffix/baseline-anc-suffix-detail.component';
import { BaselineAncSuffixService } from '../../../../../../main/webapp/app/entities/baseline-anc-suffix/baseline-anc-suffix.service';
import { BaselineAncSuffix } from '../../../../../../main/webapp/app/entities/baseline-anc-suffix/baseline-anc-suffix.model';

describe('Component Tests', () => {

    describe('BaselineAncSuffix Management Detail Component', () => {
        let comp: BaselineAncSuffixDetailComponent;
        let fixture: ComponentFixture<BaselineAncSuffixDetailComponent>;
        let service: BaselineAncSuffixService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [XwiseSampleApplicationTestModule],
                declarations: [BaselineAncSuffixDetailComponent],
                providers: [
                    BaselineAncSuffixService
                ]
            })
            .overrideTemplate(BaselineAncSuffixDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(BaselineAncSuffixDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(BaselineAncSuffixService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new BaselineAncSuffix(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.baseline).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
