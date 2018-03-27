import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { FileEntryAncSuffix } from './file-entry-anc-suffix.model';
import { FileEntryAncSuffixPopupService } from './file-entry-anc-suffix-popup.service';
import { FileEntryAncSuffixService } from './file-entry-anc-suffix.service';
import { UploadVersionAncSuffix, UploadVersionAncSuffixService } from '../upload-version-anc-suffix';
import { BaselineAncSuffix, BaselineAncSuffixService } from '../baseline-anc-suffix';

@Component({
    selector: 'jhi-file-entry-anc-suffix-dialog',
    templateUrl: './file-entry-anc-suffix-dialog.component.html'
})
export class FileEntryAncSuffixDialogComponent implements OnInit {

    fileEntry: FileEntryAncSuffix;
    isSaving: boolean;

    uploadversions: UploadVersionAncSuffix[];

    baselines: BaselineAncSuffix[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private fileEntryService: FileEntryAncSuffixService,
        private uploadVersionService: UploadVersionAncSuffixService,
        private baselineService: BaselineAncSuffixService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.uploadVersionService.query()
            .subscribe((res: HttpResponse<UploadVersionAncSuffix[]>) => { this.uploadversions = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
        this.baselineService.query()
            .subscribe((res: HttpResponse<BaselineAncSuffix[]>) => { this.baselines = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
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

    trackUploadVersionById(index: number, item: UploadVersionAncSuffix) {
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
