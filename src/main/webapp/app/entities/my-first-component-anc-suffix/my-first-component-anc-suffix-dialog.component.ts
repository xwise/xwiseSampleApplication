import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { MyFirstComponentAncSuffix } from './my-first-component-anc-suffix.model';
import { MyFirstComponentAncSuffixPopupService } from './my-first-component-anc-suffix-popup.service';
import { MyFirstComponentAncSuffixService } from './my-first-component-anc-suffix.service';

@Component({
    selector: 'jhi-my-first-component-anc-suffix-dialog',
    templateUrl: './my-first-component-anc-suffix-dialog.component.html'
})
export class MyFirstComponentAncSuffixDialogComponent implements OnInit {

    myFirstComponent: MyFirstComponentAncSuffix;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private myFirstComponentService: MyFirstComponentAncSuffixService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.myFirstComponent.id !== undefined) {
            this.subscribeToSaveResponse(
                this.myFirstComponentService.update(this.myFirstComponent));
        } else {
            this.subscribeToSaveResponse(
                this.myFirstComponentService.create(this.myFirstComponent));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<MyFirstComponentAncSuffix>>) {
        result.subscribe((res: HttpResponse<MyFirstComponentAncSuffix>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: MyFirstComponentAncSuffix) {
        this.eventManager.broadcast({ name: 'myFirstComponentListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }
}

@Component({
    selector: 'jhi-my-first-component-anc-suffix-popup',
    template: ''
})
export class MyFirstComponentAncSuffixPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private myFirstComponentPopupService: MyFirstComponentAncSuffixPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.myFirstComponentPopupService
                    .open(MyFirstComponentAncSuffixDialogComponent as Component, params['id']);
            } else {
                this.myFirstComponentPopupService
                    .open(MyFirstComponentAncSuffixDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
