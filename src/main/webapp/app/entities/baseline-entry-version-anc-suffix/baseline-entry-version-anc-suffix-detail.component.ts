import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiDataUtils } from 'ng-jhipster';

import { BaselineEntryVersionAncSuffix } from './baseline-entry-version-anc-suffix.model';
import { BaselineEntryVersionAncSuffixService } from './baseline-entry-version-anc-suffix.service';

@Component({
    selector: 'jhi-baseline-entry-version-anc-suffix-detail',
    templateUrl: './baseline-entry-version-anc-suffix-detail.component.html'
})
export class BaselineEntryVersionAncSuffixDetailComponent implements OnInit, OnDestroy {

    baselineEntryVersion: BaselineEntryVersionAncSuffix;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private dataUtils: JhiDataUtils,
        private baselineEntryVersionService: BaselineEntryVersionAncSuffixService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInBaselineEntryVersions();
    }

    load(id) {
        this.baselineEntryVersionService.find(id)
            .subscribe((baselineEntryVersionResponse: HttpResponse<BaselineEntryVersionAncSuffix>) => {
                this.baselineEntryVersion = baselineEntryVersionResponse.body;
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

    registerChangeInBaselineEntryVersions() {
        this.eventSubscriber = this.eventManager.subscribe(
            'baselineEntryVersionListModification',
            (response) => this.load(this.baselineEntryVersion.id)
        );
    }
}
