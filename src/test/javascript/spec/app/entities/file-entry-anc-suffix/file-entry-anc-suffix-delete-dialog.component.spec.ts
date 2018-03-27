/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { XwiseSampleApplicationTestModule } from '../../../test.module';
import { FileEntryAncSuffixDeleteDialogComponent } from '../../../../../../main/webapp/app/entities/file-entry-anc-suffix/file-entry-anc-suffix-delete-dialog.component';
import { FileEntryAncSuffixService } from '../../../../../../main/webapp/app/entities/file-entry-anc-suffix/file-entry-anc-suffix.service';

describe('Component Tests', () => {

    describe('FileEntryAncSuffix Management Delete Component', () => {
        let comp: FileEntryAncSuffixDeleteDialogComponent;
        let fixture: ComponentFixture<FileEntryAncSuffixDeleteDialogComponent>;
        let service: FileEntryAncSuffixService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [XwiseSampleApplicationTestModule],
                declarations: [FileEntryAncSuffixDeleteDialogComponent],
                providers: [
                    FileEntryAncSuffixService
                ]
            })
            .overrideTemplate(FileEntryAncSuffixDeleteDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(FileEntryAncSuffixDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(FileEntryAncSuffixService);
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
