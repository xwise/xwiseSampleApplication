import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { FileEntryAncSuffix } from './file-entry-anc-suffix.model';
import { FileEntryAncSuffixPopupService } from './file-entry-anc-suffix-popup.service';
import { FileEntryAncSuffixService } from './file-entry-anc-suffix.service';

@Component({
    selector: 'jhi-file-entry-anc-suffix-delete-dialog',
    templateUrl: './file-entry-anc-suffix-delete-dialog.component.html'
})
export class FileEntryAncSuffixDeleteDialogComponent {

    fileEntry: FileEntryAncSuffix;

    constructor(
        private fileEntryService: FileEntryAncSuffixService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.fileEntryService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'fileEntryListModification',
                content: 'Deleted an fileEntry'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-file-entry-anc-suffix-delete-popup',
    template: ''
})
export class FileEntryAncSuffixDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private fileEntryPopupService: FileEntryAncSuffixPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.fileEntryPopupService
                .open(FileEntryAncSuffixDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
