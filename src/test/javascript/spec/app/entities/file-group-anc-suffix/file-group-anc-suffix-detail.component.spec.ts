/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { XwiseSampleApplicationTestModule } from '../../../test.module';
import { FileGroupAncSuffixDetailComponent } from '../../../../../../main/webapp/app/entities/file-group-anc-suffix/file-group-anc-suffix-detail.component';
import { FileGroupAncSuffixService } from '../../../../../../main/webapp/app/entities/file-group-anc-suffix/file-group-anc-suffix.service';
import { FileGroupAncSuffix } from '../../../../../../main/webapp/app/entities/file-group-anc-suffix/file-group-anc-suffix.model';

describe('Component Tests', () => {

    describe('FileGroupAncSuffix Management Detail Component', () => {
        let comp: FileGroupAncSuffixDetailComponent;
        let fixture: ComponentFixture<FileGroupAncSuffixDetailComponent>;
        let service: FileGroupAncSuffixService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [XwiseSampleApplicationTestModule],
                declarations: [FileGroupAncSuffixDetailComponent],
                providers: [
                    FileGroupAncSuffixService
                ]
            })
            .overrideTemplate(FileGroupAncSuffixDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(FileGroupAncSuffixDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(FileGroupAncSuffixService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new FileGroupAncSuffix(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.fileGroup).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
