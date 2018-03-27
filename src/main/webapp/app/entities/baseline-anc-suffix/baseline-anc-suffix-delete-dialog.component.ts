import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { BaselineAncSuffix } from './baseline-anc-suffix.model';
import { BaselineAncSuffixPopupService } from './baseline-anc-suffix-popup.service';
import { BaselineAncSuffixService } from './baseline-anc-suffix.service';

@Component({
    selector: 'jhi-baseline-anc-suffix-delete-dialog',
    templateUrl: './baseline-anc-suffix-delete-dialog.component.html'
})
export class BaselineAncSuffixDeleteDialogComponent {

    baseline: BaselineAncSuffix;

    constructor(
        private baselineService: BaselineAncSuffixService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.baselineService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'baselineListModification',
                content: 'Deleted an baseline'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-baseline-anc-suffix-delete-popup',
    template: ''
})
export class BaselineAncSuffixDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private baselinePopupService: BaselineAncSuffixPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.baselinePopupService
                .open(BaselineAncSuffixDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
