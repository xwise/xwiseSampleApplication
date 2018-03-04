/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { XwiseSampleApplicationTestModule } from '../../../test.module';
import { BaselineEntryVersionAncSuffixDeleteDialogComponent } from '../../../../../../main/webapp/app/entities/baseline-entry-version-anc-suffix/baseline-entry-version-anc-suffix-delete-dialog.component';
import { BaselineEntryVersionAncSuffixService } from '../../../../../../main/webapp/app/entities/baseline-entry-version-anc-suffix/baseline-entry-version-anc-suffix.service';

describe('Component Tests', () => {

    describe('BaselineEntryVersionAncSuffix Management Delete Component', () => {
        let comp: BaselineEntryVersionAncSuffixDeleteDialogComponent;
        let fixture: ComponentFixture<BaselineEntryVersionAncSuffixDeleteDialogComponent>;
        let service: BaselineEntryVersionAncSuffixService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [XwiseSampleApplicationTestModule],
                declarations: [BaselineEntryVersionAncSuffixDeleteDialogComponent],
                providers: [
                    BaselineEntryVersionAncSuffixService
                ]
            })
            .overrideTemplate(BaselineEntryVersionAncSuffixDeleteDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(BaselineEntryVersionAncSuffixDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(BaselineEntryVersionAncSuffixService);
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
