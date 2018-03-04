import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { FileEntryAncSuffix } from './file-entry-anc-suffix.model';
import { FileEntryAncSuffixPopupService } from './file-entry-anc-suffix-popup.service';
import { FileEntryAncSuffixService } from './file-entry-anc-suffix.service';
import { FileGroupAncSuffix, FileGroupAncSuffixService } from '../file-group-anc-suffix';

@Component({
    selector: 'jhi-file-entry-anc-suffix-dialog',
    templateUrl: './file-entry-anc-suffix-dialog.component.html'
})
export class FileEntryAncSuffixDialogComponent implements OnInit {

    fileEntry: FileEntryAncSuffix;
    isSaving: boolean;

    filegroups: FileGroupAncSuffix[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private fileEntryService: FileEntryAncSuffixService,
        private fileGroupService: FileGroupAncSuffixService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.fileGroupService.query()
            .subscribe((res: HttpResponse<FileGroupAncSuffix[]>) => { this.filegroups = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
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

    trackFileGroupById(index: number, item: FileGroupAncSuffix) {
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
