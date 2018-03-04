/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { XwiseSampleApplicationTestModule } from '../../../test.module';
import { BaselineEntryVersionAncSuffixComponent } from '../../../../../../main/webapp/app/entities/baseline-entry-version-anc-suffix/baseline-entry-version-anc-suffix.component';
import { BaselineEntryVersionAncSuffixService } from '../../../../../../main/webapp/app/entities/baseline-entry-version-anc-suffix/baseline-entry-version-anc-suffix.service';
import { BaselineEntryVersionAncSuffix } from '../../../../../../main/webapp/app/entities/baseline-entry-version-anc-suffix/baseline-entry-version-anc-suffix.model';

describe('Component Tests', () => {

    describe('BaselineEntryVersionAncSuffix Management Component', () => {
        let comp: BaselineEntryVersionAncSuffixComponent;
        let fixture: ComponentFixture<BaselineEntryVersionAncSuffixComponent>;
        let service: BaselineEntryVersionAncSuffixService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [XwiseSampleApplicationTestModule],
                declarations: [BaselineEntryVersionAncSuffixComponent],
                providers: [
                    BaselineEntryVersionAncSuffixService
                ]
            })
            .overrideTemplate(BaselineEntryVersionAncSuffixComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(BaselineEntryVersionAncSuffixComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(BaselineEntryVersionAncSuffixService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new BaselineEntryVersionAncSuffix(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.baselineEntryVersions[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
