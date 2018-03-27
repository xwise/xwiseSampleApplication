/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { XwiseSampleApplicationTestModule } from '../../../test.module';
import { FileEntryAncSuffixDialogComponent } from '../../../../../../main/webapp/app/entities/file-entry-anc-suffix/file-entry-anc-suffix-dialog.component';
import { FileEntryAncSuffixService } from '../../../../../../main/webapp/app/entities/file-entry-anc-suffix/file-entry-anc-suffix.service';
import { FileEntryAncSuffix } from '../../../../../../main/webapp/app/entities/file-entry-anc-suffix/file-entry-anc-suffix.model';
import { UploadVersionAncSuffixService } from '../../../../../../main/webapp/app/entities/upload-version-anc-suffix';
import { BaselineAncSuffixService } from '../../../../../../main/webapp/app/entities/baseline-anc-suffix';

describe('Component Tests', () => {

    describe('FileEntryAncSuffix Management Dialog Component', () => {
        let comp: FileEntryAncSuffixDialogComponent;
        let fixture: ComponentFixture<FileEntryAncSuffixDialogComponent>;
        let service: FileEntryAncSuffixService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [XwiseSampleApplicationTestModule],
                declarations: [FileEntryAncSuffixDialogComponent],
                providers: [
                    UploadVersionAncSuffixService,
                    BaselineAncSuffixService,
                    FileEntryAncSuffixService
                ]
            })
            .overrideTemplate(FileEntryAncSuffixDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(FileEntryAncSuffixDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(FileEntryAncSuffixService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new FileEntryAncSuffix(123);
                        spyOn(service, 'update').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.fileEntry = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.update).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'fileEntryListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );

            it('Should call create service on save for new entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new FileEntryAncSuffix();
                        spyOn(service, 'create').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.fileEntry = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.create).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'fileEntryListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
