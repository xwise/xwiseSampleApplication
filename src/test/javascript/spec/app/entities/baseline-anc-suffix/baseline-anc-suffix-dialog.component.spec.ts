/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { XwiseSampleApplicationTestModule } from '../../../test.module';
import { BaselineAncSuffixDialogComponent } from '../../../../../../main/webapp/app/entities/baseline-anc-suffix/baseline-anc-suffix-dialog.component';
import { BaselineAncSuffixService } from '../../../../../../main/webapp/app/entities/baseline-anc-suffix/baseline-anc-suffix.service';
import { BaselineAncSuffix } from '../../../../../../main/webapp/app/entities/baseline-anc-suffix/baseline-anc-suffix.model';
import { BaselineEntryAncSuffixService } from '../../../../../../main/webapp/app/entities/baseline-entry-anc-suffix';

describe('Component Tests', () => {

    describe('BaselineAncSuffix Management Dialog Component', () => {
        let comp: BaselineAncSuffixDialogComponent;
        let fixture: ComponentFixture<BaselineAncSuffixDialogComponent>;
        let service: BaselineAncSuffixService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [XwiseSampleApplicationTestModule],
                declarations: [BaselineAncSuffixDialogComponent],
                providers: [
                    BaselineEntryAncSuffixService,
                    BaselineAncSuffixService
                ]
            })
            .overrideTemplate(BaselineAncSuffixDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(BaselineAncSuffixDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(BaselineAncSuffixService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new BaselineAncSuffix(123);
                        spyOn(service, 'update').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.baseline = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.update).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'baselineListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );

            it('Should call create service on save for new entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new BaselineAncSuffix();
                        spyOn(service, 'create').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.baseline = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.create).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'baselineListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
