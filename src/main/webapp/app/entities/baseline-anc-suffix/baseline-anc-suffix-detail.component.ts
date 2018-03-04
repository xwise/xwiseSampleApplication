import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { BaselineAncSuffix } from './baseline-anc-suffix.model';
import { BaselineAncSuffixService } from './baseline-anc-suffix.service';

@Component({
    selector: 'jhi-baseline-anc-suffix-detail',
    templateUrl: './baseline-anc-suffix-detail.component.html'
})
export class BaselineAncSuffixDetailComponent implements OnInit, OnDestroy {

    baseline: BaselineAncSuffix;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private baselineService: BaselineAncSuffixService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInBaselines();
    }

    load(id) {
        this.baselineService.find(id)
            .subscribe((baselineResponse: HttpResponse<BaselineAncSuffix>) => {
                this.baseline = baselineResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInBaselines() {
        this.eventSubscriber = this.eventManager.subscribe(
            'baselineListModification',
            (response) => this.load(this.baseline.id)
        );
    }
}
