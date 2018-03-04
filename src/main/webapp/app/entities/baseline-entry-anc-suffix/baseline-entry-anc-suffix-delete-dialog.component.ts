import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { BaselineEntryAncSuffix } from './baseline-entry-anc-suffix.model';
import { BaselineEntryAncSuffixPopupService } from './baseline-entry-anc-suffix-popup.service';
import { BaselineEntryAncSuffixService } from './baseline-entry-anc-suffix.service';

@Component({
    selector: 'jhi-baseline-entry-anc-suffix-delete-dialog',
    templateUrl: './baseline-entry-anc-suffix-delete-dialog.component.html'
})
export class BaselineEntryAncSuffixDeleteDialogComponent {

    baselineEntry: BaselineEntryAncSuffix;

    constructor(
        private baselineEntryService: BaselineEntryAncSuffixService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.baselineEntryService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'baselineEntryListModification',
                content: 'Deleted an baselineEntry'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-baseline-entry-anc-suffix-delete-popup',
    template: ''
})
export class BaselineEntryAncSuffixDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private baselineEntryPopupService: BaselineEntryAncSuffixPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.baselineEntryPopupService
                .open(BaselineEntryAncSuffixDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
