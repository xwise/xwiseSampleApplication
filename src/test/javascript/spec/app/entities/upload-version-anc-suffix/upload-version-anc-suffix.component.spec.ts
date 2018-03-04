/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { XwiseSampleApplicationTestModule } from '../../../test.module';
import { UploadVersionAncSuffixComponent } from '../../../../../../main/webapp/app/entities/upload-version-anc-suffix/upload-version-anc-suffix.component';
import { UploadVersionAncSuffixService } from '../../../../../../main/webapp/app/entities/upload-version-anc-suffix/upload-version-anc-suffix.service';
import { UploadVersionAncSuffix } from '../../../../../../main/webapp/app/entities/upload-version-anc-suffix/upload-version-anc-suffix.model';

describe('Component Tests', () => {

    describe('UploadVersionAncSuffix Management Component', () => {
        let comp: UploadVersionAncSuffixComponent;
        let fixture: ComponentFixture<UploadVersionAncSuffixComponent>;
        let service: UploadVersionAncSuffixService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [XwiseSampleApplicationTestModule],
                declarations: [UploadVersionAncSuffixComponent],
                providers: [
                    UploadVersionAncSuffixService
                ]
            })
            .overrideTemplate(UploadVersionAncSuffixComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(UploadVersionAncSuffixComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(UploadVersionAncSuffixService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new UploadVersionAncSuffix(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.uploadVersions[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
