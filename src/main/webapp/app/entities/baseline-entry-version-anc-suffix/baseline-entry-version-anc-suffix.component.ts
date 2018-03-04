import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { BaselineEntryVersionAncSuffix } from './baseline-entry-version-anc-suffix.model';
import { BaselineEntryVersionAncSuffixService } from './baseline-entry-version-anc-suffix.service';
import { Principal } from '../../shared';

@Component({
    selector: 'jhi-baseline-entry-version-anc-suffix',
    templateUrl: './baseline-entry-version-anc-suffix.component.html'
})
export class BaselineEntryVersionAncSuffixComponent implements OnInit, OnDestroy {
baselineEntryVersions: BaselineEntryVersionAncSuffix[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private baselineEntryVersionService: BaselineEntryVersionAncSuffixService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.baselineEntryVersionService.query().subscribe(
            (res: HttpResponse<BaselineEntryVersionAncSuffix[]>) => {
                this.baselineEntryVersions = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInBaselineEntryVersions();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: BaselineEntryVersionAncSuffix) {
        return item.id;
    }
    registerChangeInBaselineEntryVersions() {
        this.eventSubscriber = this.eventManager.subscribe('baselineEntryVersionListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}
