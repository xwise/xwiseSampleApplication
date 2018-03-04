import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { UploadVersionAncSuffix } from './upload-version-anc-suffix.model';
import { UploadVersionAncSuffixPopupService } from './upload-version-anc-suffix-popup.service';
import { UploadVersionAncSuffixService } from './upload-version-anc-suffix.service';

@Component({
    selector: 'jhi-upload-version-anc-suffix-delete-dialog',
    templateUrl: './upload-version-anc-suffix-delete-dialog.component.html'
})
export class UploadVersionAncSuffixDeleteDialogComponent {

    uploadVersion: UploadVersionAncSuffix;

    constructor(
        private uploadVersionService: UploadVersionAncSuffixService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.uploadVersionService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'uploadVersionListModification',
                content: 'Deleted an uploadVersion'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-upload-version-anc-suffix-delete-popup',
    template: ''
})
export class UploadVersionAncSuffixDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private uploadVersionPopupService: UploadVersionAncSuffixPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.uploadVersionPopupService
                .open(UploadVersionAncSuffixDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
