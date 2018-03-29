import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { MyFirstComponentAncSuffix } from './my-first-component-anc-suffix.model';
import { MyFirstComponentAncSuffixPopupService } from './my-first-component-anc-suffix-popup.service';
import { MyFirstComponentAncSuffixService } from './my-first-component-anc-suffix.service';

@Component({
    selector: 'jhi-my-first-component-anc-suffix-delete-dialog',
    templateUrl: './my-first-component-anc-suffix-delete-dialog.component.html'
})
export class MyFirstComponentAncSuffixDeleteDialogComponent {

    myFirstComponent: MyFirstComponentAncSuffix;

    constructor(
        private myFirstComponentService: MyFirstComponentAncSuffixService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.myFirstComponentService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'myFirstComponentListModification',
                content: 'Deleted an myFirstComponent'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-my-first-component-anc-suffix-delete-popup',
    template: ''
})
export class MyFirstComponentAncSuffixDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private myFirstComponentPopupService: MyFirstComponentAncSuffixPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.myFirstComponentPopupService
                .open(MyFirstComponentAncSuffixDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
