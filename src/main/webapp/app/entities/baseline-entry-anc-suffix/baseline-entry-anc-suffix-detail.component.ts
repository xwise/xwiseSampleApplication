import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiDataUtils } from 'ng-jhipster';

import { BaselineEntryAncSuffix } from './baseline-entry-anc-suffix.model';
import { BaselineEntryAncSuffixService } from './baseline-entry-anc-suffix.service';

@Component({
    selector: 'jhi-baseline-entry-anc-suffix-detail',
    templateUrl: './baseline-entry-anc-suffix-detail.component.html'
})
export class BaselineEntryAncSuffixDetailComponent implements OnInit, OnDestroy {

    baselineEntry: BaselineEntryAncSuffix;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private dataUtils: JhiDataUtils,
        private baselineEntryService: BaselineEntryAncSuffixService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInBaselineEntries();
    }

    load(id) {
        this.baselineEntryService.find(id)
            .subscribe((baselineEntryResponse: HttpResponse<BaselineEntryAncSuffix>) => {
                this.baselineEntry = baselineEntryResponse.body;
            });
    }
    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInBaselineEntries() {
        this.eventSubscriber = this.eventManager.subscribe(
            'baselineEntryListModification',
            (response) => this.load(this.baselineEntry.id)
        );
    }
}
