/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { XwiseSampleApplicationTestModule } from '../../../test.module';
import { FileGroupAncSuffixDeleteDialogComponent } from '../../../../../../main/webapp/app/entities/file-group-anc-suffix/file-group-anc-suffix-delete-dialog.component';
import { FileGroupAncSuffixService } from '../../../../../../main/webapp/app/entities/file-group-anc-suffix/file-group-anc-suffix.service';

describe('Component Tests', () => {

    describe('FileGroupAncSuffix Management Delete Component', () => {
        let comp: FileGroupAncSuffixDeleteDialogComponent;
        let fixture: ComponentFixture<FileGroupAncSuffixDeleteDialogComponent>;
        let service: FileGroupAncSuffixService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [XwiseSampleApplicationTestModule],
                declarations: [FileGroupAncSuffixDeleteDialogComponent],
                providers: [
                    FileGroupAncSuffixService
                ]
            })
            .overrideTemplate(FileGroupAncSuffixDeleteDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(FileGroupAncSuffixDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(FileGroupAncSuffixService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        spyOn(service, 'delete').and.returnValue(Observable.of({}));

                        // WHEN
                        comp.confirmDelete(123);
                        tick();

                        // THEN
                        expect(service.delete).toHaveBeenCalledWith(123);
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
