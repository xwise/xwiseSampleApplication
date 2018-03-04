/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { XwiseSampleApplicationTestModule } from '../../../test.module';
import { FileGroupAncSuffixDialogComponent } from '../../../../../../main/webapp/app/entities/file-group-anc-suffix/file-group-anc-suffix-dialog.component';
import { FileGroupAncSuffixService } from '../../../../../../main/webapp/app/entities/file-group-anc-suffix/file-group-anc-suffix.service';
import { FileGroupAncSuffix } from '../../../../../../main/webapp/app/entities/file-group-anc-suffix/file-group-anc-suffix.model';
import { BaselineAncSuffixService } from '../../../../../../main/webapp/app/entities/baseline-anc-suffix';
import { FileEntryAncSuffixService } from '../../../../../../main/webapp/app/entities/file-entry-anc-suffix';

describe('Component Tests', () => {

    describe('FileGroupAncSuffix Management Dialog Component', () => {
        let comp: FileGroupAncSuffixDialogComponent;
        let fixture: ComponentFixture<FileGroupAncSuffixDialogComponent>;
        let service: FileGroupAncSuffixService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [XwiseSampleApplicationTestModule],
                declarations: [FileGroupAncSuffixDialogComponent],
                providers: [
                    BaselineAncSuffixService,
                    FileEntryAncSuffixService,
                    FileGroupAncSuffixService
                ]
            })
            .overrideTemplate(FileGroupAncSuffixDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(FileGroupAncSuffixDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(FileGroupAncSuffixService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new FileGroupAncSuffix(123);
                        spyOn(service, 'update').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.fileGroup = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.update).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'fileGroupListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );

            it('Should call create service on save for new entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new FileGroupAncSuffix();
                        spyOn(service, 'create').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.fileGroup = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.create).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'fileGroupListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
