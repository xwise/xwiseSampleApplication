import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { FileGroupAncSuffix } from './file-group-anc-suffix.model';
import { FileGroupAncSuffixPopupService } from './file-group-anc-suffix-popup.service';
import { FileGroupAncSuffixService } from './file-group-anc-suffix.service';
import { BaselineAncSuffix, BaselineAncSuffixService } from '../baseline-anc-suffix';
import { FileEntryAncSuffix, FileEntryAncSuffixService } from '../file-entry-anc-suffix';

@Component({
    selector: 'jhi-file-group-anc-suffix-dialog',
    templateUrl: './file-group-anc-suffix-dialog.component.html'
})
export class FileGroupAncSuffixDialogComponent implements OnInit {

    fileGroup: FileGroupAncSuffix;
    isSaving: boolean;

    baselines: BaselineAncSuffix[];

    fileentries: FileEntryAncSuffix[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private fileGroupService: FileGroupAncSuffixService,
        private baselineService: BaselineAncSuffixService,
        private fileEntryService: FileEntryAncSuffixService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.baselineService.query()
            .subscribe((res: HttpResponse<BaselineAncSuffix[]>) => { this.baselines = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
        this.fileEntryService.query()
            .subscribe((res: HttpResponse<FileEntryAncSuffix[]>) => { this.fileentries = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.fileGroup.id !== undefined) {
            this.subscribeToSaveResponse(
                this.fileGroupService.update(this.fileGroup));
        } else {
            this.subscribeToSaveResponse(
                this.fileGroupService.create(this.fileGroup));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<FileGroupAncSuffix>>) {
        result.subscribe((res: HttpResponse<FileGroupAncSuffix>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: FileGroupAncSuffix) {
        this.eventManager.broadcast({ name: 'fileGroupListModification', content: 'OK'});
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

    trackFileEntryById(index: number, item: FileEntryAncSuffix) {
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
    selector: 'jhi-file-group-anc-suffix-popup',
    template: ''
})
export class FileGroupAncSuffixPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private fileGroupPopupService: FileGroupAncSuffixPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.fileGroupPopupService
                    .open(FileGroupAncSuffixDialogComponent as Component, params['id']);
            } else {
                this.fileGroupPopupService
                    .open(FileGroupAncSuffixDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
