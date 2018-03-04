import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { BaselineEntryVersionAncSuffix } from './baseline-entry-version-anc-suffix.model';
import { BaselineEntryVersionAncSuffixPopupService } from './baseline-entry-version-anc-suffix-popup.service';
import { BaselineEntryVersionAncSuffixService } from './baseline-entry-version-anc-suffix.service';

@Component({
    selector: 'jhi-baseline-entry-version-anc-suffix-delete-dialog',
    templateUrl: './baseline-entry-version-anc-suffix-delete-dialog.component.html'
})
export class BaselineEntryVersionAncSuffixDeleteDialogComponent {

    baselineEntryVersion: BaselineEntryVersionAncSuffix;

    constructor(
        private baselineEntryVersionService: BaselineEntryVersionAncSuffixService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.baselineEntryVersionService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'baselineEntryVersionListModification',
                content: 'Deleted an baselineEntryVersion'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-baseline-entry-version-anc-suffix-delete-popup',
    template: ''
})
export class BaselineEntryVersionAncSuffixDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private baselineEntryVersionPopupService: BaselineEntryVersionAncSuffixPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.baselineEntryVersionPopupService
                .open(BaselineEntryVersionAncSuffixDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
