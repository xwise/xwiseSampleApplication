/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { XwiseSampleApplicationTestModule } from '../../../test.module';
import { FileEntryAncSuffixComponent } from '../../../../../../main/webapp/app/entities/file-entry-anc-suffix/file-entry-anc-suffix.component';
import { FileEntryAncSuffixService } from '../../../../../../main/webapp/app/entities/file-entry-anc-suffix/file-entry-anc-suffix.service';
import { FileEntryAncSuffix } from '../../../../../../main/webapp/app/entities/file-entry-anc-suffix/file-entry-anc-suffix.model';

describe('Component Tests', () => {

    describe('FileEntryAncSuffix Management Component', () => {
        let comp: FileEntryAncSuffixComponent;
        let fixture: ComponentFixture<FileEntryAncSuffixComponent>;
        let service: FileEntryAncSuffixService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [XwiseSampleApplicationTestModule],
                declarations: [FileEntryAncSuffixComponent],
                providers: [
                    FileEntryAncSuffixService
                ]
            })
            .overrideTemplate(FileEntryAncSuffixComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(FileEntryAncSuffixComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(FileEntryAncSuffixService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new FileEntryAncSuffix(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.fileEntries[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
