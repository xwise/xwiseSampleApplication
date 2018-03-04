/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { XwiseSampleApplicationTestModule } from '../../../test.module';
import { FileGroupAncSuffixComponent } from '../../../../../../main/webapp/app/entities/file-group-anc-suffix/file-group-anc-suffix.component';
import { FileGroupAncSuffixService } from '../../../../../../main/webapp/app/entities/file-group-anc-suffix/file-group-anc-suffix.service';
import { FileGroupAncSuffix } from '../../../../../../main/webapp/app/entities/file-group-anc-suffix/file-group-anc-suffix.model';

describe('Component Tests', () => {

    describe('FileGroupAncSuffix Management Component', () => {
        let comp: FileGroupAncSuffixComponent;
        let fixture: ComponentFixture<FileGroupAncSuffixComponent>;
        let service: FileGroupAncSuffixService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [XwiseSampleApplicationTestModule],
                declarations: [FileGroupAncSuffixComponent],
                providers: [
                    FileGroupAncSuffixService
                ]
            })
            .overrideTemplate(FileGroupAncSuffixComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(FileGroupAncSuffixComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(FileGroupAncSuffixService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new FileGroupAncSuffix(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.fileGroups[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
