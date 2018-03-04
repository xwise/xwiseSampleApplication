/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { XwiseSampleApplicationTestModule } from '../../../test.module';
import { BaselineEntryAncSuffixDeleteDialogComponent } from '../../../../../../main/webapp/app/entities/baseline-entry-anc-suffix/baseline-entry-anc-suffix-delete-dialog.component';
import { BaselineEntryAncSuffixService } from '../../../../../../main/webapp/app/entities/baseline-entry-anc-suffix/baseline-entry-anc-suffix.service';

describe('Component Tests', () => {

    describe('BaselineEntryAncSuffix Management Delete Component', () => {
        let comp: BaselineEntryAncSuffixDeleteDialogComponent;
        let fixture: ComponentFixture<BaselineEntryAncSuffixDeleteDialogComponent>;
        let service: BaselineEntryAncSuffixService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [XwiseSampleApplicationTestModule],
                declarations: [BaselineEntryAncSuffixDeleteDialogComponent],
                providers: [
                    BaselineEntryAncSuffixService
                ]
            })
            .overrideTemplate(BaselineEntryAncSuffixDeleteDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(BaselineEntryAncSuffixDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(BaselineEntryAncSuffixService);
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
