import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiDataUtils } from 'ng-jhipster';

import { UploadVersionAncSuffix } from './upload-version-anc-suffix.model';
import { UploadVersionAncSuffixPopupService } from './upload-version-anc-suffix-popup.service';
import { UploadVersionAncSuffixService } from './upload-version-anc-suffix.service';

@Component({
    selector: 'jhi-upload-version-anc-suffix-dialog',
    templateUrl: './upload-version-anc-suffix-dialog.component.html'
})
export class UploadVersionAncSuffixDialogComponent implements OnInit {

    uploadVersion: UploadVersionAncSuffix;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private dataUtils: JhiDataUtils,
        private uploadVersionService: UploadVersionAncSuffixService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
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
        if (this.uploadVersion.id !== undefined) {
            this.subscribeToSaveResponse(
                this.uploadVersionService.update(this.uploadVersion));
        } else {
            this.subscribeToSaveResponse(
                this.uploadVersionService.create(this.uploadVersion));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<UploadVersionAncSuffix>>) {
        result.subscribe((res: HttpResponse<UploadVersionAncSuffix>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: UploadVersionAncSuffix) {
        this.eventManager.broadcast({ name: 'uploadVersionListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }
}

@Component({
    selector: 'jhi-upload-version-anc-suffix-popup',
    template: ''
})
export class UploadVersionAncSuffixPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private uploadVersionPopupService: UploadVersionAncSuffixPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.uploadVersionPopupService
                    .open(UploadVersionAncSuffixDialogComponent as Component, params['id']);
            } else {
                this.uploadVersionPopupService
                    .open(UploadVersionAncSuffixDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
