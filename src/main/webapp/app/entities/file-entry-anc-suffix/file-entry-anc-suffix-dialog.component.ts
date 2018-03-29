import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { FileEntryAncSuffix } from './file-entry-anc-suffix.model';
import { FileEntryAncSuffixPopupService } from './file-entry-anc-suffix-popup.service';
import { FileEntryAncSuffixService } from './file-entry-anc-suffix.service';
import { BaselineAncSuffix, BaselineAncSuffixService } from '../baseline-anc-suffix';

@Component({
    selector: 'jhi-file-entry-anc-suffix-dialog',
    templateUrl: './file-entry-anc-suffix-dialog.component.html'
})
export class FileEntryAncSuffixDialogComponent implements OnInit {

    fileEntry: FileEntryAncSuffix;
    isSaving: boolean;

    baselines: BaselineAncSuffix[];

    constructor(
        public activeModal: NgbActiveModal,
        private dataUtils: JhiDataUtils,
        private jhiAlertService: JhiAlertService,
        private fileEntryService: FileEntryAncSuffixService,
        private baselineService: BaselineAncSuffixService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
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
        if (this.fileEntry.id !== undefined) {
            this.subscribeToSaveResponse(
                this.fileEntryService.update(this.fileEntry));
        } else {
            this.subscribeToSaveResponse(
                this.fileEntryService.create(this.fileEntry));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<FileEntryAncSuffix>>) {
        result.subscribe((res: HttpResponse<FileEntryAncSuffix>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: FileEntryAncSuffix) {
        this.eventManager.broadcast({ name: 'fileEntryListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
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
    selector: 'jhi-file-entry-anc-suffix-popup',
    template: ''
})
export class FileEntryAncSuffixPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private fileEntryPopupService: FileEntryAncSuffixPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.fileEntryPopupService
                    .open(FileEntryAncSuffixDialogComponent as Component, params['id']);
            } else {
                this.fileEntryPopupService
                    .open(FileEntryAncSuffixDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
