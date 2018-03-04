import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { BaselineAncSuffix } from './baseline-anc-suffix.model';
import { BaselineAncSuffixPopupService } from './baseline-anc-suffix-popup.service';
import { BaselineAncSuffixService } from './baseline-anc-suffix.service';
import { BaselineEntryAncSuffix, BaselineEntryAncSuffixService } from '../baseline-entry-anc-suffix';

@Component({
    selector: 'jhi-baseline-anc-suffix-dialog',
    templateUrl: './baseline-anc-suffix-dialog.component.html'
})
export class BaselineAncSuffixDialogComponent implements OnInit {

    baseline: BaselineAncSuffix;
    isSaving: boolean;

    baselineentries: BaselineEntryAncSuffix[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private baselineService: BaselineAncSuffixService,
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
        if (this.baseline.id !== undefined) {
            this.subscribeToSaveResponse(
                this.baselineService.update(this.baseline));
        } else {
            this.subscribeToSaveResponse(
                this.baselineService.create(this.baseline));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<BaselineAncSuffix>>) {
        result.subscribe((res: HttpResponse<BaselineAncSuffix>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: BaselineAncSuffix) {
        this.eventManager.broadcast({ name: 'baselineListModification', content: 'OK'});
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
    selector: 'jhi-baseline-anc-suffix-popup',
    template: ''
})
export class BaselineAncSuffixPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private baselinePopupService: BaselineAncSuffixPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.baselinePopupService
                    .open(BaselineAncSuffixDialogComponent as Component, params['id']);
            } else {
                this.baselinePopupService
                    .open(BaselineAncSuffixDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
