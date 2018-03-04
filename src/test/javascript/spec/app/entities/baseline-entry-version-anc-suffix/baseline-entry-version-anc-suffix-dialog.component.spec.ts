/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { XwiseSampleApplicationTestModule } from '../../../test.module';
import { BaselineEntryVersionAncSuffixDialogComponent } from '../../../../../../main/webapp/app/entities/baseline-entry-version-anc-suffix/baseline-entry-version-anc-suffix-dialog.component';
import { BaselineEntryVersionAncSuffixService } from '../../../../../../main/webapp/app/entities/baseline-entry-version-anc-suffix/baseline-entry-version-anc-suffix.service';
import { BaselineEntryVersionAncSuffix } from '../../../../../../main/webapp/app/entities/baseline-entry-version-anc-suffix/baseline-entry-version-anc-suffix.model';
import { FileEntryAncSuffixService } from '../../../../../../main/webapp/app/entities/file-entry-anc-suffix';

describe('Component Tests', () => {

    describe('BaselineEntryVersionAncSuffix Management Dialog Component', () => {
        let comp: BaselineEntryVersionAncSuffixDialogComponent;
        let fixture: ComponentFixture<BaselineEntryVersionAncSuffixDialogComponent>;
        let service: BaselineEntryVersionAncSuffixService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [XwiseSampleApplicationTestModule],
                declarations: [BaselineEntryVersionAncSuffixDialogComponent],
                providers: [
                    FileEntryAncSuffixService,
                    BaselineEntryVersionAncSuffixService
                ]
            })
            .overrideTemplate(BaselineEntryVersionAncSuffixDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(BaselineEntryVersionAncSuffixDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(BaselineEntryVersionAncSuffixService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new BaselineEntryVersionAncSuffix(123);
                        spyOn(service, 'update').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.baselineEntryVersion = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.update).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'baselineEntryVersionListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );

            it('Should call create service on save for new entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new BaselineEntryVersionAncSuffix();
                        spyOn(service, 'create').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.baselineEntryVersion = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.create).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'baselineEntryVersionListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
