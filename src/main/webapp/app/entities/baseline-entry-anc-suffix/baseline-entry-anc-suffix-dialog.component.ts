import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { BaselineEntryAncSuffix } from './baseline-entry-anc-suffix.model';
import { BaselineEntryAncSuffixPopupService } from './baseline-entry-anc-suffix-popup.service';
import { BaselineEntryAncSuffixService } from './baseline-entry-anc-suffix.service';
import { BaselineEntryVersionAncSuffix, BaselineEntryVersionAncSuffixService } from '../baseline-entry-version-anc-suffix';
import { BaselineAncSuffix, BaselineAncSuffixService } from '../baseline-anc-suffix';

@Component({
    selector: 'jhi-baseline-entry-anc-suffix-dialog',
    templateUrl: './baseline-entry-anc-suffix-dialog.component.html'
})
export class BaselineEntryAncSuffixDialogComponent implements OnInit {

    baselineEntry: BaselineEntryAncSuffix;
    isSaving: boolean;

    baselineentryversions: BaselineEntryVersionAncSuffix[];

    baselines: BaselineAncSuffix[];

    constructor(
        public activeModal: NgbActiveModal,
        private dataUtils: JhiDataUtils,
        private jhiAlertService: JhiAlertService,
        private baselineEntryService: BaselineEntryAncSuffixService,
        private baselineEntryVersionService: BaselineEntryVersionAncSuffixService,
        private baselineService: BaselineAncSuffixService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.baselineEntryVersionService.query()
            .subscribe((res: HttpResponse<BaselineEntryVersionAncSuffix[]>) => { this.baselineentryversions = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
        this.baselineService.query()
            .subscribe((res: HttpResponse<BaselineAncSuffix[]>) => { this.baselines = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    setFileData(event, entity, field, isImage) {
        this.dataUtils.setFileData(event, entity, field, isImage);
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.baselineEntry.id !== undefined) {
            this.subscribeToSaveResponse(
                this.baselineEntryService.update(this.baselineEntry));
        } else {
            this.subscribeToSaveResponse(
                this.baselineEntryService.create(this.baselineEntry));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<BaselineEntryAncSuffix>>) {
        result.subscribe((res: HttpResponse<BaselineEntryAncSuffix>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: BaselineEntryAncSuffix) {
        this.eventManager.broadcast({ name: 'baselineEntryListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackBaselineEntryVersionById(index: number, item: BaselineEntryVersionAncSuffix) {
        return item.id;
    }

    trackBaselineById(index: number, item: BaselineAncSuffix) {
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
    selector: 'jhi-baseline-entry-anc-suffix-popup',
    template: ''
})
export class BaselineEntryAncSuffixPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private baselineEntryPopupService: BaselineEntryAncSuffixPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.baselineEntryPopupService
                    .open(BaselineEntryAncSuffixDialogComponent as Component, params['id']);
            } else {
                this.baselineEntryPopupService
                    .open(BaselineEntryAncSuffixDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
