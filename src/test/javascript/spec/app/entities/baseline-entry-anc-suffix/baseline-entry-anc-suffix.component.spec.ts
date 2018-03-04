/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { XwiseSampleApplicationTestModule } from '../../../test.module';
import { BaselineEntryAncSuffixComponent } from '../../../../../../main/webapp/app/entities/baseline-entry-anc-suffix/baseline-entry-anc-suffix.component';
import { BaselineEntryAncSuffixService } from '../../../../../../main/webapp/app/entities/baseline-entry-anc-suffix/baseline-entry-anc-suffix.service';
import { BaselineEntryAncSuffix } from '../../../../../../main/webapp/app/entities/baseline-entry-anc-suffix/baseline-entry-anc-suffix.model';

describe('Component Tests', () => {

    describe('BaselineEntryAncSuffix Management Component', () => {
        let comp: BaselineEntryAncSuffixComponent;
        let fixture: ComponentFixture<BaselineEntryAncSuffixComponent>;
        let service: BaselineEntryAncSuffixService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [XwiseSampleApplicationTestModule],
                declarations: [BaselineEntryAncSuffixComponent],
                providers: [
                    BaselineEntryAncSuffixService
                ]
            })
            .overrideTemplate(BaselineEntryAncSuffixComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(BaselineEntryAncSuffixComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(BaselineEntryAncSuffixService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new BaselineEntryAncSuffix(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.baselineEntries[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
