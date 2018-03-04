import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { FileGroupAncSuffix } from './file-group-anc-suffix.model';
import { FileGroupAncSuffixPopupService } from './file-group-anc-suffix-popup.service';
import { FileGroupAncSuffixService } from './file-group-anc-suffix.service';

@Component({
    selector: 'jhi-file-group-anc-suffix-delete-dialog',
    templateUrl: './file-group-anc-suffix-delete-dialog.component.html'
})
export class FileGroupAncSuffixDeleteDialogComponent {

    fileGroup: FileGroupAncSuffix;

    constructor(
        private fileGroupService: FileGroupAncSuffixService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.fileGroupService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'fileGroupListModification',
                content: 'Deleted an fileGroup'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-file-group-anc-suffix-delete-popup',
    template: ''
})
export class FileGroupAncSuffixDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private fileGroupPopupService: FileGroupAncSuffixPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.fileGroupPopupService
                .open(FileGroupAncSuffixDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
