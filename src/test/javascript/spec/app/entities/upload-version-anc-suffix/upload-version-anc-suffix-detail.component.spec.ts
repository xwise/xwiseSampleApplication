/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { XwiseSampleApplicationTestModule } from '../../../test.module';
import { UploadVersionAncSuffixDetailComponent } from '../../../../../../main/webapp/app/entities/upload-version-anc-suffix/upload-version-anc-suffix-detail.component';
import { UploadVersionAncSuffixService } from '../../../../../../main/webapp/app/entities/upload-version-anc-suffix/upload-version-anc-suffix.service';
import { UploadVersionAncSuffix } from '../../../../../../main/webapp/app/entities/upload-version-anc-suffix/upload-version-anc-suffix.model';

describe('Component Tests', () => {

    describe('UploadVersionAncSuffix Management Detail Component', () => {
        let comp: UploadVersionAncSuffixDetailComponent;
        let fixture: ComponentFixture<UploadVersionAncSuffixDetailComponent>;
        let service: UploadVersionAncSuffixService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [XwiseSampleApplicationTestModule],
                declarations: [UploadVersionAncSuffixDetailComponent],
                providers: [
                    UploadVersionAncSuffixService
                ]
            })
            .overrideTemplate(UploadVersionAncSuffixDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(UploadVersionAncSuffixDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(UploadVersionAncSuffixService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new UploadVersionAncSuffix(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.uploadVersion).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
