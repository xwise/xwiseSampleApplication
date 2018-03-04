import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { BaselineEntryVersionAncSuffix } from './baseline-entry-version-anc-suffix.model';
import { BaselineEntryVersionAncSuffixPopupService } from './baseline-entry-version-anc-suffix-popup.service';
import { BaselineEntryVersionAncSuffixService } from './baseline-entry-version-anc-suffix.service';
import { BaselineEntryAncSuffix, BaselineEntryAncSuffixService } from '../baseline-entry-anc-suffix';

@Component({
    selector: 'jhi-baseline-entry-version-anc-suffix-dialog',
    templateUrl: './baseline-entry-version-anc-suffix-dialog.component.html'
})
export class BaselineEntryVersionAncSuffixDialogComponent implements OnInit {

    baselineEntryVersion: BaselineEntryVersionAncSuffix;
    isSaving: boolean;

    baselineentries: BaselineEntryAncSuffix[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private baselineEntryVersionService: BaselineEntryVersionAncSuffixService,
        private baselineEntryService: BaselineEntryAncSuffixService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.baselineEntryService.query()
            .subscribe((res: HttpResponse<BaselineEntryAncSuffix[]>) => { this.baselineentries = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.baselineEntryVersion.id !== undefined) {
            this.subscribeToSaveResponse(
                this.baselineEntryVersionService.update(this.baselineEntryVersion));
        } else {
            this.subscribeToSaveResponse(
                this.baselineEntryVersionService.create(this.baselineEntryVersion));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<BaselineEntryVersionAncSuffix>>) {
        result.subscribe((res: HttpResponse<BaselineEntryVersionAncSuffix>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: BaselineEntryVersionAncSuffix) {
        this.eventManager.broadcast({ name: 'baselineEntryVersionListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackBaselineEntryById(index: number, item: BaselineEntryAncSuffix) {
        return item.id;
    }

    getSelected(selectedVals: Array<any>, option: any) {
        if (selectedVals) {
            for (let i = 0; i < selectedVals.length; i++) {
                if (option.id === selectedVals[i].id) {
                    return selectedVals[i];
                }
            }
        }
        return option;
    }
}

@Component({
    selector: 'jhi-baseline-entry-version-anc-suffix-popup',
    template: ''
})
export class BaselineEntryVersionAncSuffixPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private baselineEntryVersionPopupService: BaselineEntryVersionAncSuffixPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.baselineEntryVersionPopupService
                    .open(BaselineEntryVersionAncSuffixDialogComponent as Component, params['id']);
            } else {
                this.baselineEntryVersionPopupService
                    .open(BaselineEntryVersionAncSuffixDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
