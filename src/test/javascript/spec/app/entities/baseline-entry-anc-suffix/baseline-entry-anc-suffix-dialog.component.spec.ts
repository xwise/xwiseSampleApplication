/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { XwiseSampleApplicationTestModule } from '../../../test.module';
import { BaselineEntryAncSuffixDialogComponent } from '../../../../../../main/webapp/app/entities/baseline-entry-anc-suffix/baseline-entry-anc-suffix-dialog.component';
import { BaselineEntryAncSuffixService } from '../../../../../../main/webapp/app/entities/baseline-entry-anc-suffix/baseline-entry-anc-suffix.service';
import { BaselineEntryAncSuffix } from '../../../../../../main/webapp/app/entities/baseline-entry-anc-suffix/baseline-entry-anc-suffix.model';
import { BaselineEntryVersionAncSuffixService } from '../../../../../../main/webapp/app/entities/baseline-entry-version-anc-suffix';
import { BaselineAncSuffixService } from '../../../../../../main/webapp/app/entities/baseline-anc-suffix';

describe('Component Tests', () => {

    describe('BaselineEntryAncSuffix Management Dialog Component', () => {
        let comp: BaselineEntryAncSuffixDialogComponent;
        let fixture: ComponentFixture<BaselineEntryAncSuffixDialogComponent>;
        let service: BaselineEntryAncSuffixService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [XwiseSampleApplicationTestModule],
                declarations: [BaselineEntryAncSuffixDialogComponent],
                providers: [
                    BaselineEntryVersionAncSuffixService,
                    BaselineAncSuffixService,
                    BaselineEntryAncSuffixService
                ]
            })
            .overrideTemplate(BaselineEntryAncSuffixDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(BaselineEntryAncSuffixDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(BaselineEntryAncSuffixService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new BaselineEntryAncSuffix(123);
                        spyOn(service, 'update').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.baselineEntry = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.update).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'baselineEntryListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );

            it('Should call create service on save for new entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new BaselineEntryAncSuffix();
                        spyOn(service, 'create').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.baselineEntry = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.create).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'baselineEntryListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
