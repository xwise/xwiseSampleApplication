/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { XwiseSampleApplicationTestModule } from '../../../test.module';
import { FileEntryAncSuffixDetailComponent } from '../../../../../../main/webapp/app/entities/file-entry-anc-suffix/file-entry-anc-suffix-detail.component';
import { FileEntryAncSuffixService } from '../../../../../../main/webapp/app/entities/file-entry-anc-suffix/file-entry-anc-suffix.service';
import { FileEntryAncSuffix } from '../../../../../../main/webapp/app/entities/file-entry-anc-suffix/file-entry-anc-suffix.model';

describe('Component Tests', () => {

    describe('FileEntryAncSuffix Management Detail Component', () => {
        let comp: FileEntryAncSuffixDetailComponent;
        let fixture: ComponentFixture<FileEntryAncSuffixDetailComponent>;
        let service: FileEntryAncSuffixService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [XwiseSampleApplicationTestModule],
                declarations: [FileEntryAncSuffixDetailComponent],
                providers: [
                    FileEntryAncSuffixService
                ]
            })
            .overrideTemplate(FileEntryAncSuffixDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(FileEntryAncSuffixDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(FileEntryAncSuffixService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new FileEntryAncSuffix(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.fileEntry).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
