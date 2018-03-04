/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { XwiseSampleApplicationTestModule } from '../../../test.module';
import { UploadVersionAncSuffixDialogComponent } from '../../../../../../main/webapp/app/entities/upload-version-anc-suffix/upload-version-anc-suffix-dialog.component';
import { UploadVersionAncSuffixService } from '../../../../../../main/webapp/app/entities/upload-version-anc-suffix/upload-version-anc-suffix.service';
import { UploadVersionAncSuffix } from '../../../../../../main/webapp/app/entities/upload-version-anc-suffix/upload-version-anc-suffix.model';

describe('Component Tests', () => {

    describe('UploadVersionAncSuffix Management Dialog Component', () => {
        let comp: UploadVersionAncSuffixDialogComponent;
        let fixture: ComponentFixture<UploadVersionAncSuffixDialogComponent>;
        let service: UploadVersionAncSuffixService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [XwiseSampleApplicationTestModule],
                declarations: [UploadVersionAncSuffixDialogComponent],
                providers: [
                    UploadVersionAncSuffixService
                ]
            })
            .overrideTemplate(UploadVersionAncSuffixDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(UploadVersionAncSuffixDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(UploadVersionAncSuffixService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new UploadVersionAncSuffix(123);
                        spyOn(service, 'update').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.uploadVersion = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.update).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'uploadVersionListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );

            it('Should call create service on save for new entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new UploadVersionAncSuffix();
                        spyOn(service, 'create').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.uploadVersion = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.create).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'uploadVersionListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
