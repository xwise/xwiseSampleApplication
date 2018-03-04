/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { XwiseSampleApplicationTestModule } from '../../../test.module';
import { BaselineAncSuffixDeleteDialogComponent } from '../../../../../../main/webapp/app/entities/baseline-anc-suffix/baseline-anc-suffix-delete-dialog.component';
import { BaselineAncSuffixService } from '../../../../../../main/webapp/app/entities/baseline-anc-suffix/baseline-anc-suffix.service';

describe('Component Tests', () => {

    describe('BaselineAncSuffix Management Delete Component', () => {
        let comp: BaselineAncSuffixDeleteDialogComponent;
        let fixture: ComponentFixture<BaselineAncSuffixDeleteDialogComponent>;
        let service: BaselineAncSuffixService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [XwiseSampleApplicationTestModule],
                declarations: [BaselineAncSuffixDeleteDialogComponent],
                providers: [
                    BaselineAncSuffixService
                ]
            })
            .overrideTemplate(BaselineAncSuffixDeleteDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(BaselineAncSuffixDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(BaselineAncSuffixService);
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
