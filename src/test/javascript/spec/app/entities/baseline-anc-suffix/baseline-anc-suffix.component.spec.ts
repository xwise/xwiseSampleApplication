/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { XwiseSampleApplicationTestModule } from '../../../test.module';
import { BaselineAncSuffixComponent } from '../../../../../../main/webapp/app/entities/baseline-anc-suffix/baseline-anc-suffix.component';
import { BaselineAncSuffixService } from '../../../../../../main/webapp/app/entities/baseline-anc-suffix/baseline-anc-suffix.service';
import { BaselineAncSuffix } from '../../../../../../main/webapp/app/entities/baseline-anc-suffix/baseline-anc-suffix.model';

describe('Component Tests', () => {

    describe('BaselineAncSuffix Management Component', () => {
        let comp: BaselineAncSuffixComponent;
        let fixture: ComponentFixture<BaselineAncSuffixComponent>;
        let service: BaselineAncSuffixService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [XwiseSampleApplicationTestModule],
                declarations: [BaselineAncSuffixComponent],
                providers: [
                    BaselineAncSuffixService
                ]
            })
            .overrideTemplate(BaselineAncSuffixComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(BaselineAncSuffixComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(BaselineAncSuffixService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new BaselineAncSuffix(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.baselines[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
